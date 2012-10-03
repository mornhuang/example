package net.sf.hibernate.tool.hbm2java;

import net.sf.hibernate.type.Type;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Build queries for use in finder generation.
 * @author Matt Hall (matt2k(at)users.sf.net)
 */
public class QueryBuilder {

   public static final String CRITERIA_EQUALS = "=";
   public static final String CRITERIA_GREATER_THAN = ">";
   public static final String CRITERIA_LESS_THAN = "<";
   public static final String CRITERIA_LIKE = "LIKE";

   // List of strings that will later be put together to form the query
   private ArrayList objects = new ArrayList();
   private ArrayList joinConditions = new ArrayList();
   private ArrayList criteria = new ArrayList();
   private ArrayList params = new ArrayList();
   private ArrayList criteriaParamTypes = new ArrayList();

   private ClassMapping localClass = null;
   private ClassMapping foreignClass = null;
   private String joinFieldName = "";

   public QueryBuilder() {
   }

   public void setLocalClass(ClassMapping localClass) {
      this.localClass = localClass;
   }

   public void setForeignClass(ClassName foreignClass, Map classMappings, String joinFieldName) {
      ClassMapping classMapToAdd = (ClassMapping) classMappings.get(foreignClass.getFullyQualifiedName());
      this.foreignClass = classMapToAdd;
      this.joinFieldName = joinFieldName;
   }

   public void addCritera(ClassMapping criteriaClass, FieldProperty field, String condition) {
      String newCritera = criteriaClass.getName().toLowerCase() + "." + field.getFieldName() + condition + "?";
      params.add(FinderRenderer.getFieldAsObject(false, field));
      criteria.add(newCritera);
      criteriaParamTypes.add(FinderRenderer.getFieldAsHibernateType(false, field));
   }

   /**
    * @return The query in string form
    */
   public String getQuery() {
      StringBuffer sb = new StringBuffer("select ");

      // Foreign class is what we're selecting from
      sb.append(foreignClass.getName().toLowerCase()+" from ");
      sb.append(foreignClass.getName().toLowerCase()+" in class ");
      sb.append(foreignClass.getName()+", ");

      // Now the collections stuff based on the local class
      sb.append(localClass.getName().toLowerCase()+ " in ");
      sb.append(foreignClass.getName().toLowerCase()+".");
      sb.append(joinFieldName+".elements where ");

      // The join back to the local class
      sb.append(localClass.getName().toLowerCase()+ "=? and ");

/*
      if (objects.size() > 0) {
         sb.append(" from ");
         for (int i = 0; i < objects.size(); i++) {
            ClassMapping classMapping = (ClassMapping) objects.get(i);
            sb.append(classMapping.getCanonicalName() + " " + classMapping.getName().toLowerCase());
            sb.append(" ");
         }
      }
*/

      if (criteria.size() > 0) {
         for (int i = 0; i < criteria.size(); i++) {
            String thisCriteria = (String) criteria.get(i);
            sb.append(" " + thisCriteria + " ");
            if (i < criteria.size()-1) {
               sb.append(" and ");
            }
         }

      }

      return sb.toString();
   }

   public List getParamTypes() {
      return criteriaParamTypes;
   }

   public String getParamTypesAsString() {
      String types = "new Type[] {";
      // Always need the local class as an association type
      types += "Hibernate.association("+localClass.getName()+".class), ";
      for (int i = 0; i < criteriaParamTypes.size(); i++) {
         String s = (String) criteriaParamTypes.get(i);
         types += s;
         if (i != criteriaParamTypes.size() - 1) {
            types += ",";
         }
      }
      return types + "}";
   }

   public List getParams() {
      return params;
   }

   public String getParamsAsString() {
      String types = "new Object[] {";
      // Always joining via the local class
      types += localClass.getName().toLowerCase()+", ";
      for (int i = 0; i < params.size(); i++) {
         String s = (String) params.get(i);
         types += s;
         if (i != params.size() - 1) {
            types += ",";
         }
      }
      return types + "}";
   }

}

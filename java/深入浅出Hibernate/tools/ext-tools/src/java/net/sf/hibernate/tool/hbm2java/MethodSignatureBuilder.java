package net.sf.hibernate.tool.hbm2java;

import java.util.ArrayList;

/**
 * Build method signatures given lots of parameters
 * Date: Apr 15, 2003
 * Time: 7:30:09 PM
 * @author Matt Hall (matt2k(at)users.sf.net)
 */
public class MethodSignatureBuilder {

   private String name = "";
   private String returnType = "";
   private String accessModifier = "";
   private ArrayList paramList = new ArrayList();
   private ArrayList throwsList = new ArrayList();

   public MethodSignatureBuilder(String methodName, String returnType, String accessModifier) {
      name = methodName;
      this.returnType = returnType;
      this.accessModifier = accessModifier;
   }

   public String buildMethodSignature() {
      StringBuffer sb = new StringBuffer(accessModifier+" "+returnType+" "+name+"(");

      for (int i = 0; i < paramList.size(); i++) {
         String param = (String) paramList.get(i);
         sb.append(param);

         if (i < paramList.size()-1) {
            sb.append(", ");
         }
      }
      sb.append(") ");

      for (int i = 0; i < throwsList.size(); i++) {
         if (i == 0) {
            sb.append(" throws ");
         }
         String thr = (String) throwsList.get(i);
         sb.append(thr);

         if (i < throwsList.size()-1) {
            sb.append(", ");
         }
      }

      sb.append(" {");
      return sb.toString();
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getReturnType() {
      return returnType;
   }

   public void setReturnType(String returnType) {
      this.returnType = returnType;
   }

   public String getAccessModifier() {
      return accessModifier;
   }

   public void setAccessModifier(String accessModifier) {
      this.accessModifier = accessModifier;
   }

   public ArrayList getParamList() {
      return paramList;
   }

   public void setParamList(ArrayList paramList) {
      this.paramList = paramList;
   }

   public void addParam(String param) {
      this.paramList.add(param);
   }

   public void addThrows(String throwsString) {
      this.throwsList.add(throwsString);
   }

}

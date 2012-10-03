/*
 * $Id: TestValidator.java,v 1.2 2006/10/02 02:59:39 chenth Exp $
 * $Rev: 155434 $
 * $Date: 2006/10/02 02:59:39 $
 *
 * ====================================================================
 * Copyright 2001-2005 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.hxex.validator.example;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericTypeValidator;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.util.ValidatorUtils;
                                                          
/**                                                       
 * Contains validation methods for different unit tests.
 */                                                       
public class TestValidator {
          
   /**
    * Checks if the field is required.
    *
    * @return boolean If the field isn't <code>null</code> and
    * has a length greater than zero, <code>true</code> is returned.  
    * Otherwise <code>false</code>.
    */
   public static boolean validateRequired(Object bean, Field field) {
      String value = ValidatorUtils.getValueAsString(bean, field.getProperty());

      return !GenericValidator.isBlankOrNull(value);
   }

   /**
    * Checks if the field can be successfully converted to a <code>int</code>.
    *
    * @param value The value validation is being performed on.
    * @return boolean If the field can be successfully converted 
    * to a <code>int</code> <code>true</code> is returned.  
    * Otherwise <code>false</code>.
    */
   public static Integer validateInt(Object bean, Field field) {
      String value = ValidatorUtils.getValueAsString(bean, field.getProperty());

      return GenericTypeValidator.formatInt(value);
   }
}                                                         

/*
 * Created on 2004/7/9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.taifook.mtss.web.common.validation;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.commons.validator.Validator;
import org.apache.struts.validator.FieldChecks;
import org.apache.struts.validator.Resources;

/**
 * @author myli
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StrutsValidationUtil {

	/**
	* Validates that two fields match.
	* @param bean
	* @param va
	* @param field
	* @param errors
	* @param request
	* @return boolean
	*/
	public static boolean validateTwoFields(
		Object bean,
		ValidatorAction va,
		Field field,
		ActionMessages errors,
		Validator validator,
		HttpServletRequest request) {
		String value =
			ValidatorUtils.getValueAsString(bean, field.getProperty());
		String sProperty2 = field.getVarValue("secondProperty");
		String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);

		String sCheckUnequal = field.getVarValue("checkUnequal");
		boolean checkUnequal =
			((sCheckUnequal != null)
				&& (sCheckUnequal.equalsIgnoreCase("true")));

		if (!GenericValidator.isBlankOrNull(value)) {
			try {
				if (!checkUnequal) {
					if (!value.equals(value2)) {
						errors.add(
							field.getKey(),
							Resources.getActionMessage(validator, request, va, field));

						return false;
					}
				} else {
					if (value.equals(value2)) {
						errors.add(
							field.getKey(),
							Resources.getActionMessage(validator, request, va, field));

						return false;
					}
				}
			} catch (Exception e) {
				errors.add(
					field.getKey(),
					Resources.getActionMessage(validator, request, va, field));

				return false;
			}
		}

		return true;
	}

	/**
	* Validates that two fields match.
	* @param bean
	* @param va
	* @param field
	* @param errors
	* @param request
	* @return boolean
	*/
	public static boolean validateEmailOrFax(
			Object bean,
			ValidatorAction va,
			Field field,
			ActionMessages errors,
			Validator validator,
			HttpServletRequest request) {
			String value =
				ValidatorUtils.getValueAsString(bean, field.getProperty());
			String sProperty2 = field.getVarValue("secondProperty");
			String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);

			String sFaxMask = field.getVarValue("faxMask");

			if ((value2 != null)
					&& (value2.equalsIgnoreCase("email"))) {
				// Checking for email
				
				if (!GenericValidator.isBlankOrNull(value)) {
					try {
							if (!GenericValidator.isEmail(value)) {
								errors.add(
									field.getKey(),
									Resources.getActionMessage(validator, request, va, field));

								return false;
							}
					} catch (Exception e) {
						errors.add(
							field.getKey(),
							Resources.getActionMessage(validator, request, va, field));

						return false;
					}
				}

				
				
			}
			else {
				// Checking for fax

				if (!GenericValidator.isBlankOrNull(value)) {
					try {
							if (!GenericValidator.matchRegexp(value, sFaxMask)) {
								errors.add(
									field.getKey(),
									Resources.getActionMessage(validator, request, va, field));

								return false;
							}
					} catch (Exception e) {
						errors.add(
							field.getKey(),
							Resources.getActionMessage(validator, request, va, field));

						return false;
					}
				}

			}

			return true;
	}
	
	public static boolean validateMaskIf(Object bean, ValidatorAction va, Field field, ActionMessages errors, Validator validator, HttpServletRequest request) {
		if(isValidate(bean, va, field, errors, validator, request)) {
			return FieldChecks.validateMask(bean, va, field, errors, validator, request);
		}
		return true;
	}
	
	public static boolean validateMinLengthIf(Object bean, ValidatorAction va, Field field, ActionMessages errors, Validator validator, HttpServletRequest request) {
		if(isValidate(bean, va, field, errors, validator, request)) {
			return FieldChecks.validateMinLength(bean, va, field, errors, validator, request);
		}
		return true;
	}
	
	public static boolean validateMaxLengthIf(Object bean, ValidatorAction va, Field field, ActionMessages errors, Validator validator, HttpServletRequest request) {
		if(isValidate(bean, va, field, errors, validator, request)) {
			return FieldChecks.validateMaxLength(bean, va, field, errors, validator, request);
		}
		return true;
	}
	
	public static boolean validateWMTRequiredIf(Object bean, ValidatorAction va, Field field, ActionMessages errors, Validator validator, HttpServletRequest request) {
		if(isValidate(bean, va, field, errors, validator, request)) {
			return FieldChecks.validateRequired(bean, va, field, errors, validator, request);
		}
		return true;
	}
	
	public static boolean validatePriceIf(Object bean, ValidatorAction va, Field field, ActionMessages errors, Validator validator, HttpServletRequest request) {
		if(isValidate(bean, va, field, errors, validator, request)) {
			return validatePrice(bean, va, field, errors, validator, request);
		}
		return true;
	}
	
	public static boolean validatePrice(Object bean, ValidatorAction va, Field field, ActionMessages errors, Validator validator, HttpServletRequest request) {
		String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		value = removeUselessZero(value);
		String regex = field.getVarValue("price");
        if(!GenericValidator.isBlankOrNull(value)) {
        	try {
				float floatValue = Float.parseFloat(value);
				if (floatValue > 0) {
					Pattern pattern = Pattern.compile(regex);
					if (!pattern.matcher(value).find()) {
						errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
		                return false;
					}
				} else {
					errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
	                return false;
				}
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
                return false;
			}
        }
        return true;
	}
	
	private static String removeUselessZero(String origStr){
		return StringUtils.stripEnd(origStr, "0");
	}
	
	private static boolean isValidate(Object bean, ValidatorAction va, Field field, ActionMessages errors, Validator validator, HttpServletRequest request) {
        Object form = validator.getParameterValue("java.lang.Object");
        boolean required = false;
        int i = 0;
        String fieldJoin = "AND";
        if(!GenericValidator.isBlankOrNull(field.getVarValue("fieldJoin")))
            fieldJoin = field.getVarValue("fieldJoin");
        if(fieldJoin.equalsIgnoreCase("AND"))
            required = true;
        for(; !GenericValidator.isBlankOrNull(field.getVarValue("field[" + i + "]")); i++)
        {
            String dependProp = field.getVarValue("field[" + i + "]");
            String dependTest = field.getVarValue("fieldTest[" + i + "]");
            String dependTestValue = field.getVarValue("fieldValue[" + i + "]");
            String dependIndexed = field.getVarValue("fieldIndexed[" + i + "]");
            if(dependIndexed == null)
                dependIndexed = "false";
            String dependVal = null;
            boolean thisRequired = false;
            if(field.isIndexed() && dependIndexed.equalsIgnoreCase("true"))
            {
                String key = field.getKey();
                if(key.indexOf("[") > -1 && key.indexOf("]") > -1)
                {
                    String ind = key.substring(0, key.indexOf(".") + 1);
                    dependProp = ind + dependProp;
                }
            }
            dependVal = ValidatorUtils.getValueAsString(form, dependProp);
            if(dependTest.equals("NULL"))
                if(dependVal != null && dependVal.length() > 0)
                    thisRequired = false;
                else
                    thisRequired = true;
            if(dependTest.equals("NOTNULL"))
                if(dependVal != null && dependVal.length() > 0)
                    thisRequired = true;
                else
                    thisRequired = false;
            if(dependTest.equals("EQUAL"))
                thisRequired = dependTestValue.equalsIgnoreCase(dependVal);
            if(dependTest.equals("NOTEQUAL"))
                thisRequired = !dependTestValue.equalsIgnoreCase(dependVal);
            
            if(fieldJoin.equalsIgnoreCase("AND"))
                required = required && thisRequired;
            else
                required = required || thisRequired;
        }

        return required;
	}
	
	
}

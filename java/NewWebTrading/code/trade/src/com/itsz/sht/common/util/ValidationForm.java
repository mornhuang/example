/**
 * <p>Title: 3G Portal</p>
 * <p>Description: 3G Portal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.sht.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidationForm {//return true ��ʾ��false��ʾ����
    public static boolean validateNull(String s){      
        if(s==null||"".equals(s.trim())){
            return false;
        }
        return true;
    }
    
    public static boolean validateInstrCode(String instrCode){
        Pattern p = Pattern.compile("^[0-9]{1,5}$"); //������ʽ
        Matcher m = p.matcher(instrCode); //�������ַ�
        boolean b=true;//�ַ�ƥ�� 
        b = m.matches();
        return b;      
    }
    
    public static boolean validateQuantity(String quantity){       
        Pattern p = Pattern.compile("^[0-9]*[1-9][0-9]*$"); //������ʽ
        Matcher m = p.matcher(quantity); //�������ַ�
        boolean b=true;//�ַ�ƥ�� 
        b = m.matches();
        return b;     
    }
    
    public static boolean validatePrice(String price){
        Pattern p = Pattern.compile("^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$"); //������ʽ
        Matcher m = p.matcher(price); //�������ַ�
        boolean b=true;//�ַ�ƥ�� 
        b = m.matches();
        return b;
    }
    
    public static boolean validatePassword(String password){
        Pattern p = Pattern.compile("^[A-Za-z0-9]{6,8}$"); //������ʽ
        Matcher m = p.matcher(password); //�������ַ�
        boolean b=true;//�ַ�ƥ�� 
        b = m.matches();
        return b;
    }    
    
    public static void main(String[] args) {
        if(!validatePrice(null)){
            System.out.println("error");
        }
        if(!validateQuantity("1")){
            System.out.println("error q");
        }
    }

}

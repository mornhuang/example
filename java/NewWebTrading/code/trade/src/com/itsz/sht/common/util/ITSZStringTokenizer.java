package com.itsz.sht.common.util;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;
/**
 * 
 * 
 * Title:ITSZStringTokenizer
 * Description:�ַ�����
 * Copyright:Copyright (c) 2003 ITSZ
 * Company:ITSZ
 * @author jim
 * 
 *
 */
public class ITSZStringTokenizer {
    public ITSZStringTokenizer() {
    }

    private Vector v=null;
    StringTokenizer ST ;
    /**
     *
     * @param string=0001;0002;0003;...
     * @param token    ;
     * @return Vector
     * format the string by token
     * eg. [0001,0002,0003]
     */
    public Vector format(String str,char token){
        v = new Vector();
        try {
            ST = new StringTokenizer(str, String.valueOf(token));
            while (ST.hasMoreTokens()) {
                // System.out.println(ST.nextToken());
                v.add(ST.nextToken());

            }
        }
        catch (Exception e) {
            System.out.println("[FormatException]="+e.toString()+"   [FormatObject]="+str+"   [return null]");
            return null;

        }
       return v;
}
   public Vector format(String str,String token){
       v=new Vector();
       ST=new StringTokenizer(str,token);
       while(ST.hasMoreTokens()){
         // System.out.println(ST.nextToken());
        v.add(ST.nextToken());

     }
      return v;
}

  private ArrayList list;
  /**
   *
   * @param str=123;456;789;
   * @param token=;
   * @return ����ArrayList=[123,456,789]
   */
  public ArrayList formatStringToArrayList(String str, char token) {
      list = new ArrayList();
      ST = new StringTokenizer(str, String.valueOf(token));
      while (ST.hasMoreTokens()) {
          // System.out.println(ST.nextToken());
          list.add(ST.nextToken());
      }
      if (list.size() != 0) {
          return list;
      }
      else {
          return null;
      }
  }

   /**
    *
    * @param string=0001;0002;0003;...
    * @param token=    ;
    * @param addlast=   .HK
    * @return Vector  [0001.HK,0002.HK,0003.HK]
    * eg.
    */
   public Vector format(String str,char token,String addlast){
       v = new Vector();
       ST = new StringTokenizer(str, String.valueOf(token));
       while (ST.hasMoreTokens()) {
           // System.out.println(ST.nextToken());
           v.add(ST.nextToken() + addlast);
       }
       return v;
}
   public static void main(String[] args) {
       ITSZStringTokenizer test = new ITSZStringTokenizer();
       String str="yu|||234|";
       System.out.print(test.format(str,'|').size());

   }


}

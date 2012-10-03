//$Id: Counter.java,v 1.1 2011/03/01 02:15:43 xlliu Exp $

package com.itsz.util;

import java.io.Serializable;

/**
 * A counter
 * <p>Title: eService III</p>
 * <p>Description: eService RTQ,Price Alert</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: TaiFook</p>
 * @author yhliu
 * @version 1.0
 */

public class Counter implements Serializable{

  // Fields
  private int value;

  public Counter(){
      this(0);
  }
  
  // Constructors
  public Counter(int value) {
      this.value = value;
  }
  
  int setValue(int value){
      this.value = value;
      return value;
  }

  // Methods
  public synchronized int increment() {
      return ++value;
  }

  public synchronized int decrement() {
      return --value;
  }

  public int getValue() {
      return value;
  }

  public String toString(){
      return String.valueOf(value);
  }
}

/******************************************************************************
		     (c) COPYRIGHT 2000-2008 by Harbour Networks System, Ltd.
                              All rights reserved.

     This software is confidential and proprietary to Harbour Networks
     System, Ltd. No part of this software may be reproduced, stored,
     transmitted, disclosed or used in any form or by any means other
     than as expressly provided by the written license agreement between
     Harbour Networks System and its licensee.
===============================================================================
文件名   : complex.h 
文件描述 : 
历史记录 : 
  作者              日期            版本                修订说明
huangmeng1698     2004-03-15         V1.0                  创建
******************************************************************************/
#ifndef _COMPLEX_H_
#define _COMPLEX_H_

/**
 *  @<class> CComplex  
 *
 *  @<brief> 复数类
 */
class CComplex
{ 
    friend class CTestComplex3;

private:   
    double real;                // 复数的实部
    double imaginary;           // 复数的虚部    

public:
    CComplex (double r, double i = 0) : real(r), imaginary(i) {}
    
    friend bool operator ==(const CComplex& a, const CComplex& b);
    friend bool operator !=(const CComplex& a, const CComplex& b);

    bool Test(int i);
};


#endif/*_COMPLEX_H_*/
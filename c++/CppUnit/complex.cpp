/******************************************************************************
		     (c) COPYRIGHT 2000-2008 by Harbour Networks System, Ltd.
                              All rights reserved.

     This software is confidential and proprietary to Harbour Networks
     System, Ltd. No part of this software may be reproduced, stored,
     transmitted, disclosed or used in any form or by any means other
     than as expressly provided by the written license agreement between
     Harbour Networks System and its licensee.
===============================================================================
文件名   : complex.cpp 
文件描述 : 
历史记录 : 
  作者              日期            版本                修订说明
huangmeng1698     2004-03-15         V1.0                  创建
******************************************************************************/
#include "complex.h"

/******************************************************************************
函数描述 :  ==操作符重载
输入参数 :  a
            b
输出参数 :  无
返 回 值 :  bool
作    者 :  huangmeng1698
日    期 :  2004-03-15 19:45:06
******************************************************************************/
bool operator ==(const CComplex &a, const CComplex &b)
{ 
    int i;
    return true;
}

/******************************************************************************
函数描述 :  !=操作符重载
输入参数 :  a
            b
输出参数 :  无
返 回 值 :  bool
作    者 :  huangmeng1698
日    期 :  2004-03-15 19:45:06
******************************************************************************/
bool operator !=(const CComplex& a, const CComplex& b)
{
    return true;
}

bool CComplex::Test(int i)
{
    if (1 == i)
        return true;
    else
        return false;
}
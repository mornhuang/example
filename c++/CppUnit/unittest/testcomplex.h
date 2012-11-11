/******************************************************************************
		     (c) COPYRIGHT 2000-2008 by Harbour Networks System, Ltd.
                              All rights reserved.

     This software is confidential and proprietary to Harbour Networks
     System, Ltd. No part of this software may be reproduced, stored,
     transmitted, disclosed or used in any form or by any means other
     than as expressly provided by the written license agreement between
     Harbour Networks System and its licensee.
===============================================================================
文件名   : testcomplex.h 
文件描述 : 
历史记录 : 
  作者              日期            版本                修订说明
huangmeng1698     2004-03-15         V1.0                  创建
******************************************************************************/
#ifndef _TEST_COMPLEX_H_
#define _TEST_COMPLEX_H_

#include "cppunit/TestFixture.h"
#include "cppunit/extensions/HelperMacros.h"
#include "../complex.h"

class CTestComplex : public CppUnit::TestCase 
{ 
public: 
    CTestComplex(std::string name) : CppUnit::TestCase(name){}

    void runTest()
    {
        // 001
        CPPUNIT_ASSERT( CComplex (10, 1) == CComplex (10, 1) );
        
        // 002
        CPPUNIT_ASSERT( !(CComplex (1, 1) == CComplex (2, 2)) );
    }
};

#endif/*_TEST_COMPLEX_H_*/
/******************************************************************************
		     (c) COPYRIGHT 2000-2008 by Harbour Networks System, Ltd.
                              All rights reserved.

     This software is confidential and proprietary to Harbour Networks
     System, Ltd. No part of this software may be reproduced, stored,
     transmitted, disclosed or used in any form or by any means other
     than as expressly provided by the written license agreement between
     Harbour Networks System and its licensee.
===============================================================================
文件名   : testcomplex3.cpp
文件描述 : 
历史记录 : 
  作者              日期            版本                修订说明
huangmeng1698     2004-03-15         V1.0                  创建
******************************************************************************/
#include "testcomplex3.h"

CPPUNIT_TEST_SUITE_REGISTRATION(CTestComplex3);

void CTestComplex3::setUp()
{
    m_p1 = new CComplex(10, 1);
    m_p2 = new CComplex(11, 2);
}

void CTestComplex3::tearDown()
{
    if (m_p1)
    {
        delete m_p1;
        m_p1 = NULL;
    }

    if (m_p2)
    {
        delete m_p2;
        m_p2 = NULL;
    }
}

void CTestComplex3::testEqual()
{
    *m_p1 == *m_p2;
    m_p1->imaginary = 1;
}

void CTestComplex3::testUnequal()
{
    *m_p1 != *m_p2;
}

void CTestComplex3::testTest()
{
    // 001
    m_p1->Test(1);

    // 002
    m_p1->Test(0);
}
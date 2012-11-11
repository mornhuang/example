#include <iostream>
#include "cppunit/TestCaller.h"
#include "cppunit/TestResult.h"
#include "cppunit/ui/text/TestRunner.h"
#include "unittest/testcomplex.h"
#include "unittest/testcomplex2.h"
#include "unittest/testcomplex3.h"
using namespace std;

int main(int argc, char *argv[])
{
    /*=======================用TestCase测试====================*/
    /*CTestComplex a("test");
    CppUnit::TextUi::TestRunner runner;
    runner.addTest(&a);
    runner.run();*/

    /*======================用TestFixture测试===================*/
    /*CppUnit::TextUi::TestRunner runner;
    CTestComplex2 a;
    runner.addTest(a.suite());
    runner.run();*/

    /*=====================用宏测试=============================*/
	CppUnit::TextUi::TestRunner runner;
	
	// 从注册的TestSuite中获取特定的TestSuite, 没有参数获取未命名的TestSuite.
	CppUnit::TestFactoryRegistry &registry = CppUnit::TestFactoryRegistry::getRegistry();

	// 添加这个TestSuite到TestRunner中
	runner.addTest( registry.makeTest() );
	
    // 运行测试
	runner.run();
    
    system("PAUSE");	
    return 0;
}
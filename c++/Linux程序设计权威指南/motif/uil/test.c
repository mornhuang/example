
	/* File: test.c */
  
	#include <locale.h>
	#include <stdio.h>
	#include <Xm/Xm.h>
	#include <Mrm/MrmPublic.h>
  

        void quit(Widget w, char *tag, caddr_t reason)
	{
		exit(0);
	}
  
	int main(int argc, char **argv)
	{
		MrmHierarchy     s_MrmHierarchy;
		char             *vec[]={"test.uid"};
		MrmCode          class ;
		MrmCount         regnum = 1 ;
		MrmRegisterArg   regvec[] = { {"quit",(caddr_t)quit} };
		XtAppContext    app;
  
		Widget toplevel, testmain = NULL;
		int n;
		Arg arglist[10] ;
      

		//初始化
		setlocale(LC_ALL, "");

		//建立顶级窗口
		MrmInitialize ();
		toplevel = XtVaAppInitialize(&app, "test", NULL, 0,
                        &argc, argv, NULL, NULL);

		//XtToolkitInitialize();
  
		if (MrmOpenHierarchy (
			1,		// 文件数目
			vec,		// 文件
			NULL,		// os_ext_list
			&s_MrmHierarchy)// return id
			!= MrmSUCCESS) {
				printf ("Can't open hierarchy\n");
		}
  
 		//注册回调函数 
		if (MrmRegisterNames (regvec, regnum)!= MrmSUCCESS)
			printf("Can't register names\n");
  
		//建立界面
		if (MrmFetchWidget (s_MrmHierarchy, "test_main", toplevel,
			&testmain, &class) != MrmSUCCESS)
			printf("Can't fetch interface\n");
  
		XtManageChild(testmain);
      
		XtRealizeWidget(toplevel);
		XtAppMainLoop(app);

		return (0);
	}

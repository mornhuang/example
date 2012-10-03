

	/*
	 * By birney@sanger.ac.uk
	 */

	#include <stdio.h>
	#include <orb/orbit.h>

	//由echo.idl生成的文件
	#include "echo.h"

	//Echo 物件
	Echo echo_client;

	int main (int argc, char *argv[])
	{
		CORBA_Environment ev;
		CORBA_ORB orb;

		FILE * ifp;
		char * ior;
		char filebuffer[1024];

		//初始化
		CORBA_exception_init(&ev);
		orb = CORBA_ORB_init(&argc, argv, "orbit-local-orb", &ev);

		//打开文件 echo.ior, 它在运行Server时产生
		ifp = fopen("echo.ior","r");
			if( ifp == NULL ) {
				g_error("No echo.ior file!");
				exit(-1);
			}

			fgets(filebuffer,1024,ifp);
			ior = g_strdup(filebuffer);

			fclose(ifp);
		}

		//取得物件
		echo_client = CORBA_ORB_string_to_object(orb, ior, &ev);
		if (!echo_client) {
			printf("Cannot bind to %s\n", ior);
        		return 1;
		}

		//使用物件
		printf("Type messages to the server\n");
		printf("Type return to end.\n");
		while( fgets(filebuffer,1024,stdin) ) {
			if( filebuffer[0] == '.' && filebuffer[1] == '\n' ) 
			break;

			//截去回车符
			filebuffer[strlen(filebuffer)-1] = '\0';
      
			//使用echoString
      			Echo_echoString(echo_client,filebuffer,&ev);

			if(ev._major != CORBA_NO_EXCEPTION) {
				printf("we got exception %d from echoString!\n",
					ev._major);
        			return 1;
			}
		}
	
		//清除
		CORBA_Object_release(echo_client, &ev);
		CORBA_Object_release((CORBA_Object)orb, &ev);

		return 0;
	}

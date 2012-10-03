

	/*
	 * by birney@sanger.ac.uk
	 */

	#include <stdio.h>
	#include <stdlib.h>
	#include <string.h>
	#include <signal.h>
	#include <orb/orbit.h>
	#include "echo.h"

	//物件
	Echo echo_client = CORBA_OBJECT_NIL;

	static void do_echoString(PortableServer_Servant servant,
              CORBA_char *astring,
              CORBA_Environment *ev);

	//Orbit的比较复杂的东西
	PortableServer_ServantBase__epv base_epv = {
		NULL, NULL, NULL
	};
	POA_Echo__epv echo_epv = { NULL, do_echoString };
	POA_Echo__vepv poa_echo_vepv = { &base_epv, &echo_epv };
	POA_Echo poa_echo_servant = { NULL, &poa_echo_vepv };

	int main (int argc, char *argv[])
	{
		PortableServer_ObjectId objid = {0, 
			sizeof("myEchoString"), "myEchoString"};
		PortableServer_POA poa;

		CORBA_Environment ev;
		char *retval;
		CORBA_ORB orb;
		FILE * ofp;

		signal(SIGINT, exit);
		signal(SIGTERM, exit);

		//初始化
		CORBA_exception_init(&ev);
		orb = CORBA_ORB_init(&argc, argv, "orbit-local-orb", &ev);
		POA_Echo__init(&poa_echo_servant, &ev);

		poa = (PortableServer_POA)CORBA_ORB_resolve_initial_references(
			orb, "RootPOA", &ev);
		PortableServer_POAManager_activate(
			PortableServer_POA__get_the_POAManager(poa, &ev), &ev);
		PortableServer_POA_activate_object_with_id(poa,
			&objid, &poa_echo_servant, &ev);

		//客户联接
		echo_client = PortableServer_POA_servant_to_reference(poa,
			&poa_echo_servant, &ev);
		if (!echo_client) {
			printf("Cannot get objref\n");
			return 1;
		}

		retval = CORBA_ORB_object_to_string(orb, echo_client, &ev);

		ofp = fopen("echo.ior","w");

		fprintf(ofp,"%s", retval);
		fclose(ofp);

		CORBA_free(retval);

		fprintf(stdout,"Written the file echo.ior with the IOR\
 of this server.\n Now waiting for requests...\n");
		fflush(stdout);
		CORBA_ORB_run(orb, &ev);

		return 0;
	}

	static void do_echoString(PortableServer_Servant servant,
              CORBA_char *astring,
              CORBA_Environment *ev)
	{
		g_message("[server] %s", astring);
		return;
	}


RCWTESTps.dll: dlldata.obj RCWTEST_p.obj RCWTEST_i.obj
	link /dll /out:RCWTESTps.dll /def:RCWTESTps.def /entry:DllMain dlldata.obj RCWTEST_p.obj RCWTEST_i.obj \
		kernel32.lib rpcndr.lib rpcns4.lib rpcrt4.lib oleaut32.lib uuid.lib \

.c.obj:
	cl /c /Ox /DWIN32 /D_WIN32_WINNT=0x0400 /DREGISTER_PROXY_DLL \
		$<

clean:
	@del RCWTESTps.dll
	@del RCWTESTps.lib
	@del RCWTESTps.exp
	@del dlldata.obj
	@del RCWTEST_p.obj
	@del RCWTEST_i.obj

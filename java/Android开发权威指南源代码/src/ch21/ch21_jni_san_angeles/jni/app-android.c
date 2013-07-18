#include <jni.h>
#include <sys/time.h>
#include <time.h>
#include <android/log.h>
#include <stdint.h>

int   gAppAlive   = 1;

static int  sWindowWidth  = 320;
static int  sWindowHeight = 480;
static int  sDemoStopped  = 0;
static long sTimeOffset   = 0;
static int  sTimeOffsetInit = 0;
static long sTimeStopped  = 0;

static long
_getTime(void)
{
    struct timeval  now;

    gettimeofday(&now, NULL);
    return (long)(now.tv_sec*1000 + now.tv_usec/1000);
}

void
Java_mobile_android_ch21_jni_san_angeles_MyRenderer_nativeInit( JNIEnv*  env )
{
    importGLInit();
    appInit();
    gAppAlive    = 1;
    sDemoStopped = 0;
    sTimeOffsetInit = 0; 
}

void
Java_mobile_android_ch21_jni_san_angeles_MyRenderer_nativeResize( JNIEnv*  env, jobject  thiz, jint w, jint h )
{
    sWindowWidth  = w;
    sWindowHeight = h;
    __android_log_print(ANDROID_LOG_INFO, "SanAngeles", "resize w=%d h=%d", w, h);
}


void
Java_mobile_android_ch21_jni_san_angeles_MyRenderer_nativeDone( JNIEnv*  env )
{
    appDeinit();
    importGLDeinit();
}


void
Java_mobile_android_ch21_jni_san_angeles_DemoGLSurfaceView_nativePause( JNIEnv*  env )
{
    sDemoStopped = !sDemoStopped; 
    if (sDemoStopped) {
       
        sTimeStopped = _getTime();
    } else {

        sTimeOffset -= _getTime() - sTimeStopped;
    }
}


void
Java_mobile_android_ch21_jni_san_angeles_MyRenderer_nativeRender( JNIEnv*  env )
{
    long   curTime;

    if (sDemoStopped) {
        curTime = sTimeStopped + sTimeOffset;
    } else {
        curTime = _getTime() + sTimeOffset;
        if (sTimeOffsetInit == 0) {
            sTimeOffsetInit = 1;
            sTimeOffset     = -curTime;
            curTime         = 0;
        }
    }

 

    appRender(curTime, sWindowWidth, sWindowHeight);
}

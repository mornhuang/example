#include <string.h>
#include <jni.h>

jstring Java_mobile_android_ch21_jni_helloworld_HelloWorldJni_stringFromJNI( JNIEnv* env,
                                                  jobject thiz )
{ 
    return (*env)->NewStringUTF(env, "世界你好"); 
}  
       
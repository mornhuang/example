#ifndef APP_H_INCLUDED
#define APP_H_INCLUDED


#ifdef __cplusplus
extern "C" {
#endif


#define WINDOW_DEFAULT_WIDTH    640
#define WINDOW_DEFAULT_HEIGHT   480

#define WINDOW_BPP              16


extern void appInit();
extern void appDeinit();
extern void appRender(long tick, int width, int height);

extern int gAppAlive;


#ifdef __cplusplus
}
#endif


#endif 

#include <iostream.h>
#include <math.h>
#include <string.h>

const double PI=4*atan(1);
const double RAD2DEG=180.0/PI;
const double INIT_SEED=113;
const double MIN_XY=1.0;
const double MAX_XY=1000.0;
const double CRITIC_DIST=10.0;
const double EPSILON=1.0e-8;
const double INFINITY=1.0e+50;

enum Logical{FALSE,TRUE};

double sqr(double x)
{
    return x*x;
}

double cube(double x)
{
    return x*x*x;
}

double farc(double x)
{
    return x-(long)x;
}
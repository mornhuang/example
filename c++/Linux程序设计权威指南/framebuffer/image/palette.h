/*
###
### This file is part of
###
###                        Unicon Project
###
###                 Copyright (C) 1999 TurboLinux, Inc. 
###                        All Rights Reserved
### Distributed under the terms of the GNU General Public License (GPL)
###
*/


#define    __initdata

unsigned char image_red[] __initdata = {
#include "./pics/image.red"
};

unsigned char image_green[] __initdata = {
#include "./pics/image.grn"
};

unsigned char image_blue[] __initdata = {
#include "./pics/image.blu"
};


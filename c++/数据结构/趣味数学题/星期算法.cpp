#include<iostream.h>
#include<conio.h>
void main()


    { 
    int Ydiff,Ddiff,Mdiff;
    char *DAY[7]={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    int cd,cm,cy,cs,rd,rm,ry,rs,i;
    cd=19;cm=2;cy=2001;cs=2; // setting 19 feb 2001 , Monday
    cout<<"Enter REQUIRED date (DD MM YYYY) : ";
    cin>>rd>>rm>>ry;
    // difference in years
    Ydiff=cy-ry;
    // find no of leap years
    if(cy>=ry)


        { for(i=cy;i>=ry;i--)


            {
            if((i%100)==0) // for a century


                { if((i%400)==0) {Ydiff++;}
            }
            else


                { if((i%4)==0) {Ydiff++;}
            }
        }
    }
    else


        { for(i=cy;i<ry;i++)


            { if((i%100)==0) // for a century


                { if((i%400)==0) {Ydiff--;}
            }
            else


                { if((i%4)==0) {Ydiff--;}
            }
        }
    }
    Ydiff=Ydiff%7;
    Ddiff=(cd-rd)%7;
    rs=cs-(Ydiff+Ddiff)%7;
    rs=rs%7;
    //difference in months
    Mdiff=cm-rm;
    if(Mdiff<0)


        { for(i=cm;i<rm;i++)


            { switch(i)


                { case 1:rs+=3;break;
                	case 2:if((ry%4)==0) {rs++;}
                	break;
                	case 3:rs+=3;break;
                	case 4:rs+=2;break;
                	case 5:rs+=3;break;
                	case 6:rs+=2;break;
                	case 7:rs+=3;break;
                	case 8:rs+=3;break;
                	case 9:rs+=2;break;
                	case 10:rs+=3;break;
                	case 11:rs+=2;break;
                	default:break;
            }
        }
        rs=rs%7;
    }
    if(Mdiff>0)


        { for(i=cm;i>rm;i--)


            { switch(i)


                { case 2:rs-=3;break;
                	case 3:if(((ry-1)%4)==0) {rs--;}
                	break;
                	case 4:rs-=3;break;
                	case 5:rs-=2;break;
                	case 6:rs-=3;break;
                	case 7:rs-=2;break;
                	case 8:rs-=3;break;
                	case 9:rs-=3;break;
                	case 10:rs-=2;break;
                	case 11:rs-=3;break;
                	case 12:rs-=2;break;
                	default:break;
            }
        }
        rs=rs%7;
    }
    if(rs<=0) {rs=7+rs;}
    cout<<"It's a "<<DAY[rs-1]<<" !!";
    getch();
}

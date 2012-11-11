#include <iostream.h>
#include <fstream.h>

void main()
{
    int i=0;
    char *open,temp;
    cout <<"please input file name:";
    cin >>open;
    ifstream file(open);

    if(!file)
        cout <<"Open file error!";
    else
    {
        while(!file.eof())
        {
            temp=file.get();
            if(temp=='\n')
            i++;
        }
        cout <<i <<" line" <<endl;
    }
}
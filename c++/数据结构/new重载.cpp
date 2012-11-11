//#include "stdafx.h"
#include "iostream.h"
#include "string.h"
#include "malloc.h"

class COnlineUser
{
protected:
    bool _state;
    char* _pszName;
public:
static unsigned char _ObjPool[];
static unsigned char _Flag[]; 
bool& State();
enum { MaxUser = 100000 };
    COnlineUser();
COnlineUser(const COnlineUser& rU);
    virtual ~COnlineUser();
    void* operator new(size_t sz);
    void operator delete(void* p);
};

unsigned char COnlineUser::_ObjPool[COnlineUser::MaxUser*sizeof(COnlineUser)] = {0};
unsigned char COnlineUser::_Flag[COnlineUser::MaxUser] = {0};

COnlineUser::COnlineUser() : _state(0),_pszName(0)
{
cout<<"Constructor Called"<<endl;
}

COnlineUser::COnlineUser(const COnlineUser& rU)
{
_state = rU._state ;
_pszName = ::new char[strlen(rU._pszName) + 1];
strcpy(_pszName,rU._pszName);
cout<<"Copy Constructor Called"<<endl;
}

COnlineUser::~COnlineUser()
{
cout<<"Destructor Called"<<endl;
::delete[] _pszName;
}

void* COnlineUser::operator new(size_t sz)
{
cout<<"operator new called"<<endl;
for(int ii = 0;ii<MaxUser; ii++)
if(!_Flag[ii])
{
_Flag[ii] = 1;
return _ObjPool+sizeof(COnlineUser)*ii;
}
cout<<"Out of memery"<<endl;
return 0;
}

void COnlineUser::operator delete(void* p)
{
cout<<"operator delete called"<<endl;
int offset = ((unsigned long)p-(unsigned long)_ObjPool)/sizeof(COnlineUser);
if(_Flag[offset])
{
_Flag[offset] = 0;
}

}

bool& COnlineUser::State()
{
return _state;
}

int main(int argc, char* argv[])
{
COnlineUser *pUser;
pUser= new COnlineUser;
pUser->State() = true;
cout <<"Current User state : "<<pUser->State()<<endl;
delete pUser;
return 0;
}
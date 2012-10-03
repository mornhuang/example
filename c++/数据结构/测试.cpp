 #include<iostream.h>

      class Base0 {
      public:
      Base0() {
      void **p = (void **)this;
      cout<<"this="<< this << ' '<<"*this="<<*p<<" Base0 size=";
      cout<< sizeof(*this)<<'\n';
      }
      };
      class Base1 : public Base0{
      public:
      Base1() {
      void **p = (void **)this;
      cout<<"this="<< this << ' '<<"*this="<<*p<<" Base1 size=";
      cout<< sizeof(*this)<<'\n';
      cout<<"        Base1::x["<<&x<<"]"<<endl;
      }
      int x;
      };

      class Base2 : public Base1 {
      public:
      Base2() {
      void **p = (void **)this;
      cout<<"this="<< this << ' '<<"*this="<<*p<<" Base2 size=";
      cout<< sizeof(*this)<<'\n';
      cout<<"        Base2::x["<<&x<<"]"<<endl;
      cout<<"        Base2::y["<<&y<<"]"<<endl;
      }

      int y;
      virtual void func(){};
      };

      class Base3 : public Base2 {
      public:
      Base3() {
      void **p = (void **)this;
      cout<<"this="<< this << ' '<<"*this="<<*p<<" Base3 size=";
      cout<< sizeof(*this)<<'\n';
      cout<<"        Base3::x["<<&x<<"]"<<endl;
      cout<<"        Base3::y["<<&y<<"]"<<endl;
      cout<<"        Base3::z["<<&z<<"]"<<endl;
      }

      int z;
      virtual void func(){};
      };

      class Base4 : public Base3 {
      public:
      Base4() {
      void **p = (void **)this;
      cout<<"this="<< this << ' '<<"*this="<<*p<<" Base4 size=";
      cout<< sizeof(*this)<<'\n';
      cout<<"        Base4::x["<<&x<<"]"<<endl;
      cout<<"        Base4::y["<<&y<<"]"<<endl;
      cout<<"        Base4::z["<<&z<<"]"<<endl;
      cout<<"        Base4::w["<<&w<<"]"<<endl;
      }
      int w;
      };

      int main()
      {
      Base4 b;
      return 0;
      }
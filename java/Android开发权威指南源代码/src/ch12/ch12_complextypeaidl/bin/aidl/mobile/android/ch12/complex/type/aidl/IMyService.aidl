package mobile.android.ch12.complex.type.aidl;
import mobile.android.ch12.complex.type.aidl.Product;

interface IMyService  
{  
    Map getMap(in String country, in Product product);
    Product getProduct();     
}          
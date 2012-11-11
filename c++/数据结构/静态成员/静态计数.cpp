#include <iostream.h>
#include <iomanip.h>

class Box
{
public:
	Box(double fLength=0,double fWidth=0,double fHeight=0);
	~Box();

	//declare static member function to access
	//static data member
    static int getNumberOfInstances()
	{return m_nNumberInstances;}
	void initialize(double fLength=0,double fWidth=0,double fHeight=0);
	void setLength(double fLength)
	{m_fLength = fLength;}
	void setWidth(double fWidth)
	{m_fWidth = fWidth;}
	void setHeight(double fHeight)
	{m_fHeight = fHeight;}

	double getLength()
	{return m_fLength;}
	double getWidth()
	{return m_fWidth;}
	double getHeight()
	{return m_fHeight;}
	double getBaseArea()
	{return m_fLength * m_fWidth;}
	double getVolume()
	{return m_fHeight * getBaseArea();}

protected:
	double m_fLength;//length of box
	double m_fWidth; //width of box
	double m_fHeight;//height of box

	static int m_nNumberInstances;//number of class instances
};
	//initialize the static data member
	int Box::m_nNumberInstances = 0;

	Box::Box(double fLength,double fWidth,double fHeight)
	{
	    initialize(fLength,fWidth,fHeight);
		//increase the number of instances
		m_nNumberInstances++;
	}

	Box::~Box()
	{
	    //decrease the number of instances
		m_nNumberInstances--;
	}

	void Box::initialize(double fLength,
		                 double fWidth,
						 double fHeight)
	{
	    m_fLength = fLength;
		m_fWidth = fWidth;
		m_fHeight = fHeight;
	}

	main()
	{
	    cout << " Before declaring an object\n ";
		cout << " There are " << Box::getNumberOfInstances()
			<<" instance of class Box\n ";
		Box Box1(10.5,34.5,12.3);
        
		    cout << "\nAfter declaring the Box1 object\n ";
		    cout << " There is " << Box1.getNumberOfInstances()
			    << " instance of class Box\n";
		{	
		Box Box2(1.9,3.4,5.6);
		
		    cout << "\nInside first nested block\n ";
			cout << " There are " << Box2.getNumberOfInstances()
				<< " instance of class Box\n";
			{
				const int MAX_BOXES=10;
				//declare an array
		Box BoxArray[MAX_BOXES];
		
			cout << "\nInside second nested block\n ";
			cout << "There are " << BoxArray[0].getNumberOfInstances()
				<< "instances of class Box\n ";
		}
			cout << "\nAfter exiting second nested block\n";
			cout << " There are " << Box2.getNumberOfInstances()
				<< "instances of class Box\n";
		}
		    cout << "\nAfter exiting fifst nested block\n ";
		    cout << " There is " << Box1.getNumberOfInstances()
			   << "instances of class Box\n ";
		
		return 0;
}
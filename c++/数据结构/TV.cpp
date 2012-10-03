#include <iostream.h>
#include <iomanip.h>

//declare the enumerated type OnOffMode

enum OnOffMode{ tvOff,tvOn};

//declare the type byte as an alias to unsigned char
typedef unsigned char byte;

class TV01
{
public:
	TV01()
	{initialize();}
	void initialize();
	void turnOn()
	{m_eOnOffSwitch = tvOn;}
	void turnOff()
	{m_eOnOffSwitch = tvOff;}
	OnOffMode getOnOffMode()const
	{return m_eOnOffSwitch;}
	void setChannelNumber(byte uChannelNumber)
	{m_uChannelNumber=uChannelNumber;}
	byte getChannelNumber()const
	{return m_uChannelNumber;}
	void setVolume(byte uVolume)
	{m_uVolume = uVolume;}
	byte getVolume()const
	{return m_uVolume;}

protected:
	OnOffMode m_eOnOffSwitch;
	byte m_uChannelNumber;
	byte m_uVolume;
};

void TV01::initialize()
{
    m_eOnOffSwitch = tvOff;
	m_uChannelNumber = 0;
	m_uVolume = 0;
}

main()
{
    TV01 myTV;
	TV01 yourTV;

	myTV.turnOn();
	myTV.setChannelNumber(8);
	myTV.setVolume(5);

	if(myTV.getOnOffMode()==tvOn)
	{
	    cout << " Object myTV is on\n "
			<< " Selected channel number is "
			<< int(myTV.getChannelNumber()) << endl
			<< " Volume is set at level "
			<< int(myTV.getVolume()) << endl;
	}

	else
		cout << " Object myTV is off!\n";
	cout << endl;

	yourTV.turnOn();
	yourTV.setChannelNumber(9);
	yourTV.setVolume(14);

	if(yourTV.getOnOffMode()==tvOn)
	{
	    cout << " Object yourTV is on\n"
			<< " Selected channel number is "
			<< int(yourTV.getChannelNumber()) << endl
			<< " Volume is set at level "
			<< int(yourTV.getVolume()) << endl;
		if(yourTV.getChannelNumber() !=
			myTV.getChannelNumber())
		{
			yourTV.setChannelNumber(myTV.getChannelNumber());
			cout << " Switching yourTV to channel "
				<< int(yourTV.getChannelNumber()) << endl;
		}

		if(yourTV.getVolume() > 10)
		{
		    yourTV.setVolume((yourTV.getVolume()-10)/2);
			cout << " Switching volume level of yourTV to "
				<< int(yourTV.getVolume()) << endl;
		}
		else
			cout <<  " Object yourTV is off! \n";

		return 0;
	}
}
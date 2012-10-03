class Rescue:public Random,public Distance
{
public:
	Rescue(double fMin,double fMax,double fCriticlDistance);

	void initRescue();
	Logical searchMore();
	void getCoords();

protected:
	double m_fMin;
	double m_fMax;
	double m_fShift;
	double m_fCriticalDistance;
	double calcRandCoord()
	{return getRandom() * m_fMax + m_fMin;}
	double calcRandShift()
	{return(0.5-getRandom()) * m_fShift;}
	double checkCoord(double fX)
	{return(fX>=m_fMin&&fX<=m_fMax)?
             fX:m_fMin + m_fMax/2.;}
};

Rescue::Rescue(double fMin,double fMax,
			   double fCriticalDistance)
			   :Random(),Distance(0,0,0,0)
{
    m_fMin=fMin;
	m_fMax=fMax;
	m_fShift=(fMax-fMin+1)/10.;
	m_fCriticalDistance=fCriticalDistance;
	initRescue();
}

void Rescue::initRescue()
{
    double fX=calcRandCoord();
	double fY=calcRandCoord();

	setPoint1((m_fMin + m_fMax)/2,(m_fMin + m_fMax)/2);
	setPoint2(fX,fY);
}

Logical Rescue::searchMore()
{
    if(fabs(m_fDeltaX)<1.0||fabs(m_fDeltaY)<1.0||
		getDistance()<m_fCriticalDistance)
		return FALSE;
	else
		return TRUE;
}

void Rescue::getCoords()
{
    cout << " Cuttent distance = "
		<< getDistance() << "\n"
		<< " Current angle = "
		<< getAngle() << " degrees\n ";
    m_fDeltaX=calcRandShift();
	m_fDeltaY=calcRandShift();

	cout << " Enter movement in X direction : ";
	cin >> m_fDeltaX;
	if(m_fDeltaX > m_fShift)
		m_fDeltaX=m_fShift;
	cout << " Enter mouement in Y direction : ";
	cin >> m_fDeltaY;
	if(m_fDeltaY > m_fShift)
		m_fDeltaY=m_fShift;
	setPoint1(checkCoord(m_fX1 + m_fDeltaX),
		checkCoord(m_fY1 + m_fDeltaY));
}
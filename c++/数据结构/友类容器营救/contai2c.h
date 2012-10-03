class Distance
{
friend class Rescue;

public:
	Distance(double fX1,double fY1,
		double fX2,double fY2);
	void setPoint1(double fX,double fY);
	void setPoint2(double fX,double fY);
	double getDistance()
	{return sqrt(sqr(m_fX2-m_fX1)+
	           sqr(m_fY2-m_fY1));}
	double getAngle();

protected:
	double m_fX1;
	double m_fX2;
	double m_fY1;
	double m_fY2;
	double m_fDeltaX;
	double m_fDeltaY;
};

Distance::Distance(double fX1,double fY1,
				   double fX2,double fY2)
{
	setPoint1(fX1,fY1);
	setPoint2(fX2,fY2);
}

void Distance::setPoint1(double fX,double fY)
{
	m_fX1=fX;
	m_fY1=fY;
}

void Distance::setPoint2(double fX,double fY)
{
	m_fX2=fX;
	m_fY2=fY;
}

double Distance::getAngle()
{
    double fDeltaY=m_fY2-m_fY1;

	return(fabs(fDeltaY>EPSILON))?
		RAD2DEG*atan((m_fX2-m_fX1)/fDeltaY):
	        INFINITY;
}
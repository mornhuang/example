class NCScalc120:virtual public NCScalc100,
virtual public NCScalc200
{
public:
	NCScalc120(int nMaxMem=10,double fRegX=0.,
		double fRegY=0.)
		:NCScalc100(nMaxMem,fRegX,fRegY)
	{
	    strcpy(m_cFunc," ");
	}
};
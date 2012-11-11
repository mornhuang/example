class NCScalc100:virtual public NCScalc01
{
public:
	NCScalc100(int nMaxMem=10,
		double fRegX=0.,
		double fRegY=0.);
	~NCScalc100();
	void stoMem(int nIndex,double x);
	void rclMem(int nIndex,double &x);
	void clearMem();

protected:
	double * m_pfMem;
	int m_nMaxMem;
};

NCScalc100::NCScalc100(int nMaxMem,
					   double fRegX,double fRegY)
					   :NCScalc01(fRegX,fRegY)
{
    m_pfMem=new double[m_nMaxMem=nMaxMem];
	for(int i=0;i<m_nMaxMem;i++)
		m_pfMem[i]=0.;
}

NCScalc100::~NCScalc100()
{
    delete []m_pfMem;
}

void NCScalc100::stoMem(int nIndex,double x)
{
    if(nIndex>=0&&nIndex<m_nMaxMem)
	{
	    m_pfMem[nIndex]=x;
		m_bErr=FALSE;
	}
	else
		m_bErr=TRUE;
}

void NCScalc100::rclMem(int nIndex,double &x)
{
    if(nIndex>=0&&nIndex<m_nMaxMem)
	{
	    x=m_pfMem[nIndex];
		m_bErr=FALSE;
	}
	else
		m_bErr=TRUE;
}

void NCScalc100::clearMem()
{
    for(int i=0;i<m_nMaxMem;i++)
		m_pfMem[i]=0.;
}

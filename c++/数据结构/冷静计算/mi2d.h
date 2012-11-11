class NCScalc200:virtual public NCScalc01
{
public:
	NCScalc200(double fRegX=0.,double fRegY=0.)
		:NCScalc01(fRegX,fRegY)
	{
	    strcpy(m_cFunc," ");
	}

	void Sqrt(Logical bShowResul=TRUE,
		const char *pszMsg=" ");
	void Sqr(Logical bShowResult=TRUE,
		const char *pszMsg=" ");
	void showFunction(const char *pszMsg=" ");
	void clearRegs();

protected:
	char m_cFunc[10];
};

void NCScalc200::Sqrt(Logical bShowResult,const char *pszMsg)
{
    if(m_fRegX>=0.)
	{
	    m_fRegZ=sqrt(m_fRegX);
		m_bErr=FALSE;
		strcpy(m_cFunc,"Sqrt(");
		if(bShowResult)
			cout << m_fRegZ;
	}
	else
	{
	    m_fRegZ=BAD_RESULT;
		m_bErr=TRUE;
		strcpy(m_cFunc," ");
	}
}

void NCScalc200::Sqr(Logical bShowResult,const char *pszMsg)
{
    m_fRegZ=m_fRegX * m_fRegX;
	m_bErr=FALSE;
	strcpy(m_cFunc,"Sqr(");
	if(bShowResult)
		cout << m_fRegZ;
}

void NCScalc200::showFunction(const char *pszMsg)
{
    cout << pszMsg << m_cFunc << m_fRegX
		<< ")="<<m_fRegZ << " \n";
}

void NCScalc200::clearRegs()
{
	NCScalc01::clearRegs();
	strcpy(m_cFunc," ");
}
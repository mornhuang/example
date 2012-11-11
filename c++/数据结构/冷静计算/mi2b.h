class NCScalc01
{
public:
	NCScalc01(double fRegX=0.,double fRegY=0.);

	//set new register values
	void setRegX(double fRegX)
	{m_fRegX=fRegX;}
	void setRegY(double fRegY)
	{m_fRegY=fRegY;}
	double getRegZ()
	{return m_fRegZ;}
	Logical getError();
	void add(Logical bShowResult=TRUE,
		const char *pszMsg=" ");
	void sub(Logical bShowResult=TRUE,
		const char *pszMsg=" ");
	void mult(Logical bShowResult=TRUE,
		const char *pszMsg=" ");
	void div(Logical bShowResult=TRUE,
		const char *pszMsg=" ");
	void showRegZ(const char *pszMsg=" ");
	void showOperation(const char *pszMsg=" ");
	void clearRegs();

protected:
	double m_fRegX;
	double m_fRegY;
	double m_fRegZ;
	char m_cOp;
	Logical m_bErr;
};

NCScalc01::NCScalc01(double fRegX,double fRegY)
{
    m_fRegX=fRegX;
	m_fRegY=fRegY;
	m_fRegZ=0.0;
	m_cOp=' ';
	m_bErr=FALSE;
}

Logical NCScalc01::getError()
{
    Logical bTemp=m_bErr;
	m_bErr=FALSE;
	return bTemp;
}

void NCScalc01::add(Logical bShowResult,const char *pszMsg)
{
    m_fRegZ=m_fRegY+m_fRegX;
	m_cOp='+';
	m_bErr=FALSE;
	if(bShowResult)
		cout << pszMsg << m_fRegZ;
}

void NCScalc01::sub(Logical bShowResult,const char *pszMsg)
{
    m_fRegZ=m_fRegY-m_fRegX;
	m_cOp='-';
	m_bErr=FALSE;
	if(bShowResult)
		cout << pszMsg << m_fRegZ;
}

void NCScalc01::mult(Logical bShowResult,const char *pszMsg)
{
    m_fRegZ=m_fRegY * m_fRegX;
	m_cOp='*';
	m_bErr=FALSE;
	if(bShowResult)
		cout << pszMsg << m_fRegZ;
}

void NCScalc01::div(Logical bShowResult,const char* pszMsg)
{
    if(fabs(m_fRegY)>EPSILON)
	{
	    m_cOp='/';
		m_bErr=FALSE;
		m_fRegZ=m_fRegY/m_fRegX;
		if(bShowResult)
			cout << pszMsg << m_fRegZ;
	}
	else
	{
	    m_fRegZ=BAD_RESULT;
		m_cOp=' ';
		m_bErr=TRUE;
	}
}

void NCScalc01::showRegZ(const char * pszMsg)
{
    cout << pszMsg << m_fRegZ;
}

void NCScalc01::showOperation(const char *pszMsg)
{
    cout << pszMsg << m_fRegY << " " << m_cOp << " "
		<< m_fRegX << "=" << m_fRegZ << "\n";
}

void NCScalc01::clearRegs()
{
    m_fRegX=0.0;
	m_fRegY=0.0;
	m_fRegZ=0.0;
	m_cOp=' ';
	m_bErr=FALSE;
}
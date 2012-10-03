#include <iostream.h>
#include <iomanip.h>

enum Logical{FALSE,TRUE};

typedef unsigned char Byte;
typedef unsigned char UChar;

const unsigned char NULL_CHAR='\0';
const unsigned MAX_CHARS=256;
const unsigned CHAR_SIZE=8;
const unsigned CHAR_ARRAY_SIZE=MAX_CHARS/CHAR_SIZE;

class CharacterSet
{
public:
	CharacterSet()
	{Clear();}
	CharacterSet(UChar cFirst,UChar cLast);
	unsigned char GetSetSize()
	{return m_uSetSize;}
	CharacterSet& Clear();
	Logical IsMember(CharacterSet& cs);
	CharacterSet& Add(CharacterSet& cs);
	CharacterSet& Remove(CharacterSet& cs);
	void Show(const char* pszMsg=" ");

protected:
	UChar m_cSet[CHAR_ARRAY_SIZE];
	Byte m_uSetSize;

private:
	void SetBit(Byte uBitNumber);
	void ClearBit(Byte uBitNumber);
	Logical IsBitSet(Byte uBitNumber);

	//declare staic data member
	static UChar m_uBitValues[CHAR_ARRAY_SIZE];
};

UChar CharacterSet::m_uBitValues[CHAR_ARRAY_SIZE]=
                    {1,2,4,8,16,32,64,128};

CharacterSet::CharacterSet(UChar cFirst,UChar cLast)
{
    UChar c;

	Clear();
	//need to swap parameters cFirst and cLast?
	if(cFirst>cLast)
	{
	    c=cFirst;
		cFirst=cLast;
		cLast=c;
	}

	m_uSetSize=cLast - cFirst + 1;
	for(c=cFirst;c<=cLast; c++)
		SetBit(c);
}

CharacterSet& CharacterSet::Clear()
{
    for(UChar i=0;i<CHAR_ARRAY_SIZE;i++)
	    m_cSet[i]=0;
	m_uSetSize=0;
	return *this;
	
}

void CharacterSet::SetBit(Byte uBitNumber)
{
    m_cSet[uBitNumber / CHAR_SIZE] |=
		m_uBitValues[uBitNumber % CHAR_SIZE];
}

void CharacterSet::ClearBit(Byte uBitNumber)
{
    m_cSet[uBitNumber / CHAR_SIZE] ^=
		m_uBitValues[uBitNumber % CHAR_SIZE];
}

Logical CharacterSet::IsBitSet(Byte uBitNumber)
{
    return((m_cSet[uBitNumber / CHAR_SIZE]&
		m_uBitValues[uBitNumber % CHAR_SIZE]) > 0)?
             TRUE : FALSE;
}

Logical CharacterSet::IsMember(CharacterSet& cs)
{
    int i;
	unsigned uBit = 0;
	//loop to test members of cs set?
	do
	{
	    //is character ASCII " uBit " a member of cs set?
		i=cs.IsBitSet((UChar)uBit);
		if(i)
		{
		    //is member of cs not a member of this set?
			if(!IsBitSet((UChar)uBit))
				//cs is not equivalent or a subset of this set
				return FALSE;
		}
		uBit++;
	}while(uBit<=MAX_CHARS);
	//Every member of cs is also a member of this set
	return TRUE;
}

CharacterSet& CharacterSet::Add(CharacterSet &cs)
{
    //exit if set cs is empty
    if(cs.m_uSetSize==0)
		return* this;
	//bitwize OR the m_cSet member of this set and set cs
	for(unsigned i=0; i<CHAR_ARRAY_SIZE;i++)
		m_cSet[i]|=cs.m_cSet[i];
	//reset the set size
	m_uSetSize=0;
	//scan the members of this set to recalculate m_uSetSize
	for(i=0;i<MAX_CHARS;i++)
		//is ASCII i a member of this set?
		if(IsBitSet((UChar)i))
			m_uSetSize++;//increment the set size
	return * this;
}

CharacterSet& CharacterSet::Remove(CharacterSet& cs)
{
    //exit if the set cs is empty
	if(cs.m_uBitValues==0)
		return *this;

	//bitwize XOR to clear members of this set
	//the are also in set cs
	for(unsigned i=0;i<CHAR_ARRAY_SIZE;i++)
		m_cSet[i]^=cs.m_cSet[i];
	//reset the set size
	m_uSetSize=0;
	//scan the bits of this set to recalculate the new set size
	for(i=0;i<=MAX_CHARS;i++)
		//is ASCII i a member of this set?
		if(IsBitSet((UChar)i))
			m_uSetSize++;
		return *this;
}

void CharacterSet::Show(const char *pszMsg)
{
    cout << pszMsg << "[";

	if(m_uSetSize==0)
	{
	    cout << " ]\n";
		return;
	}

	for(unsigned i=0;i<MAX_CHARS;i++)
		if(IsBitSet((UChar)i))
			cout << UChar(i);
		cout << "]\n";
}

main()
{
   CharacterSet CharSet1('A','F');
   CharacterSet CharSet2('L','P');
   CharacterSet CharSet3('C','Y');
   CharacterSet CharSet4('N','O');
   CharacterSet CharSet5('B','F');

   CharSet1.Show(" Character set 1 has the range of:\n");
   CharSet2.Show(" Character set 2 has the range of:\n");

   //add character of set 2 to 1
   CharSet1.Add(CharSet2);
   CharSet1.Show(" Adding set 2 to set 1 yields:\n");

   CharSet3.Show(" Character set 3 has the range of:\n");
   if(CharSet1.IsMember(CharSet3))
	   cout << " Members of set 3 are in set 1\n";
   else
	   cout << " Members of set 3 are NOT in set 1\n";

   //remove the characters of set 4 from set 1d
   CharSet1.Remove(CharSet4);
   CharSet4.Show(" Character set 4 has the range of:\n");
   CharSet1.Show(" After removing set 4 from set 1,set 1 is:\n");

   CharSet3.Show(" Character set 3 has to the range of:\n");
   if(CharSet1.IsMember(CharSet3))
	   cout << " Members of set 3 are in set 1\n";
   else
	   cout << " Members of set 3 are NOT in set 1\n";

   CharSet5.Show(" Character set 5 has the range of:\n");
   if(CharSet1.IsMember(CharSet5))
	   cout << " Members of set 5 are in set 1\n ";
   else
	   cout << " Members of set 5 are NOT in set 1\n";

   return 0;
}
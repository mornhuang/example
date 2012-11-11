#include <iostream.h>

const int MIN_NUM=1;
const int MAX_NUM=1000;

class guessGame
{
public:
	void play();

protected:
	int m_nHi;
	int m_nLo;
	int m_nGuess;
	int m_nHint;

	void startGame();
	void showGuess();
	int playMore();
};

void guessGame::play()
{
    startGame();
	showGuess();
	while(playMore())
		showGuess();
}

void guessGame::startGame()
{
    cout << " Guess a number between " << MIN_NUM
		<< " and " << MAX_NUM << "\n";
	cout << " At hint prompt enter one of the following:\n"
		<< " 1 when my guess is high\n"
		<< " 0 when my guess is correct\n"
		<< " -1 when my guess is low\n\n";
	m_nLo=MIN_NUM;
	m_nHi=MAX_NUM;
	m_nGuess=(m_nLo+m_nHi)/2;
}

void guessGame::showGuess()
{
    cout << " Guess is" << m_nGuess << "\n";
}

int guessGame::playMore()
{
    cout << " Enter hint value : ";
	cin >> m_nHint;

	if(m_nHint == 0)
	{
	   cout << " End of game! \n";
	   return 0;
	}
	else if(m_nHint > 0)
		// set lower limit to last guess
		m_nHi = m_nGuess;
	else
		m_nLo = m_nGuess;

	//get new guess
	m_nGuess = (m_nLo + m_nHi)/2;

	// double-check player is not cheating!
	if(m_nGuess == m_nLo || m_nGuess == m_nHi)
	{
	    cout << "your secret number must be" << m_nGuess << "\n";
		return 0;
	}
	else
		return 1;
}

main()
{
    guessGame game;
	game.play();

	return 0;
}
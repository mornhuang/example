main()
{
    Rescue R(MIN_XY,MAX_XY,CRITIC_DIST);

	R.getCoords();
	while(R.searchMore())
		R.getCoords();

	cout << "\n\n";
	if(R.getDistance()<=CRITIC_DIST)
		cout << " Congratulations for the rescue! ";
	else
		cout << " Sorry you gave up! ";

	return 0;
}
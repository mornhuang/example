main()
{
    NCScalc120 calculator;

	cout << " Testing class NCScalc120\n";

	calculator.setRegX(13.0);
	calculator.setRegY(55.0);
	calculator.add(FALSE);
	calculator.showOperation();

	calculator.setRegX(55.0);
	calculator.setRegY(123.0);
	calculator.sub(FALSE);
	calculator.showOperation();

	calculator.setRegX(13.0);
	calculator.setRegY(0.55);
	calculator.mult(FALSE);
	calculator.showOperation();

	calculator.setRegX(113.0);
	calculator.setRegY(355.0);
	calculator.div(FALSE);
	calculator.showOperation();

	calculator.setRegX(13.0);
	calculator.Sqrt(FALSE);
	calculator.showFunction();

	calculator.Sqr(FALSE);
	calculator.showFunction();
	return 0;
}
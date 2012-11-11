using System;


namespace CSharpFactorial
{
	//[ClassInterface(ClassInterfaceType.AutoDual)] 
	public class Factorial 
	{
		public Factorial()
		{
		}
      

		//THIS METHOD COMPUTES THE 
		//FACTORIAL FOR A NUMBER. 
		public int ComputeFactorial(int n)
		{   
			int intFactorial=n;
			for (int i=1;i<n;i++)
			{
				intFactorial*=i; 
			}
			return intFactorial; 
		}
		private int DoubleNumber (int n)
		{
			return n*2;
		}

	}
}

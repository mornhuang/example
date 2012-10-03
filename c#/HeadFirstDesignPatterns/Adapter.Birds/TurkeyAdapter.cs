using System;
using System.Text;

namespace HeadFirstDesignPatterns.Adapter.Birds
{
	/// <summary>
	/// Summary description for TurkeyAdapter.
	/// </summary>
	public class TurkeyAdapter : Duck
	{
		Turkey turkey;

		public TurkeyAdapter(Turkey turkey)
		{
			this.turkey = turkey;
		}

		#region Duck Members

		public string Quack()
		{
			return turkey.Gobble();
		}

		public string Fly()
		{
			StringBuilder sb = new StringBuilder();

			for(int i = 0; i < 5; i++)
			{
				sb.Append(turkey.Fly());
				sb.Append("\n");
			}
			return sb.ToString();
		}

		#endregion
	}
}

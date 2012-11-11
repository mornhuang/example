using System;


namespace Wrox.ProCSharp.Assemblies.CrossLanguage
{
	
	/// <summary>
	///    Summary description for HelloCSharp.
	/// </summary>

	public class HelloCSharp : HelloVB
	{
		public HelloCSharp()
		{
		}

		public override void Hello()
		{
			base.Hello();
			Console.WriteLine("Hello, C#");
		}

		public new int Add(int val1, int val2)
		{
			return val1 + val2;
		}

		[STAThread]
		public static void Main()
		{
			HelloCSharp hello = new HelloCSharp();
			hello.Hello();
		}
	}
}

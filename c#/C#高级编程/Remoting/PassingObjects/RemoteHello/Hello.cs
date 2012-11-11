using System;

namespace Wrox.ProfessionalCSharp
{
	[Serializable]
	public class MySerialized 
	{
		public MySerialized(int val)
		{
			a = val;
		}

		public void Foo()
		{
			Console.WriteLine("MySerialized.Foo called");
		}

		public int A
		{
			get
			{
				Console.WriteLine("MySerialized.A called");
				return a;
			}
			set
			{
				a = value;
			}
		}

		protected int a;
	}

	public class MyRemote : System.MarshalByRefObject
	{
		public MyRemote(int val)
		{
			a = val;
		}

		public void Foo()
		{
			Console.WriteLine("MyRemote.Foo called");
		}

		public int A
		{
			get
			{           
				Console.WriteLine("MyRemote.A called");
				return a;
			}
			set
			{
				a = value;
			}
		}

		protected int a;
	}


	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	public class Hello : System.MarshalByRefObject
	{
		public Hello()
		{
			Console.WriteLine("Constructor called");
		}

		~Hello()
		{
			Console.WriteLine("Destructor called");
		}

		public string Greeting(string name)
		{
			Console.WriteLine("Greeting called");
			return "Hello, " + name;
		}

		public MySerialized GetMySerialized()
		{
			return new MySerialized(4711);   
		}

		public MyRemote GetMyRemote()
		{
			return new MyRemote(4712);
		}

		// directional

		public void InSerialized(MySerialized obj)
		{
			Console.WriteLine("InSerialized called " + obj.A);
			obj.A = 44;
		}

		public void RefSerialized(ref MySerialized refObj)
		{
			Console.WriteLine("RefSerialized called " + refObj.A);
			refObj.A = 55;
		}

		public void OutSerialized(out MySerialized outObj)
		{
			outObj = new MySerialized(66);
			Console.WriteLine("OutSerialized called " + outObj.A);
		}

	}
}

using System;

namespace HeadFirstDesignPatterns.Iterator.Menu
{
	/// <summary>
	/// Summary description for UnSupportedOperationException.
	/// </summary>
	public class UnSupportedOperationException : System.ApplicationException
	{
		private string message;
		public override string Message
		{
			get {return message;}
		}

		public UnSupportedOperationException() : base()
		{}

		public UnSupportedOperationException(string message) : base(message)
		{
			this.message = message;
		}
	}
}

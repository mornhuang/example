using System;

namespace HeadFirstDesignPatterns.Composite.Menu
{
	/// <summary>
	/// Summary description for UnsupportedOperationException.
	/// </summary>
	public class UnsupportedOperationException : Exception
	{
		private string message;
		
			
		public UnsupportedOperationException() : base()
		{}

		public UnsupportedOperationException(string message) : base(message)
		{
			this.message = message;
		}

		public override string Message
		{
			get
			{
				return this.message;
			}
		}
	}
}

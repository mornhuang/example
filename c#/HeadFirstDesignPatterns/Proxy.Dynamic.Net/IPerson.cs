using System;

namespace HeadFirstDesignPatterns.Proxy.Dynamic.Net
{
	/// <summary>
	/// Summary description for IPerson.
	/// </summary>
	public interface IPerson
	{
		string Name{get; set;}
		string Gender{get; set;}
		string Interest{get; set;}
		int HotOrNot{get; set;}
	}
}

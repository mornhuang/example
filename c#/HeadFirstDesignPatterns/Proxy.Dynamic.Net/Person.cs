using System;

namespace HeadFirstDesignPatterns.Proxy.Dynamic.Net
{
	/// <summary>
	/// Summary description for PersonImpl.
	/// </summary>
	public class Person : IPerson
	{
		#region Members
		private string name;
		private string gender;
		private string interest;
		private int rating;
		private int ratingCount = 0;
		#endregion//Members

		#region Constructor
		public Person()
		{}
		#endregion//Constructor

		#region IPerson Members

		#region Name
		public string Name
		{
			get
			{
				return name;
			}
			set
			{
				name = value;
			}
		}
		#endregion//Name

		#region Gender
		public string Gender
		{
			get
			{
				return gender;
			}
			set
			{
				gender = value;
			}
		}
		#endregion//Gender

		#region Interest
		public string Interest
		{
			get
			{
				return interest;
			}
			set
			{
				interest = value;
			}
		}
		#endregion//Interest

		#region HotOrNot
		public int HotOrNot
		{
			get
			{
				if(ratingCount == 0)
				{
					return 0;
				}
				else
				{
					return rating/ratingCount;
				}
			}
			set
			{
				rating += value;
				ratingCount++;
			}
		}
		#endregion//HotOrNot

		#endregion//IPerson Members
	}
}

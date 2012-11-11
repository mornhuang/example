using System;

namespace HeadFirstDesignPatterns.Iterator.Menu
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	public class MenuItem
	{
		string name;
		string description;
		bool isVegetarian;
		double price;

		public string Name
		{
			get { return name; }
			set { name = value; }
		}

		public string Description
		{
			get { return description; }
			set { description = value; }
		}

		public bool IsVegetarian
		{
			get { return isVegetarian; }
			set { isVegetarian = value; }
		}

		public double Price
		{
			get { return price; }
			set { price = value; }
		}

		public MenuItem(string name,
			string description,
			bool isVegetarian,
			double price)
		{
			this.name = name;
			this.description = description;
			this.isVegetarian = isVegetarian;
			this.price = price;
		}
	}
}

using System;
using System.Collections;

namespace HeadFirstDesignPatterns.Composite.Menu
{
	/// <summary>
	/// Summary description for MenuComponent.
	/// </summary>
	public abstract class MenuComponent
	{
		string name;
		string description;
		bool vegetarian;
		double price;

		public virtual string Name
		{
			get {return name;}
			set {name = value;}
		}

		public virtual string Description
		{
			get { return description; }
			set { description = value; }
		}

		public virtual bool IsVegetarian
		{
			get { return vegetarian; }
			set { vegetarian = value; }
		}

		public virtual double Price
		{
			get { return price; }
			set { price = value; }
		}

		public virtual void Add(MenuComponent menuComponent)
		{
			throw new UnsupportedOperationException();
		}

		public virtual void Remove(MenuComponent menuComponent)
		{
			throw new UnsupportedOperationException();
		}

		public virtual MenuComponent GetChild(int i) 
		{
			throw new UnsupportedOperationException();
		}

		public virtual string Print()
		{
			throw new UnsupportedOperationException();
		}

		public virtual ArrayList GetMenu()
		{
			throw new UnsupportedOperationException();
		}

		public virtual int Count()
		{
			throw new UnsupportedOperationException();
		}
	}
}

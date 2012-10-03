using System;
using System.Collections;

namespace HeadFirstDesignPatterns.AbstractFactory.PizzaStore
{
	/// <summary>
	/// Summary description for Pizza.
	/// </summary>
	public abstract class Pizza
	{
		#region Members
		private string name;
		protected IDough dough;
		protected ISauce sauce;
		protected IVeggies[] veggies;
		protected ICheese cheese;
		protected IPepperoni pepperoni;
		protected IClams clam;
		#endregion//Members

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

		#region Constructor
		public Pizza()
		{}
		#endregion//Constructor

		#region Prepare
		public abstract string Prepare();
		#endregion//Prepare
		
		#region Bake
		public virtual string Bake()
		{
			return "Bake for 25 minutes at 350 \n";
		}
		#endregion//Bake

		#region Cut
		public virtual string Cut()
		{
			return "Cutting the pizza into diagonal slices \n";
		}
		#endregion//Cut

		#region Box
		public virtual string Box()
		{
			return "Place pizza in official PizzaStore box \n";
		}
		#endregion//Box
	}
}

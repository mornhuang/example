using System;
using System.Collections;
using System.Text;

namespace HeadFirstDesignPatterns.Factory.PizzaStore
{
	/// <summary>
	/// Summary description for Pizza.
	/// </summary>
	public abstract class Pizza
	{
		#region Members
		protected string name;
		protected string dough;
		protected string sauce;
		protected ArrayList toppings = new ArrayList();
		#endregion//Members

		#region Constructor
		public Pizza()
		{}
		#endregion//Constructor

		#region Prepare
		public string Prepare()
		{
			StringBuilder sb = new StringBuilder();

			sb.Append("Preparing " + name + "\n");
			sb.Append("Tossing " + dough + "\n");
			sb.Append("Adding " + sauce + "\n");
			sb.Append("Adding toppings:" + "\n");

			foreach(string topping in toppings)
			{
				sb.Append("\t" + topping + "\n");
			}

			return sb.ToString();
		}
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

		#region GetName
		public string GetName()
		{
			return name;
		}
		#endregion//GetName
	}
}

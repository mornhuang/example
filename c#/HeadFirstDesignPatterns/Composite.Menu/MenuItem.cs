using System;
using System.Text;
using System.Collections;

namespace HeadFirstDesignPatterns.Composite.Menu
{
	/// <summary>
	/// Summary description for MenuItem.
	/// </summary>
	public class MenuItem : MenuComponent
	{
		#region Members
		string name;
		string description;
		bool vegetarian;
		double price;
		#endregion//Members

		#region MenuItem
		public MenuItem(string name, string description, 
			bool vegetarian, double price)
		{
			this.name = name;
			this.description = description;
			this.vegetarian = vegetarian;
			this.price = price;
		}
		#endregion//MenuItem

		#region Name Property
		public override string Name
		{
			get 
				{ 
					return name;
				}
			set 
				{
					this.name = value;
				}
		}
		#endregion//

		#region Description Property
		public override string Description
		{
			get
			{
				return description;
			}
			set
			{
				this.description = value;
			}
		}
		#endregion//

		#region Price Property
		public override double Price
		{
			get
			{
				return price;
			}
			set
			{
				price = value;
			}
		}
		#endregion//

		#region IsVegetarian Property
		public override bool IsVegetarian
		{
			get
			{
				return vegetarian;
			}
			set
			{
				this.vegetarian = value;
			}
		}
		#endregion//

		#region 
		public override string Print()
		{
			StringBuilder printOutPut = new StringBuilder();
			printOutPut.Append("\t" + Name);
			if(IsVegetarian)
			{
				printOutPut.Append(" (v) ");
			}
			printOutPut.Append(", $" + Price + "\n");
			printOutPut.Append("\t\t--" + Description +"\n");

			return printOutPut.ToString();
		}
		#endregion//

	}
}

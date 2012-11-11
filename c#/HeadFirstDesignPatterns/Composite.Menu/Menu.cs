using System;
using System.Text;
using System.Collections;

namespace HeadFirstDesignPatterns.Composite.Menu
{
	/// <summary>
	/// Summary description for Menu.
	/// </summary>
	public class Menu : MenuComponent
	{
		ArrayList menuComponents = new ArrayList();
		string name;
		string description;

		public Menu(string name, string description)
		{
			this.name = name;
			this.description = description;
		}

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

		public override void Add(MenuComponent menuComponent)
		{
			menuComponents.Add(menuComponent);
		}

		public override void Remove(MenuComponent menuComponent)
		{
			menuComponents.Remove(menuComponent);
		}
        
		public override MenuComponent GetChild(int i)
		{
			return (MenuComponent)menuComponents[i];
		}

		public override ArrayList GetMenu()
		{
			return menuComponents;
		}

		public override int Count()
		{
			return menuComponents.Count;
		}

		public override string Print()
		{
			StringBuilder printOutPut = new StringBuilder();
			printOutPut.Append("\n" + name);
			printOutPut.Append(", " + description + "\n");
			printOutPut.Append("-------------------------\n");
			
			foreach(MenuComponent menuComponent in menuComponents)
			{
				printOutPut.Append(menuComponent.Print());
			}

			return printOutPut.ToString();
		}
	}
}

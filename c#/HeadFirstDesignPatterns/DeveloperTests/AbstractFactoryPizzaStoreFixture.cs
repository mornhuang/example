using System;
using HeadFirstDesignPatterns.AbstractFactory.PizzaStore;
using NUnit.Framework;

namespace HeadFirstDesignPatterns.DeveloperTests.AbstractFactory.PizzaStore
{
	/// <summary>
	/// AbstractFactoryPizzaStoreFixture tests the factory pattern
	/// </summary>
	[TestFixture]
	public class AbstractFactoryPizzaStoreFixture
	{
		#region TestNYStyleCheesePizza
		[Test]
		public void TestNYStyleCheesePizza()
		{
			HeadFirstDesignPatterns.AbstractFactory.PizzaStore.PizzaStore nyStore = 
				new NYPizzaStore();
			
			Pizza pizza = nyStore.OrderPizza("cheese");
			
			string pizzaPrepareReturn = "Preparing New York Style Cheese Pizza\n"+
				"Thin Crust Dough\n" +
				"Marinara Sauce\n" +
				"Reggiano Cheese";
			
			Assert.AreEqual(pizzaPrepareReturn,pizza.Prepare());
			Assert.AreEqual("Bake for 25 minutes at 350 \n",pizza.Bake());
			Assert.AreEqual("Cutting the pizza into diagonal slices \n",pizza.Cut());
			Assert.AreEqual("Place pizza in official PizzaStore box \n",pizza.Box());
			Assert.AreEqual("New York Style Cheese Pizza",pizza.Name);
		}
		#endregion//TestNYStyleCheesePizza

		#region TestNYStyleClamPizza
		[Test]
		public void TestNYStyleClamPizza()
		{
			HeadFirstDesignPatterns.AbstractFactory.PizzaStore.PizzaStore nyStore = 
				new NYPizzaStore();
			
			Pizza pizza = nyStore.OrderPizza("clam");
			
			string pizzaPrepareReturn = "Preparing New York Style Clam Pizza\n"+
				"Thin Crust Dough\n" +
				"Marinara Sauce\n" +
				"Reggiano Cheese\n" +
				"Fresh Clams";
			
			Assert.AreEqual(pizzaPrepareReturn,pizza.Prepare());
			Assert.AreEqual("Bake for 25 minutes at 350 \n",pizza.Bake());
			Assert.AreEqual("Cutting the pizza into diagonal slices \n",pizza.Cut());
			Assert.AreEqual("Place pizza in official PizzaStore box \n",pizza.Box());
			Assert.AreEqual("New York Style Clam Pizza",pizza.Name);
		}
		#endregion//TestNYStyleClamPizza

		#region TestNYStylePepperoniPizza
		[Test]
		public void TestNYStylePepperoniPizza()
		{
			HeadFirstDesignPatterns.AbstractFactory.PizzaStore.PizzaStore nyStore = 
				new NYPizzaStore();
			
			Pizza pizza = nyStore.OrderPizza("pepperoni");
			
			string pizzaPrepareReturn = "Preparing New York Style Pepperoni Pizza\n"+
				"Thin Crust Dough\n" +
				"Marinara Sauce\n" +
				"Reggiano Cheese\n" +
				"Sliced Pepperoni";
			
			Assert.AreEqual(pizzaPrepareReturn,pizza.Prepare());
			Assert.AreEqual("Bake for 25 minutes at 350 \n",pizza.Bake());
			Assert.AreEqual("Cutting the pizza into diagonal slices \n",pizza.Cut());
			Assert.AreEqual("Place pizza in official PizzaStore box \n",pizza.Box());
			Assert.AreEqual("New York Style Pepperoni Pizza",pizza.Name);
		}
		#endregion//TestNYStylePepperoniPizza

		#region TestChicagoStyleCheesePizza
		[Test]
		public void TestChicagoStyleCheesePizza()
		{
			HeadFirstDesignPatterns.AbstractFactory.PizzaStore.PizzaStore chicagoStore = 
				new ChicagoPizzaStore();
			
			Pizza pizza = chicagoStore.OrderPizza("cheese");
			
			string pizzaPrepareReturn = "Preparing Chicago Style Cheese Pizza\n" +
				"Thick Crust Dough\n" +
				"Plum Tomato Sauce\n" +
				"Mozzerella Cheese";

			Assert.AreEqual(pizzaPrepareReturn,pizza.Prepare());
			Assert.AreEqual("Bake for 25 minutes at 350 \n",pizza.Bake());
			Assert.AreEqual("Cutting the pizza into diagonal slices \n",pizza.Cut());
			Assert.AreEqual("Place pizza in official PizzaStore box \n",pizza.Box());
			Assert.AreEqual("Chicago Style Cheese Pizza",pizza.Name);
		}
		#endregion//TestChicagoStyleCheesePizza

		#region TestChicagoStyleClamPizza
		[Test]
		public void TestChicagoStyleClamPizza()
		{
			HeadFirstDesignPatterns.AbstractFactory.PizzaStore.PizzaStore chicagoStore = 
				new ChicagoPizzaStore();
			
			Pizza pizza = chicagoStore.OrderPizza("clam");
			
			string pizzaPrepareReturn = "Preparing Chicago Style Clam Pizza\n" +
				"Thick Crust Dough\n" +
				"Plum Tomato Sauce\n" +
				"Mozzerella Cheese\n" +
				"Frozen Clams";

			Assert.AreEqual(pizzaPrepareReturn,pizza.Prepare());
			Assert.AreEqual("Bake for 25 minutes at 350 \n",pizza.Bake());
			Assert.AreEqual("Cutting the pizza into diagonal slices \n",pizza.Cut());
			Assert.AreEqual("Place pizza in official PizzaStore box \n",pizza.Box());
			Assert.AreEqual("Chicago Style Clam Pizza",pizza.Name);
		}
		#endregion//TestChicagoStyleClamPizza

		#region TestChicagoStylePepperoniPizza
		[Test]
		public void TestChicagoStylePepperoniPizza()
		{
			HeadFirstDesignPatterns.AbstractFactory.PizzaStore.PizzaStore chicagoStore = 
				new ChicagoPizzaStore();
			
			Pizza pizza = chicagoStore.OrderPizza("pepperoni");
			
			string pizzaPrepareReturn = "Preparing Chicago Style Pepperoni Pizza\n" +
				"Thick Crust Dough\n" +
				"Plum Tomato Sauce\n" +
				"Mozzerella Cheese\n" +
				"Sliced Pepperoni";

			Assert.AreEqual(pizzaPrepareReturn,pizza.Prepare());
			Assert.AreEqual("Bake for 25 minutes at 350 \n",pizza.Bake());
			Assert.AreEqual("Cutting the pizza into diagonal slices \n",pizza.Cut());
			Assert.AreEqual("Place pizza in official PizzaStore box \n",pizza.Box());
			Assert.AreEqual("Chicago Style Pepperoni Pizza",pizza.Name);
		}
		#endregion//TestChicagoStylePepperoniPizza
	}
}

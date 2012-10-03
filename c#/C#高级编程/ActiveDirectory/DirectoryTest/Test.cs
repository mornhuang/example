using System;
using System.DirectoryServices;

namespace Wrox.ProCSharp.ActiveDirectory
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	class Test
	{
		public static void RootDSE()
		{
			using (DirectoryEntry de = new DirectoryEntry())
			{												
				de.Path = "LDAP://celticrain/rootDSE";
				de.Username = @"sentinel\chris";
				de.Password = "someSecret";

				PropertyCollection props = de.Properties;

				foreach (string prop in props.PropertyNames)
				{
					PropertyValueCollection values = props[prop];
					foreach (string val in values)
					{
						Console.Write(prop + ": ");
						Console.WriteLine(val);
					}
				}
			}
		}

		public static void ShowUserObject()
		{
			using (DirectoryEntry de = new DirectoryEntry())
			{
				de.Path = "LDAP://celticrain/CN=Christian Nagel, OU=Wrox Press, " + 
					"DC=eichkogelstrasse, DC=local";

				Console.WriteLine("Name: " + de.Name);
				Console.WriteLine("GUID: " + de.Guid);
				Console.WriteLine("Type: " + de.SchemaClassName);
				Console.WriteLine();

				Console.WriteLine("Properties: ");
				PropertyCollection properties = de.Properties;
				foreach (string name in properties.PropertyNames)
				{
					foreach (object o in properties[name])
					{
						Console.WriteLine(name + ": " + o);
					}
				}
			}
		}

		public static void ShowChildren()
		{
			using (DirectoryEntry de = new DirectoryEntry())
			{
				de.Path = "LDAP://celticrain/OU=Wrox Press, " + 
					"DC=eichkogelstrasse, DC=local";
				de.Username = @"sentinel\Administrator";
				de.Password = "someSecret";
      
				// enumerate children
				Console.WriteLine("Children of " + de.Name);
				de.Children.SchemaFilter.Add("user");
				foreach (DirectoryEntry obj in de.Children)
				{
					Console.WriteLine(obj.Name);
				}
			}
		}

		static void AddUser()
		{
			using (DirectoryEntry de = new DirectoryEntry())
			{            
				de.Path = "LDAP://celticrain/OU=Wrox Press, DC=eichkogelstrasse, DC=local";
				// de.Path = "LDAP://celticrain/CN=Users, DC=eichkogelstrasse, DC=local";

				DirectoryEntries users = de.Children;

				DirectoryEntry user = users.Add("CN=John Doe", "user");
				user.Properties["company"].Add("Some Company");
				user.Properties["department"].Add("Sales");
				user.Properties["employeeID"].Add("4711");
				user.Properties["samAccountName"].Add("JDoe");
				user.Properties["userPrincipalName"].Add("JDoe@eichkogelstrasse.local");
				user.Properties["sn"].Add("Doe");
				user.Properties["givenName"].Add("John");
				user.Properties["userPassword"].Add("someSecret");
				user.CommitChanges();
			}
		}

		public static void UpdateUser()
		{
			using (DirectoryEntry de = new DirectoryEntry())
			{
				de.Path = "LDAP://celticrain/CN=Christian Nagel, OU=Wrox Press, " + 
					"DC=eichkogelstrasse, DC=local";
				
				if (de.Properties.Contains("mobile"))
				{
					de.Properties["mobile"][0] = "+43(664)3111111111";
				}
				else
				{
					de.Properties["mobile"].Add("+43(664)3111111111");
				} 
				de.CommitChanges(); 
			}
		}

		static void EnableUser()
		{
			using (DirectoryEntry de = new DirectoryEntry())
			{
				de.Path = "LDAP://celticrain/CN=John Doe, CN=Users, DC=eichkogelstrasse, DC=local";

				de.Invoke("SetPassword", "anotherSecret");

				de.CommitChanges();

				ActiveDs.IADsUser user = (ActiveDs.IADsUser)de.NativeObject;
				user.SetPassword("someSecret");
				user.AccountDisabled = false;

				de.CommitChanges();
			}
		}
	  

		static void SearchUser()
		{
			using (DirectoryEntry de = new DirectoryEntry("LDAP://OU=Wrox PRess, " +
					   "DC=eichkogelstrasse, DC=local"))
			using (DirectorySearcher searcher = new DirectorySearcher())
			{
				searcher.SearchRoot = de;
				searcher.Filter = "(&(objectClass=user)(description=Auth*))";
				searcher.SearchScope = SearchScope.Subtree;

				searcher.PropertiesToLoad.Add("name");
				searcher.PropertiesToLoad.Add("description");
				searcher.PropertiesToLoad.Add("givenName");
				searcher.PropertiesToLoad.Add("wWWHomePage");
            
				searcher.Sort = new SortOption("givenName", SortDirection.Ascending);

				SearchResultCollection results = searcher.FindAll();

				foreach (SearchResult result in results)
				{
					ResultPropertyCollection props = result.Properties;
					foreach (string propName in props.PropertyNames)
					{
						Console.Write(propName + ": ");
						Console.WriteLine(props[propName][0]); 
					}
					Console.WriteLine();
				}
			}
		}

		[STAThread]
		static void Main(string[] args)
		{
			// Example 1 - Distinguished Name
 			// RootDSE();

			// Example 2 - Properties of User Objects
			// ShowUserObject();

			// Example 3 - Object Collection
			// ShowChildren();

			// Example 4 - Create new objects
			// AddUser();

			// Example 5 - Updating Directory Entries
			// UpdateUser();

			// Example 6 - Accessing Native ADSI Objects
			// EnableUser();
   
			// Example 7 - Searching
			SearchUser();
		}
	}
}

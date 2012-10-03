using System;
using System.Data;
using System.Data.SqlClient;
using System.EnterpriseServices;

namespace Wrox.ProCSharp.ComPlus.TransactionTest
{

	[Transaction(TransactionOption.Required)]
	public class TxTest : ServicedComponent
	{
		public TxTest()
		{
		}

 
		public bool OrderProduct(int ProductID, int Units)
		{
			SqlConnection conn = new SqlConnection("Data Source=dougp\\dogdata;" +
				"Initial Catalog=Northwind;User ID=sa;Password=datadog");
			try
			{

				if (!ContextUtil.IsInTransaction)
					throw new Exception("Not in transaction");


				conn.Open();
				SqlCommand cmd = new SqlCommand("UPDATE Products SET UnitsOnOrder = "+
					"UnitsOnOrder + " + Units + 
					" WHERE ProductID = " + ProductID, conn);

				int rowsAffected = cmd.ExecuteNonQuery();
				if (rowsAffected != 1)
					throw new Exception("Invalid number of rows affected");

				cmd = new SqlCommand("UPDATE Products SET UnitsInStock = " + 
					"UnitsInStock - " + Units + 
					" WHERE ProductID = " + ProductID, conn);

				rowsAffected = cmd.ExecuteNonQuery();
				if (rowsAffected != 1)
					throw new Exception("Invalid number of rows affected");

				ContextUtil.SetComplete();
				return true;
			}

			catch (Exception e)
			{
				Console.WriteLine("Transaction aborted with error: {0}", e.Message);
				ContextUtil.SetAbort();
				return false;
			}

			finally
			{
				if (conn.State == ConnectionState.Open)
					conn.Close();
			}
		}
	}
}
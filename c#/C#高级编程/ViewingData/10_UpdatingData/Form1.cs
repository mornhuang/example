using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

namespace UpdatingData
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class datasetUpdate : System.Windows.Forms.Form
	{
		private System.Windows.Forms.DataGrid dataGrid1;
		private System.Windows.Forms.Button retrieveButton;
		private System.Windows.Forms.Button updateButton;
		private System.Data.SqlClient.SqlConnection sqlConnection;
		private System.Data.SqlClient.SqlCommand supplierSelect;
		private System.Data.SqlClient.SqlCommand supplierInsert;
		private System.Data.SqlClient.SqlCommand supplierUpdate;
		private System.Data.SqlClient.SqlCommand supplierDelete;
		private System.Data.SqlClient.SqlDataAdapter supplierDataAdapter;
		private UpdateDataSet.DataSet1 supplierDataSet;
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;

		public datasetUpdate()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();

			//
			// TODO: Add any constructor code after InitializeComponent call
			//
		}

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		protected override void Dispose( bool disposing )
		{
			if( disposing )
			{
				if (components != null) 
				{
					components.Dispose();
				}
			}
			base.Dispose( disposing );
		}

		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.sqlConnection = new System.Data.SqlClient.SqlConnection();
			this.supplierSelect = new System.Data.SqlClient.SqlCommand();
			this.supplierInsert = new System.Data.SqlClient.SqlCommand();
			this.supplierUpdate = new System.Data.SqlClient.SqlCommand();
			this.supplierDelete = new System.Data.SqlClient.SqlCommand();
			this.supplierDataAdapter = new System.Data.SqlClient.SqlDataAdapter();
			this.supplierDataSet = new UpdateDataSet.DataSet1();
			this.dataGrid1 = new System.Windows.Forms.DataGrid();
			this.retrieveButton = new System.Windows.Forms.Button();
			this.updateButton = new System.Windows.Forms.Button();
			((System.ComponentModel.ISupportInitialize)(this.supplierDataSet)).BeginInit();
			((System.ComponentModel.ISupportInitialize)(this.dataGrid1)).BeginInit();
			this.SuspendLayout();
			// 
			// sqlConnection
			// 
			this.sqlConnection.ConnectionString = "data source=(local);initial catalog=Northwind;integrated security=SSPI;persist se" +
				"curity info=False;workstation id=MORGANSK-SERVER;packet size=4096";
			// 
			// supplierSelect
			// 
			this.supplierSelect.CommandText = "SELECT SupplierID, CompanyName, ContactName, ContactTitle, Address, City, Region," +
				" PostalCode, Country, Phone, Fax, HomePage FROM Suppliers";
			this.supplierSelect.Connection = this.sqlConnection;
			// 
			// supplierInsert
			// 
			this.supplierInsert.CommandText = @"INSERT INTO Suppliers(CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax, HomePage) VALUES (@CompanyName, @ContactName, @ContactTitle, @Address, @City, @Region, @PostalCode, @Country, @Phone, @Fax, @HomePage); SELECT SupplierID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax, HomePage FROM Suppliers WHERE (SupplierID = @@IDENTITY)";
			this.supplierInsert.Connection = this.sqlConnection;
			this.supplierInsert.Parameters.Add(new System.Data.SqlClient.SqlParameter("@CompanyName", System.Data.SqlDbType.NVarChar, 40, "CompanyName"));
			this.supplierInsert.Parameters.Add(new System.Data.SqlClient.SqlParameter("@ContactName", System.Data.SqlDbType.NVarChar, 30, "ContactName"));
			this.supplierInsert.Parameters.Add(new System.Data.SqlClient.SqlParameter("@ContactTitle", System.Data.SqlDbType.NVarChar, 30, "ContactTitle"));
			this.supplierInsert.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Address", System.Data.SqlDbType.NVarChar, 60, "Address"));
			this.supplierInsert.Parameters.Add(new System.Data.SqlClient.SqlParameter("@City", System.Data.SqlDbType.NVarChar, 15, "City"));
			this.supplierInsert.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Region", System.Data.SqlDbType.NVarChar, 15, "Region"));
			this.supplierInsert.Parameters.Add(new System.Data.SqlClient.SqlParameter("@PostalCode", System.Data.SqlDbType.NVarChar, 10, "PostalCode"));
			this.supplierInsert.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Country", System.Data.SqlDbType.NVarChar, 15, "Country"));
			this.supplierInsert.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Phone", System.Data.SqlDbType.NVarChar, 24, "Phone"));
			this.supplierInsert.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Fax", System.Data.SqlDbType.NVarChar, 24, "Fax"));
			this.supplierInsert.Parameters.Add(new System.Data.SqlClient.SqlParameter("@HomePage", System.Data.SqlDbType.NVarChar, 1073741823, "HomePage"));
			// 
			// supplierUpdate
			// 
			this.supplierUpdate.CommandText = @"UPDATE Suppliers SET CompanyName = @CompanyName, ContactName = @ContactName, ContactTitle = @ContactTitle, Address = @Address, City = @City, Region = @Region, PostalCode = @PostalCode, Country = @Country, Phone = @Phone, Fax = @Fax, HomePage = @HomePage WHERE (SupplierID = @Original_SupplierID) AND (Address = @Original_Address OR @Original_Address IS NULL AND Address IS NULL) AND (City = @Original_City OR @Original_City IS NULL AND City IS NULL) AND (CompanyName = @Original_CompanyName) AND (ContactName = @Original_ContactName OR @Original_ContactName IS NULL AND ContactName IS NULL) AND (ContactTitle = @Original_ContactTitle OR @Original_ContactTitle IS NULL AND ContactTitle IS NULL) AND (Country = @Original_Country OR @Original_Country IS NULL AND Country IS NULL) AND (Fax = @Original_Fax OR @Original_Fax IS NULL AND Fax IS NULL) AND (Phone = @Original_Phone OR @Original_Phone IS NULL AND Phone IS NULL) AND (PostalCode = @Original_PostalCode OR @Original_PostalCode IS NULL AND PostalCode IS NULL) AND (Region = @Original_Region OR @Original_Region IS NULL AND Region IS NULL); SELECT SupplierID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax, HomePage FROM Suppliers WHERE (SupplierID = @SupplierID)";
			this.supplierUpdate.Connection = this.sqlConnection;
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@CompanyName", System.Data.SqlDbType.NVarChar, 40, "CompanyName"));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@ContactName", System.Data.SqlDbType.NVarChar, 30, "ContactName"));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@ContactTitle", System.Data.SqlDbType.NVarChar, 30, "ContactTitle"));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Address", System.Data.SqlDbType.NVarChar, 60, "Address"));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@City", System.Data.SqlDbType.NVarChar, 15, "City"));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Region", System.Data.SqlDbType.NVarChar, 15, "Region"));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@PostalCode", System.Data.SqlDbType.NVarChar, 10, "PostalCode"));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Country", System.Data.SqlDbType.NVarChar, 15, "Country"));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Phone", System.Data.SqlDbType.NVarChar, 24, "Phone"));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Fax", System.Data.SqlDbType.NVarChar, 24, "Fax"));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@HomePage", System.Data.SqlDbType.NVarChar, 1073741823, "HomePage"));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_SupplierID", System.Data.SqlDbType.Int, 4, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "SupplierID", System.Data.DataRowVersion.Original, null));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_Address", System.Data.SqlDbType.NVarChar, 60, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "Address", System.Data.DataRowVersion.Original, null));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_City", System.Data.SqlDbType.NVarChar, 15, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "City", System.Data.DataRowVersion.Original, null));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_CompanyName", System.Data.SqlDbType.NVarChar, 40, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "CompanyName", System.Data.DataRowVersion.Original, null));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_ContactName", System.Data.SqlDbType.NVarChar, 30, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "ContactName", System.Data.DataRowVersion.Original, null));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_ContactTitle", System.Data.SqlDbType.NVarChar, 30, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "ContactTitle", System.Data.DataRowVersion.Original, null));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_Country", System.Data.SqlDbType.NVarChar, 15, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "Country", System.Data.DataRowVersion.Original, null));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_Fax", System.Data.SqlDbType.NVarChar, 24, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "Fax", System.Data.DataRowVersion.Original, null));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_Phone", System.Data.SqlDbType.NVarChar, 24, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "Phone", System.Data.DataRowVersion.Original, null));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_PostalCode", System.Data.SqlDbType.NVarChar, 10, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "PostalCode", System.Data.DataRowVersion.Original, null));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_Region", System.Data.SqlDbType.NVarChar, 15, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "Region", System.Data.DataRowVersion.Original, null));
			this.supplierUpdate.Parameters.Add(new System.Data.SqlClient.SqlParameter("@SupplierID", System.Data.SqlDbType.Int, 4, "SupplierID"));
			// 
			// supplierDelete
			// 
			this.supplierDelete.CommandText = @"DELETE FROM Suppliers WHERE (SupplierID = @Original_SupplierID) AND (Address = @Original_Address OR @Original_Address IS NULL AND Address IS NULL) AND (City = @Original_City OR @Original_City IS NULL AND City IS NULL) AND (CompanyName = @Original_CompanyName) AND (ContactName = @Original_ContactName OR @Original_ContactName IS NULL AND ContactName IS NULL) AND (ContactTitle = @Original_ContactTitle OR @Original_ContactTitle IS NULL AND ContactTitle IS NULL) AND (Country = @Original_Country OR @Original_Country IS NULL AND Country IS NULL) AND (Fax = @Original_Fax OR @Original_Fax IS NULL AND Fax IS NULL) AND (Phone = @Original_Phone OR @Original_Phone IS NULL AND Phone IS NULL) AND (PostalCode = @Original_PostalCode OR @Original_PostalCode IS NULL AND PostalCode IS NULL) AND (Region = @Original_Region OR @Original_Region IS NULL AND Region IS NULL)";
			this.supplierDelete.Connection = this.sqlConnection;
			this.supplierDelete.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_SupplierID", System.Data.SqlDbType.Int, 4, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "SupplierID", System.Data.DataRowVersion.Original, null));
			this.supplierDelete.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_Address", System.Data.SqlDbType.NVarChar, 60, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "Address", System.Data.DataRowVersion.Original, null));
			this.supplierDelete.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_City", System.Data.SqlDbType.NVarChar, 15, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "City", System.Data.DataRowVersion.Original, null));
			this.supplierDelete.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_CompanyName", System.Data.SqlDbType.NVarChar, 40, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "CompanyName", System.Data.DataRowVersion.Original, null));
			this.supplierDelete.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_ContactName", System.Data.SqlDbType.NVarChar, 30, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "ContactName", System.Data.DataRowVersion.Original, null));
			this.supplierDelete.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_ContactTitle", System.Data.SqlDbType.NVarChar, 30, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "ContactTitle", System.Data.DataRowVersion.Original, null));
			this.supplierDelete.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_Country", System.Data.SqlDbType.NVarChar, 15, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "Country", System.Data.DataRowVersion.Original, null));
			this.supplierDelete.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_Fax", System.Data.SqlDbType.NVarChar, 24, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "Fax", System.Data.DataRowVersion.Original, null));
			this.supplierDelete.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_Phone", System.Data.SqlDbType.NVarChar, 24, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "Phone", System.Data.DataRowVersion.Original, null));
			this.supplierDelete.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_PostalCode", System.Data.SqlDbType.NVarChar, 10, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "PostalCode", System.Data.DataRowVersion.Original, null));
			this.supplierDelete.Parameters.Add(new System.Data.SqlClient.SqlParameter("@Original_Region", System.Data.SqlDbType.NVarChar, 15, System.Data.ParameterDirection.Input, false, ((System.Byte)(0)), ((System.Byte)(0)), "Region", System.Data.DataRowVersion.Original, null));
			// 
			// supplierDataAdapter
			// 
			this.supplierDataAdapter.DeleteCommand = this.supplierDelete;
			this.supplierDataAdapter.InsertCommand = this.supplierInsert;
			this.supplierDataAdapter.SelectCommand = this.supplierSelect;
			this.supplierDataAdapter.TableMappings.AddRange(new System.Data.Common.DataTableMapping[] {
																										  new System.Data.Common.DataTableMapping("Table", "Suppliers", new System.Data.Common.DataColumnMapping[] {
																																																					   new System.Data.Common.DataColumnMapping("SupplierID", "SupplierID"),
																																																					   new System.Data.Common.DataColumnMapping("CompanyName", "CompanyName"),
																																																					   new System.Data.Common.DataColumnMapping("ContactName", "ContactName"),
																																																					   new System.Data.Common.DataColumnMapping("ContactTitle", "ContactTitle"),
																																																					   new System.Data.Common.DataColumnMapping("Address", "Address"),
																																																					   new System.Data.Common.DataColumnMapping("City", "City"),
																																																					   new System.Data.Common.DataColumnMapping("Region", "Region"),
																																																					   new System.Data.Common.DataColumnMapping("PostalCode", "PostalCode"),
																																																					   new System.Data.Common.DataColumnMapping("Country", "Country"),
																																																					   new System.Data.Common.DataColumnMapping("Phone", "Phone"),
																																																					   new System.Data.Common.DataColumnMapping("Fax", "Fax"),
																																																					   new System.Data.Common.DataColumnMapping("HomePage", "HomePage")})});
			this.supplierDataAdapter.UpdateCommand = this.supplierUpdate;
			// 
			// supplierDataSet
			// 
			this.supplierDataSet.DataSetName = "SupplierDataSet";
			this.supplierDataSet.Locale = new System.Globalization.CultureInfo("en-GB");
			this.supplierDataSet.Namespace = "http://www.tempuri.org/DataSet1.xsd";
			// 
			// dataGrid1
			// 
			this.dataGrid1.Anchor = (((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
				| System.Windows.Forms.AnchorStyles.Left) 
				| System.Windows.Forms.AnchorStyles.Right);
			this.dataGrid1.DataMember = "";
			this.dataGrid1.HeaderForeColor = System.Drawing.SystemColors.ControlText;
			this.dataGrid1.Location = new System.Drawing.Point(8, 8);
			this.dataGrid1.Name = "dataGrid1";
			this.dataGrid1.Size = new System.Drawing.Size(416, 336);
			this.dataGrid1.TabIndex = 0;
			// 
			// retrieveButton
			// 
			this.retrieveButton.Anchor = (System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right);
			this.retrieveButton.Location = new System.Drawing.Point(248, 352);
			this.retrieveButton.Name = "retrieveButton";
			this.retrieveButton.Size = new System.Drawing.Size(80, 23);
			this.retrieveButton.TabIndex = 1;
			this.retrieveButton.Text = "&Retrieve";
			this.retrieveButton.Click += new System.EventHandler(this.retrieveButton_Click);
			// 
			// updateButton
			// 
			this.updateButton.Anchor = (System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right);
			this.updateButton.Location = new System.Drawing.Point(344, 352);
			this.updateButton.Name = "updateButton";
			this.updateButton.Size = new System.Drawing.Size(80, 23);
			this.updateButton.TabIndex = 2;
			this.updateButton.Text = "&Update";
			this.updateButton.Click += new System.EventHandler(this.updateButton_Click);
			// 
			// datasetUpdate
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(432, 379);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.updateButton,
																		  this.retrieveButton,
																		  this.dataGrid1});
			this.Name = "datasetUpdate";
			this.Text = "10_UpdatingData";
			((System.ComponentModel.ISupportInitialize)(this.supplierDataSet)).EndInit();
			((System.ComponentModel.ISupportInitialize)(this.dataGrid1)).EndInit();
			this.ResumeLayout(false);

		}
		#endregion

		/// <summary>
		/// The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main() 
		{
			Application.Run(new datasetUpdate());
		}

		/// <summary>
		/// This method is called to retrieve data from the database, and populate the data grid
		/// </summary>
		/// <param name="sender"></param>
		/// <param name="e"></param>
		private void retrieveButton_Click(object sender, System.EventArgs e)
		{
			// Fill the data adapter from the database
			supplierDataAdapter.Fill ( supplierDataSet , "Supplier" ) ;

			// And display the data in the data grid...
			dataGrid1.SetDataBinding ( supplierDataSet , "Supplier" ) ;

			// And disable the retrieve button...
			retrieveButton.Enabled = false ;
		}

		/// <summary>
		/// Called when the user clicks the Update button - persist all changes to the database
		/// </summary>
		/// <param name="sender"></param>
		/// <param name="e"></param>
		private void updateButton_Click(object sender, System.EventArgs e)
		{
			// Update the database
			int modified = supplierDataAdapter.Update ( supplierDataSet , "Supplier" ) ;

			if ( modified > 0 )
				MessageBox.Show ( string.Format ( "Modified {0} rows" , modified ) ) ;
		}
	}
}

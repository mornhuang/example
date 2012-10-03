--
-- Create a procedure to insert a new category
--
CREATE PROCEDURE CategoryInsert ( @CategoryName NVARCHAR(15),
                                  @Description NTEXT,
                                  @CategoryID INTEGER OUTPUT ) AS
  SET NOCOUNT OFF;

  INSERT INTO Categories (CategoryName,Description)
    VALUES(@CategoryName, @Description);

  SELECT @CategoryID = @@IDENTITY ;
GO

--
-- Create the audit log table
--
CREATE TABLE CategoryAudit
  (
    AuditID int NOT NULL IDENTITY (1, 1),
    CategoryID int NOT NULL,
    OldName NVARCHAR(15) NULL,
    NewName NVARCHAR(15) NOT NULL
  )
GO

-- 
-- And add a primary key
--
ALTER TABLE CategoryAudit
  ADD CONSTRAINT PK_CategoryAudit 
    PRIMARY KEY ( AuditID )
GO

-- 
-- And add a foreign key
--
ALTER TABLE CategoryAudit
  ADD CONSTRAINT FK_CategoryAudit_Category
    FOREIGN KEY ( CategoryID )
      REFERENCES Categories ( CategoryID )
GO

--
-- Create a trigger to insert changes into the audit table
--
CREATE TRIGGER CategoryInsertTrigger
  ON Categories
  AFTER INSERT
AS
  INSERT INTO CategoryAudit ( CategoryID , NewName )
    SELECT CategoryID, CategoryName
      FROM Inserted ;
GO

CREATE TRIGGER CategoryUpdateTrigger
  ON Categories
  AFTER UPDATE
AS
  INSERT INTO CategoryAudit ( CategoryID , OldName , NewName )
    SELECT old.CategoryID, old.CategoryName, new.CategoryName
      FROM Deleted AS old,
           Categories AS new
      WHERE old.CategoryID = new.CategoryID ;
GO

-- 
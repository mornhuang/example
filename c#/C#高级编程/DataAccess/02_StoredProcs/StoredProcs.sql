--
-- Procedure which inserts a region record and returns the key
--
CREATE PROCEDURE RegionInsert(@RegionDescription NCHAR(50),
                              @RegionID INTEGER OUTPUT)AS
  SET NOCOUNT OFF;

  SELECT @RegionID = MAX ( RegionID ) + 1
                       FROM Region ;
     
  INSERT INTO Region(RegionID, RegionDescription)
    VALUES(@RegionID, @RegionDescription);
GO

--
-- Procedure to update the description of a region
--
CREATE PROCEDURE RegionUpdate(@RegionID INTEGER,
                              @RegionDescription NCHAR(50))AS
  SET NOCOUNT OFF;
  
  UPDATE Region
    SET RegionDescription = @RegionDescription
    WHERE RegionID = @RegionID;
GO

--
-- Procedure to delete a region
--
CREATE PROCEDURE RegionDelete (@RegionID INTEGER) AS
  SET NOCOUNT OFF;
   
  DELETE FROM Region
    WHERE RegionID = @RegionID;
GO


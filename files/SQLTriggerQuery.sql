CREATE TRIGGER UpdateIncomeDate
ON Income_Details
AFTER UPDATE
AS
BEGIN
    UPDATE Income_Details
    SET income_date = GETDATE()
    WHERE Income_Details.income_id = (SELECT income_id FROM inserted)
END
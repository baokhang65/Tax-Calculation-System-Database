CREATE TABLE Staffs (
    [Staff_id] BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    address VARCHAR(255) NOT NULL,
    resident VARCHAR(50) NOT NULL,
    area_code INT NOT NULL CHECK (area_code > 0), -- Ensure area code is positive
    date_of_birth DATE NOT NULL CHECK (date_of_birth < GETDATE()), -- Ensure date of birth is in the past
    phone VARCHAR(11) NOT NULL UNIQUE CHECK (LEN(phone) <= 11) -- Ensure phone number is exactly 11 digits
);
GO

-- Create Users table
CREATE TABLE Users (
	Staff_id BIGINT NOT NULL,
	username VARCHAR(100) NOT NULL UNIQUE,
	password VARCHAR(100) NOT NULL CHECK (LEN(password) >= 8), -- Ensure password length is at least 8 characters
	FOREIGN KEY (Staff_id) REFERENCES Staffs
)

-- Create Deductions table
CREATE TABLE Deductions (
    deduction_id BIGINT PRIMARY KEY,
    deduction_type VARCHAR(100) NOT NULL,
	deduction_amount BigiNT,
);
GO

-- Create Deduction_Staffs table
CREATE TABLE Deduction_For_Family_Circumstance_Staffs (
    deduction_id BIGINT NOT NULL,
    [Staff_id] BIGINT NOT NULL,
	Number_of_times_deducted INT NOT NULL,
    FOREIGN KEY (deduction_id) REFERENCES Deductions(deduction_id),
    FOREIGN KEY ([Staff_id]) REFERENCES Staffs(staff_id),
    PRIMARY KEY (deduction_id, [Staff_id])
);
GO

-- Create Income_Details table
CREATE TABLE Income_Details (
    income_id BIGINT PRIMARY KEY,
    [Staff_id] BIGINT NOT NULL,
    income_type VARCHAR(100) NOT NULL,
    amount DECIMAL(19, 2) NOT NULL CHECK (amount >= 0), -- Ensure income amount is non-negative
    income_date DATE NOT NULL,
    FOREIGN KEY ([Staff_id]) REFERENCES Staffs 
);
GO

-- Create Tax_Calculations table
CREATE TABLE Tax_Calculations (
    calc_id BIGINT PRIMARY KEY,
    [Staff_id] BIGINT NOT NULL,
    total_tax DECIMAL(19, 2) CHECK (total_tax >= 0), -- Ensure total tax is non-negative
    FOREIGN KEY ([Staff_id]) REFERENCES Staffs,
);
GO



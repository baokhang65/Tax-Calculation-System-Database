INSERT INTO Staffs ([Staff_id], name, email, address, resident, area_code, date_of_birth, phone)
VALUES
(1, 'Nguyen Van A', 'nguyenvana@gmail.com', '123 Le Loi, HCM', 'Vietnam', 700000, '1985-05-20', '0912345678'),
(2, 'Tran Thi B', 'tranthib@gmail.com', '456 Nguyen Trai, HCM', 'Vietnam', 700000, '1990-10-15', '0987654321'),
(3, 'Pham Van C', 'phamvanc@gmail.com', '789 Le Duan, HCM', 'Vietnam', 700000, '1988-07-12', '0901234567'),
(4, 'Le Thi D', 'lethid@gmail.com', '321 Dong Khoi, HCM', 'Vietnam', 700000, '1992-09-09', '0912345689'),
(5, 'Tran Van E', 'tranvane@gmail.com', '654 Cach Mang Thang Tam, HCM', 'Vietnam', 700000, '1987-11-20', '0934567890'),
(6, 'Nguyen Thi F', 'nguyenthif@gmail.com', '987 Nguyen Hue, HCM', 'Vietnam', 700000, '1995-12-05', '0945678901'),
(7, 'Pham Van G', 'phamvang@gmail.com', '543 Tran Hung Dao, HCM', 'Vietnam', 700000, '1983-04-14', '0956789012'),
(8, 'Le Van H', 'levanh@gmail.com', '876 Hai Ba Trung, HCM', 'Vietnam', 700000, '1991-03-17', '0967890123'),
(9, 'Tran Thi I', 'tranthii@gmail.com', '210 Vo Van Kiet, HCM', 'Vietnam', 700000, '1989-06-22', '0978901234'),
(10, 'Nguyen Van J', 'nguyenvanj@gmail.com', '432 Pasteur, HCM', 'Vietnam', 700000, '1986-08-25', '0989012345');

INSERT INTO Users (Staff_id, username, password)
VALUES
(1, 'A', 'password123'),
(2, 'B', 'password567')


INSERT INTO Deductions (deduction_id, deduction_type, deduction_amount)
VALUES
(1, 'Personal Deduction', 11000000),
(2, 'Dependent Deduction', 4400000 );

INSERT INTO Deduction_For_Family_Circumstance_Staffs ([Staff_id], deduction_id, Number_of_times_deducted)
VALUES
(1, 1, 1),
(1, 2, 1),
(2, 1, 1),
(2, 2, 2),
(3, 1, 1),
(3, 2, 0),
(4, 1, 1),
(4, 2, 1),
(5, 1, 1),
(5, 2, 2),
(6, 1, 1),
(6, 2, 1),
(7, 1, 1),
(7, 2, 1),
(8, 1, 1),
(8, 2, 2),
(9, 1, 1),
(9, 2, 1),
(10, 1, 1),
(10, 2, 0);

INSERT INTO Income_Details (income_id, [Staff_id], income_type, amount, income_date)
VALUES
(1, 1, 'Salary', 30000000,'2024-10-01'),
(2, 2, 'Salary', 50000000,'2024-10-01'),
(3, 3, 'Salary', 380000000,'2024-10-01'),
(4, 4, 'Salary', 45000000,'2024-10-01'),
(5, 5, 'Salary', 35000000,'2024-10-01'),
(6, 6, 'Salary', 40000000,'2024-10-01'),
(7, 7, 'Salary', 25000000,'2024-10-01'),
(8, 8, 'Salary', 60000000,'2024-10-01'),
(9, 9, 'Salary', 25000000,'2024-10-01'),
(10, 10, 'Salary', 70000000,'2024-10-01');


INSERT INTO Tax_Calculations (calc_id, [Staff_id], total_tax)
VALUES
(1, 1, null),
(2, 2, null),
(3, 3, null),
(4, 4, null),
(5, 5, null),
(6, 6, null),
(7, 7, null),
(8, 8, null),
(9, 9, null),
(10, 10, null);

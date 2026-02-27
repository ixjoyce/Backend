use cart_service;


CREATE TABLE Users (
    UserID INT PRIMARY KEY IDENTITY(1,1),
    UserName NVARCHAR(100),
    AccountStatus NVARCHAR(20) CHECK (AccountStatus IN ('Active', 'Blocked', 'Inactive')),
    CreatedAt DATETIME DEFAULT GETDATE()
);

CREATE TABLE Restaurants (
    RestaurantID INT PRIMARY KEY IDENTITY(1,1),
    Name NVARCHAR(100),
    IsOpen BIT DEFAULT 1,
    UpdatedAt DATETIME DEFAULT GETDATE()
);

CREATE TABLE Items (
    ItemID INT PRIMARY KEY IDENTITY(1,1),
    RestaurantID INT FOREIGN KEY REFERENCES Restaurants(RestaurantID),
    ItemName NVARCHAR(100),
    CurrentPrice DECIMAL(10, 2),
    IsAvailable BIT DEFAULT 1
);

CREATE TABLE Carts (
    CartID INT PRIMARY KEY IDENTITY(1,1),
    UserID INT UNIQUE FOREIGN KEY REFERENCES Users(UserID), 
    RestaurantID INT FOREIGN KEY REFERENCES Restaurants(RestaurantID),
    CreatedAt DATETIME DEFAULT GETDATE(),
    UpdatedAt DATETIME DEFAULT GETDATE(),
    IsActive BIT DEFAULT 1,
    TaxAmount DECIMAL(10, 2) DEFAULT 0.0,
    DeliveryFee DECIMAL(10, 2) DEFAULT 0.0
);

CREATE TABLE CartItems (
    CartItemID INT PRIMARY KEY IDENTITY(1,1),
    CartID INT FOREIGN KEY REFERENCES Carts(CartID) ON DELETE CASCADE,
    ItemID INT FOREIGN KEY REFERENCES Items(ItemID),
    Quantity INT CHECK (Quantity > 0), 
    PriceAtAddition DECIMAL(10, 2),
    AddedAt DATETIME DEFAULT GETDATE()
);



INSERT INTO Users (UserName, AccountStatus) VALUES 
('Alice Smith', 'Active'),    
('Bob Jones', 'Active'),     
('Charlie Brown', 'Blocked'), 
('David Miller', 'Active'),   
('Eve Wilson', 'Active'),     
('Frank Castle', 'Inactive'), 
('Grace Hopper', 'Active'),   
('Heidi Klum', 'Active');     


INSERT INTO Restaurants (Name, IsOpen) VALUES 
('Pizza Palace', 1),   
('Burger Barn', 1),    
('Sushi Zen', 1),     
('Taco Town', 0),      
('Pasta Prime', 1),    
('Curry House', 1),    
('Wok Express', 1),    
('Dessert Den', 0);    


INSERT INTO Items (RestaurantID, ItemName, CurrentPrice, IsAvailable) VALUES 
(1, 'Margherita Pizza', 12.00, 1),
(1, 'Pepperoni Pizza', 15.00, 1),
(2, 'Cheeseburger', 9.00, 1),
(3, 'California Roll', 11.00, 1),
(5, 'Fettuccine Alfredo', 14.00, 1),
(6, 'Chicken Tikka', 13.00, 1),
(7, 'Veggie Noodles', 10.00, 0), 
(1, 'Garlic Bread', 5.00, 1);

INSERT INTO Carts (UserID, RestaurantID, UpdatedAt, IsActive) VALUES 
(1, 1, GETDATE(), 1),                
(2, 2, DATEADD(MINUTE, -45, GETDATE()), 1), 
(4, 3, GETDATE(), 1),               
(5, 5, GETDATE(), 1),                
(7, 6, GETDATE(), 1),               
(8, 1, GETDATE(), 1),                
(3, 1, GETDATE(), 0),                
(6, 2, GETDATE(), 0);               

INSERT INTO CartItems (CartID, ItemID, Quantity, PriceAtAddition) VALUES 
(1, 1, 2, 12.00),
(1, 2, 1, 15.00), 
(2, 3, 1, 9.00),  
(3, 4, 3, 11.00),
(4, 5, 1, 14.00), 
(5, 6, 2, 13.00), 
(6, 1, 1, 12.00), 
(1, 8, 1, 5.00);  

CREATE PROCEDURE AddItemToCart 
    @UserID INT, 
    @ItemID INT, 
    @Qty INT
AS
BEGIN
    DECLARE @ItemRestaurantID INT, @CurrentCartRestaurantID INT, @UserStatus NVARCHAR(20), @RestOpen BIT;

    -- 1. Check User Status (Constraint: No cart if blocked/inactive)
    SELECT @UserStatus = AccountStatus FROM Users WHERE UserID = @UserID;
    IF @UserStatus <> 'Active' THROW 50001, 'User account is blocked or inactive.', 1;

    -- 2. Check Restaurant Status (Constraint: Restaurant must be open)
    SELECT @ItemRestaurantID = RestaurantID FROM Items WHERE ItemID = @ItemID;
    SELECT @RestOpen = IsOpen FROM Restaurants WHERE RestaurantID = @ItemRestaurantID;
    IF @RestOpen = 0 THROW 50002, 'Restaurant is currently closed.', 1;

    -- 3. Check for Existing Cart and Restaurant Match
    SELECT @CurrentCartRestaurantID = RestaurantID FROM Carts WHERE UserID = @UserID;

    -- Rule: If different restaurant, clear cart
    IF @CurrentCartRestaurantID IS NOT NULL AND @CurrentCartRestaurantID <> @ItemRestaurantID
    BEGIN
        DELETE FROM CartItems WHERE CartID = (SELECT CartID FROM Carts WHERE UserID = @UserID);
        UPDATE Carts SET RestaurantID = @ItemRestaurantID, UpdatedAt = GETDATE() WHERE UserID = @UserID;
    END
    
    -- Create Cart if doesn't exist
    IF NOT EXISTS (SELECT 1 FROM Carts WHERE UserID = @UserID)
        INSERT INTO Carts (UserID, RestaurantID) VALUES (@UserID, @ItemRestaurantID);

    -- 4. Insert or Update Item
    DECLARE @CID INT = (SELECT CartID FROM Carts WHERE UserID = @UserID);
    IF EXISTS (SELECT 1 FROM CartItems WHERE CartID = @CID AND ItemID = @ItemID)
        UPDATE CartItems SET Quantity = Quantity + @Qty WHERE CartID = @CID AND ItemID = @ItemID;
    ELSE
        INSERT INTO CartItems (CartID, ItemID, Quantity, PriceAtAddition)
        SELECT @CID, @ItemID, @Qty, CurrentPrice FROM Items WHERE ItemID = @ItemID;

    -- Update Cart Timestamp
    UPDATE Carts SET UpdatedAt = GETDATE() WHERE CartID = @CID;
END



SELECT * FROM Users;
SELECT * FROM Restaurants;
SELECT * FROM Items;
SELECT * FROM Carts;
SELECT * FROM CartItems;


UPDATE CartItems
SET Quantity = 5
WHERE CartID = 1 AND ItemID = 1;


SELECT 
    c.UserID, 
    u.UserName,
    SUM(ci.Quantity * i.CurrentPrice) AS Subtotal,
    c.TaxAmount,
    c.DeliveryFee,
    (SUM(ci.Quantity * i.CurrentPrice) + c.TaxAmount + c.DeliveryFee) AS FinalPayableAmount
FROM Carts c
JOIN Users u ON c.UserID = u.UserID
JOIN CartItems ci ON c.CartID = ci.CartID
JOIN Items i ON ci.ItemID = i.ItemID
WHERE c.UserID = 1 AND c.IsActive = 1
GROUP BY c.UserID, u.UserName, c.TaxAmount, c.DeliveryFee;

SELECT u.UserID, u.UserName, c.CartID, c.CreatedAt
FROM Users u
INNER JOIN Carts c ON u.UserID = c.UserID
WHERE c.IsActive = 1;

SELECT 
    CartID, 
    UserID, 
    UpdatedAt, 
    DATEDIFF(MINUTE, UpdatedAt, GETDATE()) AS MinutesInactive
FROM Carts
WHERE IsActive = 1 
  AND UpdatedAt < DATEADD(MINUTE, -30, GETDATE());


SELECT 
    ci.CartID, 
    COUNT(DISTINCT i.RestaurantID) AS RestaurantCount
FROM CartItems ci
JOIN Items i ON ci.ItemID = i.ItemID
GROUP BY ci.CartID
HAVING COUNT(DISTINCT i.RestaurantID) > 1;


SELECT 
    c.CartID,
    CASE 
        WHEN NOT EXISTS (SELECT 1 FROM CartItems WHERE CartID = c.CartID) THEN 'Empty Cart'
        WHEN r.IsOpen = 0 THEN 'Restaurant Closed'
        WHEN c.UpdatedAt < DATEADD(MINUTE, -30, GETDATE()) THEN 'Cart Expired'
        ELSE 'Ready for Checkout'
    END AS ValidationStatus
FROM Carts c
JOIN Restaurants r ON c.RestaurantID = r.RestaurantID
WHERE c.UserID = 1; 



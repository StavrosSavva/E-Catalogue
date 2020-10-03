DROP TABLE Restaurants;


CREATE TABLE Restaurants (
    VenueID varchar(255) NOT NULL PRIMARY KEY,
    Country varchar(14),
    City varchar(14),
    Name varchar(255),
    Openhour TIME,
    Closehour TIME,
    Logo BLOB
);


DROP TABLE Items;
CREATE TABLE Items (
    ItemID INTEGER  PRIMARY KEY AUTOINCREMENT,
    Name varchar(255),
    Description varchar(255),
    Photo BLOB,
    Visible bit,
    VenueID varchar(255),
    Type varchar(255),
    Price REAL,
    SecondType varchar(255),
    FOREIGN KEY (VenueID) REFERENCES Restaurants(VenueID)

);

DROP TABLE CustomerOrders;
CREATE TABLE CustomerOrders (
    OrderID INTEGER PRIMARY KEY AUTOINCREMENT,
    TableNo INTEGER,
    VenueID varchar(255),
    TelNo INTEGER,


);

DROP TABLE Orders;
CREATE TABLE Orders (
    OrderID INTEGER,
    ItemID  INTEGER,
    Description varchar(255),
    Quantity varchar(255)
    PRIMARY KEY(OrderID,ItemID),
    FOREIGN KEY (OrderID) REFERENCES CustomerOrders(OrderID),
    FOREIGN KEY (ItemID) REFERENCES Items(ItemID)

);

CREATE TABLE Restaurant(RestaurantID INT PRIMARY KEY, Name TEXT, Zip INT, State TEXT, City TEXT, Street TEXT, Latitude INT, Longitude INT, Delivery NUMERIC, Phone TEXT, Website TEXT, Email TEXT, ClosingTime INT);

CREATE TABLE Items(ItemID INT PRIMARY KEY, Name TEXT, Price REAL, Description TEXT);

CREATE TABLE Serves(ItemID INT REFERENCES Item(ItemID), RestaurantID INT REFERENCES Restaurant(RestaurantID), PRIMARY KEY(ItemID, RestaurantID));

CREATE TABLE Genre(GenreID INT PRIMARY KEY, RestaurantID INT NOT NULL REFERENCES Restaurant(RestaurantID), Name TEXT, Description TEXT);

CREATE TABLE User(Username TEXT PRIMARY KEY, Password TEXT NOT NULL, Zip INT, State TEXT, City TEXT, Street TEXT, Latitude INT, Longitude INT);

CREATE TABLE Review(RestaurantID INT REFERENCES Restaurant(RestaurantID) NOT NULL, Username TEXT REFERENCES User(Username) NOT NULL, Comments TEXT, Rating INT, PRIMARY KEY(RestaurantID, Username));

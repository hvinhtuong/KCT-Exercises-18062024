CREATE TABLE Account (
    id INTEGER PRIMARY KEY,
    user_name NVARCHAR2(100) NOT NULL,
    password NVARCHAR2(100) NOT NULL,
    role INTEGER,
    status INTEGER
);

CREATE TABLE Warehouses (
    id INTEGER PRIMARY KEY,
    user_id INTEGER unique,
    name NVARCHAR2(100) NOT NULL,
    category INTEGER,
    FOREIGN KEY (user_id) REFERENCES Account(id)
);

CREATE TABLE Item (
    id INTEGER PRIMARY KEY,
    warehouse_id INTEGER,
    name NVARCHAR2(100) NOT NULL,
    detail NVARCHAR2(100) NOT NULL,
    price NUMBER(10,2),
    amount INTEGER,
    FOREIGN KEY (warehouse_id) REFERENCES Warehouses(id)
);

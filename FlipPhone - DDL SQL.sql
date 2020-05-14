
Generate SQL
CREATE TABLE Client
(
  client_id INT NOT NULL,
  client_name INT NOT NULL,
  client_email INT NOT NULL,
  client_password INT NOT NULL,
  address INT NOT NULL,
  nif INT NOT NULL,
  PRIMARY KEY (client_id)
);

CREATE TABLE Order
(
  date INT NOT NULL,
  order_id INT NOT NULL,
  total INT NOT NULL,
  client_id INT NOT NULL,
  PRIMARY KEY (order_id),
  FOREIGN KEY (client_id) REFERENCES Client(client_id)
);

CREATE TABLE Product
(
  product_id INT NOT NULL,
  cpu_gpu INT NOT NULL,
  ram_rom INT NOT NULL,
  image INT NOT NULL,
  sreen_size INT NOT NULL,
  screen_type INT NOT NULL,
  battery INT NOT NULL,
  OS INT NOT NULL,
  selfie_cam INT NOT NULL,
  camera INT NOT NULL,
  product_name INT NOT NULL,
  PRIMARY KEY (product_id)
);

CREATE TABLE Seller
(
  seller_id INT NOT NULL,
  seller_name INT NOT NULL,
  seller_email INT NOT NULL,
  seller_password INT NOT NULL,
  seller_type INT NOT NULL,
  PRIMARY KEY (seller_id)
);

CREATE TABLE Item
(
  item_id INT NOT NULL,
  grade INT NOT NULL,
  color INT NOT NULL,
  price INT NOT NULL,
  seller_id INT NOT NULL,
  product_id INT NOT NULL,
  order_id INT,
  PRIMARY KEY (item_id),
  FOREIGN KEY (seller_id) REFERENCES Seller(seller_id),
  FOREIGN KEY (product_id) REFERENCES Product(product_id),
  FOREIGN KEY (order_id) REFERENCES Order(order_id)
);

CREATE TABLE Payment
(
  payment_id INT NOT NULL,
  status INT NOT NULL,
  gateway INT NOT NULL,
  date INT NOT NULL,
  client_id INT NOT NULL,
  seller_id INT NOT NULL,
  order_id INT NOT NULL,
  PRIMARY KEY (payment_id),
  FOREIGN KEY (client_id) REFERENCES Client(client_id),
  FOREIGN KEY (seller_id) REFERENCES Seller(seller_id),
  FOREIGN KEY (order_id) REFERENCES Order(order_id)
);
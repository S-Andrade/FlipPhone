CREATE TABLE User
(
  user_password INT NOT NULL,
  user_name INT NOT NULL,
  user_salt INT NOT NULL,
  user_email INT NOT NULL,
  user_address INT NOT NULL,
  user_nif INT NOT NULL,
  user_id INT NOT NULL,
  user_type INT NOT NULL,
  PRIMARY KEY (user_id)
);

CREATE TABLE Admin
(
  admin_id INT NOT NULL,
  admin_password INT NOT NULL,
  admin_hash INT NOT NULL,
  admin_email INT NOT NULL,
  PRIMARY KEY (admin_id)
);

CREATE TABLE Order
(
  date INT NOT NULL,
  order_id INT NOT NULL,
  total INT NOT NULL,
  user_id INT NOT NULL,
  PRIMARY KEY (order_id),
  FOREIGN KEY (user_id) REFERENCES User(user_id)
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
  admin_id INT NOT NULL,
  PRIMARY KEY (product_id),
  FOREIGN KEY (admin_id) REFERENCES Admin(admin_id)
);

CREATE TABLE Payment
(
  payment_id INT NOT NULL,
  status INT NOT NULL,
  gateway INT NOT NULL,
  date INT NOT NULL,
  order_id INT NOT NULL,
  user_id INT NOT NULL,
  recievesuser_id INT NOT NULL,
  PRIMARY KEY (payment_id),
  FOREIGN KEY (order_id) REFERENCES Order(order_id),
  FOREIGN KEY (user_id) REFERENCES User(user_id),
  FOREIGN KEY (recievesuser_id) REFERENCES User(user_id)
);

CREATE TABLE Item
(
  item_id INT NOT NULL,
  grade INT NOT NULL,
  color INT NOT NULL,
  price INT NOT NULL,
  version INT NOT NULL,
  product_id INT NOT NULL,
  order_id INT,
  user_id INT NOT NULL,
  PRIMARY KEY (item_id),
  FOREIGN KEY (product_id) REFERENCES Product(product_id),
  FOREIGN KEY (order_id) REFERENCES Order(order_id),
  FOREIGN KEY (user_id) REFERENCES User(user_id)
);
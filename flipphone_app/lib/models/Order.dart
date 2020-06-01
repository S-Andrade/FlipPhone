class Order {
  final int orderID;
  final String date;
  final double total;
  final int userID;

  Order({
    this.orderID,
    this.date,
    this.total,
    this.userID,
  });

  factory Order.fromJson(Map<String, dynamic> json) {
    return Order(
      orderID: json['order_id'],
      date: json['date'],
      total: json['total'],
      userID: json['user_id'],
    );
  }
}

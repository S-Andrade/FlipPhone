import 'package:flipphoneapp/models/User.dart';

class Order {
  final int orderID;
  final String date;
  final double total;
  final User clientID;

  Order({
    this.orderID,
    this.date,
    this.total,
    this.clientID,
  });

  factory Order.fromJson(Map<String, dynamic> json) {
    return Order(
      orderID: json['order_id'] as int,
      date: json['date'],
      total: json['total'] as double,
      clientID: User.fromJson(json['client_id']),

    );
  }
}

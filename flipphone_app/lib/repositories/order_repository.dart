import 'dart:async';
import 'package:flipphoneapp/models/Order.dart';
import 'package:meta/meta.dart';
import 'order_api_client.dart';

class OrderRepository {
  final OrderAPIClient orderAPIClient;

  OrderRepository({@required this.orderAPIClient})
      : assert(orderAPIClient != null);

  Future<List<Order>> fetchOrders() async {
    return await orderAPIClient.fetchOrders();
  }
}

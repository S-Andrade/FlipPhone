import 'dart:convert';
import 'package:flipphoneapp/models/Order.dart';
import 'package:http/http.dart' as http;
import 'package:meta/meta.dart';

class OrderAPIClient {
  final _baseUrl = 'http://192.168.160.49:8080';

  Future<List<Order>> fetchOrders() async {
    final url = '$_baseUrl/product/all';
    final response = await http.get(url);

    if (response.statusCode == 200) {
      List responseJson = json.decode(response.body);
      return responseJson
          .map((order) => new Order.fromJson(order))
          .toList();
    } else {
      throw Exception('Failed to load orders from API');
    }
  }
}

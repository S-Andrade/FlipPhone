import 'dart:convert';
import 'package:flipphoneapp/models/Order.dart';
import 'package:http/http.dart' as http;
import 'package:meta/meta.dart';

class OrderAPIClient {
  final _baseUrl = '192.168.160.49:8080';
  final http.Client httpClient;

  OrderAPIClient({
    @required this.httpClient,
  }) : assert(httpClient != null);

  Future<List<Order>> fetchOrders() async {
    final url = '$_baseUrl/product/all';
    final response = await this.httpClient.get(url);

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

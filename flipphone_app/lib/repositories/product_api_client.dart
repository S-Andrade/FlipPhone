import 'dart:convert';
import 'package:flipphoneapp/models/Product.dart';
import 'package:http/http.dart' as http;

class ProductAPIClient {
  final _baseUrl = 'http://192.168.160.49:8080';

  Future<List<Product>> fetchProducts() async {
    final url = '$_baseUrl/product/all';
    final response = await http.get(url);
    if (response.statusCode == 200) {
      List responseJson = json.decode(response.body);
      return responseJson
          .map((product) => new Product.fromJson(product))
          .toList();
    } else {
      throw Exception('Failed to load products from API');
    }
  }
}

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

  Future<Product> fetchProductsById(int productId) async {
    final url = '$_baseUrl/product/$productId';
    final response = await http.get(url);
    print(url);
    if (response.statusCode == 200) {
      return Product.fromJson(json.decode(response.body));
//      List responseJson = json.decode(response.body);
//      return responseJson
//          .map((product) => new Product.fromJson(product))
//          .toList();
    } else {
      throw Exception('Failed to load products from API');
    }
  }

  Future<Product> fetchProductByName(String productName) async {
    final url = '$_baseUrl/product/filter?product_name=$productName';
    final response = await http.get(url);
    print(url);
    if (response.statusCode == 200) {
      List responseJson = json.decode(response.body);
      return responseJson
          .map((product) => new Product.fromJson(product))
          .toList()[0];
    } else {
      throw Exception('Failed to load products from API');
    }
  }


}

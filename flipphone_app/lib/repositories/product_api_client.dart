import 'dart:convert';
import 'package:flipphoneapp/models/Product.dart';
import 'package:http/http.dart' as http;
import 'package:meta/meta.dart';

class ProductAPIClient {
  final _baseUrl = '192.168.160.49:8080';
  final http.Client httpClient;

  ProductAPIClient({
    @required this.httpClient,
  }) : assert(httpClient != null);

  Future<List<Product>> fetchProduct() async {
    final url = '$_baseUrl/product/all';
    final response = await this.httpClient.get(url);

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

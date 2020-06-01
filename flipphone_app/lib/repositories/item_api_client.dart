import 'dart:convert';
import 'package:flipphoneapp/models/Item.dart';
import 'package:http/http.dart' as http;
import 'package:meta/meta.dart';

class ItemAPIClient {
  final _baseUrl = '192.168.160.49:8080';
  final http.Client httpClient;

  ItemAPIClient({
    @required this.httpClient,
  }) : assert(httpClient != null);

  Future<List<Item>> fetchItems() async {
    final url = '$_baseUrl/item/all';
    final response = await this.httpClient.get(url);

    if (response.statusCode == 200) {
      List responseJson = json.decode(response.body);
      return responseJson.map((item) => new Item.fromJson(item)).toList();
    } else {
      throw Exception('Failed to load items from API');
    }
  }

  Future<List<Item>> fetchItemsByProduct(String productID) async {
    final url = '$_baseUrl/item/byProduct/$productID';
    final response = await this.httpClient.get(url);
    if (response.statusCode == 200) {
      List responseJson = json.decode(response.body);
      return responseJson.map((item) => new Item.fromJson(item)).toList();
    } else {
      throw Exception('Failed to load items from API');
    }
  }
}

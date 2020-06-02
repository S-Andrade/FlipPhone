import 'dart:convert';
import 'dart:io';
import 'package:flipphoneapp/models/Item.dart';
import 'package:http/http.dart' as http;

class ItemAPIClient {
  final _baseUrl = 'http://192.168.160.49:8080';

  Future<List<Item>> fetchItems() async {
    final url = '$_baseUrl/item/all';
    final response = await http.get(url);
    if (response.statusCode == 200) {
      List responseJson = json.decode(response.body);
      return responseJson.map((item) => new Item.fromJson(item)).toList();
    } else {
      throw Exception('Failed to load items from API');
    }
  }

  Future<List<Item>> fetchItemsByProduct(int productID) async {
    final url = '$_baseUrl/item/byProduct/?product_id=$productID';
    final response = await http.get(url);
    if (response.statusCode == 200) {
      List responseJson = json.decode(response.body);
      return responseJson.map((item) => new Item.fromJson(item)).toList();
    } else {
      throw Exception('Failed to load items from API');
    }
  }

//  Future<http.Response> addItem(String color, String grade, double price,
//      int product_id, int seller_id, String version) {
//    return http.post(
//      'https://jsonplaceholder.typicode.com/albums',
//      headers: <String, String>{
//        'Content-Type': 'application/json; charset=UTF-8',
//      },
//    );
//  }

  Future<http.Response> createItem(Item item) async {
    print('chegou aqui');
    print(json.encode(item.toJson()));
    final url = '$_baseUrl/item/add/';
    final response = await http.post(
      '$url',
      headers: {
        HttpHeaders.contentTypeHeader: 'application/json',
        HttpHeaders.authorizationHeader: ''
      },
      body: json.encode(item.toJson()),
    );
    return response;
  }
}

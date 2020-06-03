import 'dart:convert';
import 'package:flipphoneapp/models/User.dart';
import 'package:http/http.dart' as http;
import 'package:meta/meta.dart';

class UserAPIClient {
  final _baseUrl = '192.168.160.49:8080';

  Future<List<User>> fetchUsers() async {
    final url = 'http://$_baseUrl/user/all';
    final response = await http.get(url);

    if (response.statusCode == 200) {
      List responseJson = json.decode(response.body);
      return responseJson.map((user) => new User.fromJson(user)).toList();
    } else {
      throw Exception('Failed to load users from API');
    }
  }
  Future<User> fetchUserByEmail(String email) async {
    final url = 'http://$_baseUrl/user/byEmail?email=$email';
    final response = await http.get(url);

    if (response.statusCode == 200) {
      return User.fromJson(json.decode(response.body));
    } else {
      throw Exception('Failed to load user from API');
    }
  }
}

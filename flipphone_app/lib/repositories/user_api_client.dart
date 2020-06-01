import 'dart:convert';
import 'package:flipphoneapp/models/User.dart';
import 'package:http/http.dart' as http;
import 'package:meta/meta.dart';

class UserAPIClient {
  final _baseUrl = '192.168.160.49:8080';
  final http.Client httpClient;

  UserAPIClient({
    @required this.httpClient,
  }) : assert(httpClient != null);

  Future<List<User>> fetchUsers() async {
    final url = '$_baseUrl/user/all';
    final response = await this.httpClient.get(url);

    if (response.statusCode == 200) {
      List responseJson = json.decode(response.body);
      return responseJson.map((user) => new User.fromJson(user)).toList();
    } else {
      throw Exception('Failed to load users from API');
    }
  }
}

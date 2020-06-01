import 'dart:async';
import 'package:flipphoneapp/models/User.dart';
import 'package:flipphoneapp/repositories/user_api_client.dart';
import 'package:meta/meta.dart';

class UserRepository {
  final UserAPIClient userAPIClient;

  UserRepository({@required this.userAPIClient})
      : assert(userAPIClient != null);

  Future<List<User>> fetchUsers() async {
    return await userAPIClient.fetchUsers();
  }
}

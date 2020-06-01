import 'dart:async';
import 'package:flipphoneapp/models/User.dart';
import 'package:flipphoneapp/repositories/user_api_client.dart';
import 'package:meta/meta.dart';

class UserRepository {
  final UserAPIClient userApiClient;

  UserRepository({@required this.userApiClient})
      : assert(UserAPIClient != null);

  Future<List<User>> fetchUser() async {
    return await UserAPIClient.fetchUser();
  }
}

class User {
  final int userID;
  final String userName;
  final String userPassword;
  final String userSalt;
  final String userEmail;
  final String userAddress;
  final String userNIF;
  final String userType;

  User({
    this.userID,
    this.userName,
    this.userPassword,
    this.userSalt,
    this.userEmail,
    this.userAddress,
    this.userNIF,
    this.userType,
  });

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      userName: json['name'],
      userID: json['user_id'],
      userPassword: json['password'],
      userSalt: json['salt'],
      userEmail: json['email'],
      userAddress: json['address'],
      userNIF: json['nif'],
      userType: json['type'],
    );
  }
}

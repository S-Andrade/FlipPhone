class Admin {
  final int adminID;
  final String adminPassword;
  final String adminHash;
  final String adminEmail;

  Admin({
    this.adminID,
    this.adminPassword,
    this.adminHash,
    this.adminEmail,
  });

  factory Admin.fromJson(Map<String, dynamic> json) {
    return Admin(
      adminID: json['admin_id'],
      adminPassword: json['admin_password'],
      adminHash: json['admin_hash'],
      adminEmail: json['admin_email'],
    );
  }
}

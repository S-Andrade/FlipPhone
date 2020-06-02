class Product {
  final int productId;
//  final int adminID;
  final String productName;
  final String image;
  final String screenSize;
  final String screenType;
  final String phoneOS;
  final String battery;
  final String selfieCam;
  final String camera;
  final String cpuGpu;
  final String ramRom;

  Product(
      {this.productId,
//      this.adminID,
      this.productName,
      this.image,
      this.screenSize,
      this.screenType,
      this.phoneOS,
      this.battery,
      this.selfieCam,
      this.camera,
      this.cpuGpu,
      this.ramRom});

  factory Product.fromJson(Map<String, dynamic> json) {
    return Product(
      productId: json['product_id'] as int,
//      adminID: json['admin_id'],
      productName: json['product_name'],
      image: json['photoUrl'],
      screenSize: json[' screen_size'],
      screenType: json[' screen_type'],
      phoneOS: json['os'],
      battery: json[' battery'],
      selfieCam: json[' selfie_cam'],
      camera: json[' camera'],
      cpuGpu: json[' cpu_gpu'],
      ramRom: json[' ram_rom'],
    );
  }
}

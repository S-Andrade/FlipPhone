import 'dart:convert';

class Item {
  final int itemId;
  final String grade;
  final String color;
  final double price;
  final String version;
  final int productId;
  final int orderId;
  final int userId;

  Item({
    this.itemId,
    this.grade,
    this.color,
    this.price,
    this.version,
    this.productId,
    this.orderId,
    this.userId,
  });

  factory Item.fromJson(Map<String, dynamic> json) {
    return Item(
      itemId: json['item_id'] as int,
      grade: json['grade'],
      color: json['color'],
      price: json['price'] as double,
      version: json['version'],
      productId: json['product_id'] as int,
      orderId: json['order_id'] as int,
      userId: json['user_id'] as int,
    );
  }

//  String itemToJson(Item data) {
//    final dyn = data.toJson();
//    return json.encode(dyn);
//  }

  Map<String, dynamic> toJson() => {
        'color': color,
        'grade': grade,
        'price': price,
        'product_id': productId,
        'seller_id': userId,
        'version': version,
      };
}

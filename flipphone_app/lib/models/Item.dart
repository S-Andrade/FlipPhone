import 'dart:convert';
import 'Order.dart';
import 'Product.dart';
import 'User.dart';

class Item {
  final int itemId;
  final String grade;
  final String color;
  final double price;
  final String version;
  final Product productObj;
//  final Order orderObj;
  final User userId;

  Item({
    this.itemId,
    this.grade,
    this.color,
    this.price,
    this.version,
    this.productObj,
//    this.orderObj,
    this.userId,
  });

  factory Item.fromJson(Map<String, dynamic> json) {
    return Item(
      itemId: json['item_id'] as int,
      grade: json['grade'],
      color: json['color'],
      price: json['price'] as double,
      version: json['version'],
      productObj: Product.fromJson(json['productId']),
//      orderObj: Order.fromJson(json['order_id']),
      userId: User.fromJson(json['seller_id']),
    );
  }

  Map<String, dynamic> toJson() => {
        'color': color,
        'grade': grade,
        'price': price,
        'productId': productObj,
        'seller_id': userId,
        'version': version,
      };
}

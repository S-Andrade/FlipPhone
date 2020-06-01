class Item {
  final int itemId;
  final String grade;
  final String color;
  final String price;
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
      itemId: json['item_id'],
      grade: json['grade'],
      color: json['color'],
      price: json['price'],
      version: json['version'],
      productId: json['product_id'],
      orderId: json['order_id'],
      userId: json['user_id'],
        );
  }
}

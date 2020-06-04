class Payment {
  final int paymentID;
  final String status;
  final String gateway;
  final String date;
  final int orderID;
  final int userID;
  final int receiveID;

  Payment({
    this.paymentID,
    this.status,
    this.gateway,
    this.date,
    this.orderID,
    this.userID,
    this.receiveID,
  });

  factory Payment.fromJson(Map<String, dynamic> json) {
    return Payment(
      paymentID: json['payment_id'],
      status: json['status'],
      gateway: json['gateway'],
      date: json['date'],
      orderID: json['order_id'],
      userID: json['user_id'],
      receiveID: json['receive_id'],
    );
  }
}

import 'package:flipphoneapp/product_details.dart';
import 'package:flipphoneapp/repositories/order_api_client.dart';
import 'package:flutter/material.dart';
import 'models/Order.dart';


class OrdersList extends StatefulWidget {
  @override
  _OrdersListState createState() => _OrdersListState();
}

class _OrdersListState extends State<OrdersList> {

  OrderAPIClient _orderAPIClient = new OrderAPIClient();

  var orders = new List<Order>();

  @override
  void initState(){
    _getOrders();
    super.initState();
  }

  _getOrders() async {
    orders = await _orderAPIClient.fetchOrders();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar : AppBar(
          title: Text("Lista de Produtos"),
        ),
        body: listProducts()
    );
  }

  listProducts() {
    return ListView.builder(
      itemCount : orders.length,
      itemBuilder: (context, index) {
        return ListTile(
          title: Text('$orders[index].orderID',
              style: TextStyle(
                  fontSize: 20.0,
                  color: Colors.black)),
        );
      },
    );
  }

}
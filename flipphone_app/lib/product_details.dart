import 'package:flipphoneapp/models/Product.dart';
import 'package:flipphoneapp/repositories/item_api_client.dart';
import 'package:flutter/material.dart';
import 'models/Item.dart';

class ProductDetails extends StatefulWidget {
  final Product product;
  ProductDetails({Key key, this.product}) : super (key:key);

  @override
  _ProductDetailsState createState() => _ProductDetailsState();
}

class _ProductDetailsState extends State<ProductDetails> {


  ItemAPIClient _itemAPIClient;
  var items = new List<Item>();

//  ProductDetailsState(this.product);

  _getItems() async {
    items = await _itemAPIClient.fetchItems();
  }

  @override
  void initState(){
    super.initState();
    _getItems();
  }
  

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(widget.product.productName),
        ),
        body: productDetails()
    );
  }

  productDetails() {
    return Container(padding: new EdgeInsets.all(32.0),
      child:
      ListTile(
        title: Text(widget.product.productName,
            style: TextStyle(fontWeight: FontWeight.w500)),
//        subtitle: Text("$user.username"),
        leading: Icon(Icons.email, color: Colors.blue),
      ),
    );
  }
}

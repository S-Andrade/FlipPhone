import 'package:flipphoneapp/models/Product.dart';
import 'package:flipphoneapp/repositories/item_api_client.dart';
import 'package:flipphoneapp/repositories/product_api_client.dart';
import 'package:flutter/material.dart';
import 'models/Item.dart';

class ProductDetails extends StatefulWidget {
  final Product product;
  ProductDetails({Key key, this.product}) : super(key: key);

  @override
  _ProductDetailsState createState() => _ProductDetailsState();
}

class _ProductDetailsState extends State<ProductDetails> {
  ItemAPIClient _itemAPIClient = new ItemAPIClient();
  var items = new List<Item>();

  @override
  void initState() {
    _getItemsByProduct();
    super.initState();
    print('print no state');
    print(items);
  }

  _getItemsByProduct() async {
    List<Item> fetchedItem =
        await _itemAPIClient.fetchItemsByProduct(widget.product.productId);
    setState(() {
      items = fetchedItem;
    });
  }

  @override
  Widget build(BuildContext context) {
    print('print na lista do items');
    print(items);
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.product.productName),
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Text(widget.product.productName,
              textAlign: TextAlign.center,
              style: TextStyle(fontWeight: FontWeight.w500)),
          Flexible(
            child: ListView.builder(
              itemCount: items.length,
              itemBuilder: (context, index) {
                return Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: [
                    Text(items[index].grade,
                        textAlign: TextAlign.center,
                        style: TextStyle(fontSize: 14.0, color: Colors.black)),
                    Text(items[index].color,
                        textAlign: TextAlign.center,
                        style: TextStyle(fontSize: 14.0, color: Colors.black)),
                    Text(items[index].price.toString(),
                        textAlign: TextAlign.center,
                        style: TextStyle(fontSize: 14.0, color: Colors.black)),
                    Text(items[index].userId.userName,
                        textAlign: TextAlign.center,
                        style: TextStyle(fontSize: 14.0, color: Colors.black)),
                  ],
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}

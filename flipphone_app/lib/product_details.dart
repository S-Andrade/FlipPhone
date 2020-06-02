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
    List<Item> fetchedItem = await _itemAPIClient.fetchItemsByProduct(widget.product.productId);
    setState(()  {
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
      body: Container(
        child: Stack(
          children: [
            Text(widget.product.productName,
                style: TextStyle(fontWeight: FontWeight.w500)),
            ListView.builder(
              itemCount: items.length,
              itemBuilder: (context, index) {
                return Text(items[index].color,
                    style: TextStyle(fontSize: 20.0, color: Colors.black));
              },
            ),
          ],
        ),
      ),
    );
  }
}

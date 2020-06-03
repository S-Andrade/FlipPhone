import 'package:flipphoneapp/models/Product.dart';
import 'package:flipphoneapp/repositories/item_api_client.dart';
import 'package:flipphoneapp/repositories/product_api_client.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'models/Item.dart';

class ShoppingCart extends StatefulWidget {
  @override
  _ShoppingCartState createState() => _ShoppingCartState();
}

class _ShoppingCartState extends State<ShoppingCart> {
  int userID;
  List<String> cart;
  ItemAPIClient _itemAPIClient = new ItemAPIClient();
  ProductAPIClient _productAPIClient = new ProductAPIClient();
//  var items = new List<Item>();

  @override
  void initState() {
    _getCartSharedPref();
    super.initState();
  }

  Future<Item>_getItemById(int itemId) async {
    Item fetchedItem = await _itemAPIClient.fetchItemsById(itemId);
    return fetchedItem;
  }

//  Future<Product> _getProductById(int productId) async {
//    Product fetchedProduct = await _productAPIClient.fetchProductsById(productId);
//    return fetchedProduct;
//  }

  _getUserIdSharedPref() async {
    SharedPreferences sp = await SharedPreferences.getInstance();
    setState(() {
      userID = sp.getInt('userId');
    });
  }

  _getCartSharedPref() async {
    SharedPreferences sp = await SharedPreferences.getInstance();
    final userID = _getUserIdSharedPref();
    List<String> spCart = sp.getStringList('$userID') ?? [];
    setState(() {
      cart = spCart;
    });
  }

  @override
  Widget build(BuildContext context) {
    print(cart);
    return Scaffold(
      appBar: AppBar(
        title: Text("Shopping Cart"),
        actions: <Widget>[
          IconButton(
            icon: Icon(
              Icons.shopping_cart,
              color: Colors.white,
            ),
            onPressed: () {
              Navigator.push(context,
                  new MaterialPageRoute(builder: (context) => ShoppingCart()));
              // do something
            },
          )
        ],
      ),
      body: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Flexible(
              child: ListView.builder(
                  itemCount: cart.length,
                  itemBuilder: (context, index) {
                    return FutureBuilder(
                        future: _getItemById(int.parse(cart[index])),
                        builder: (context, snapshot) {
                          return Padding(
                            padding: const EdgeInsets.symmetric(vertical:5.0),
                            child: Row(
                              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                              children: [
                                Text(snapshot.data.productObj.productName),
                                Text(snapshot.data.itemId.toString()),
                                Text(snapshot.data.grade.toString()),
                                Text(snapshot.data.color.toString()),
                                Text(snapshot.data.price.toString()),
                              ],
                            ),
                          );
                        });
                  }),
            ),
          ]),
    );
  }
}

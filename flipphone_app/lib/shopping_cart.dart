import 'package:flipphoneapp/repositories/item_api_client.dart';
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
//  var items = new List<Item>();

  @override
  void initState() {
    _getCartSharedPref();
    super.initState();
  }

  Future<Item>_getItemById(int itemId) async {
    Item fetchedItem = await _itemAPIClient.fetchItemsById(itemId);
//    setState(() {
//      items.add(fetchedItem);
//    });
    return fetchedItem;
  }

  _getUserIdSharedPref() async {
//    SharedPreferences sp = await SharedPreferences.getInstance();
//    int userID = sp.getInt('userId');
//    return userID;
    setState(() {
      userID = 9;
    });
    return userID;
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
//                    var fetchedItem = new Item();
//                    _getItemById(int itemId) async {
//                      fetchedItem = await _itemAPIClient.fetchItemsById(itemId);
//                          print('teste' + fetchedItem.itemId.toString());
//                    }
//                    print(int.parse(cart[index]));
//                    _getItemById(int.parse(cart[index]));
//                    print(fetchedItem.itemId);
                    return Row(
                      children: [
                        Text(cart[index]),
                        FutureBuilder(
                            future: _getItemById(int.parse(cart[index])),
                            builder: (context, snapshot) {
                              print('snap' + snapshot.data.itemId.toString());
                              return Text(snapshot.data.itemId.toString());
                            }),
//                        Text(fetchedItem.itemId.toString()),
                      ],
                    );
                  }),
            ),
          ]),
    );
  }
}

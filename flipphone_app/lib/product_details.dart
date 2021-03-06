import 'package:flipphoneapp/models/Product.dart';
import 'package:flipphoneapp/repositories/item_api_client.dart';
import 'package:flipphoneapp/sell_item.dart';
import 'package:flipphoneapp/shopping_cart.dart';
import 'package:flutter/material.dart';
import 'login_page.dart';
import 'models/Item.dart';
import 'package:shared_preferences/shared_preferences.dart';

class ProductDetails extends StatefulWidget {
  final Product product;
  ProductDetails({Key key, this.product}) : super(key: key);

  @override
  _ProductDetailsState createState() => _ProductDetailsState();
}

class _ProductDetailsState extends State<ProductDetails> {
  ItemAPIClient _itemAPIClient = new ItemAPIClient();
  var items = new List<Item>();
  int userID;

  @override
  void initState() {
    _getItemsByProduct();
    _getUserIdSharedPref();
    super.initState();
  }

  _getItemsByProduct() async {
    List<Item> fetchedItem =
        await _itemAPIClient.fetchItemsByProduct(widget.product.productId);
    setState(() {
      items = fetchedItem;
    });
  }

  _getUserIdSharedPref() async {
    SharedPreferences sp = await SharedPreferences.getInstance();
    setState(() {
      userID = sp.getInt('userId');
    });
    print(userID);
  }

  _addCartSharedPref(int itemId) async {
    SharedPreferences sp = await SharedPreferences.getInstance();
    final itemCart = itemId.toString();
    final userID = _getUserIdSharedPref();
    final cart = sp.getStringList('$userID') ?? [];
    cart.add(itemCart);
    sp.setStringList('$userID', cart);
  }

//  _getCartSharedPref() async {
//    SharedPreferences sp = await SharedPreferences.getInstance();
//    final cart = sp.getStringList('cart') ?? [];
//  }

  _removeItemCartSharedPref(int itemId) async {
    SharedPreferences sp = await SharedPreferences.getInstance();
    final itemCart = itemId.toString();
    final userID = _getUserIdSharedPref();
    final cart = sp.getStringList('$userID') ?? [];
    cart.remove(itemCart);
    sp.setStringList('$userID', cart);
  }

//  Future<bool> _checkCart(int itemId) async {
//    SharedPreferences sp = await SharedPreferences.getInstance();
//    bool checkValue = sp.containsKey('$itemId');
//    return checkValue;
//  }

  ////bool CheckValue = prefs.containsKey('value');

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.product.productName),
        actions: <Widget>[
          IconButton(
            icon: Icon(
              Icons.monetization_on,
              color: Colors.lightGreenAccent,
            ),
            onPressed: () {
              Navigator.push(context,
                  new MaterialPageRoute(builder: (context) => SellItem()));
              // do something
            },
          ),
          IconButton(
            icon: Icon(
              Icons.shopping_cart,
              color: Colors.white,
            ),
            onPressed: () {
              Navigator.push(
                  context,
                  new MaterialPageRoute(
                      builder: (context) => ShoppingCart()));
              // do something
            },
          ),
          IconButton(
            icon: Icon(
              Icons.person,
              color: Colors.white,
            ),
            onPressed: () {
              Navigator.push(context,
                  new MaterialPageRoute(builder: (context) => LoginPage()));

              // do something
            },
          ),
        ],
      ),
      body: Padding(
        padding: const EdgeInsets.all(20.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Padding(
              padding: const EdgeInsets.only(bottom: 20.0),
              child: Image(
                image: NetworkImage(
                    'https://fdn2.gsmarena.com/vv/pics/samsung/samsung-galaxy-s10-1.jpg'),
              ),
            ),
            // true link: widget.product.photoUrl,
            Text(
                widget.product.productName,
                key: Key('passed_product_name'),
                textAlign: TextAlign.center,
                style: TextStyle(fontSize: 24, fontWeight: FontWeight.normal)),
            Text('Disponiveis:',
                textAlign: TextAlign.center,
                style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold)),
            Padding(
              padding: const EdgeInsets.only(top: 24.0, left: 24, right: 24),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text('Seller',
                      textAlign: TextAlign.left,
                      style: TextStyle(
                          fontWeight: FontWeight.w900,
                          fontSize: 14.0,
                          color: Colors.black)),
                  Text('Estado',
                      textAlign: TextAlign.left,
                      style: TextStyle(
                          fontWeight: FontWeight.w900,
                          fontSize: 14.0,
                          color: Colors.black)),
                  Text('Cor',
                      textAlign: TextAlign.left,
                      style: TextStyle(
                          fontWeight: FontWeight.w900,
                          fontSize: 14.0,
                          color: Colors.black)),
                  Text('Preço',
                      textAlign: TextAlign.left,
                      style: TextStyle(
                          fontWeight: FontWeight.bold,
                          fontSize: 14.0,
                          color: Colors.black)),
                  Text('Buy'),
                ],
              ),
            ),

            Flexible(
              child: Padding(
                padding: const EdgeInsets.only(
                    top: 10.0, left: 10, right: 10, bottom: 20),
                child: ListView.builder(
                  itemCount: items.length,
                  itemBuilder: (context, index) {
                    return Padding(
                      padding: const EdgeInsets.all(4.0),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Text(items[index].userId.userName,
                              textAlign: TextAlign.left,
                              style: TextStyle(
                                  fontSize: 14.0, color: Colors.black)),
                          Text(items[index].grade,
                              textAlign: TextAlign.left,
                              style: TextStyle(
                                  fontSize: 14.0, color: Colors.black)),
                          Text(items[index].color,
                              textAlign: TextAlign.left,
                              style: TextStyle(
                                  fontSize: 14.0, color: Colors.black)),
                          Text(items[index].price.toString() + ' €',
                              textAlign: TextAlign.left,
                              style: TextStyle(
                                  fontWeight: FontWeight.bold,
                                  fontSize: 14.0,
                                  color: Colors.black)),

                           IconButton(
                             key: Key('add_to_cart'),
                             icon: Icon(Icons.add_shopping_cart),
                             color: Colors.indigo,
                            onPressed: () =>  _addCartSharedPref(items[index].itemId),
                          ),
                          IconButton(
                            key: Key('remove_from_cart'),
                            icon: Icon(Icons.remove_shopping_cart),
                            color: Colors.red,
                            onPressed: () =>  _removeItemCartSharedPref(items[index].itemId),
                          ),

                        ],
                      ),
                    );
                  },
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

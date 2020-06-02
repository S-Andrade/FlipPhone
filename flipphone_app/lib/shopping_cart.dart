import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class ShoppingCart extends StatefulWidget {
  @override
  _ShoppingCartState createState() => _ShoppingCartState();
}

class _ShoppingCartState extends State<ShoppingCart> {
  int userID;
  List<String> cart;

  @override
  void initState() {
    _getCartSharedPref();
    super.initState();
    userID = 9;

  }

//  Future<int> getUserIdSharedPref() async {
////    SharedPreferences sp = await SharedPreferences.getInstance();
////    int userID = sp.getInt('userId');
////    return userID;
//    setState(() {
//      userID = 9;
//    });
//    return userID;
//  }

   _getCartSharedPref() async {
    SharedPreferences sp = await SharedPreferences.getInstance();
    final cart = sp.getStringList('$userID') ?? ['1'];
//    return cart;
  }

  @override
  Widget build(BuildContext context) {
    print(userID);
    print(cart);
    return Column(
        mainAxisAlignment: MainAxisAlignment.center,
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Flexible(
            child: ListView.builder(
                itemCount: cart.length,
                itemBuilder: (context, index) {
                  return Container(
                    child: Text(cart[index]),
                  );
                }),
          ),
        ]);
  }
}

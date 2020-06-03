import 'package:flipphoneapp/product_details.dart';
import 'package:flipphoneapp/repositories/product_api_client.dart';
import 'package:flipphoneapp/sell_item.dart';
import 'package:flipphoneapp/shopping_cart.dart';
import 'package:flutter/material.dart';
import 'login_page.dart';
import 'models/Product.dart';

class ProductListView extends StatefulWidget {
  @override
  _ProductListViewState createState() => _ProductListViewState();
}

class _ProductListViewState extends State<ProductListView> {
  ProductAPIClient _productAPIClient = new ProductAPIClient();
  var products = new List<Product>();

//  bool _isLoading = false;

  @override
  void initState() {
//    _getProducts();
    super.initState();
  }

  _getProducts() async {
//    setState(() {
//      _isLoading = true;
//    });
    products = await _productAPIClient.fetchProducts();
//    setState(() {
//      _isLoading = false;
//    });
  }

//  _filterName
//  http://192.168.160.49:8080/product/filter?battery=4000mah

  @override
  Widget build(BuildContext context) {
    _getProducts();

    return Scaffold(
        appBar: AppBar(
          title: Text("Lista de Produtos"),
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
        body: listProducts());
  }

  listProducts() {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 20.0),
      child: GridView.builder(
        itemCount: products.length,
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            childAspectRatio: 0.8,
            crossAxisCount: 2,
            crossAxisSpacing: 10.0,
            mainAxisSpacing: 10.0),
        itemBuilder: (context, index) {
          return GestureDetector(
            onTap: () {
              Navigator.push(
                  context,
                  new MaterialPageRoute(
                      builder: (context) =>
                          ProductDetails(product: products[index])));
            },
            child: Column(
//            mainAxisSize: MainAxisSize.min,
              children: [
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Text(products[index].productName,
                      style: TextStyle(
                          fontWeight: FontWeight.bold,
                          fontSize: 16.0,
                          color: Colors.black)),
                ),
                Image(
                  fit: BoxFit.contain,
                  image: NetworkImage(
                      'https://fdn2.gsmarena.com/vv/pics/samsung/samsung-galaxy-s10-1.jpg'),
                ),
              ],
//          onTap: () {
//            Navigator.push(
//                context,
//                new MaterialPageRoute(
//                    builder: (context) =>
//                        ProductDetails(product: products[index])));
//          },
            ),
          );
        },
      ),
    );
  }
}

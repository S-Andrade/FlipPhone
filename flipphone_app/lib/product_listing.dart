import 'package:flipphoneapp/product_details.dart';
import 'package:flipphoneapp/repositories/product_api_client.dart';
import 'package:flipphoneapp/shopping_cart.dart';
import 'package:flutter/material.dart';
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
    _getProducts();
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
    return Scaffold(
        appBar: AppBar(
          title: Text("Lista de Produtos"),
          actions: <Widget>[
            IconButton(
              icon: Icon(
                Icons.shopping_cart,
                color: Colors.white,
              ),
              onPressed: () {
                Navigator.push(
                    context,
                    new MaterialPageRoute(
                        builder: (context) => ShoppingCart()
                    ));
                // do something
              },
            )
          ],
        ),
        body: listProducts());
  }

  listProducts() {
    return ListView.builder(
      itemCount: products.length,
      itemBuilder: (context, index) {
        return ListTile(
          title: Text(products[index].productName,
              style: TextStyle(fontSize: 20.0, color: Colors.black)),
          onTap: () {
            Navigator.push(
                context,
                new MaterialPageRoute(
                    builder: (context) =>
                        ProductDetails(product: products[index])));
          },
        );
      },
    );
  }
}

import 'package:flipphoneapp/product_details.dart';
import 'package:flipphoneapp/repositories/product_repository.dart';
import 'package:flutter/material.dart';
import 'models/Product.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  build(context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Http-Json-ListView',
      home: ProductListView(),
    );
  }
}
class ProductListView extends StatefulWidget {
  @override
  _ProductListViewState createState() => _ProductListViewState();
}

class _ProductListViewState extends State<ProductListView> {
  ProductRepository _productRepository;
  var products = new List<Product>();

  bool _isLoading = false;

  @override
  void initState(){
    _getProducts();
    super.initState();
  }

  _getProducts() async {
    setState(() {
      _isLoading = true;
    });
    products = await _productRepository.fetchProducts();
    setState(() {
      _isLoading = false;
    });
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
      itemCount : products.length,
      itemBuilder: (context, index) {
        return ListTile(
          title: Text(products[index].productName,
              style: TextStyle(
                  fontSize: 20.0,
                  color: Colors.black)),
          onTap: () {
            Navigator.push(context,
                new MaterialPageRoute(
                    builder: (context) => ProductDetails(products[index])));
          },
        );
      },
    );
  }

}
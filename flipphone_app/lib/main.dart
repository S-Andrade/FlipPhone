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

  _getProducts() async {
    products = await _productRepository.fetchProduct();
  }

  @override
  void initState(){
    super.initState();
    _getProducts();
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
        );
      },
    );
  }

}
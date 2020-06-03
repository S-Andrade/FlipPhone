import 'package:flipphoneapp/models/Product.dart';
import 'package:flipphoneapp/repositories/item_api_client.dart';
import 'package:flipphoneapp/repositories/product_api_client.dart';
import 'package:flipphoneapp/repositories/user_api_client.dart';
import 'package:flipphoneapp/shopping_cart.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'login_page.dart';
import 'models/Item.dart';
import 'models/User.dart';

class SellItem extends StatefulWidget {
  @override
  _SellItemState createState() => _SellItemState();
}

class _SellItemState extends State<SellItem> {
  final _colorController = TextEditingController();
  final _gradeController = TextEditingController();
  final _priceController = TextEditingController();
  final _productNameController = TextEditingController();
  final _versionController = TextEditingController();

  String saleGrade;
  double salePrice;
  String saleColor;
  String saleProductName;
  String saleVersion;
  int saleUserID;
  Product saleProduct;
  User seller;

  int userID;
  ItemAPIClient _itemAPIClient = new ItemAPIClient();
  UserAPIClient _userAPIClient = new UserAPIClient();
  ProductAPIClient _productAPIClient = new ProductAPIClient();

  @override
  void initState() {
    _getUserIdSharedPref();
    print(userID);
    super.initState();
  }

  _getUserIdSharedPref() async {
    SharedPreferences sp = await SharedPreferences.getInstance();
    setState(() {
      userID = sp.getInt('userId');
    });
  }
  _getUserByID(int userId) async {
    seller = await _userAPIClient.fetchUserById(userId);
    print(seller.userID.toString());
  }

  _getProductByName(productName) async {
    saleProduct = await _productAPIClient.fetchProductByName(productName);
  }

  _createSaleItem(Item item) async {
    await _itemAPIClient.createItem(item);
  }

  final _formKey = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Vender o meu"),
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
              Navigator.push(context,
                  new MaterialPageRoute(builder: (context) => ShoppingCart()));
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
      body: Form(
          key: _formKey,
          child: Padding(
            padding: const EdgeInsets.all(20.0),
            child: Column(children: <Widget>[
              Text(
                'O meu telemóvel:',
                style: TextStyle(
                    fontSize: 20.0,
                    fontWeight: FontWeight.bold,
                    color: Colors.black),
              ),
              // subs por select field
              TextFormField(
                  controller: _productNameController,
                  keyboardType: TextInputType.text,
                  style: TextStyle(color: Colors.black),
                  decoration: InputDecoration(
                      labelText: "Modelo",
                      labelStyle:
                          TextStyle(fontSize: 18.0, color: Colors.black),
                      hintText: "Samsung Galaxy")),
              TextFormField(
                  controller: _colorController,
                  keyboardType: TextInputType.text,
                  style: TextStyle(color: Colors.black),
                  decoration: InputDecoration(
                      labelText: "Cor",
                      labelStyle:
                          TextStyle(fontSize: 18.0, color: Colors.black),
                      hintText: "Fancy Blue")),
              TextFormField(
                  controller: _gradeController,
                  keyboardType: TextInputType.text,
                  style: TextStyle(color: Colors.black),
                  decoration: InputDecoration(
                      labelText: "Estado",
                      labelStyle:
                          TextStyle(fontSize: 18.0, color: Colors.black),
                      hintText: "New / Good condition / etc")),
              TextFormField(
                  controller: _priceController,
                  keyboardType: TextInputType.text,
                  style: TextStyle(color: Colors.black),
                  decoration: InputDecoration(
                      labelText: "Preço (€)",
                      labelStyle:
                          TextStyle(fontSize: 18.0, color: Colors.black),
                      hintText: "how many moneros")),
              TextFormField(
                  controller: _versionController,
                  keyboardType: TextInputType.text,
                  style: TextStyle(color: Colors.black),
                  decoration: InputDecoration(
                      labelText: "Versão",
                      labelStyle:
                          TextStyle(fontSize: 18.0, color: Colors.black),
                      hintText: "ex: PRO LTE 6GB 64GB")),
              Padding(
                padding: const EdgeInsets.all(20.0),
                child: Container(
                  width: 160,
                  child: FlatButton(
                    padding: const EdgeInsets.all(10.0),
                    color: Colors.blue,
                    child: Text("Sell this",
                        style: TextStyle(color: Colors.white, fontSize: 18.0)),
                    onPressed: () {
                      _sellItem(context);
                    },
                  ),
                ),
              ),
            ]),
          )),
    );
  }

  _sellItem(BuildContext context) {
    setState(() {
      saleGrade = _gradeController.text;
      salePrice = double.parse(_priceController.text);
      saleColor = _colorController.text;
      saleProductName = _productNameController.text;
      saleVersion = _versionController.text;
      saleUserID = userID;
      _getProductByName(saleProductName);
      print('aqui'+userID.toString());
//      _getUserByID(saleUserID);
    });

//    var saleItem = new Item(
//        color: saleColor,
//        grade: saleGrade,
//        price: salePrice,
//        productObj: saleProduct,
//        userId: seller,
//        version: saleVersion);
////    _createSaleItem(item);
  }
}

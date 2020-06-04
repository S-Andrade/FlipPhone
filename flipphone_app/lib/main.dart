import 'package:flipphoneapp/product_listing.dart';
import 'package:flipphoneapp/repositories/item_api_client.dart';
import 'package:flutter/material.dart';
import 'create_item.dart';
import 'login_page.dart';
import 'models/Item.dart';

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

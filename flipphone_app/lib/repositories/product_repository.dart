import 'dart:async';
import 'package:flipphoneapp/models/Product.dart';
import 'package:flipphoneapp/repositories/product_api_client.dart';
import 'package:meta/meta.dart';

class ProductRepository {
  final ProductAPIClient productApiClient;

  ProductRepository({@required this.productApiClient})
      : assert(productApiClient != null);

  Future<List<Product>> fetchProducts() async {
    return await productApiClient.fetchProducts();
  }
}

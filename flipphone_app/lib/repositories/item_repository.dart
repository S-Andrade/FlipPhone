import 'dart:async';
import 'package:flipphoneapp/models/Item.dart';
import 'package:meta/meta.dart';
import 'item_api_client.dart';

class ItemRepository {
  final ItemAPIClient itemAPIClient;

  ItemRepository({@required this.itemAPIClient})
      : assert(itemAPIClient != null);

  Future<List<Item>> fetchItems() async {
    return await itemAPIClient.fetchItems();
  }
}

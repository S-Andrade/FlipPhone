//import 'package:shared_preferences/shared_preferences.dart';
//
//class ActionsSharePreferences{
//// User session user id
//addUserIdSharedPref(int userId) async {
//  SharedPreferences sp = await SharedPreferences.getInstance();
//  sp.setInt('userId', userId);
//}
//getUserIdSharedPref() async {
//  SharedPreferences sp = await SharedPreferences.getInstance();
//  int userID = sp.getInt('userId');
//  return userID;
//}
//// Cart
//addCartSharedPref(int itemId) async {
//  SharedPreferences sp = await SharedPreferences.getInstance();
//  final item = itemId.toString();
//  final userID = getUserIdSharedPref();
//  final cart = sp.getStringList('$userID') ?? [];
//  cart.add(item);
//  sp.setStringList('userID', cart);
//}
//getCartSharedPref() async {
//  SharedPreferences sp = await SharedPreferences.getInstance();
//  final cart = sp.getStringList('cart') ?? [];
//}
//removeItemCartSharedPref(int itemId) async {
//  SharedPreferences sp = await SharedPreferences.getInstance();
//  final item = itemId.toString();
//  final userID = getUserIdSharedPref();
//  final cart = sp.getStringList('$userID') ?? [];
//  cart.remove(item);
//  sp.setStringList('userID', cart);
//}
//}
//
////removeValues() async {
////  SharedPreferences prefs = await SharedPreferences.getInstance();
////  //Remove String
////  prefs.remove("stringValue");
////  //Remove bool
////  prefs.remove("boolValue");
////  //Remove int
////  prefs.remove("intValue");
////  //Remove double
////  prefs.remove("doubleValue");
////}
//
////addStringSharePref(String stringToAdd) async {
////  SharedPreferences prefs = await SharedPreferences.getInstance();
////  prefs.setString('stringValue', stringToAdd);
////}
////
////addIntSharePref() async {
////  SharedPreferences prefs = await SharedPreferences.getInstance();
////  prefs.setInt('intValue', 123);
////}
////
////addDoubleSharePref() async {
////  SharedPreferences prefs = await SharedPreferences.getInstance();
////  prefs.setDouble('doubleValue', 115.0);
////}
////
////
////addBoolSharePref() async {
////  SharedPreferences prefs = await SharedPreferences.getInstance();
////  prefs.setBool('boolValue', true);
////}
////
////
////getStringSharePref() async {
////  SharedPreferences prefs = await SharedPreferences.getInstance();
////  //Return String
////  String stringValue = prefs.getString('stringValue');
////  return stringValue;
////}
//////getIntSharePref() async {
////  SharedPreferences prefs = await SharedPreferences.getInstance();
////  //Return int
////  int intValue = prefs.getInt('intValue');
////  return intValue;
////}
////getDoubleSharePref() async {
////  SharedPreferences prefs = await SharedPreferences.getInstance();
////  //Return double
////  double doubleValue = prefs.getDouble('doubleValue');
////  return doubleValue;
////}
////getBoolSharePref() async {
////  SharedPreferences prefs = await SharedPreferences.getInstance();
////  //Return bool
////  bool boolValue = prefs.getBool('boolValue');
////  return boolValue;
////}
////int intValue= await prefs.getInt('intValue') ?? 0;
//
////SharedPreferences prefs = await SharedPreferences.getInstance();
////
////bool CheckValue = prefs.containsKey('value');
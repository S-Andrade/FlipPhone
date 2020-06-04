import 'package:flutter_driver/flutter_driver.dart';
import 'package:test/test.dart';

void main() {

//  group('Product Details', () {
//    final buttonAddCart = find.byValueKey('add_to_cart');
//    final counterTextFinder = find.byValueKey('counter');
//
//    FlutterDriver driver;
//
//    setUpAll(() async {
//      driver = await FlutterDriver.connect();
//    });
//
//    tearDownAll(() async {
//      if (driver != null) {
//        driver.close();
//      }
//    });
//
//    test('adds item to sharepreferences aka cart', () async{
//      await driver.tap(buttonAddCart);
//      expect(await driver.getText(counterTextFinder), "1");
//    });
////    test('starts at 0', () async {
////      // Use the `driver.getText` method to verify the counter starts at 0.
////      expect(await driver.getText(counterTextFinder), "0");
////    });
////
////    test('increments the counter', () async {
////      // First, tap the button.
////      await driver.tap(buttonFinder);
////
////      // Then, verify the counter text is incremented by 1.
////      expect(await driver.getText(counterTextFinder), "1");
////    });
//  });
  group('Login Page', () {
    final loginPageBtnFinder = find.byValueKey('loginPageBtn');
    final logoutBtnFinder = find.byValueKey('logoutBtn');
    final loginBtnFinder = find.byValueKey('loginBtn');
    final emailTextFieldFinder = find.byValueKey('emailTextField');
    final passTextFieldFinder = find.byValueKey('passTextField');

    FlutterDriver driver;

    setUpAll(() async {
      driver = await FlutterDriver.connect();
    });

    tearDownAll(() async {
      if (driver != null) {
        driver.close();
      }
    });

    test('Open login page', () async {
      await driver.waitFor(loginPageBtnFinder);
      await driver.tap(loginPageBtnFinder);
    });

    test('Enter email', () async {
      await driver.waitFor(emailTextFieldFinder);
      await driver.tap(emailTextFieldFinder);
      await driver.enterText("user@email.com");
      await driver.waitFor(find.text('user@email.com'));
    });
    test('Enter password', () async {
      await driver.waitFor(passTextFieldFinder);
      await driver.tap(passTextFieldFinder);
      await driver.enterText("user@email.com");
      await driver.waitFor(find.text('user@email.com'));
    });
    test('Submit login', () async {
      await driver.waitFor(loginBtnFinder);
      await driver.tap(loginBtnFinder);
      //...
    });
    test('Logout', () async {
      await driver.waitFor(logoutBtnFinder);
      await driver.tap(logoutBtnFinder);
      //...
    });



  });
}
import 'package:flutter_driver/flutter_driver.dart';
import 'package:test/test.dart';

void main() {
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
  group('Product List', () {
    FlutterDriver driver;

    setUpAll(() async {
      driver = await FlutterDriver.connect();
    });

    tearDownAll(() async {
      if (driver != null) {
        driver.close();
      }
    });
  });
  group('Sell Item', () {
    final modelTextFormField = find.byValueKey('modelTextFormField');
    final colorTextFormField = find.byValueKey('colorTextFormField');
    final priceTextFormField = find.byValueKey('priceTextFormField');
    final gradeTextFormField = find.byValueKey('gradeTextFormField');
    final versionTextFormField = find.byValueKey('versionTextFormField');
    final sellBtn = find.byValueKey('sellBtn');

    FlutterDriver driver;

    setUpAll(() async {
      driver = await FlutterDriver.connect();
    });

    tearDownAll(() async {
      if (driver != null) {
        driver.close();
      }
    });

    test('Input Model', () async {
      await driver.waitFor(modelTextFormField);
      await driver.tap(modelTextFormField);
      await driver.enterText("Samsung Galaxy");
      await driver.waitFor(find.text('Samsung Galaxy'));
    });
    test('Input Color', () async {
      await driver.waitFor(colorTextFormField);
      await driver.tap(colorTextFormField);
      await driver.enterText("Test Shade");
      await driver.waitFor(find.text('Test Shade'));
    });
    test('Input Price', () async {
      await driver.waitFor(priceTextFormField);
      await driver.tap(priceTextFormField);
      await driver.enterText("999.50");
      await driver.waitFor(find.text('999.50'));
    });
    test('Input Grade', () async {
      await driver.waitFor(gradeTextFormField);
      await driver.tap(gradeTextFormField);
      await driver.enterText("NEW");
      await driver.waitFor(find.text('NEW'));
    });
    test('Input Version', () async {
      await driver.waitFor(versionTextFormField);
      await driver.tap(versionTextFormField);
      await driver.enterText("Special Edition");
      await driver.waitFor(find.text('Special Edition'));
    });
    test('Sell Button (Post Item)', () async {
      await driver.waitFor(sellBtn);
      await driver.tap(sellBtn);
      //...
    });
    //...

  });
  group('Shopping Cart', () {
    FlutterDriver driver;

    setUpAll(() async {
      driver = await FlutterDriver.connect();
    });

    tearDownAll(() async {
      if (driver != null) {
        driver.close();
      }
    });
  });
  group('Product Details', () {
    FlutterDriver driver;

    setUpAll(() async {
      driver = await FlutterDriver.connect();
    });

    tearDownAll(() async {
      if (driver != null) {
        driver.close();
      }
    });
  });
}

import 'package:flipphoneapp/repositories/user_api_client.dart';
import 'package:flipphoneapp/sell_item.dart';
import 'package:flipphoneapp/shopping_cart.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'models/User.dart';

class LoginPage extends StatefulWidget {
  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final _emailController = TextEditingController();
  final _passwordController = TextEditingController();

  final GlobalKey<FormState> formKey = GlobalKey<FormState>();
  String _password;
  UserAPIClient _userAPIClient = new UserAPIClient();
  User user;
//  int userId;

  @override
  void initState() {
    super.initState();
  }

  _addUserIdSharedPref(int userId) async {
    SharedPreferences sp = await SharedPreferences.getInstance();
    sp.setInt('userId', userId);
    print(userId);
  }

  _removeUserIdSharedPref() async {
    SharedPreferences sp = await SharedPreferences.getInstance();
    sp.remove('userId');
  }

  _getUserByEmail(String email) async {
    user = await _userAPIClient.fetchUserByEmail(email);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Login Page"),
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
              Navigator.push(
                  context,
                  new MaterialPageRoute(
                      builder: (context) => ShoppingCart()));
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
      body: Stack(
        children: [
          Padding(
            padding: EdgeInsets.all(16.0),
            child: _loginBody(context),
          ),
          Positioned(
            child: Align(
              alignment: Alignment.center,
              child: FlatButton(
                key: Key('logoutBtn'),
                padding: const EdgeInsets.all(10.0),
                color: Colors.red,
                child: Text("Logout",
                    style: TextStyle(color: Colors.white, fontSize: 18.0)),
                onPressed: () {
                  _removeUserIdSharedPref();
                },
              ),
            ),
          ),
        ],
      ),
    );
  }

//  String _validateUser(String text) {
//    if (text.isEmpty) {
//      return "I WANNA A EMAIL";
//    }
//    return null;
//  }

//  String _validatePass(String text) {
//    if (text.isEmpty) {
//      return "I WANNA A PASSWORD";
//    }
//    return null;
//  }

  _loginBody(BuildContext context) {
    return Form(
        key: formKey,
        child: ListView(
          children: <Widget>[
            textFormFieldEmail(),
            textFormFieldPassword(),
            submitBtn(context)
          ],
        ));
  }

  TextFormField textFormFieldEmail() {
    return TextFormField(
        key: Key('emailTextField'),
        controller: _emailController,
//        validator: _validateUser,
        keyboardType: TextInputType.text,
        style: TextStyle(color: Colors.black),
        decoration: InputDecoration(
            labelText: "Email",
            labelStyle: TextStyle(fontSize: 18.0, color: Colors.black),
            hintText: "exemple@thismail.com"));
  }

  TextFormField textFormFieldPassword() {
    return TextFormField(
        key: Key('passTextField'),
        controller: _passwordController,
//        validator: _validatePass,
        obscureText: true,
        keyboardType: TextInputType.text,
        style: TextStyle(color: Colors.black),
        decoration: InputDecoration(
            labelText: "Password",
            labelStyle: TextStyle(fontSize: 18.0, color: Colors.black),
            hintText: "Enter password"));
  }

  Container submitBtn(BuildContext context) {
    return Container(
      child: FlatButton(
        key: Key('loginBtn'),
        padding: const EdgeInsets.all(10.0),
        color: Colors.blue,
        child: Text("Login",
            style: TextStyle(color: Colors.white, fontSize: 18.0)),
        onPressed: () {
          _submitLogin(context);
        },
      ),
    );
  }

  _submitLogin(BuildContext context) {
    final loginEmail = _emailController.text;
    print(loginEmail);
    final loginPass = _passwordController.text;
    print(loginPass);
    _getUserByEmail(loginEmail);
    if (user != null) {
      if (user.userPassword == loginPass) _addUserIdSharedPref(user.userID);
    } else {
      print('email errado');
    }
  }
}

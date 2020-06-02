import 'package:flutter/material.dart';

class LoginPage extends StatelessWidget {
//  final _tedLogin = TextEditingController();
//  final _tedSenha = TextEditingController();

  final GlobalKey<FormState> formKey  = GlobalKey<FormState>();
  String _password;


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Login Page"),
      ),
      body: Padding(
        padding: EdgeInsets.all(16.0),
        child: _loginBody(context),
      ),
    );
  }

//  String _checkUser(String text) {
//    if (text.isEmpty) {
//      return "Incorrect User";
//    }
//    return null;
//  }

//  String _checkPass(String text) {
//    if (text.isEmpty) {
//      return "Incorrect Password";
//    }
//    return null;
//  }

  _loginBody(BuildContext context) {
    return Form(
        key: formKey,
        child: ListView(
          children: <Widget>[
            textFormFieldLogin(),
            textFormFieldSenha(),
            containerButton(context)
          ],
        ));
  }

  TextFormField textFormFieldLogin() {
    return TextFormField(
//        controller: _tedLogin,
//        validator: _checkUser,
        keyboardType: TextInputType.text,
        style: TextStyle(color: Colors.black),
        decoration: InputDecoration(
            labelText: "Login",
            labelStyle: TextStyle(fontSize: 20.0, color: Colors.black),
            hintText: "Informe a senha"));
  }

  Container containerButton(BuildContext context) {
    return Container(
      height: 40.0,
      margin: EdgeInsets.only(top: 10.0),
      child: RaisedButton(
        color: Colors.blue,
        child: Text("Login",
            style: TextStyle(color: Colors.white, fontSize: 20.0)),
        onPressed: () {
//          _onClickLogin(context);
        },
      ),
    );
  }

  TextFormField textFormFieldSenha() {
    return TextFormField(
//        controller: _tedSenha,
//        validator: _checkPass,
        obscureText: true,
        keyboardType: TextInputType.text,
        style: TextStyle(color: Colors.black),
        decoration: InputDecoration(
            labelText: "Senha",
            labelStyle: TextStyle(fontSize: 20.0, color: Colors.black),
            hintText: "Informe a senha"));
  }

//  _onClickLogin(BuildContext context) {
//    final login = _tedLogin.text;
//    final senha = _tedSenha.text;
//
//    print("Login: $login , Senha: $senha ");
//
//    if (!_formKey.currentState.validate()) {
//      return;
//    }

//    if (login.isEmpty || senha.isEmpty) {
//      showDialog(
//        context: context,
//        builder: (context) {
//          return AlertDialog(
//              title: Text("Erro"),
//              content: Text("Login e/ou Senha inv�lido(s)"),
//              actions: <Widget>[
//                FlatButton(
//                    child: Text("OK"),
//                    onPressed: () {
//                      Navigator.pop(context);
//                    })
//              ]);
//        },
//      );
//    }
  }

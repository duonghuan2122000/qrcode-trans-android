import "package:flutter/material.dart";
import 'package:flutter_app/home/HomeScreen.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "QRCode Test",
      home: HomeScreen(
        key: key,
      ),
    );
  }
}

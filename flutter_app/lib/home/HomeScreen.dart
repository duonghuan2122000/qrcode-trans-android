import 'package:camera/camera.dart';
import 'package:flutter/material.dart';
import 'package:flutter_app/scan_qrcode/ScanQRCodeScreen.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({Key? key}) : super(key: key);

  void _onClickBtnScanQRCode(BuildContext context) async {
    try {
      List<CameraDescription> cameras = await availableCameras();
      Navigator.of(context).push(
        MaterialPageRoute(
          builder: (context) => ScanQRCodeScreen(
            cameras: cameras,
          ),
        ),
      );
    } catch (e){
      print(e);
    }
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(
        title: Text("QRCode Test"),
      ),
      body: Builder(
        builder: (context) => Center(
          child: ElevatedButton(
            child: Text("Quét QRCode"),
            onPressed: () => _onClickBtnScanQRCode(context),
          ),
        ),
      ),
    );
  }
}

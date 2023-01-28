import 'dart:math';

import 'package:camera/camera.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:google_ml_kit/google_ml_kit.dart';

class ScanQRCodeScreen extends StatelessWidget {
  final List<CameraDescription>? cameras;

  const ScanQRCodeScreen({Key? key, this.cameras}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(
        title: Text("QRCode Test"),
      ),
      body: CameraPreviewScanQRCode(
        cameras: cameras,
      ),
    );
  }
}

class CameraPreviewScanQRCode extends StatefulWidget {
  final List<CameraDescription>? cameras;

  const CameraPreviewScanQRCode({Key? key, this.cameras}) : super(key: key);

  @override
  State<StatefulWidget> createState() => _CameraPreviewScanQRCodeState();
}

class _CameraPreviewScanQRCodeState extends State<CameraPreviewScanQRCode> {
  CameraController? controller;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    loadCamera();
  }

  void loadCamera() {
    controller = CameraController(widget.cameras![0], ResolutionPreset.max);
    controller?.initialize().then((_) {
      if (!mounted) {
        return;
      }

      controller?.startImageStream((CameraImage image) async {
        final WriteBuffer allBytes = WriteBuffer();
        for (Plane plane in image.planes) {
          allBytes.putUint8List(plane.bytes);
        }
        final bytes = allBytes.done().buffer.asUint8List();
        final Size imageSize =
            Size(image.width.toDouble(), image.height.toDouble());
        final InputImageRotation imageRotation =
            InputImageRotation.Rotation_0deg;
        final InputImageFormat inputImageFormat =
            InputImageFormatMethods.fromRawValue(image.format.raw) ??
                InputImageFormat.NV21;

        final planeData = image.planes.map(
          (Plane plane) {
            return InputImagePlaneMetadata(
              bytesPerRow: plane.bytesPerRow,
              height: plane.height,
              width: plane.width,
            );
          },
        ).toList();

        final inputImageData = InputImageData(
            size: imageSize,
            imageRotation: imageRotation,
            inputImageFormat: inputImageFormat,
            planeData: planeData);

        final inputImage = InputImage.fromBytes(
          bytes: bytes,
          inputImageData: inputImageData,
        );

        final barcodeScanner = GoogleMlKit.vision.barcodeScanner();
        final List<Barcode> barcodes = await barcodeScanner.processImage(inputImage);
        controller?.stopImageStream();
        for(Barcode barcode in barcodes){
          final BarcodeType type = barcode.type;
          final String? rawValue = barcode.value.rawValue;
          print(rawValue);
        }

        barcodeScanner.close();

      });

      setState(() {});
    }).catchError((error) {
      print("error: $error");
    });
  }

  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
    controller?.dispose();
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Column(
      children: [
        Expanded(
          child: Container(
            child: Padding(
              padding: const EdgeInsets.all(1.0),
              child: Center(
                child: _cameraPreviewWidget(),
              ),
            ),
          ),
        ),
      ],
    );
  }

  Widget _cameraPreviewWidget() {
    final CameraController? cameraController = controller;

    if (cameraController == null || !cameraController.value.isInitialized) {
      return const Text(
        'Tap a camera',
        style: TextStyle(
          color: Colors.blue,
          fontSize: 24.0,
          fontWeight: FontWeight.w900,
        ),
      );
    } else {
      return Listener(
        child: CameraPreview(
          controller!,
        ),
      );
    }
  }

  void detechQRCode() {
    final WriteBuffer allBytes = WriteBuffer();
  }
}

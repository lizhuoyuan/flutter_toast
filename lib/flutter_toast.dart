import 'dart:async';

import 'package:flutter/services.dart';

class FlutterToast {
  static const MethodChannel _channel = const MethodChannel('flutter_toast');
  static const int LENGTH_LONG = 1;
  static const int LENGTH_SHORT = 0;

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<Null> showToast(
    String msg, {
    int showTime = LENGTH_SHORT,
  }) async {
    Map<String, dynamic> arguments = Map<String, dynamic>();
    arguments.putIfAbsent('msg', () => msg);
    arguments.putIfAbsent('showTime', () => showTime);
    await _channel.invokeMethod('showToast', arguments);
    return null;
  }
}

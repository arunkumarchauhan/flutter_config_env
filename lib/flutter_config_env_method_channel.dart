import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'flutter_config_env_platform_interface.dart';

/// An implementation of [FlutterConfigEnvPlatform] that uses method channels.
class MethodChannelFlutterConfigEnv extends FlutterConfigEnvPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('flutter_config_env');

  @override
  Future<String?> getPlatformVersion() async {
    final version =
        await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}

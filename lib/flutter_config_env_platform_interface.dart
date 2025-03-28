import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'flutter_config_env_method_channel.dart';

abstract class FlutterConfigEnvPlatform extends PlatformInterface {
  /// Constructs a FlutterConfigEnvPlatform.
  FlutterConfigEnvPlatform() : super(token: _token);

  static final Object _token = Object();

  static FlutterConfigEnvPlatform _instance = MethodChannelFlutterConfigEnv();

  /// The default instance of [FlutterConfigEnvPlatform] to use.
  ///
  /// Defaults to [MethodChannelFlutterConfigEnv].
  static FlutterConfigEnvPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [FlutterConfigEnvPlatform] when
  /// they register themselves.
  static set instance(FlutterConfigEnvPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}

import 'package:flutter/services.dart';
import 'package:flutter_config_env/flutter_config_env.dart';
import 'package:flutter_test/flutter_test.dart';

void main() {
  const MethodChannel channel = MethodChannel('flutter_config_env');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return {'FABRIC': 67};
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('get variable', () async {
    await FlutterConfigEnv.loadEnvVariables();
    expect(FlutterConfigEnv.get('FABRIC'), 67);
  });
}

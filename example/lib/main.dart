import 'package:flutter/material.dart';

import 'package:flutter_config_env/flutter_config_env.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await FlutterConfigEnv.loadEnvVariables();

  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    var allValues = <Widget>[];

    FlutterConfigEnv.variables.forEach((k, v) {
      allValues.add(Text('$k: $v'));
    });

    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
            child: Column(children: [
          ...allValues,
          const SizedBox(
            height: 20,
          ),
          Text('Values of fabric Id: ${FlutterConfigEnv.get('FABRIC_ID')}')
        ])),
      ),
    );
  }
}

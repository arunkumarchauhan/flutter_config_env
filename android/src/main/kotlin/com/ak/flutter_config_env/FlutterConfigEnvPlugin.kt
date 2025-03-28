package com.ak.flutter_config_env

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import android.content.Context
import android.content.res.Resources
import android.util.Log
import androidx.annotation.NonNull

import java.lang.reflect.Field

/** FlutterConfigEnvPlugin */
class FlutterConfigEnvPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel
  private  var applicationContext: Context? = null
  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    applicationContext = flutterPluginBinding.applicationContext
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_config_env")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(call: MethodCall, result: MethodChannel.Result): Unit {
    if (call.method == "loadEnvVariables") {
      val variables = loadEnvVariables()
      result.success(variables)
    } else {
      result.notImplemented()
    }
  }


  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
    applicationContext = null
  }
  private fun loadEnvVariables(): Map<String, Any?> {
    val variables = hashMapOf<String, Any?>()

    try {
      val context = applicationContext!!.applicationContext

      val resId = context.resources.getIdentifier("build_config_package", "string", context.packageName)
      val className: String = try {
        context.getString(resId)
      } catch (e: Resources.NotFoundException) {
        applicationContext!!.packageName
      }

      val clazz = Class.forName("$className.BuildConfig")

      fun extractValue(f: Field): Any? {
        return try {
          f.get(null)
        } catch (e: IllegalArgumentException) {
          null
        } catch (e: IllegalAccessException) {
          null
        }
      }

      clazz.declaredFields.forEach {
        variables += it.name to extractValue(it)
      }
    } catch (e: ClassNotFoundException) {
      Log.d("FlutterConfigEnv", "Could not access BuildConfig")
    }
    return variables
  }
}

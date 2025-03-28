plugins {
    id("com.android.application")
    id("kotlin-android")
    // The Flutter Gradle Plugin must be applied after the Android and Kotlin Gradle plugins.
    id("dev.flutter.flutter-gradle-plugin")
}

apply(from = "${project(":flutter_config_env").projectDir}/dotenv.gradle")

android {
    namespace = "com.ak.flutter_config_env_example"
    compileSdk = flutter.compileSdkVersion
    ndkVersion = "27.0.12077973"
   // ndkVersion = flutter.ndkVersion
    buildFeatures.buildConfig = true
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    defaultConfig {
        // TODO: Specify your own unique Application ID (https://developer.android.com/studio/build/application-id.html).
        applicationId = "com.ak.flutter_config_env_example"
        // You can update the following values to match your application needs.
        // For more information, see: https://flutter.dev/to/review-gradle-config.
        minSdk = flutter.minSdkVersion
        targetSdk = flutter.targetSdkVersion
        versionCode = flutter.versionCode
        versionName = flutter.versionName
    }

    buildTypes {
        release {
            // TODO: Add your own signing config for the release build.
            // Signing with the debug keys for now, so `flutter run --release` works.
            signingConfig = signingConfigs.getByName("debug")
        }
    }


    flavorDimensions("flavors")
    productFlavors {
        create("develop") {
            dimension = "flavors"
        }
        create("staging") {
            dimension = "flavors"
        }
        create("production") {
            dimension = "flavors"
        }
    }

}

flutter {
    source = "../.."
}

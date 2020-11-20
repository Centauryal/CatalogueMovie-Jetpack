plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(Dependencies.ANDROID_COMPILE_SDK_VERSION)
    buildToolsVersion(Dependencies.ANDROID_BUILD_TOOLS_VERSION)

    buildFeatures {
        dataBinding = true
    }

    defaultConfig {
        applicationId = "com.centaury.cataloguemovie"
        minSdkVersion(Dependencies.ANDROID_MIN_SDK_VERSION)
        targetSdkVersion(Dependencies.ANDROID_TARGET_SDK_VERSION)
        versionCode(Dependencies.ANDROID_VERSION_CODE)
        versionName(Dependencies.ANDROID_VERSION_NAME)

        testInstrumentationRunner = Dependencies.ANDROID_TEST_INSTRUMENTATION
        vectorDrawables.useSupportLibrary = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isDebuggable = true
        }

        val imageUrl = project.properties["IMAGE_URL"] as String
        buildTypes.forEach { buildType ->
            buildType.buildConfigField("String", "IMAGE_URL", imageUrl)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(AppDependencies.appDependencies)
    kapt(AppDependencies.kaptAppDependencies)
    androidTestImplementation(AppDependencies.androidTestAppDependencies)
}

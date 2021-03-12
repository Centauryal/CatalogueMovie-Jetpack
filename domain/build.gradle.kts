plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(Dependencies.ANDROID_COMPILE_SDK_VERSION)
    buildToolsVersion(Dependencies.ANDROID_BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdkVersion(Dependencies.ANDROID_MIN_SDK_VERSION)
        targetSdkVersion(Dependencies.ANDROID_TARGET_SDK_VERSION)

        testInstrumentationRunner = Dependencies.ANDROID_TEST_INSTRUMENTATION
        consumerProguardFiles(Dependencies.PROGUARD_CONSUMER_RULES)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
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
    implementation(AppDependencies.domainDependencies)
    testImplementation(AppDependencies.testImplementationDependencies)
}
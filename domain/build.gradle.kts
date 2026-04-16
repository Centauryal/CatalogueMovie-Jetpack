plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.centaury.cataloguemovie"
    compileSdk = AppConfig.ANDROID_COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = AppConfig.ANDROID_MIN_SDK_VERSION

        testInstrumentationRunner = AppConfig.ANDROID_TEST_INSTRUMENTATION
        consumerProguardFiles(AppConfig.PROGUARD_CONSUMER_RULES)
    }

    testOptions {
        targetSdk = AppConfig.ANDROID_TARGET_SDK_VERSION
    }

    lint {
        targetSdk = AppConfig.ANDROID_TARGET_SDK_VERSION
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        debug {
            isJniDebuggable = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}

dependencies {
    implementation(libs.bundles.domain)
    testImplementation(libs.bundles.test)
}
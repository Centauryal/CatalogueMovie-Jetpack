plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.centaury.cataloguemovie"
    compileSdk = AppConfig.ANDROID_COMPILE_SDK_VERSION

    buildFeatures {
        dataBinding = true
        compose = true
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.centaury.cataloguemovie"
        minSdk = AppConfig.ANDROID_MIN_SDK_VERSION
        targetSdk = AppConfig.ANDROID_TARGET_SDK_VERSION
        versionCode = AppConfig.ANDROID_VERSION_CODE
        versionName = AppConfig.ANDROID_VERSION_NAME

        testInstrumentationRunner = AppConfig.ANDROID_TEST_INSTRUMENTATION
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }

        val imageUrl = project.properties["IMAGE_URL"] as String
        buildTypes.forEach { buildType ->
            buildType.buildConfigField("String", "IMAGE_URL", imageUrl)
        }
    }

    bundle {
        language.enableSplit = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    setDynamicFeatures(mutableSetOf(":favorite", ":search"))
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}

dependencies {
    implementation(project(AppConfig.PROJECT_DATA))
    implementation(project(AppConfig.PROJECT_DOMAIN))
    implementation(libs.bundles.app)
    implementation(platform(libs.androidx.compose.bom))
    ksp(libs.bundles.kspApp)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.bundles.androidTestApp)
    debugImplementation(libs.bundles.debugApp)
}

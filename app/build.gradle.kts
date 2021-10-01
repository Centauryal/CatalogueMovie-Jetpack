plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Dependencies.ANDROID_COMPILE_SDK_VERSION
    buildToolsVersion = Dependencies.ANDROID_BUILD_TOOLS_VERSION

    buildFeatures {
        dataBinding = true
    }

    defaultConfig {
        applicationId = "com.centaury.cataloguemovie"
        minSdk = Dependencies.ANDROID_MIN_SDK_VERSION
        targetSdk = Dependencies.ANDROID_TARGET_SDK_VERSION
        versionCode = Dependencies.ANDROID_VERSION_CODE
        versionName = Dependencies.ANDROID_VERSION_NAME

        testInstrumentationRunner = Dependencies.ANDROID_TEST_INSTRUMENTATION
        vectorDrawables.useSupportLibrary = true
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

    kotlinOptions {
        jvmTarget = "11"
    }

    setDynamicFeatures(mutableSetOf(":favorite", ":search"))
}

dependencies {
    implementation(project(AppDependencies.projectData))
    implementation(project(AppDependencies.projectDomain))
    implementation(AppDependencies.appDependencies)
    kapt(AppDependencies.kaptAppDependencies)
    androidTestImplementation(AppDependencies.androidTestAppDependencies)
    debugImplementation(AppDependencies.debugAppDependencies)
}

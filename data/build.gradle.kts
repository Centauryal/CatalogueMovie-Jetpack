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
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isDebuggable = true
        }

        val apiKey = project.properties["API_KEY"] as String
        val baseUrl = project.properties["BASE_URL"] as String
        buildTypes.forEach { buildType ->
            buildType.buildConfigField("String", "API_KEY", apiKey)
            buildType.buildConfigField("String", "BASE_URL", baseUrl)
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
    implementation(project(":domain"))
    implementation(AppDependencies.dataDependencies)
    kapt(AppDependencies.kaptDataDependencies)
    api(AppDependencies.apiDataDependencies)
    testImplementation(AppDependencies.testImplementationDependencies)
}
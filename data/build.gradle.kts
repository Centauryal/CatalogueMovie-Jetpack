plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Dependencies.ANDROID_COMPILE_SDK_VERSION
    buildToolsVersion = Dependencies.ANDROID_BUILD_TOOLS_VERSION

    defaultConfig {
        minSdk = Dependencies.ANDROID_MIN_SDK_VERSION
        targetSdk = Dependencies.ANDROID_TARGET_SDK_VERSION

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
            isJniDebuggable = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }

        val apiKey = project.properties["API_KEY"] as String
        val baseUrl = project.properties["BASE_URL"] as String
        val passPhrase = project.properties["PASS_PHRASE"] as String
        val hostname = project.properties["HOSTNAME"] as String
        buildTypes.forEach { buildType ->
            buildType.buildConfigField("String", "API_KEY", apiKey)
            buildType.buildConfigField("String", "BASE_URL", baseUrl)
            buildType.buildConfigField("String", "PASS_PHRASE", passPhrase)
            buildType.buildConfigField("String", "HOSTNAME", hostname)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(AppDependencies.projectDomain))
    implementation(AppDependencies.dataDependencies)
    kapt(AppDependencies.kaptDataDependencies)
    api(AppDependencies.apiDataDependencies)
    testImplementation(AppDependencies.testImplementationDependencies)
}
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.centaury.cataloguemovie"
    compileSdk = AppConfig.ANDROID_COMPILE_SDK_VERSION

    buildFeatures {
        buildConfig = true
    }

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
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}

dependencies {
    implementation(project(AppConfig.PROJECT_DOMAIN))
    implementation(libs.bundles.data)
    ksp(libs.bundles.kspData)
    api(libs.bundles.apiData)
    testImplementation(libs.bundles.test)
}
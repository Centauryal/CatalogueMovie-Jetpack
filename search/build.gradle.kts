plugins {
    alias(libs.plugins.android.dynamic.feature)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
}
android {
    namespace = "com.centaury.cataloguemovie.search"
    compileSdk = AppConfig.ANDROID_COMPILE_SDK_VERSION

    buildFeatures {
        dataBinding = true
        compose = true
        buildConfig = true
    }

    defaultConfig {
        minSdk = AppConfig.ANDROID_MIN_SDK_VERSION

        testInstrumentationRunner = AppConfig.ANDROID_TEST_INSTRUMENTATION
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
        debug {
            isDebuggable = true
        }

        val imageUrl = project.properties["IMAGE_URL"] as String
        buildTypes.forEach { buildType ->
            buildType.buildConfigField("String", "IMAGE_URL", imageUrl)
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
    implementation(project(":app"))
    implementation(project(AppConfig.PROJECT_DATA))
    implementation(project(AppConfig.PROJECT_DOMAIN))
    implementation(libs.bundles.app)
    implementation(platform(libs.androidx.compose.bom))
    ksp(libs.bundles.kspApp)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.bundles.androidTestApp)
}
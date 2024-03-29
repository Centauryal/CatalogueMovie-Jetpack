import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 11/18/2020.
 */
object Dependencies {

    // Version
    const val GRADLE_VERSION = "7.1.1"
    const val KOTLIN_VERSION = "1.6.10"

    // Project
    const val PROJECT_APP = ":app"
    const val PROJECT_DATA = ":data"
    const val PROJECT_DOMAIN = ":domain"

    // Android Core
    const val ANDROID_COMPILE_SDK_VERSION = 31
    const val ANDROID_BUILD_TOOLS_VERSION = "31.0.0"
    const val ANDROID_MIN_SDK_VERSION = 23
    const val ANDROID_TARGET_SDK_VERSION = 31
    const val ANDROID_VERSION_CODE = 5
    const val ANDROID_VERSION_NAME = "2.0"

    const val ANDROID_TEST_INSTRUMENTATION = "androidx.test.runner.AndroidJUnitRunner"
    const val PROGUARD_CONSUMER_RULES = "consumer-rules.pro"

    // Android Libraries
    const val CORE_KTX_VERSION = "1.7.0"
    const val ANDROID_ANNOTATIONS_VERSION = "1.3.0"
    const val ANDROID_LEGACY_VERSION = "1.0.0"
    const val APP_COMPAT_VERSION = "1.4.1"
    const val CONSTRAINT_LAYOUT_VERSION = "2.1.3"
    const val MATERIAL_VERSION = "1.5.0"
    const val CARD_VIEW_VERSION = "1.0.0"
    const val RECYCLER_VIEW_VERSION = "1.2.1"
    const val ROOM_VERSION = "2.4.1"
    const val ACTIVITY_KTX_VERSION = "1.4.0"
    const val FRAGMENT_KTX_VERSION = "1.4.1"

    // Network Libraries
    const val RETROFIT_VERSION = "2.9.0"
    const val LOGGING_INTERCEPTOR_VERSION = "4.9.3"

    // DI Libraries
    const val DAGGER2_VERSION = "2.40.5"
    const val JAVAX_VERSION = "1"

    // ReactiveX Libraries
    const val RX_JAVA_VERSION = "2.2.21"
    const val RX_ANDROID_VERSION = "2.1.1"

    // Other Libraries
    const val COIL_VERSION = "1.4.0"
    const val TIMBER_VERSION = "5.0.1"
    const val SHIMMER_VERSION = "0.5.0"
    const val LOTTIE_VERSION = "4.2.1"
    const val INTUIT_VERSION = "1.0.6"
    const val SQL_CIPHER_VERSION = "4.5.0"

    //Testing Libraries
    const val ANDROIDX_TEST_VERSION = "1.4.0"
    const val ANDROIDX_JUNIT_VERSION = "1.1.3"
    const val ARCH_LIFECYCLE_TEST_VERSION = "2.1.0"
    const val JUNIT_VERSION = "4.13.2"
    const val MOCKITO_VERSION = "4.3.1"
    const val MOCKITO_KOTLIN_VERSION = "4.0.0"
    const val ESPRESSO_VERSION = "3.4.0"
    const val LEAK_CANARY_VERSION = "2.7"
    //const val LEAK_CANARY_VERSION = "2.8.1"
}

object BuildPlugins {
    const val ANDROID_GRADLE = "com.android.tools.build:gradle:${Dependencies.GRADLE_VERSION}"
    const val KOTLIN_GRADLE =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Dependencies.KOTLIN_VERSION}"
}

object AndroidLibs {
    const val ANDROID_CORE = "androidx.core:core-ktx:${Dependencies.CORE_KTX_VERSION}"
    const val APP_COMPACT = "androidx.appcompat:appcompat:${Dependencies.APP_COMPAT_VERSION}"
    const val ANDROID_MATERIAL =
        "com.google.android.material:material:${Dependencies.MATERIAL_VERSION}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Dependencies.CONSTRAINT_LAYOUT_VERSION}"
    const val CARD_VIEW = "androidx.cardview:cardview:${Dependencies.CARD_VIEW_VERSION}"
    const val RECYCLER_VIEW =
        "androidx.recyclerview:recyclerview:${Dependencies.RECYCLER_VIEW_VERSION}"
    const val ANNOTATION =
        "androidx.annotation:annotation:${Dependencies.ANDROID_ANNOTATIONS_VERSION}"
    const val LEGACY_SUPPORT =
        "androidx.legacy:legacy-support-v4:${Dependencies.ANDROID_LEGACY_VERSION}"
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Dependencies.ROOM_VERSION}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Dependencies.ROOM_VERSION}"
    const val ROOM_RX_JAVA = "androidx.room:room-rxjava2:${Dependencies.ROOM_VERSION}"
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${Dependencies.ACTIVITY_KTX_VERSION}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Dependencies.FRAGMENT_KTX_VERSION}"
}

object NetworkLibs {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Dependencies.RETROFIT_VERSION}"
    const val RETROFIT_ADAPTER =
        "com.squareup.retrofit2:adapter-rxjava2:${Dependencies.RETROFIT_VERSION}"
    const val RETROFIT_CONVERTER =
        "com.squareup.retrofit2:converter-moshi:${Dependencies.RETROFIT_VERSION}"
    const val LOGGING_INTERCEPTOR =
        "com.squareup.okhttp3:logging-interceptor:${Dependencies.LOGGING_INTERCEPTOR_VERSION}"
}

object DependencyInjectionLibs {
    const val DAGGER_CORE = "com.google.dagger:dagger:${Dependencies.DAGGER2_VERSION}"
    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${Dependencies.DAGGER2_VERSION}"
    const val JAVAX = "javax.inject:javax.inject:${Dependencies.JAVAX_VERSION}"
}

object ReactiveXLibs {
    const val RX_JAVA = "io.reactivex.rxjava2:rxjava:${Dependencies.RX_JAVA_VERSION}"
    const val RX_ANDROID = "io.reactivex.rxjava2:rxandroid:${Dependencies.RX_ANDROID_VERSION}"
}

object ImageLibs {
    const val COIL = "io.coil-kt:coil:${Dependencies.COIL_VERSION}"
}

object LoggingLibs {
    const val TIMBER = "com.jakewharton.timber:timber:${Dependencies.TIMBER_VERSION}"
}

object OtherLibs {
    const val FACEBOOK_SHIMMER = "com.facebook.shimmer:shimmer:${Dependencies.SHIMMER_VERSION}"
    const val LOTTIE = "com.airbnb.android:lottie:${Dependencies.LOTTIE_VERSION}"
    const val SDP_ANDROID = "com.intuit.sdp:sdp-android:${Dependencies.INTUIT_VERSION}"
    const val SSP_ANDROID = "com.intuit.ssp:ssp-android:${Dependencies.INTUIT_VERSION}"
    const val SQL_CIPHER =
        "net.zetetic:android-database-sqlcipher:${Dependencies.SQL_CIPHER_VERSION}"
}

object TestLibs {
    const val JUNIT = "junit:junit:${Dependencies.JUNIT_VERSION}"
    const val MOCKITO_CORE = "org.mockito:mockito-core:${Dependencies.MOCKITO_VERSION}"
    const val MOCKITO_INLINE = "org.mockito:mockito-inline:${Dependencies.MOCKITO_VERSION}"
    const val MOCKITO_KOTLIN2 =
        "org.mockito.kotlin:mockito-kotlin:${Dependencies.MOCKITO_KOTLIN_VERSION}"
    const val CORE_TESTING =
        "androidx.arch.core:core-testing:${Dependencies.ARCH_LIFECYCLE_TEST_VERSION}"
    const val EXT_JUNIT = "androidx.test.ext:junit:${Dependencies.ANDROIDX_JUNIT_VERSION}"
    const val RULES = "androidx.test:rules:${Dependencies.ANDROIDX_TEST_VERSION}"
    const val ESPRESSO_CORE =
        "androidx.test.espresso:espresso-core:${Dependencies.ESPRESSO_VERSION}"
    const val ESPRESSO_CONTRIB =
        "androidx.test.espresso:espresso-contrib:${Dependencies.ESPRESSO_VERSION}"
    const val LEAK_CANARY =
        "com.squareup.leakcanary:leakcanary-android:${Dependencies.LEAK_CANARY_VERSION}"
}

object AppDependencies {
    const val projectApp = Dependencies.PROJECT_APP
    const val projectData = Dependencies.PROJECT_DATA
    const val projectDomain = Dependencies.PROJECT_DOMAIN

    val appDependencies = arrayListOf<String>().apply {
        add(AndroidLibs.ANDROID_CORE)
        add(AndroidLibs.APP_COMPACT)
        add(AndroidLibs.ANDROID_MATERIAL)
        add(AndroidLibs.CONSTRAINT_LAYOUT)
        add(AndroidLibs.CARD_VIEW)
        add(AndroidLibs.RECYCLER_VIEW)
        add(AndroidLibs.ANNOTATION)
        add(AndroidLibs.LEGACY_SUPPORT)
        add(AndroidLibs.ACTIVITY_KTX)
        add(AndroidLibs.FRAGMENT_KTX)
        add(DependencyInjectionLibs.DAGGER_CORE)
        add(DependencyInjectionLibs.JAVAX)
        add(ReactiveXLibs.RX_JAVA)
        add(ReactiveXLibs.RX_ANDROID)
        add(ImageLibs.COIL)
        add(OtherLibs.FACEBOOK_SHIMMER)
        add(OtherLibs.LOTTIE)
        add(OtherLibs.SDP_ANDROID)
        add(OtherLibs.SSP_ANDROID)
        add(LoggingLibs.TIMBER)
    }

    val kaptAppDependencies = arrayListOf<String>().apply {
        add(DependencyInjectionLibs.DAGGER_COMPILER)
    }

    val androidTestAppDependencies = arrayListOf<String>().apply {
        add(TestLibs.JUNIT)
        add(TestLibs.EXT_JUNIT)
        add(TestLibs.RULES)
        add(TestLibs.ESPRESSO_CORE)
        add(TestLibs.ESPRESSO_CONTRIB)
    }

    val debugAppDependencies = arrayListOf<String>().apply {
        add(TestLibs.LEAK_CANARY)
    }

    val dataDependencies = arrayListOf<String>().apply {
        add(DependencyInjectionLibs.DAGGER_CORE)
        add(DependencyInjectionLibs.JAVAX)
        add(ReactiveXLibs.RX_JAVA)
        add(OtherLibs.SQL_CIPHER)
    }

    val kaptDataDependencies = arrayListOf<String>().apply {
        add(DependencyInjectionLibs.DAGGER_COMPILER)
        add(AndroidLibs.ROOM_COMPILER)
    }

    val apiDataDependencies = arrayListOf<String>().apply {
        add(AndroidLibs.ROOM_RUNTIME)
        add(AndroidLibs.ROOM_RX_JAVA)
        add(NetworkLibs.RETROFIT)
        add(NetworkLibs.RETROFIT_ADAPTER)
        add(NetworkLibs.RETROFIT_CONVERTER)
        add(NetworkLibs.LOGGING_INTERCEPTOR)
    }

    val domainDependencies = arrayListOf<String>().apply {
        add(DependencyInjectionLibs.DAGGER_CORE)
        add(DependencyInjectionLibs.JAVAX)
        add(ReactiveXLibs.RX_JAVA)
        add(ReactiveXLibs.RX_ANDROID)
    }

    val testImplementationDependencies = arrayListOf<String>().apply {
        add(TestLibs.JUNIT)
        add(TestLibs.MOCKITO_CORE)
        add(TestLibs.MOCKITO_INLINE)
        add(TestLibs.MOCKITO_KOTLIN2)
        add(TestLibs.CORE_TESTING)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.api(list: List<String>) {
    list.forEach { dependency ->
        add("api", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.debugImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("debugImplementation", dependency)
    }
}
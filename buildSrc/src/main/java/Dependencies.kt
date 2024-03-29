object Sdk {
    val min = 23
    val target = 31
    val compile = target
}

object Versions {
    val appcompat = "1.1.0"
    val core = "1.2.0-rc01"
    val constraintlayout = "2.0.0-beta4"
    val annotation = "1.1.0"
    val cardview = "1.0.0"
    val recyclerview = "1.1.0"
    val viewpager = "1.0.0"
    val room = "2.2.3"
    val lifecycle = "2.2.0"

    val kotlin = "1.6.21"
    val coroutines = "1.6.3"

    val retrofit = "2.7.1"
    val okhttp3 = "4.9.3"

    val material = "1.2.0-alpha03"
    val dagger = "2.39.1"

    val coil = "1.4.0"
    val circleindicator = "2.1.6"

    val junit = "4.12"
    val test_extension = "1.1.0"
    val test_runner = "1.2.0"
    val test_espresso = "3.2.0"
}

object Deps {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val core = "androidx.core:core-ktx:${Versions.core}"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    val annotation = "androidx.annotation:annotation:${Versions.annotation}"
    val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val viewpager = "androidx.viewpager2:viewpager2:${Versions.viewpager}"
    val room = "androidx.room:room-runtime:${Versions.room}"

    val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    val lifecycle_common = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    val lifecycle_viewmodel_extension ="androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    val kotlin_standard = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
    val okhttp3_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"

    val material = "com.google.android.material:material:${Versions.material}"
    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    val coil = "io.coil-kt:coil:${Versions.coil}"
    val circleindicator = "me.relex:circleindicator:${Versions.circleindicator}"

    val junit = "junit:junit:${Versions.junit}"
    val test_extension = "androidx.test.ext:junit:${Versions.test_extension}"
    val test_runner = "androidx.test:runner:${Versions.test_runner}"
    val test_espresso = "androidx.test.espresso:espresso-core:${Versions.test_espresso}"
    val coroutine_test = "testImplementation 'org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
}
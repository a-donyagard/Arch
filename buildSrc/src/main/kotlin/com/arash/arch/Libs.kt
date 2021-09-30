package com.arash.arch

@Suppress("unused")
object Libs {
    object Plugins {
        const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val gradleVersions =
            "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersions}"
        const val safeArgs =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.safeArgs}"
        const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    }

    object Modules {
        const val data = ":data"
    }

    object Jetpack {
        const val lifecycleExtensions =
            "androidx.lifecycle:lifecycle-extensions:${Versions.archLifecycleExtensions}"
        const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.arch}"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.arch}"
        const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.arch}"
        const val room = "androidx.room:room-runtime:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val annotations = "androidx.annotation:annotation:${Versions.material}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigationComponent}"
        const val navigationUi =
            "androidx.navigation:navigation-ui-ktx:${Versions.navigationComponent}"
    }

    object Common {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val arrowCore = "io.arrow-kt:arrow-core:${Versions.arrow}"
        const val arrowSyntax = "io.arrow-kt:arrow-syntax:${Versions.arrow}"
        const val arrowMeta = "io.arrow-kt:arrow-meta:${Versions.arrow}"
        const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerAndroid}"
        const val daggerAndroid =
            "com.google.dagger:dagger-android-support:${Versions.daggerAndroid}"
        const val daggerAndroidCompiler =
            "com.google.dagger:dagger-android-processor:${Versions.daggerAndroid}"
        const val gson = "com.google.code.gson:gson:${Versions.gson}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
        const val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
        const val stetho_OkHttp = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
        const val retrofitCoroutines =
            "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutines}"
        const val shapeOfView = "com.github.florent37:shapeofview:${Versions.shapeOfView}"
        const val arrowLegacySupport =
            "androidx.legacy:legacy-support-v4:${Versions.arrowLegacySupport}"
        const val ratingBar = "com.github.giswangsj:AndRatingBar:${Versions.ratingBar}"
        const val exoPlayerCore =
            "com.google.android.exoplayer:exoplayer-core:${Versions.exoPlayer}"
        const val exoPlayerDash =
            "com.google.android.exoplayer:exoplayer-dash:${Versions.exoPlayer}"
        const val exoPlayerUi = "com.google.android.exoplayer:exoplayer-ui:${Versions.exoPlayer}"
        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        const val balloon = "com.github.skydoves:balloon:${Versions.balloon}"
        const val canary = "com.squareup.leakcanary:leakcanary-android:${Versions.canary}"
        const val persianDatePicker =
            "com.github.aliab:Persian-Date-Picker-Dialog:${Versions.persianDatePicker}"
        const val imageCropper =
            "com.theartofdev.edmodo:android-image-cropper:${Versions.imageCropper}"
    }

    object Testing {
        const val junit = "junit:junit:${Versions.junit}"
        const val testRunner = "androidx.test:runner:${Versions.testRunner}"
        const val esperesso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val mockitoKotlin = "com.nhaarman:mockito-kotlin-kt1.1:${Versions.mockitoKotlin}"
        const val archTesting = "androidx.arch.core:core-testing:${Versions.archTest}"
        const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    }
}

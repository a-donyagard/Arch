import com.arash.arch.Libs
plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlin-kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation(Libs.Common.coroutines)
    implementation(Libs.Common.arrowCore)
    implementation(Libs.Common.javaInject)
}
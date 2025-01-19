plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android)
    kotlin("kapt")
}

android {
    namespace = "com.vodafone.core"
    compileSdk = 35
    android.buildFeatures.buildConfig = true

    defaultConfig {
        minSdk = 24

        buildConfigField("String", "API_TOKEN", "\"b00bf94335194bc7b1a172330251601\"")
        buildConfigField("String", "BASE_URL", "\"https://api.weatherapi.com/v1/\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    basicDependencies()
    hiltDependencies()
    composeDependencies()
}
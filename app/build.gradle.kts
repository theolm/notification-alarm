import config.ConfigData

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.compose.compiler)
    id("detekt-setup")
}

android {
    namespace = ConfigData.applicationBundle
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = ConfigData.applicationBundle
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = 1
        versionName = "v0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kotlin {
        sourceSets.all {
            languageSettings.enableLanguageFeature("ExplicitBackingFields")
        }
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }
    kotlinOptions {
        jvmTarget = ConfigData.javaVersion.toString()
    }

    buildFeatures {
        compose = true
    }

    composeCompiler {
        enableStrongSkippingMode = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
        jniLibs.useLegacyPackaging = true
    }
}

dependencies {
    implementation(projects.data)
    implementation(projects.domain)

    implementation(platform(libs.compose.bom))

    implementation(libs.androidx.coreKtx)
    implementation(libs.androidx.appCompat)
    implementation(libs.androidx.protoDataStore)
    implementation(libs.androidx.activityCompose)
    implementation(libs.androidx.viewModelCompose)

    implementation(libs.material)
    implementation(libs.compose.foundation)
    implementation(libs.compose.materialIconsExtended)
    implementation(libs.compose.animationGraphics)
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.windowSizeClass)
    implementation(libs.compose.runtime)
    implementation(libs.compose.uiToolingPreview)
    implementation(libs.compose.navigation)

    implementation(libs.kotlin.serialization)
    implementation(libs.koin.core)
    implementation(libs.koin.compose)

    testImplementation(libs.junit.junit)

    debugImplementation(libs.compose.uiTooling)

}
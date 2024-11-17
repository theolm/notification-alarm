import config.ConfigData

plugins {
    id("module-setup")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = ConfigData.applicationBundle + ".domain"
}

dependencies {
    implementation(libs.kotlin.serialization)
    implementation(libs.koin.core)
}
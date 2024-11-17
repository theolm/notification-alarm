import config.ConfigData

plugins {
    id("module-setup")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = ConfigData.applicationBundle + ".data"
}

dependencies {
    implementation(projects.domain)
    implementation(libs.androidx.protoDataStore)
    implementation(libs.kotlin.serialization)
    implementation(libs.koin.core)
}
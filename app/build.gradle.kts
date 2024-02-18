
plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.persons"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.persons"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    //noinspection GradleCompatible,
    implementation ("androidx.appcompat:appcompat:1.6.1")
    //noinspection GradleCompatible
    implementation ("androidx.recyclerview:recyclerview:1.0.0")
    implementation ("com.opencsv:opencsv:5.6")
    //noinspection GradleCompatible
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
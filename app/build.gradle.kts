plugins {
    id("com.android.application")
}

android {
    namespace = "com.vishal.cinemate"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vishal.cinemate"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    implementation ("com.airbnb.android:lottie:3.7.0")


    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation ("com.google.android.flexbox:flexbox:3.0.0")
    implementation ("androidx.navigation:navigation-common:2.7.7")
    implementation ("androidx.navigation:navigation-fragment:2.7.7")
    implementation ("androidx.navigation:navigation-ui:2.7.7")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")


    implementation ("com.github.fornewid:neumorphism:0.3.0")



    implementation ("com.github.bumptech.glide:okhttp3-integration:4.12.0") // For OkHttp integration (optional)
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

    implementation ("com.github.bumptech.glide:glide:4.12.0")




}
plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.byd_sushi"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.byd_sushi"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.material)
    implementation("androidx.biometric:biometric:1.1.0")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // BARU: Pertemuan 11
    implementation("androidx.recyclerview:recyclerview:1.3.2")          // RecyclerView
    implementation("androidx.viewpager2:viewpager2:1.0.0")              // ViewPager2 (Tab + Onboarding)
    implementation("com.tbuonomo:dotsindicator:5.1.0")                  // Indikator dots onboarding
    implementation("com.github.bumptech.glide:glide:4.16.0")            // Load gambar dari URL

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

}
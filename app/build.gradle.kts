plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.androidx.navigation.safe.args)
}

android {
    namespace = "com.example.myapplication22"
    compileSdk = 36   // ✅ Giữ nguyên 36

    defaultConfig {
        applicationId = "com.example.myapplication22"
        minSdk = 36     // ✅ Giữ nguyên 36
        targetSdk = 36  // ✅ Giữ nguyên 36
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
        // ⚙️ Bật Java 11 (cần cho Navigation & ViewBinding)
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    // ✅ Bật ViewBinding để thao tác với layout XML
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // 🧩 Thư viện cơ bản
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation("androidx.core:core-ktx:1.13.1")

    // 🧭 Fragment + Navigation Component
    implementation("androidx.fragment:fragment:1.8.5")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-ui:2.7.7")
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    
    // 🔐 Safe Args - Truyền dữ liệu an toàn giữa các Fragment
    // Safe Args sẽ tự động generate code khi build project
    
    // 🏗️ ViewModel & Lifecycle - Quản lý state và lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.8.6")
    implementation("androidx.lifecycle:lifecycle-livedata:2.8.6")
    implementation("androidx.lifecycle:lifecycle-runtime:2.8.6")

    // 🧪 Kiểm thử
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

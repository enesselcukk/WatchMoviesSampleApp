package configs

object Dependencies {
    object Kotlin {
        const val version = "1.6.10"
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$version"

        object Coroutines {
            private const val version = "1.6.1"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        }
    }

    object AndroidX {
        object AppCompat {
            private const val version = "1.4.2"
            const val appcompat = "androidx.appcompat:appcompat:$version"
        }

        object Core {
            private const val version = "1.8.0"
            const val coreKtx = "androidx.core:core-ktx:$version"
        }

        object ConstraintLayout {
            private const val version = "2.1.4"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:$version"
        }

        object LegacySupport {
            private const val version = "1.0.0"
            const val legacy_support = "androidx.legacy:legacy-support-v4:$version"
        }

        object TestJunit {
            private const val version = "4.13.2"
            const val testImplementation = "junit:junit:$version"
        }

        object AndroidTest {
            private const val version = "1.1.3"
            const val androidTestExtImplementation = "androidx.test.ext:junit:$version"
        }

        object AndroidTestEspresso {
            private const val version = "3.4.0"
            const val androidTestImplementation = "androidx.test.espresso:espresso-core:$version"
        }

        object FragmentKtx {
            private const val version = "1.5.0"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
        }

        object ActivityKtx {
            private const val version = "1.4.0"
            const val activityKtx = "androidx.activity:activity-ktx:$version"
        }

        object Work {
            private const val version = "2.7.1"
            const val workKtx = "androidx.work:work-runtime-ktx:$version"
        }

        object Hilt {
            private const val version = "2.41"
            const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$version"
            const val compiler = "androidx.hilt:hilt-compiler:$version"
        }

        object Lifecycle {
            private const val version = "2.5.0"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val common = "androidx.lifecycle:lifecycle-common-java8:$version"
        }

        object Navigation {
            const val version = "2.5.1"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
        }

        object Room {
            private const val version = "2.4.2"
            const val runtime = "androidx.room:room-runtime:$version"
            const val compiler = "androidx.room:room-compiler:$version"
            const val ktx = "androidx.room:room-ktx:$version"
            const val roomPaging = "androidx.room:room-paging:$version"
            const val testing = "androidx.room:room-testing:$version"
        }
    }

    object Material {
        private const val version = "1.6.1"
        const val material = "com.google.android.material:material:$version"
    }

    object DaggerHilt {
        const val version = "2.42"
        const val hiltAndroid = "com.google.dagger:hilt-android:$version"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$version"
        const val json = "com.google.code.gson:gson:$version"
        const val jsonConverter = "com.squareup.retrofit2:converter-gson:$version"
    }

    object OkHttp {
        private const val version = "4.8.0"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$version"
//        const val urlConnection = "com.squareup.okhttp:okhttp-urlconnection:$version"

    }

    object Moshi {
        private const val version = "1.9.2"
        const val moshi = "com.squareup.moshi:moshi-kotlin:$version"
        const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
    }

    object Glide {
        private const val version = "4.13.0"
        const val glide = "com.github.bumptech.glide:glide:$version"
        const val compiler = "com.github.bumptech.glide:compiler:$version"
    }

    object Timber {
        private const val version = "4.1.2"
        const val timber = "com.jakewharton.timber:timber:$version"
    }

    object Paging {
        private const val version = "3.1.1"
        const val paging = "androidx.paging:paging-runtime:$version"
        const val pagingRxJava = "androidx.paging:paging-rxjava2:$version"
    }
}
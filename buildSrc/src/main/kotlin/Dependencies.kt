import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
    const val CORE_KTX = "androidx.core:core-ktx:1.15.0"
    const val WORK_RUNTIME = "androidx.work:work-runtime-ktx:2.10.0"

    // Lifecycle
    private const val GROUP_LIFECYCLE = "androidx.lifecycle:"
    const val LIFECYCLE_RUNTIME_KTX = "${GROUP_LIFECYCLE}lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
    const val LIFECYCLE_VIEWMODEL_KTX = "${GROUP_LIFECYCLE}lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
    const val LIFECYCLE_VIEWMODEL_COMPOSE = "${GROUP_LIFECYCLE}lifecycle-viewmodel-compose:${Versions.LIFECYCLE}"
    const val LIFECYCLE_LIVEDATA_KTX = "${GROUP_LIFECYCLE}lifecycle-livedata-ktx:${Versions.LIFECYCLE}"
    const val LIFECYCLE_RUNTIME_COMPOSE = "${GROUP_LIFECYCLE}lifecycle-runtime-compose:${Versions.LIFECYCLE}"

    // Activity Compose
    const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:${Versions.COMPOSE}"

    // Compose BOM
    const val COMPOSE_BOM = "androidx.compose:compose-bom:2024.12.01"

    // Compose Runtime
    private const val GROUP_COMPOSE_RUNTIME = "androidx.compose.runtime:"
    const val COMPOSE_LIVEDATA = "${GROUP_COMPOSE_RUNTIME}runtime-livedata:1.7.6"
    const val COMPOSE_RUNTIME = "${GROUP_COMPOSE_RUNTIME}runtime"

    // Compose UI
    private const val GROUP_COMPOSE_UI = "androidx.compose.ui:"
    const val COMPOSE_UI = "${GROUP_COMPOSE_UI}ui"
    const val COMPOSE_UI_GRAPHICS = "${GROUP_COMPOSE_UI}ui-graphics"
    const val COMPOSE_UI_TOOLING = "${GROUP_COMPOSE_UI}ui-tooling"
    const val COMPOSE_UI_TOOLING_PREVIEW = "${GROUP_COMPOSE_UI}ui-tooling-preview"

    // Material3
    const val COMPOSE_MATERIAL3 = "androidx.compose.material3:material3"

    // Navigation Compose
    const val COMPOSE_NAVIGATION = "androidx.navigation:navigation-compose:2.8.5"

    // Coil (Image Loading)
    private const val GROUP_COIL = "io.coil-kt.coil3:"
    const val COIL_COMPOSE = "${GROUP_COIL}coil-compose:${Versions.COIL}"
    const val COIL_NETWORK = "${GROUP_COIL}coil-network-okhttp:${Versions.COIL}"

    // Hilt (Dependency Injection)
    private const val GROUP_HILT = "com.google.dagger:"
    const val HILT_ANDROID = "${GROUP_HILT}hilt-android:${Versions.HILT}"
    const val HILT_COMPILER = "${GROUP_HILT}hilt-android-compiler:${Versions.HILT}"
    const val HILT_TESTING = "${GROUP_HILT}hilt-android-testing:${Versions.HILT}"

    private const val GROUP_HILT_ANDROIDX = "androidx.hilt:"
    const val HILT_WORK = "${GROUP_HILT_ANDROIDX}hilt-work:1.0.0"
    const val HILT_NAVIGATION_COMPOSE = "${GROUP_HILT_ANDROIDX}hilt-navigation-compose:1.2.0"

    // Networking (Retrofit, OkHttp)
    private const val GROUP_RETROFIT = "com.squareup.retrofit2:"
    const val RETROFIT = "${GROUP_RETROFIT}retrofit:${Versions.RETROFIT}"
    const val GSON_CONVERTER = "${GROUP_RETROFIT}converter-gson:${Versions.RETROFIT}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OK_HTTP}"
    const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OK_HTTP}"

    // Room (Database)
    private const val GROUP_ROOM = "androidx.room:"
    const val ROOM_RUNTIME = "${GROUP_ROOM}room-runtime:${Versions.ROOM}"
    const val ROOM_COMPILER = "${GROUP_ROOM}room-compiler:${Versions.ROOM}"
    const val ROOM_KTX = "${GROUP_ROOM}room-ktx:${Versions.ROOM}"

    // Testing
    const val JUNIT = "junit:junit:4.13.2"
    const val MOCKITO_KOTLIN = "org.mockito.kotlin:mockito-kotlin:5.4.0"
    const val MOCKITO_INLINE = "org.mockito:mockito-inline:5.2.0"
    const val MOCKITO_COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.1"
}

fun DependencyHandler.basicDependencies() {
    Dependencies.apply {
        implementation(CORE_KTX)
        implementation(WORK_RUNTIME)
        implementation(LIFECYCLE_RUNTIME_KTX)
        implementation(COMPOSE_ACTIVITY)
        implementation(platform(COMPOSE_BOM))
        androidTestImplementation(platform(COMPOSE_BOM))
        implementation(COMPOSE_UI)
        implementation(COMPOSE_UI_GRAPHICS)
        implementation(COMPOSE_MATERIAL3)
        debugImplementation(COMPOSE_UI_TOOLING)
        implementation(COMPOSE_UI_TOOLING_PREVIEW)
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")
        debugImplementation("androidx.compose.ui:ui-test-manifest")
    }
}

fun DependencyHandler.composeDependencies() {
    Dependencies.apply {
        implementation(COMPOSE_NAVIGATION)
        implementation(COMPOSE_RUNTIME)
        implementation(COIL_COMPOSE)
        implementation(COIL_NETWORK)
    }
}

fun DependencyHandler.lifecycleDependencies() {
    Dependencies.apply {
        implementation(LIFECYCLE_VIEWMODEL_KTX)
        implementation(LIFECYCLE_VIEWMODEL_COMPOSE)
        implementation(LIFECYCLE_LIVEDATA_KTX)
        implementation(LIFECYCLE_RUNTIME_COMPOSE)
        implementation(COMPOSE_LIVEDATA)
    }
}

fun DependencyHandler.hiltDependencies() {
    Dependencies.apply {
        implementation(HILT_ANDROID)
        kapt(HILT_COMPILER)
        implementation(HILT_NAVIGATION_COMPOSE)

        androidTestImplementation(HILT_TESTING)
        kaptAndroidTest(HILT_COMPILER)

        testImplementation(HILT_TESTING)
        kaptTest(HILT_COMPILER)

        implementation(HILT_WORK)
    }
}

fun DependencyHandler.retrofitDependencies() {
    Dependencies.apply {
        implementation(RETROFIT)
        implementation(GSON_CONVERTER)
        implementation(OKHTTP)
        implementation(OKHTTP_LOGGING_INTERCEPTOR)
    }
}

fun DependencyHandler.roomDependencies() {
    Dependencies.apply {
        implementation(ROOM_RUNTIME)
        implementation(ROOM_KTX)
        kapt(ROOM_COMPILER)
    }
}

fun DependencyHandler.unitTestDependencies() {
    Dependencies.apply {
        testImplementation(JUNIT)
        testImplementation(MOCKITO_KOTLIN)
        testImplementation(MOCKITO_INLINE)
        testImplementation(MOCKITO_COROUTINES)
    }
}
dependencies {
    implementation(Libs.guava)
    implementation(Libs.jerseyClient)
    testImplementation(Libs.junitJupiter)
    testRuntimeOnly(Libs.junitJupiterPlatformLauncher)
    testImplementation(Libs.assertj)
    testImplementation(Libs.mockito)
    testImplementation(Libs.mockitoJunitJupiter)
}

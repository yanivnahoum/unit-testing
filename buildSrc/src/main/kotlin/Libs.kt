object Libs {
    const val guava = "com.google.guava:guava:${Versions.guava}"
    const val jerseyClient = "org.glassfish.jersey.core:jersey-client:${Versions.jersey}"
    const val junitJupiter = "org.junit.jupiter:junit-jupiter:${Versions.junitJupiter}"

    // Version managed by BOM pulled in by the JUnit Jupiter dependency
    const val junitJupiterPlatformLauncher = "org.junit.platform:junit-platform-launcher"
    const val assertj = "org.assertj:assertj-core:${Versions.assertj}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoJunitJupiter = "org.mockito:mockito-junit-jupiter:${Versions.mockito}"
}

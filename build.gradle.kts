import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    java
}

allprojects {
    group = "com.att.tlv.training"
    version = "1.0.0-SNAPSHOT"

    tasks {
        withType<JavaCompile>().configureEach {
            options.release.set(11)
        }
    }
}

subprojects {
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    tasks.test {
        useJUnitPlatform()
        filter {
            includeTestsMatching("*")
        }
        testLogging {
            events("passed", "skipped", "failed")
            exceptionFormat = TestExceptionFormat.FULL
            showStandardStreams = true
        }
    }
}

import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    java
}

allprojects {
    group = "com.att.tlv.training"
    version = "1.0.0-SNAPSHOT"
}

subprojects {
    repositories {
        mavenCentral()
    }

    apply(plugin = "java")

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
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

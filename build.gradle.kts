import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    java
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

allprojects {
    group = "com.att.tlv.training"
    version = "1.0.0-SNAPSHOT"
}

subprojects {
    apply(plugin = "java")

    repositories {
        mavenLocal()
        maven {
            url = uri("http://repo.maven.apache.org/maven2")
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

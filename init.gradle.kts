allprojects {
    repositories {
        maven {
            url = uri("https://repocentral.it.att.com:8084/nexus/content/groups/att-public-group")
            credentials {
                username = "pMt5iezY"
                password = "ugX2TehhT8Tiqz23BIfqTnBWLC9Uk_t92_9h3DzZplXw"
            }
        }
    }
}
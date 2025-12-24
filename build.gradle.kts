plugins {
    kotlin("jvm") version "2.2.21"
}

group = "me.jereme"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.xerial:sqlite-jdbc:3.46.0.0") // Check for latest version
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.mockito:mockito-core:5.11.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1") // Kotlin helper for Mockito
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}
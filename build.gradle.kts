plugins {
    kotlin("jvm") version "2.0.10"
}

group = "prog2425.dam1.seguros"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("at.favre.lib:bcrypt:0.9.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
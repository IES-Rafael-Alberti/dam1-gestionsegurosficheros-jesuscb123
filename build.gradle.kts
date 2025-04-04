plugins {
    kotlin("jvm") version "2.0.10"
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "prog2425.dam1.seguros"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("prog2425.dam1.seguros.Main.kt")
}
repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("at.favre.lib:bcrypt:0.9.0")
    implementation("org.jline:jline:3.29.0")
}

tasks.test {
    useJUnitPlatform()
    tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveBaseName.set("GestionSeguros")    // Nombre personalizado
        archiveVersion.set("1.0")                // Versión
        archiveClassifier.set("")                // Sin sufijo -all
        mergeServiceFiles()
        exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA") // Evita errores de firma
    }
}
kotlin {
    jvmToolchain(21)
}



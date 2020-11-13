import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val hikariVersion: String by project
val exposedVersion: String by project

plugins {
    application
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
}

group = "com.gabriel.kspanel.project"
version = "0.0.1"

application {
    mainClassName = "com.gabriel.kspanel.project.rest.Application"
}

repositories {
    mavenLocal()
    jcenter()

    maven("https://kotlin.bintray.com/ktor")
    maven("https://kotlin.bintray.com/kotlinx/")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("io.ktor:ktor-server-jetty:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-locations:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common:$ktorVersion")
    implementation("io.ktor:ktor-auth:$ktorVersion")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-client-apache:$ktorVersion")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    implementation("io.ktor:ktor-serialization:$ktorVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.1.0")

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")

    implementation("com.zaxxer:HikariCP:$hikariVersion")
    runtimeOnly("mysql:mysql-connector-java:8.0.20")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")


tasks.withType<KotlinCompile> {
    kotlinOptions.freeCompilerArgs += listOf("-Xopt-in=kotlin.RequiresOptIn")
    kotlinOptions.languageVersion = "1.4"
}
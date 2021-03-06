import io.gitlab.arturbosch.detekt.Detekt

plugins {
    jacoco
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
    kotlin("plugin.jpa") version "1.5.31"
    kotlin("plugin.allopen") version "1.5.31"
    kotlin("plugin.serialization") version "1.5.31"
    id("org.springframework.boot") version "2.5.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("io.gitlab.arturbosch.detekt") version "1.18.0"
    id("org.jmailen.kotlinter") version "3.6.0"
}

group = "me.dgahn"
version = "0.1.0"

repositories {
    mavenCentral()
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

noArg {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
    annotation("kotlinx.serialization.Serializable")
}

kotlinter {
    ignoreFailures = false
    indentSize = 4
    reporters = arrayOf("checkstyle", "plain")
    experimentalRules = false
    disabledRules = emptyArray()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.4")
    implementation("io.github.microutils:kotlin-logging-jvm:2.0.11")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.5.4")
    implementation("com.h2database:h2:1.4.200")
    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.5.21")

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.4")
    testImplementation("io.kotest:kotest-assertions-core:4.6.3")
    testImplementation("io.mockk:mockk:1.12.0")
}

jacoco {
    toolVersion = "0.8.7"
}

tasks {
    withType<Test> {
        useJUnitPlatform()
        finalizedBy(jacocoTestReport)
    }
    compileKotlin {
        dependsOn(detekt)
    }
    withType<Detekt> {
        dependsOn(formatKotlin)
    }
    jacocoTestReport {
        reports {
            html.required.set(true)
            xml.required.set(false)
            csv.required.set(false)
        }
        finalizedBy(jacocoTestCoverageVerification)
    }
    jacocoTestCoverageVerification {
        violationRules {
            rule {
                element = "SOURCEFILE"

                limit {
                    counter = "LINE"
                    value = "COVEREDRATIO"
                    minimum = (1.0).toBigDecimal()
                }
                excludes = listOf("me/dgahn/App.kt")
            }
        }
        enabled = true
    }
}

plugins {
    jacoco
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
    kotlin("plugin.jpa") version "1.5.31"
    kotlin("plugin.allopen") version "1.5.31"
    kotlin("plugin.serialization") version "1.5.31"
    id("org.springframework.boot") version "2.5.5"
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
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.github.microutils:kotlin-logging-jvm:2.0.10")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
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
        dependsOn(formatKotlin)
        dependsOn(detekt)
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

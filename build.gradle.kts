plugins {
    kotlin("jvm") version "1.9.22"
    application
    antlr
}

group = "com.isekaiofficial"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    antlr("org.antlr:antlr4:4.13.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("com.isekaiofficial.antlrdemo.MainKt")
}

tasks {
    compileKotlin {
        dependsOn(generateGrammarSource)
    }
    compileTestKotlin {
        dependsOn(generateGrammarSource)
    }
    generateGrammarSource {
        arguments = arguments + listOf("-visitor")
    }
    jar {
        manifest {
            attributes("Main-Class" to application.mainClass)
        }
        from(configurations.runtimeClasspath.get().map { zipTree(it) })
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}

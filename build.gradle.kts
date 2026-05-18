plugins {
    idea
    java
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories.mavenCentral()

java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))

application.mainClass = "DataAnalyzerApp"

javafx {
    version = "21.0.2"
    modules("javafx.controls", "javafx.fxml")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test { useJUnitPlatform() }
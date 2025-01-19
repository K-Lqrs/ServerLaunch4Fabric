import com.google.gson.GsonBuilder

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("fabric-loom")
}

group = "net.rk4z"
version = "1.0-SNAPSHOT"

val modName = "ServerLaunch4Fabric"
val modId = "sl4fabric"
val modDescription = "A mod to launch Minecraft servers directly from your Fabric client."

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://jitpack.io")
}

dependencies {
    val minecraftVersion: String by project
    val mappingsVersion: String by project
    val loaderVersion: String by project
    val fabricVersion: String by project
    val fabricLanguageKotlinVersion: String by project

    minecraft("com.mojang:minecraft-server:$minecraftVersion")
    mappings("net.fabricmc:yarn:$mappingsVersion")
    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")
    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricVersion")
    modImplementation("net.fabricmc:fabric-language-kotlin:$fabricLanguageKotlinVersion")

    // MCEF from CCBlueX team: https://github.com/CCBlueX/mcef
    modApi("com.github.CCBlueX:mcef:1.3.2-1.21.4")
    modApi("net.rk4z:mcef-al:1.0.0")
    modApi("net.rk4z:beacon:1.4.8")

    // for testing
    val libsDir = rootProject.file("libs/")
    if (libsDir.exists() && libsDir.isDirectory) {
        libsDir.listFiles()?.filter { it.extension.equals("jar", ignoreCase = true) }?.forEach { jarFile ->
            println("Including JAR: ${jarFile.name}") // デバッグ用
            compileOnly(files(jarFile))
        }
    } else {
        println("libs/ directory not found or is not a directory: ${libsDir.absolutePath}")
    }

    // for generate fabric meta file
    compileOnly("com.google.code.gson:gson:2.11.0")
}

fun generateMeta() {
    val meta = mapOf(
        "schemaVersion" to 1,
        "id" to modId,
        "version" to version,
        "name" to modName,
        "description" to modDescription,
        "contributors" to arrayOf("Lars"),
        "environment" to "client",
        "icon" to "assets/fabricord/icon.png",
        "license" to "MIT",
        "contact" to mapOf(
            "sources" to "https://github.com/K-Lqrs/ServerLaunch4Fabric"
        ),
        "entrypoints" to mapOf(
            "client" to listOf(
                mapOf(
                    "value" to "net.rk4z.sl4fabric.ServerLaunch4Fabric",
                    "adapter" to "kotlin"
                )
            )
        ),
        "depends" to mapOf(
            "fabricloader" to ">=0.16.9",
            "minecraft" to ">=1.21.4",
            "java" to ">=21",
            "fabric-api" to ">=0.114.0+1.21.4",
            "fabric-language-kotlin" to ">=1.13.0+kotlin.2.1.0"
        ),
        "mixins" to arrayOf(
            "sl4fabric.mixins.json"
        )
    )

    val gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
    val jsonContent = gson.toJson(meta)

    val projectDir = project.projectDir
    val outputFile = projectDir.resolve("src/main/resources/fabric.mod.json")
    outputFile.parentFile.mkdirs()
    outputFile.writeText(jsonContent)

    println("fabric.mod.json generated successfully!")
}

tasks.register("genMetaAndCompile") {
    doLast {
        generateMeta()
    }
}

tasks.named("remapJar") {
    dependsOn("genMetaAndCompile")
}

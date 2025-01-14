pluginManagement {
	repositories {
		gradlePluginPortal()
		maven("https://maven.fabricmc.net/")
	}
	plugins {
		id("org.jetbrains.kotlin.jvm") version "2.1.0"
		id("org.jetbrains.kotlin.plugin.serialization") version "2.1.20-Beta1"
		id("fabric-loom") version "1.9-SNAPSHOT"
	}
}
rootProject.name = "ServerLaunch4Fabric"


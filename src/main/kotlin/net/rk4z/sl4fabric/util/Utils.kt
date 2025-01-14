package net.rk4z.sl4fabric.util

import kotlinx.serialization.json.Json
import net.rk4z.sl4fabric.system.ServerManager
import java.io.File
import java.net.URI
import java.nio.file.Path

fun Path.exists(): Boolean = toFile().exists()
fun String.toURI() = URI(this)
fun URI.toFile() = File(this)
fun ServerConfigs.getServer(name: String) = servers.find { it.name == name }
fun ServerConfig.initialize() { ServerManager.startServer(this) }
fun ServerConfigs.loadFromJson(file: File): ServerConfigs {
	val json = file.readText()
	return Json.decodeFromString(json)
}
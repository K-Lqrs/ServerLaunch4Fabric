package net.rk4z.sl4fabric.util

import kotlinx.serialization.Serializable
import java.net.URI

@Serializable
data class ServerConfigs(
	val servers: List<ServerConfig>
)

@Serializable
data class ServerConfig(
	val name: String,
	val url: String,
	val icon: String,
	val args: List<String>,
	val version: String
) {
	fun getMainJar(): URI = url.toURI().resolve("server.jar")
}


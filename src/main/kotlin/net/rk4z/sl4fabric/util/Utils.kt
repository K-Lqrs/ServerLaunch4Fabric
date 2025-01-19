package net.rk4z.sl4fabric.util

import com.mojang.blaze3d.systems.RenderSystem
import kotlinx.serialization.json.Json
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.render.BufferRenderer
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.Tessellator
import net.minecraft.client.render.VertexFormat
import net.minecraft.client.render.VertexFormats
import net.rk4z.sl4fabric.system.ServerManager
import org.lwjgl.opengl.GL30
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
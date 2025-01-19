package net.rk4z.sl4fabric

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.MinecraftClient
import net.rk4z.mcefal.MCEFAL
import net.rk4z.sl4fabric.screen.SL4FabricMainMenu
import net.rk4z.sl4fabric.util.ServerConfigs
import net.rk4z.sl4fabric.util.exists
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.file.Path
import kotlin.io.path.readText

class ServerLaunch4Fabric() : ClientModInitializer {
    companion object {
        const val MOD_ID = "sl4fabric"
        const val MOD_NAME = "ServerLaunch4Fabric"

        @JvmField
        val loader: FabricLoader = FabricLoader.getInstance()
        @JvmField
        val mc: MinecraftClient = MinecraftClient.getInstance()
        @JvmField
        val logger: Logger = LoggerFactory.getLogger(MOD_NAME)
        @JvmField
        val gameDir: Path = loader.gameDir
        @JvmField
        val modDir: Path = gameDir.resolve(MOD_ID)
        @JvmField
        val serverListFile: Path = modDir.resolve("serverlist.json")
        @JvmField
        var serverList: ServerConfigs? = null
    }

    override fun onInitializeClient() {
        logger.info("Initializing $MOD_NAME...")

        try {
            if (!modDir.exists()) {
                modDir.toFile().mkdirs()
                logger.info("Created directory: $modDir")
            }

            if (!serverListFile.exists()) {
                createDefaultServerListFile()
                logger.info("Created default server list file: $serverListFile")
            }

            MCEFAL.INSTANCE.initialize()

            serverList = loadServerList()

            SL4FabricMainMenu()
            logger.info("Loaded server list: ${serverList?.servers?.size ?: 0} servers")
        } catch (e: Exception) {
            logger.error("Failed to initialize $MOD_NAME: ${e.message}", e)
        }
    }

    private fun createDefaultServerListFile() {
        val defaultServerConfigs = ServerConfigs(servers = emptyList())
        val json = Json.encodeToString(defaultServerConfigs)
        serverListFile.toFile().writeText(json)
    }

    private fun loadServerList(): ServerConfigs? {
        return try {
            val json = serverListFile.readText()
            Json.decodeFromString<ServerConfigs>(json)
        } catch (e: SerializationException) {
            logger.warn("Failed to parse server list. Using default configuration.", e)
            createDefaultServerListFile()
            ServerConfigs(servers = emptyList())
        }
    }
}
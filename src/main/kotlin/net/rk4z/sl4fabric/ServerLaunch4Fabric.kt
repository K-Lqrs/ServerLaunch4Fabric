package net.rk4z.sl4fabric

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.ButtonTextures
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServerLaunch4Fabric() : ClientModInitializer {
    companion object {
        const val MOD_ID = "sl4fabric"
        const val MOD_NAME = "ServerLaunch4Fabric"

        @JvmStatic
        val loader: FabricLoader = FabricLoader.getInstance()
        @JvmStatic
        val mc: MinecraftClient = MinecraftClient.getInstance()
        @JvmStatic
        val logger: Logger = LoggerFactory.getLogger(MOD_NAME)
    }

    override fun onInitializeClient() {
        logger.info("Initializing $MOD_NAME...")

    }

    object Textures {
        @JvmField
        val SERVER_MAIN: ButtonTextures = Utils.createButtonTextures(
            Identifier.of(MOD_ID, "textures/gui/server_main.png"),
            Identifier.of(MOD_ID, "textures/gui/server_main_focused.png")
        )

    }
}
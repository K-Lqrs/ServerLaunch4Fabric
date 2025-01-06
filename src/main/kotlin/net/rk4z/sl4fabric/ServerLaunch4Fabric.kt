package net.rk4z.sl4fabric

import net.ccbluex.liquidbounce.mcef.MCEF
import net.ccbluex.liquidbounce.mcef.MCEFApp
import net.ccbluex.liquidbounce.mcef.MCEFBrowser
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import org.cef.CefSettings
import org.lwjgl.glfw.GLFW
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServerLaunch4Fabric : ClientModInitializer {
    companion object {
        const val MOD_ID = "sl4fabric"
        const val MOD_NAME = "ServerLaunch4Fabric"
    }

    val logger: Logger = LoggerFactory.getLogger(MOD_NAME)
    private lateinit var browser: MCEFBrowser
    private lateinit var openBrowserKey: KeyBinding

    override fun onInitializeClient() {
        logger.info("Initializing $MOD_NAME...")

        // MCEFの初期化
        initializeMCEF()

        // キーバインドを設定
        setupKeyBindings()

        // キーイベントのリスナーを登録
        registerKeyEvents()
    }

    private fun initializeMCEF() {
        if (!MCEF.INSTANCE.isInitialized) {
            logger.info("Initializing MCEF...")
            MCEF.INSTANCE.newResourceManager()
            if ((MCEF.INSTANCE.resourceManager.requiresDownload())) {
                MCEF.INSTANCE.resourceManager.downloadJcef()
            }
            MCEF.INSTANCE.initialize()
        }

        browser = MCEF.INSTANCE.createBrowser("https://127.0.0.1", false, 60)
        logger.info("MCEF initialized successfully.")
    }

    private fun setupKeyBindings() {
        openBrowserKey = KeyBindingHelper.registerKeyBinding(
            KeyBinding(
                "key.sl4fabric.open_smgui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_B,
                "category.sl4fabric"
            )
        )
        logger.info("Keybinding for opening browser registered.")
    }

    private fun registerKeyEvents() {
        ClientTickEvents.END_CLIENT_TICK.register { client ->
            if (openBrowserKey.wasPressed()) {
                logger.info("Browser screen opened.")
            }
        }
    }
}
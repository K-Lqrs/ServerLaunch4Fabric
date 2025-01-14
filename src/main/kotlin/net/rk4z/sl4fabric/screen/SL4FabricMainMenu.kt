package net.rk4z.sl4fabric.screen

import net.ccbluex.liquidbounce.mcef.MCEF
import net.ccbluex.liquidbounce.mcef.MCEFBrowser
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text

class SL4FabricMainMenu : Screen(Text.of("SL4Fabric")) {
	private var browser: MCEFBrowser? = null

	init {
		browser = MCEF.INSTANCE.createBrowser("http://127.0.0.1:", false, 60)
	}
}
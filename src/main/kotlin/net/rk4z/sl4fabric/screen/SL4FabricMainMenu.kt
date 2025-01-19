package net.rk4z.sl4fabric.screen

import net.rk4z.mcefal.CefTab
import net.rk4z.mcefal.EmbeddedBrowserScreen
import net.rk4z.mcefal.TabDim

class SL4FabricMainMenu : EmbeddedBrowserScreen("SL4Fabric") {
	private lateinit var tab: CefTab

	override fun initTab() {
		try {
			tab = browser.createTab(
				"https://google.com",
				TabDim.fullScreen(),
				60,
				"sl4fabric"
			)
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}
}
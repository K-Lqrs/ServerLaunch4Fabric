package net.rk4z.sl4fabric.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.rk4z.sl4fabric.ServerLaunch4Fabric;
import net.rk4z.sl4fabric.screen.SL4FabricMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Inject(method = "addNormalWidgets", at = @At("HEAD"))
    public void init(int i, int j, CallbackInfoReturnable<Integer> cir) {
        var client = ServerLaunch4Fabric.mc;

        var screen = new SL4FabricMainMenu();

        int buttonX = client.getWindow().getScaledWidth() / 2 + 110;
        int buttonY = client.getWindow().getScaledHeight() / 4 + 48;
        int buttonWidth = 100;
        int buttonHeight = 20;

        var button = ButtonWidget.builder(Text.translatable("key.sl4fabric.open_smgui"), it -> client.setScreen(screen))
                .dimensions(buttonX, buttonY, buttonWidth, buttonHeight)
                .build();

        ((ScreenAccessor) client.currentScreen).invokeAddDrawableChild(button);
    }
}
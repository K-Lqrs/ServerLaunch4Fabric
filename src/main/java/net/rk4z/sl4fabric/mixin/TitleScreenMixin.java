package net.rk4z.sl4fabric.mixin;

import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.text.Text;
import net.rk4z.sl4fabric.ServerLaunch4Fabric;
import net.rk4z.sl4fabric.screen.SL4FabricMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Inject(method = "init", at = @At("TAIL"))
    public void init(CallbackInfo info) {
        var screen = new SL4FabricMainMenu();
        var client = ServerLaunch4Fabric.getMc();

        if (!(client.currentScreen instanceof TitleScreen)) {
            return;
        }

        var singlePlayerButton = ((ScreenAccessor) client.currentScreen).getChildren().stream()
                .filter(element -> element instanceof ButtonWidget)
                .map(element -> (ButtonWidget) element)
                .filter(button -> button.getMessage().getString().equals("Singleplayer"))
                .findFirst();

        singlePlayerButton.ifPresent(spButton -> {
            var buttonX = spButton.getX() + spButton.getWidth() + 10;
            var buttonY = spButton.getY();
            var buttonWidth = 100;
            var buttonHeight = spButton.getHeight();

            var button = new TexturedButtonWidget(
                    buttonWidth,
                    buttonHeight,
                    buttonX,
                    buttonY,
                    ServerLaunch4Fabric.Textures.SERVER_MAIN,
                    b -> client.setScreen(screen),
                    Text.translatable("menu.sl4fabric.button")
            );

            ((ScreenAccessor) this).invokeAddDrawableChild(button);
        });
    }
}

package net.rk4z.sl4fabric;

import net.minecraft.client.gui.screen.ButtonTextures;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class Utils {
    public static @NotNull ButtonTextures createButtonTextures(Identifier identifier) {
        return new ButtonTextures(identifier, identifier, identifier, identifier);
    }
}

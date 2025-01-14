package net.rk4z.sl4fabric;

import net.minecraft.client.gui.screen.ButtonTextures;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Utils {
    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull ButtonTextures createButtonTextures(Identifier identifier, Identifier identifier2) {
        return new ButtonTextures(identifier, identifier, identifier2, identifier2);
    }
}

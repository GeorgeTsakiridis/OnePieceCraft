package georgetsak.opcraft.client.registry;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class OPKeys {
    public static KeyBinding key1;
    public static KeyBinding key2;
    public static KeyBinding key3;
    public static KeyBinding statsButton;
    public static KeyBinding hakiButton;
    public static KeyBinding emperorHakiButton;
    public static KeyBinding sixPowersMenuButton;
    public static KeyBinding sixPowersButton;
    public static KeyBinding powerUpgradeButton;

    public static void registerKeyBindings() {
        key1 = new KeyBinding("key.power_decrease", KeyConflictContext.IN_GAME, Keyboard.KEY_X, "key.categories.onepiece");
        key2 = new KeyBinding("key.power_increase", KeyConflictContext.IN_GAME, Keyboard.KEY_C, "key.categories.onepiece");
        key3 = new KeyBinding("key.power_execute", KeyConflictContext.IN_GAME, Keyboard.KEY_V, "key.categories.onepiece");
        statsButton = new KeyBinding("key.statsButton", KeyConflictContext.IN_GAME, Keyboard.KEY_Z, "key.categories.onepiece");
        hakiButton = new KeyBinding("key.hakiButton", KeyConflictContext.IN_GAME, KeyModifier.SHIFT, Keyboard.KEY_Z, "key.categories.onepiece");
        emperorHakiButton = new KeyBinding("key.emperorHakiButton", KeyConflictContext.IN_GAME, KeyModifier.SHIFT, Keyboard.KEY_X, "key.categories.onepiece");
        sixPowersMenuButton = new KeyBinding("key.six_powers_menu_button", KeyConflictContext.IN_GAME, KeyModifier.CONTROL, Keyboard.KEY_Z, "key.categories.onepiece");
        sixPowersButton = new KeyBinding("key.six_powers_button", KeyConflictContext.IN_GAME, KeyModifier.CONTROL, Keyboard.KEY_V, "key.categories.onepiece");
        powerUpgradeButton = new KeyBinding("key.power_upgrade_button", KeyConflictContext.IN_GAME, KeyModifier.SHIFT, Keyboard.KEY_C, "key.categories.onepiece");

        ClientRegistry.registerKeyBinding(key1);
        ClientRegistry.registerKeyBinding(key2);
        ClientRegistry.registerKeyBinding(key3);
        ClientRegistry.registerKeyBinding(statsButton);
        ClientRegistry.registerKeyBinding(hakiButton);
        ClientRegistry.registerKeyBinding(emperorHakiButton);
        ClientRegistry.registerKeyBinding(sixPowersMenuButton);
        ClientRegistry.registerKeyBinding(sixPowersButton);
        ClientRegistry.registerKeyBinding(powerUpgradeButton);
    }


}

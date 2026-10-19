package de.jnsdev.mcflymod;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

public final class McFlyModClient implements ClientModInitializer {
    private static final String KEY_CATEGORY = "category.mc-flymod.general";
    private static final String KEY_ACTION = "key.mc-flymod.call_function";

    private static KeyBinding actionKeyBinding;

    @Override
    public void onInitializeClient() {
        actionKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            KEY_ACTION,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            KEY_CATEGORY
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (actionKeyBinding.wasPressed()) {
                onActionKeyPressed(client);
            }
        });
    }

    private static void onActionKeyPressed(MinecraftClient client) {
        if (client.player == null) {
            return;
        }

        var abilities = client.player.getAbilities();
        if (!abilities.allowFlying && !client.player.isCreative()) {
            client.player.sendMessage(Text.literal("Creative fly is not available for this player."), true);
            McFlyMod.LOGGER.info("Creative fly requested but not allowed");
            return;
        }

        abilities.allowFlying = true;
        abilities.flying = !abilities.flying;
        client.player.sendAbilitiesUpdate();

        String state = abilities.flying ? "enabled" : "disabled";
        client.player.sendMessage(Text.literal("Creative fly " + state + "."), true);
        McFlyMod.LOGGER.info("Creative fly {} for {}", state, client.player.getEntityName());
    }
}
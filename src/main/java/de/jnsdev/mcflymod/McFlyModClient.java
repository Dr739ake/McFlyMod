package de.jnsdev.mcflymod;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;

@SideOnly(Side.CLIENT)
public final class McFlyModClient {
    private static final String KEY_CATEGORY = "key.categories.mc-flymod.general";
    private static final String KEY_ACTION = "key.mc-flymod.call_function";
    private static final KeyBinding ACTION_KEY_BINDING = new KeyBinding(KEY_ACTION, Keyboard.KEY_V, KEY_CATEGORY);

    public static void init() {
        ClientRegistry.registerKeyBinding(ACTION_KEY_BINDING);
        MinecraftForge.EVENT_BUS.register(new Object() {
            @SubscribeEvent
            public void onClientTick(TickEvent.ClientTickEvent event) {
                if (event.phase == TickEvent.Phase.END && ACTION_KEY_BINDING.isPressed()) {
                    onActionKeyPressed();
                }
            }
        });
    }

    private static void onActionKeyPressed() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null) {
            return;
        }

        if (!mc.thePlayer.capabilities.allowFlying && !mc.thePlayer.capabilities.isCreativeMode) {
            mc.thePlayer.addChatMessage(new ChatComponentText("Creative fly is not available for this player."));
            McFlyMod.LOGGER.info("Creative fly requested but not allowed");
            return;
        }

        mc.thePlayer.capabilities.allowFlying = true;
        mc.thePlayer.capabilities.isFlying = !mc.thePlayer.capabilities.isFlying;
        mc.thePlayer.sendPlayerAbilities();

        String state = mc.thePlayer.capabilities.isFlying ? "enabled" : "disabled";
        mc.thePlayer.addChatMessage(new ChatComponentText("Creative fly " + state + "."));
        McFlyMod.LOGGER.info("Creative fly {} for {}", state, mc.thePlayer.getCommandSenderName());
    }
}
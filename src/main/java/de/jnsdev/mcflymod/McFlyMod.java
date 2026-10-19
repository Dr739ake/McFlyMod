package de.jnsdev.mcflymod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = McFlyMod.MOD_ID, name = McFlyMod.MOD_NAME, version = McFlyMod.VERSION)
public class McFlyMod {
    public static final String MOD_ID = "mcflymod";
    public static final String MOD_NAME = "MC Fly Mod";
    public static final String VERSION = "1.0";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("{} is loading", MOD_NAME);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.info("[JNSDEV]: Initializing client controls");
        if (event.getSide().isClient()) {
            McFlyModClient.init();
        }
    }
}
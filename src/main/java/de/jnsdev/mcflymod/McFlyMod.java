package de.jnsdev.mcflymod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;

public final class McFlyMod implements ModInitializer {
    public static final String MOD_ID = "jns-togglefly";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("{} initialized", MOD_ID);
    }
}
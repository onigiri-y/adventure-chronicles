package com.adventurechronicles;

import com.adventurechronicles.command.ModCommands;
import com.adventurechronicles.profession.Profession;
import com.adventurechronicles.profession.ProfessionLoader;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdventureChronicles implements ModInitializer {
    public static final String MOD_ID = "assets/adventure_chronicles";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModCommands.register();
        ProfessionLoader.register();
        LOGGER.info("Adventure Chronicles loaded!");
    }
}

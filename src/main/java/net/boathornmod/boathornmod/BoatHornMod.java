package net.boathornmod.boathornmod;

import net.boathornmod.boathornmod.config.BHConfigManager;
import net.boathornmod.boathornmod.network.BHNetworkHandler;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;


public class BoatHornMod implements ModInitializer {
	public static final String MOD_ID = "boathornmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("BoatHornMod initialized.");
		BHNetworkHandler.registerServerReceiver();
		BHConfigManager.load();
    }
}

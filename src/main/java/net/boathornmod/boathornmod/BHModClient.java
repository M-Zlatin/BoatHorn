package net.boathornmod.boathornmod;

import net.fabricmc.api.ClientModInitializer;
import net.boathornmod.boathornmod.config.BHConfigManager;
import net.boathornmod.boathornmod.event.BHHornPressed;

public class BHModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BHConfigManager.load();
        BHHornPressed.register();


    }
}

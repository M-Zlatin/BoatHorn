package net.boathornmod.boathornmod.network;

import net.boathornmod.boathornmod.config.BHConfigManager;
import net.boathornmod.boathornmod.config.BoatHornSound;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;

public class BHNetworkHandler {
    public static final Identifier HORN_PACKET_ID = new Identifier("boathornmod", "horn");

    public static void registerServerReceiver() {
        ServerPlayNetworking.registerGlobalReceiver(HORN_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            String soundName = buf.readString();
            server.execute(() -> {
                if (player.hasVehicle() && player.getVehicle() instanceof BoatEntity) {
                    BoatHornSound sound = BoatHornSound.fromString(soundName);

                    player.getWorld().playSoundFromEntity(
                            null,
                            player,
                            sound.getSoundEvent(),
                            SoundCategory.PLAYERS,
                            BHConfigManager.getVolume(),
                            BHConfigManager.getPitch()
                    );
                }
            });
        });

    }
}

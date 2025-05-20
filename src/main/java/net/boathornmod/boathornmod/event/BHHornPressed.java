    package net.boathornmod.boathornmod.event;

    import net.boathornmod.boathornmod.network.BHNetworkHandler;
    import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
    import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
    import net.boathornmod.boathornmod.config.BoatHornSound;
    import net.boathornmod.boathornmod.config.BHConfigManager;
    import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
    import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
    import net.minecraft.client.option.KeyBinding;
    import net.minecraft.client.util.InputUtil;
    import net.minecraft.entity.vehicle.BoatEntity;
    import net.minecraft.network.PacketByteBuf;
    import net.minecraft.sound.SoundCategory;
    import net.minecraft.sound.SoundEvent;
    import net.minecraft.text.Text;
    import org.lwjgl.glfw.GLFW;
    import net.minecraft.client.option.KeyBinding;
    import net.minecraft.client.util.InputUtil;

    import javax.swing.text.JTextComponent;


    public class BHHornPressed {
        public static final String KEY_CATEGORY_BOATHORNMOD = "key.category.boathornmod.boathornmod";
        public static final String KEY_TRIGGER_HORN = "key.boathornmod.callKey_trigger";
        public static KeyBinding callkey;

        private static final long COOLDOWN_TIME = 3000;
        private static long lastHornTime = 0;

        public static void registerKeyInputs() {
            ClientTickEvents.END_CLIENT_TICK.register(client -> {
                long currentTime = System.currentTimeMillis();

                if (callkey.wasPressed() && client.player != null) {
                    if (client.player.hasVehicle() && client.player.getVehicle() instanceof BoatEntity) {
                        if (currentTime - lastHornTime >= COOLDOWN_TIME) {
                            if (BHConfigManager.isEnabled()) {
                                BoatHornSound sound = BHConfigManager.getSound();

                                SoundEvent soundEvent = sound.getSoundEvent();

                                PacketByteBuf buf = PacketByteBufs.create();
                                buf.writeString(sound.name());

                                ClientPlayNetworking.send(BHNetworkHandler.HORN_PACKET_ID, buf);

                                client.player.playSound(
                                        soundEvent,
                                        SoundCategory.PLAYERS,
                                        BHConfigManager.getVolume(),
                                        BHConfigManager.getPitch()
                                );

                                lastHornTime = currentTime;
                            }
                        } else {
                            client.player.sendMessage(Text.literal("Horn is on cooldown! Please wait."), false);
                        }
                    }
                }
            });
        }

        public static void register() {
            if (callkey == null) {
                callkey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                        KEY_TRIGGER_HORN,
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_H,
                        KEY_CATEGORY_BOATHORNMOD
                ));
                registerKeyInputs();
            }
        }
    }

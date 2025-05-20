package net.boathornmod.boathornmod.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;

public class BHMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.literal("Boat Horn Settings"))
                    .setSavingRunnable(BHConfigManager::save);

            ConfigCategory category = builder.getOrCreateCategory(Text.literal("Settings"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            category.addEntry(entryBuilder
                    .startEnumSelector(Text.literal("Horn Sound"), BoatHornSound.class, BHConfigManager.getSound())
                    .setDefaultValue(BoatHornSound.SOUND1)
                    .setSaveConsumer(BHConfigManager::setSound)
                    .setTooltip(Text.literal("Select which horn sound to use"))
                    .build());

            category.addEntry(entryBuilder
                    .startFloatField(Text.literal("Volume"), BHConfigManager.getVolume())
                    .setDefaultValue(1.0f)
                    .setMin(0.0f)
                    .setMax(2.0f)
                    .setSaveConsumer(BHConfigManager::setVolume)
                    .setTooltip(Text.literal("Set the volume of the horn (0.0–2.0)"))
                    .build());

            category.addEntry(entryBuilder
                    .startFloatField(Text.literal("Pitch"), BHConfigManager.getPitch())
                    .setDefaultValue(1.0f)
                    .setMin(0.1f)
                    .setMax(3.0f)
                    .setSaveConsumer(BHConfigManager::setPitch)
                    .setTooltip(Text.literal("Set the pitch of the horn (0.1–3.0)"))
                    .build());

            category.addEntry(entryBuilder
                    .startBooleanToggle(Text.literal("Enable Horn"), BHConfigManager.isEnabled())
                    .setDefaultValue(true)
                    .setSaveConsumer(BHConfigManager::setEnabled)
                    .setTooltip(Text.literal("Enable or disable the boat horn"))
                    .build());
           



            return builder.build();
        };
    }
}

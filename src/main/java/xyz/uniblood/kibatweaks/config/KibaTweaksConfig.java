package xyz.uniblood.kibatweaks.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class KibaTweaksConfig {

    static final String categoryEntities = "entities";

    public static String[] silverfishSpawnerList = { "Zombie", "Thaumcraft.EldritchCrab", };

    public static boolean stopPigmenLightningSpawn = true;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        stopPigmenLightningSpawn = configuration.getBoolean("Prevent lightning spawning pigmen from pigs", categoryEntities, stopPigmenLightningSpawn, "Prevent lighting from spawning pigmen due to end game attacks spawning a lot of lightning.");

        silverfishSpawnerList = configuration.getStringList(
            "Silverfish replacement entities",
            categoryEntities,
            silverfishSpawnerList,
            "Add mobs to have them potentially spawn as silverfish replacements");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}

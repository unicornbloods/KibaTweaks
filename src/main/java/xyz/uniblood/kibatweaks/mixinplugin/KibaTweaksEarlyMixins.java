package xyz.uniblood.kibatweaks.mixinplugin;

import static xyz.uniblood.kibatweaks.config.KibaTweaksConfig.stopPigmenLightningSpawn;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import xyz.uniblood.kibatweaks.config.KibaTweaksConfig;

@IFMLLoadingPlugin.Name("KibaTweaksEarlyMixins")
@IFMLLoadingPlugin.MCVersion("1.7.10")
public class KibaTweaksEarlyMixins implements IFMLLoadingPlugin, IEarlyMixinLoader {

    public void initConfigs() {
        final String configDir = "config" + File.separator;

        KibaTweaksConfig.synchronizeConfiguration(new File(configDir + "KibaTweaks.cfg"));
    }

    @Override
    public String getMixinConfig() {
        return "mixins.kibatweaks.early.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        List<String> mixins = new ArrayList<>();

        // Load the configs as early as possible
        initConfigs();

        if (stopPigmenLightningSpawn) {
            mixins.add("MixinEntityPig");
        }
        mixins.add("MixinBlockSilverfish");

        return mixins;
    }

    @Override
    public String[] getASMTransformerClass() {
        return null;
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}

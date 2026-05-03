package xyz.uniblood.kibatweaks.init;

import static xyz.uniblood.kibatweaks.config.KibaTweaksConfig.degradableItemDurabilities;

import net.minecraft.item.Item;

import com.google.common.base.CaseFormat;

import cpw.mods.fml.common.registry.GameRegistry;
import xyz.uniblood.kibatweaks.item.ItemDegradeable;

public class Items {

    public static void preInit() {
        for (int i = 0; i < degradableItemDurabilities.length; i++) {
            register(new ItemDegradeable(degradableItemDurabilities[i]), "itemDegradeable" + i);
        }
    }

    private static void register(Item item, String name) {
        String itemName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
        item.setUnlocalizedName(itemName);
        item.setTextureName("kibatweaks:" + itemName);
        GameRegistry.registerItem(item, itemName);
    }
}

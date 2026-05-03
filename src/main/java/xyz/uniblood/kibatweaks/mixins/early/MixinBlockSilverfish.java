package xyz.uniblood.kibatweaks.mixins.early;

import static xyz.uniblood.kibatweaks.config.KibaTweaksConfig.silverfishSpawnerList;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = BlockSilverfish.class, remap = false)
public class MixinBlockSilverfish extends Block {

    protected MixinBlockSilverfish(Material materialIn) {
        super(materialIn);
    }

    /**
     * @author Uniblood
     * @reason Force it to work regardless of other mods
     */
    @Overwrite
    public void onBlockDestroyedByPlayer(World worldIn, int x, int y, int z, int meta) {
        Random rand = new Random();
        String entityName = silverfishSpawnerList[rand.nextInt(silverfishSpawnerList.length)];

        Entity customEntity = EntityList.createEntityByName(entityName, worldIn);

        if (customEntity != null) {
            customEntity.setLocationAndAngles((double) x + 0.5D, y, (double) z + 0.5D, 0.0F, 0.0F);

            if (customEntity instanceof EntityLiving) {
                if (!worldIn.isRemote) {
                    worldIn.spawnEntityInWorld(customEntity);
                }
            }
        }

        super.onBlockDestroyedByPlayer(worldIn, x, y, z, meta);
    }

    /**
     * @author Uniblood
     * @reason Force it to work regardless of other mods
     */
    @Overwrite
    public void dropBlockAsItemWithChance(World worldIn, int x, int y, int z, int meta, float chance, int fortune) {
        Random rand = new Random();
        String entityName = silverfishSpawnerList[rand.nextInt(silverfishSpawnerList.length)];

        Entity customEntity = EntityList.createEntityByName(entityName, worldIn);

        if (customEntity != null) {
            customEntity.setLocationAndAngles((double) x + 0.5D, y, (double) z + 0.5D, 0.0F, 0.0F);

            if (customEntity instanceof EntityLiving) {
                if (!worldIn.isRemote) {
                    worldIn.spawnEntityInWorld(customEntity);
                }
            }
        }

    }
}

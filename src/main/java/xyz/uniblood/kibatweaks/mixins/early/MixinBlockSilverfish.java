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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = BlockSilverfish.class, remap = false)
public class MixinBlockSilverfish extends Block {

    protected MixinBlockSilverfish(Material materialIn) {
        super(materialIn);
    }

    @Redirect(
        method = "onBlockDestroyedByPlayer(Lnet/minecraft/world/World;IIII)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/World;spawnEntityInWorld(Lnet/minecraft/entity/Entity;)Z"))
    private boolean redirectSilverfishSpawn(World world, Entity originalSilverfish, World worldIn, int x, int y, int z,
        int meta) {

        Random rand = new Random();
        String entityName = silverfishSpawnerList[rand.nextInt(silverfishSpawnerList.length)];

        Entity customEntity = EntityList.createEntityByName(entityName, world);

        if (customEntity != null) {
            customEntity.setLocationAndAngles((double) x + 0.5D, y, (double) z + 0.5D, 0.0F, 0.0F);

            if (customEntity instanceof EntityLiving) {
                ((EntityLiving) customEntity).onSpawnWithEgg(null);
            }

            return world.spawnEntityInWorld(customEntity);
        }

        return world.spawnEntityInWorld(originalSilverfish);
    }

    @Redirect(
        method = "dropBlockAsItemWithChance(Lnet/minecraft/world/World;IIIIFI)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/World;spawnEntityInWorld(Lnet/minecraft/entity/Entity;)Z"))
    private boolean dropBlockAsItemWithChance(World world, Entity originalSilverfish, World worldIn, int x, int y,
        int z, int meta) {

        Random rand = new Random();
        String entityName = silverfishSpawnerList[rand.nextInt(silverfishSpawnerList.length)];

        Entity customEntity = EntityList.createEntityByName(entityName, world);

        if (customEntity != null) {
            customEntity.setLocationAndAngles((double) x + 0.5D, y, (double) z + 0.5D, 0.0F, 0.0F);

            if (customEntity instanceof EntityLiving) {
                ((EntityLiving) customEntity).onSpawnWithEgg(null);
            }

            return world.spawnEntityInWorld(customEntity);
        }

        return world.spawnEntityInWorld(originalSilverfish);
    }
}

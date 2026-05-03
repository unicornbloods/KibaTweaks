package xyz.uniblood.kibatweaks.item;

import static xyz.uniblood.kibatweaks.config.KibaTweaksConfig.spawnerItem;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSpawner extends ItemMonsterPlacer {

    public IIcon[] icon = new IIcon[spawnerItem.length];

    public ItemSpawner() {
        super();
        this.setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        for (int i = 0; i < icon.length; i++) {
            this.icon[i] = ir.registerIcon("kibatweaks:spawnable" + i);
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        if (this.icon == null || damage < 0 || damage >= this.icon.length || this.icon[damage] == null) {
            return Blocks.stone.getIcon(0, 0); // Fallback to stone icon
        }

        return this.icon[damage];
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int xCoord, int yCoord, int zCoord,
        int par7, float par8, float par9, float par10) {
        if (!world.isRemote) {
            Block var11 = world.getBlock(xCoord, yCoord, zCoord);
            xCoord += Facing.offsetsXForSide[par7];
            yCoord += Facing.offsetsYForSide[par7];
            zCoord += Facing.offsetsZForSide[par7];
            double heightOffset = 0.0D;

            if (par7 == 1 && var11 == Blocks.fence || var11 == Blocks.nether_brick_fence) {
                heightOffset = 0.5D;
            }

            Entity entity = spawnCreature(
                world,
                itemStack.getItemDamage(),
                (double) xCoord + 0.5D,
                (double) yCoord + heightOffset,
                (double) zCoord + 0.5D);

            if (entity != null) {
                if (entity instanceof EntityLiving && itemStack.hasDisplayName()) {
                    ((EntityLiving) entity).setCustomNameTag(itemStack.getDisplayName());
                }

                if (!player.capabilities.isCreativeMode) {
                    --itemStack.stackSize;
                }
            }
        }
        return true;
    }

    /**
     * Spawns the creature specified by the egg's type in the location specified by the last three parameters.
     * Parameters: world, entityID, x, y, z.
     */
    public static Entity spawnCreature(World world, int entityId, double xCoord, double yCoord, double zCoord) {

        String entityName = spawnerItem[entityId];

        Entity entityToSpawn = EntityList.createEntityByName(entityName, world);

        if (entityToSpawn instanceof EntityLivingBase) {
            EntityLiving entityliving = (EntityLiving) entityToSpawn;

            entityToSpawn.setLocationAndAngles(xCoord, yCoord, zCoord, world.rand.nextFloat() * 360.0F, 0.0F);
            entityliving.onSpawnWithEgg(null);
            world.spawnEntityInWorld(entityToSpawn);
            ((EntityLiving) entityToSpawn).playLivingSound();
        }
        return entityToSpawn;

    }

    @Override
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List<ItemStack> itemList) {
        for (int i = 0; i < icon.length; i++) {
            itemList.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean requiresMultipleRenderPasses() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack stack, int renderPass) {
        return 16777215;
    }

}

package xyz.uniblood.kibatweaks.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDegradeable extends Item {

    public IIcon[] icon = new IIcon[1];
    private final int itemNumber;

    public ItemDegradeable(int maxDamage, int itemNumber) {
        setMaxDamage(maxDamage);
        isDamageable();
        setMaxStackSize(1);
        this.setHasSubtypes(true);
        this.itemNumber = itemNumber;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.icon[0] = ir.registerIcon("kibatweaks:degradeable" + itemNumber);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        return this.icon[par1];
    }

    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return super.getUnlocalizedName() + "." + par1ItemStack.getItemDamage();
    }
}

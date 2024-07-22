package com.harleylizard.wicked.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public final class BlockPlanks extends Block implements HasUnlocalised {
    private static final Wood[] WOODS = Wood.values();

    private final IIcon[] icons = new IIcon[WOODS.length];

    public BlockPlanks() {
        super(Material.wood);
        setStepSound(soundTypeWood);
        setHardness(2.0F);
        setResistance(5.0F);
        setHarvestLevel("axe", 0);
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        list.add(new ItemStack(itemIn, 1, 0));
        list.add(new ItemStack(itemIn, 1, 1));
        list.add(new ItemStack(itemIn, 1, 2));
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return icons[clamp(meta)];
    }

    @Override
    public void registerIcons(IIconRegister reg) {
        icons[0] = reg.registerIcon("wicked:dogwood_planks");
        icons[1] = reg.registerIcon("wicked:holly_planks");
        icons[2] = reg.registerIcon("wicked:linden_planks");
    }

    @Override
    public int damageDropped(int meta) {
        return clamp(meta);
    }

    @Override
    public String getUnlocalised(int meta) {
        return "tile.wicked." + WOODS[clamp(meta)].getName() + "_planks";
    }

    public static int clamp(int i) {
        return i & WOODS.length;
    }
}

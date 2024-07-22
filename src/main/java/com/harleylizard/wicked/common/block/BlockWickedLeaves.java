package com.harleylizard.wicked.common.block;

import com.harleylizard.wicked.common.WickedBlocks;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.util.List;

public final class BlockWickedLeaves extends BlockLeaves implements HasUnlocalised {
    private static final String[] TYPES = {"dogwood", "holly", "linden"};

    private final IIcon[] icons = new IIcon[3];

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        WickedBlocks.PLANKS.getSubBlocks(itemIn, tab, list);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return icons[meta & 3];
    }

    @Override
    public String[] func_150125_e() {
        return TYPES;
    }

    @Override
    public String getUnlocalised(int meta) {
        return "tile.wicked." + Wood.values()[meta % 3].getName() + "_leaves";
    }

    @Override
    public void registerIcons(IIconRegister reg) {
        icons[0] = reg.registerIcon("wicked:dogwood_leaves");
        icons[1] = reg.registerIcon("wicked:holly_leaves");
        icons[2] = reg.registerIcon("wicked:linden_leaves");
    }

    @Override
    public int getBlockColor() {
        return 0xFFFFFF;
    }

    @Override
    public int getRenderColor(int meta) {
        return 0xFFFFFF;
    }

    @Override
    public int colorMultiplier(IBlockAccess worldIn, int x, int y, int z) {
        return 0xFFFFFF;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess worldIn, int x, int y, int z, int side) {
        return true;
    }
}

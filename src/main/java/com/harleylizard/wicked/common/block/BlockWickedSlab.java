package com.harleylizard.wicked.common.block;

import com.harleylizard.wicked.common.WickedBlocks;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public final class BlockWickedSlab extends BlockSlab {

    public BlockWickedSlab(boolean doubleSlab) {
        super(doubleSlab, Material.wood);
        setStepSound(soundTypeWood);
        setHardness(2.0F);
        setResistance(5.0F);
        setLightOpacity(1);
        setHarvestLevel("axe", 0);
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        list.add(new ItemStack(WickedBlocks.SLAB, 1, 0));
        list.add(new ItemStack(WickedBlocks.SLAB, 1, 1));
        list.add(new ItemStack(WickedBlocks.SLAB, 1, 2));
    }

    @Override
    public String getFullSlabName(int meta) {
        return "tile.wicked." + Wood.values()[meta].getName() + "_slab";
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return WickedBlocks.PLANKS.getIcon(side, meta);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return Item.getItemFromBlock(WickedBlocks.SLAB);
    }

    @Override
    public void registerIcons(IIconRegister reg) {
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
        return new ItemStack(WickedBlocks.SLAB, 1, BlockWickedPlanks.clamp(world.getBlockMetadata(x, y, z)));
    }
}

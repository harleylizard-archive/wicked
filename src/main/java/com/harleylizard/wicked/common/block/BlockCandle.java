package com.harleylizard.wicked.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public final class BlockCandle extends Block {
    public BlockCandle() {
        super(Material.clay);
        setStepSound(soundTypeGravel);
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
        ItemStack stack = player.getHeldItem();
        if (stack != null && stack.getItem() == Items.flint_and_steel) {
            int meta = worldIn.getBlockMetadata(x, y, z);
            if (!isLit(meta)) {
                if (!worldIn.isRemote) {
                    stack.damageItem(1, player);

                    int packed = pack(getColor(meta), true);
                    worldIn.setBlock(x, y, z, this, packed, 1 | 2);
                }
                worldIn.playSound(x + 0.5F, y, z + 0.5F, "fire.ignite", 1.0F, 1.0F, false);
            }
            return true;
        }
        return false;
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        for (int i = 15; i >= 0; i--) {
            list.add(new ItemStack(itemIn, 1, pack(i, false)));
        }
    }

    @Override
    public int damageDropped(int meta) {
        return pack(getColor(meta), false);
    }

    public static int pack(int color, boolean lit) {
        return ((lit ? 1 : 0) << 4) | color & 0xF;
    }

    public static int getColor(int meta) {
        return meta & 0xF;
    }

    public static boolean isLit(int meta) {
        return ((meta >> 4) & 0x1) == 1;
    }
}

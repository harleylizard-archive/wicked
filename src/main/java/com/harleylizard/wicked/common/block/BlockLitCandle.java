package com.harleylizard.wicked.common.block;

import com.harleylizard.wicked.common.WickedBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.Random;

public final class BlockLitCandle extends BlockCandle {

    {
        setLightLevel(0.75F);
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
        return false;
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return Item.getItemFromBlock(WickedBlocks.CANDLE);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
        return new ItemStack(Item.getItemFromBlock(WickedBlocks.CANDLE), 1, world.getBlockMetadata(x, y, z));
    }

    @Override
    public void randomDisplayTick(World worldIn, int x, int y, int z, Random random) {
        double centreX = x + 8.0F / 16.0F;
        double height = y + 11.0F / 16.0F;
        double centreZ = z + 8.0F / 16.0F;

        worldIn.spawnParticle("smoke", centreX, height, centreZ, 0.0D, 0.0D, 0.0D);
        worldIn.spawnParticle("flame", centreX, height, centreZ, 0.0D, 0.0D, 0.0D);
    }
}

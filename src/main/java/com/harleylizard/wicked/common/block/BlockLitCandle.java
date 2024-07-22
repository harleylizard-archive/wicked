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
        setTickRandomly(true);
    }

    @Override
    public void updateTick(World worldIn, int x, int y, int z, Random random) {
        if (worldIn.isDaytime() && random.nextInt(25) == 0) {
            extinguish(worldIn, x, y, z);
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
        ItemStack stack = player.getHeldItem();
        if (stack == null || stack.getItem() == null) {
            extinguish(worldIn, x, y, z);
            return true;
        }
        return false;
    }

    private void extinguish(World world, int x, int y, int z) {
        if (!world.isRemote) {
            world.setBlock(x, y, z, WickedBlocks.CANDLE, world.getBlockMetadata(x, y, z), 1 | 2);
        }
        world.playSound(x + 0.5F, y, z + 0.5F, "random.fizz", 0.25F, 1.25F, false);
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

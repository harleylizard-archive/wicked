package com.harleylizard.wicked.common.item;

import com.harleylizard.wicked.common.Wicked;
import com.harleylizard.wicked.common.WickedBlocks;
import com.harleylizard.wicked.common.message.CreateStirringPotMessage;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public final class ItemBigStick extends Item {

    {
        setMaxStackSize(1);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        Block block = world.getBlock(x, y, z);
        if (block instanceof BlockCauldron) {
            if (!world.isRemote) {
                world.setBlock(x, y, z, WickedBlocks.STIRRING_POT);
                stack.stackSize--;

                NetworkRegistry.TargetPoint targetPoint = new NetworkRegistry.TargetPoint(world.getWorldInfo().getDimension(), x, y, z, 16.0F);
                Wicked.NETWORK_WRAPPER.sendToAllAround(new CreateStirringPotMessage(x, y, z), targetPoint);
            }
            return true;
        }
        return false;
    }
}

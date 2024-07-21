package com.harleylizard.wicked.common.item;

import com.harleylizard.wicked.common.block.HasUnlocalised;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public final class ItemBlockWithUnlocalised extends ItemBlockWithMetadata {

    public ItemBlockWithUnlocalised(Block block) {
        super(block, block);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return ((HasUnlocalised) blockInstance).getUnlocalised(stack.getMetadata());
    }
}

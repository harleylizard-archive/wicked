package com.harleylizard.wicked.mixin;

import com.harleylizard.wicked.common.block.HasUnlocalised;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Block.class)
public final class BlockMixin implements HasUnlocalised {
    @Override
    public String getUnlocalised(int meta) {
        return ((Block) (Object) (this)).getUnlocalizedName();
    }
}

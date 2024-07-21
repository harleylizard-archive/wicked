package com.harleylizard.wicked.common.block;

import com.harleylizard.wicked.client.renderer.BlockRendererStirringPot;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public final class BlockStirringPot extends Block {
    public BlockStirringPot() {
        super(Material.iron);
        setStepSound(soundTypeMetal);
        setHarvestLevel("pickaxe", 1);
    }

    @Override
    public int getRenderType() {
        return BlockRendererStirringPot.ID;
    }
}

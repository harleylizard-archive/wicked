package com.harleylizard.wicked.common.block;

import com.harleylizard.wicked.client.ModelStirringPot;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public final class BlockStirringPot extends Block {
    public BlockStirringPot() {
        super(Material.iron);
        setStepSound(soundTypeMetal);
        setHardness(0.8F);
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public int getRenderType() {
        return ModelStirringPot.ID;
    }

    @Override
    public void registerIcons(IIconRegister reg) {
        blockIcon = reg.registerIcon("wicked:stirring_pot_side");
        reg.registerIcon("wicked:stirring_pot_top_0");
        reg.registerIcon("wicked:stirring_pot_top_1");
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }
}

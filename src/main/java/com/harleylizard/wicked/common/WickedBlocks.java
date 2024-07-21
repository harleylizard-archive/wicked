package com.harleylizard.wicked.common;

import com.harleylizard.wicked.common.block.BlockCandle;
import com.harleylizard.wicked.common.block.BlockLitCandle;
import com.harleylizard.wicked.common.block.BlockStirringPot;
import com.harleylizard.wicked.common.item.ItemCandle;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public final class WickedBlocks {
    public static Block STIRRING_POT;
    public static Block CANDLE;
    public static Block LIT_CANDLE;

    private WickedBlocks() {}

    public static void registerAll() {
        registerBlock("stirring_pot", ItemBlock.class, STIRRING_POT = new BlockStirringPot());
        registerBlock("candle", ItemCandle.class, CANDLE = new BlockCandle());
        registerBlock("lit_candle", null, LIT_CANDLE = new BlockLitCandle());
    }

    private static void registerBlock(String name, Class<? extends ItemBlock> itemClass, Block block) {
        String resourceName = Wicked.resourceLocation(name).toString();
        block
                .setUnlocalizedName(resourceName.replace(":", "."))
                .setTextureName(resourceName)
                .setCreativeTab(Wicked.CREATIVE_TAB);
        GameRegistry.registerBlock(block, itemClass, name);
    }
}

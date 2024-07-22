package com.harleylizard.wicked.common;

import com.harleylizard.wicked.common.block.*;
import com.harleylizard.wicked.common.item.ItemBlockWithUnlocalised;
import com.harleylizard.wicked.common.item.ItemCandle;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;

public final class WickedBlocks {
    public static Block PLANKS;
    public static Block DOGWOOD_STAIRS;
    public static Block HOLLY_STAIRS;
    public static Block LINDEN_STAIRS;
    public static BlockSlab SLABS;
    public static BlockSlab DOUBLE_SLABS;
    public static Block STIRRING_POT;
    public static Block CANDLE;
    public static Block LIT_CANDLE;

    private WickedBlocks() {}

    public static void registerAll() {
        registerBlock("planks", ItemBlockWithUnlocalised.class, PLANKS = new BlockPlanks());
        registerBlock("dogwood_stairs", ItemBlock.class, DOGWOOD_STAIRS = new BlockStairs(PLANKS, 0) {}.setLightOpacity(1));
        registerBlock("holly_stairs", ItemBlock.class, HOLLY_STAIRS = new BlockStairs(PLANKS, 1) {}.setLightOpacity(1));
        registerBlock("linden_stairs", ItemBlock.class, LINDEN_STAIRS = new BlockStairs(PLANKS, 2) {}.setLightOpacity(1));
        registerBlock("slabs", null, SLABS = new BlockSlabs(false));
        registerBlock("double_slabs", null, DOUBLE_SLABS = new BlockSlabs(true));
        GameRegistry.registerItem(new ItemSlab(SLABS, SLABS, DOUBLE_SLABS, false), "slabs");

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

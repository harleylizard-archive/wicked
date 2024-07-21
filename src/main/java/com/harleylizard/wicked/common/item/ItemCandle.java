package com.harleylizard.wicked.common.item;

import com.harleylizard.wicked.common.block.Color;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public final class ItemCandle extends ItemBlockWithMetadata {
    private static final Color[] COLORS = Color.values();

    private final IIcon[] icons = new IIcon[COLORS.length];

    public ItemCandle(Block block) {
        super(block, block);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "tile.wicked." + COLORS[stack.getMetadata()].getName() + "_candle";
    }

    @Override
    public int getSpriteNumber() {
        return 1;
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        return icons[damage];
    }

    @Override
    public void registerIcons(IIconRegister register) {
        int i = 0;
        for (Color color : COLORS) {
            icons[i++] = register.registerIcon("wicked:" + color.getName() + "_candle");
        }
    }
}

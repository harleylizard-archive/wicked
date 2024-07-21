package com.harleylizard.wicked.common;

import com.harleylizard.wicked.common.item.ItemBigStick;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public final class WickedItems {
    public static Item BIG_STICK;
    public static Item RAW_MUTTON;
    public static Item COOKED_MUTTON;
    public static Item TALLOW;

    private WickedItems() {}

    public static void registerAll() {
        registerItem("big_stick", BIG_STICK = new ItemBigStick());
        registerItem("raw_mutton", RAW_MUTTON = new ItemFood(0, 0, true));
        registerItem("cooked_mutton", COOKED_MUTTON = new ItemFood(0, 0, true));
        registerItem("tallow", TALLOW = new Item());
    }

    private static void registerItem(String name, Item item) {
        String resourceName = Wicked.resourceLocation(name).toString();
        item
                .setUnlocalizedName(resourceName.replace(":", "."))
                .setTextureName(resourceName)
                .setCreativeTab(Wicked.CREATIVE_TAB);
        GameRegistry.registerItem(item, name);
    }
}

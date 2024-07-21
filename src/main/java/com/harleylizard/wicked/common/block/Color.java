package com.harleylizard.wicked.common.block;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public enum Color {
    BLACK("black", "dyeBlack"),
    RED("red", "dyeRed"),
    GREEN("green", "dyeGreen"),
    BROWN("brown", "dyeBrown"),
    BLUE("blue", "dyeBlue"),
    PURPLE("purple", "dyePurple"),
    CYAN("cyan", "dyeCyan"),
    LIGHT_GRAY("light_gray", "dyeLightGray"),
    GRAY("gray", "dyeGray"),
    PINK("pink", "dyePink"),
    LIME("lime", "dyeLime"),
    YELLOW("yellow", "dyeYellow"),
    LIGHT_BLUE("light_blue", "dyeLightBlue"),
    MAGENTA("magenta", "dyeMagenta"),
    ORANGE("orange", "dyeOrange"),
    WHITE("white", "dyeWhite");

    private final String name;
    private final String oreDictionary;

    Color(String name, String oreDictionary) {
        this.name = name;
        this.oreDictionary = oreDictionary;
    }

    public String getName() {
        return name;
    }

    public List<ItemStack> getOres() {
        return OreDictionary.getOres(oreDictionary);
    }
}

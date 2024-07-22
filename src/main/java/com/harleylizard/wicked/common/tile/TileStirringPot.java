package com.harleylizard.wicked.common.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidTank;

import java.util.ArrayList;
import java.util.List;

public final class TileStirringPot extends TileSynced {
    private final FluidTank fluidTank = new FluidTank(FluidRegistry.WATER, 0, 1000);
    private final ItemList itemList = new ItemList();

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        fluidTank.readFromNBT(compound);
        itemList.readFrom(compound);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        fluidTank.writeToNBT(compound);
        itemList.writeTo(compound);
    }

    public FluidTank getFluidTank() {
        return fluidTank;
    }

    public ItemList getItemList() {
        return itemList;
    }

    public static final class ItemList {
        private final List<ItemStack> list = new ArrayList<>();

        private void readFrom(NBTTagCompound compound) {
            list.clear();
            NBTTagList tagList = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
            for (int i = 0; i < tagList.tagCount(); i++) {
                list.add(ItemStack.loadItemStackFromNBT(tagList.getCompoundTagAt(i)));
            }
        }

        public void writeTo(NBTTagCompound compound) {
            NBTTagList tagList = new NBTTagList();
            for (ItemStack stack : list) {
                if (stack != null) {
                    tagList.appendTag(stack.writeToNBT(new NBTTagCompound()));
                }
            }
            compound.setTag("Items", tagList);
        }

        public boolean add(ItemStack stack) {
            for (ItemStack itemStack : list) {
                if (itemStack.getItem() == stack.getItem()) {
                    int needed = itemStack.getMaxStackSize() - itemStack.stackSize;
                    if (needed > 0) {
                        int i = stack.stackSize;
                        int taken = Math.max(i - needed, i);
                        int remaining = i - taken;
                        if (remaining > 0) {
                            ItemStack entry = stack.copy();
                            entry.stackSize = remaining;
                            list.add(entry);
                        }
                        itemStack.stackSize += taken;
                        stack.stackSize = 0;
                        return true;
                    }
                }
            }
            list.add(stack.copy());
            stack.stackSize = 0;
            return true;
        }
    }
}

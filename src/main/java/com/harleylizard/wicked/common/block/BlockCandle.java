package com.harleylizard.wicked.common.block;

import com.harleylizard.wicked.client.ModelCandle;
import com.harleylizard.wicked.common.WickedBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class BlockCandle extends Block {
    private static final Color[] COLORS = Color.values();
    private final static IIcon[] ICONS = new IIcon[COLORS.length];

    public BlockCandle() {
        super(Material.clay);
        setStepSound(soundTypeGravel);
        setBlockBounds(5.0F / 16.0F, 0.0F, 5.0F / 16.0F, 11.0F / 16.0F, 9.0F / 16.0F, 11.0F / 16.0F);
        setLightOpacity(1);
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
        ItemStack stack = player.getHeldItem();
        if (stack != null && stack.getItem() == Items.flint_and_steel) {
            if (!worldIn.isRemote) {
                stack.damageItem(1, player);

                int color = worldIn.getBlockMetadata(x, y, z);
                worldIn.setBlock(x, y, z, WickedBlocks.LIT_CANDLE, color, 1 | 2);
            }
            worldIn.playSound(x + 0.5F, y, z + 0.5F, "fire.ignite", 1.0F, 1.0F, false);
            return true;
        }
        return false;
    }

    @Override
    public final void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        for (int i = 15; i >= 0; i--) {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }

    @Override
    public final int damageDropped(int meta) {
        return meta;
    }

    @Override
    public final IIcon getIcon(int side, int meta) {
        return ICONS[meta];
    }

    @Override
    public final void registerIcons(IIconRegister reg) {
        for (int i = 0; i < ICONS.length; i++) {
            ICONS[i] = reg.registerIcon("wicked:" + COLORS[i].getName() + "_candle");
        }
    }

    @Override
    public final int getRenderType() {
        return ModelCandle.ID;
    }

    @Override
    public final boolean isOpaqueCube() {
        return false;
    }

    @Override
    public final boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public final AxisAlignedBB getCollisionBoundingBoxFromPool(World worldIn, int x, int y, int z) {
        return null;
    }
}

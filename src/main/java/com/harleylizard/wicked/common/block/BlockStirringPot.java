package com.harleylizard.wicked.common.block;

import com.harleylizard.wicked.client.ModelStirringPot;
import com.harleylizard.wicked.common.Wicked;
import com.harleylizard.wicked.common.message.CreateStirringPotMessage;
import com.harleylizard.wicked.common.tile.TileStirringPot;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import java.util.List;

public final class BlockStirringPot extends Block implements ITileEntityProvider {

    public BlockStirringPot() {
        super(Material.iron);
        setStepSound(soundTypeMetal);
        setHardness(0.8F);
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
        ItemStack stack = player.getHeldItem();
        if (stack != null) {
            FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem(stack);
            if (fluidStack != null && fluidStack.getFluid() == FluidRegistry.WATER) {
                TileStirringPot stirringPot = (TileStirringPot) worldIn.getTileEntity(x, y, z);
                FluidTank fluidTank = stirringPot.getFluidTank();

                if (stack.getItem() == Items.potionitem) {
                    fluidStack.amount = FluidContainerRegistry.BUCKET_VOLUME / 3;
                }
                if (fluidStack.amount + fluidTank.getFluidAmount() > fluidTank.getCapacity()) {
                    return true;
                }
                int amountFilled = fluidTank.fill(fluidStack, false);
                if (amountFilled > 0) {
                    if (!worldIn.isRemote) {
                        if (!player.capabilities.isCreativeMode) {
                            player.inventory.setInventorySlotContents(player.inventory.currentItem, FluidContainerRegistry.drainFluidContainer(stack));
                        }
                        fluidTank.fill(fluidStack, true);
                        stirringPot.sync();
                    }
                    worldIn.playSound(x + 0.5F, y, z + 0.5F, "wicked:block.water_splash", 0.25F, 1.25F, false);
                }
            }
        }
        return true;
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, int x, int y, int z, Entity entityIn) {
        if (entityIn instanceof EntityItem) {
            EntityItem item = (EntityItem) entityIn;

            TileStirringPot stirringPot = (TileStirringPot) worldIn.getTileEntity(x, y, z);
            if (!worldIn.isRemote && stirringPot.getItemList().add(item.getEntityItem())) {
                NetworkRegistry.TargetPoint targetPoint = new NetworkRegistry.TargetPoint(worldIn.getWorldInfo().getDimension(), x, y, z, 16.0F);
                Wicked.NETWORK_WRAPPER.sendToAllAround(new CreateStirringPotMessage(x, y, z), targetPoint);
            }
        }
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

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileStirringPot();
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, int x, int y, int z, AxisAlignedBB mask, List list, Entity collider) {
        if (collider instanceof EntityItem) {
        } else {
            super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);
        }
    }
}

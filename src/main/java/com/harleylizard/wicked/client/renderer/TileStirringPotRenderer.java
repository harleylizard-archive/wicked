package com.harleylizard.wicked.client.renderer;

import com.harleylizard.wicked.client.model.ModelStirringPotStick;
import com.harleylizard.wicked.common.Wicked;
import com.harleylizard.wicked.common.tile.TileStirringPot;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.FluidTank;

import static org.lwjgl.opengl.GL11.*;

public final class TileStirringPotRenderer extends TileEntitySpecialRenderer {
    private static final ResourceLocation TEXTURE = Wicked.resourceLocation("textures/entity/stirring_pot_stick.png");

    private final ModelBase model = new ModelStirringPotStick();

    @Override
    public void renderTileEntityAt(TileEntity p_147500_1_, double x, double y, double z, float f) {
        glPushMatrix();
        glTranslated(x + 0.5F, y + 0.5F, z + 0.5F);
        glTranslatef(0.0F, 1.0F, 0.0F);
        glScalef(-1.0F, -1.0F, 1.0F);

        bindTexture(TEXTURE);
        float scale = 1.0F / 16.0F;
        glScalef(scale, scale, scale);
        model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
        glPopMatrix();

        TileStirringPot stirringPot = (TileStirringPot) p_147500_1_;
        FluidTank fluidTank = stirringPot.getFluidTank();
        int amount = fluidTank.getFluidAmount();
        if (amount > 0) {
            glPushMatrix();
            glTranslated(x, y, z);
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            drawWater(p_147500_1_.blockType, p_147500_1_.getWorld(), x, y, z, amount, fluidTank.getCapacity());
            glDisable(GL_BLEND);
            glPopMatrix();
        }
    }

    private void drawWater(Block block, IBlockAccess blockAccess, double x, double y, double z, float amount, float max) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();

        TextureMap textureMap = (TextureMap) Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationBlocksTexture);
        IIcon icon = textureMap.getAtlasSprite("water_still");
        if (icon == null) {
            tessellator.draw();
            return;
        }
        float minX;
        float minZ;
        float maxX = 1.0F;
        float maxZ = 1.0F;

        float minU = icon.getMinU();
        float minV = icon.getMinV();
        float maxU = icon.getMaxU();
        float maxV = icon.getMaxV();

        float height = (amount / max) * (14.0F / 16.0F);
        float width = height <= (12.0F / 16.0F) ? 15.0F / 16.0F : 13.0F / 16.0F;
        float different = 1.0F - width;
        minX = 0.0F + different;
        minZ = 0.0F + different;
        maxX -= different;
        maxZ -= different;

        float ru = maxU - minU;
        float rv = maxV - minV;
        float scaledMinU = minU + (ru * width);
        float scaledMinV = minV + (rv * width);
        float scaledMaxU = maxU - (ru * width);
        float scaledMaxV = maxV - (rv * width);
        bindTexture(TextureMap.locationBlocksTexture);
        vertex(tessellator, maxX, height, minZ, scaledMaxU, scaledMaxV, 0);
        vertex(tessellator, minX, height, minZ, scaledMaxU, scaledMinV, 0);
        vertex(tessellator, minX, height, maxZ, scaledMinU, scaledMinV, 0);
        vertex(tessellator, maxX, height, maxZ, scaledMinU, scaledMaxV, 0);

        tessellator.draw();
    }

    private void vertex(Tessellator tessellator, float x, float y, float z, float u, float v, int i) {
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 0.75F);
        tessellator.addVertexWithUV(x, y, z, u, v);
    }
}

package com.harleylizard.wicked.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public final class ModelStirringPot extends ModelBase {
    private final ModelRenderer stirringPot;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;

    {
        textureWidth = 64;
        textureHeight = 64;

        stirringPot = new ModelRenderer(this);
        stirringPot.setRotationPoint(0.0F, 23.875F, 0.0F);
        stirringPot.cubeList.add(new ModelBox(stirringPot, 30, 0, -7.0F, -10.875F, 5.0F, 14, 1, 2, 0.0F));
        stirringPot.cubeList.add(new ModelBox(stirringPot, 30, 0, -7.0F, -10.875F, -7.0F, 14, 1, 2, 0.0F));
        stirringPot.cubeList.add(new ModelBox(stirringPot, 14, 12, -7.0F, -9.875F, 6.0F, 14, 6, 1, 0.0F));
        stirringPot.cubeList.add(new ModelBox(stirringPot, 14, 12, -7.0F, -9.875F, -7.0F, 14, 6, 1, 0.0F));
        stirringPot.cubeList.add(new ModelBox(stirringPot, 30, 6, -6.0F, -4.875F, 5.0F, 12, 1, 1, 0.0F));
        stirringPot.cubeList.add(new ModelBox(stirringPot, 30, 6, -6.0F, -4.875F, -6.0F, 12, 1, 1, 0.0F));
        stirringPot.cubeList.add(new ModelBox(stirringPot, 30, 19, 5.0F, -4.875F, -5.0F, 1, 1, 10, 0.0F));
        stirringPot.cubeList.add(new ModelBox(stirringPot, 30, 19, -6.0F, -4.875F, -5.0F, 1, 1, 10, 0.0F));
        stirringPot.cubeList.add(new ModelBox(stirringPot, 16, 20, 5.0F, -10.875F, -5.0F, 2, 1, 10, 0.0F));
        stirringPot.cubeList.add(new ModelBox(stirringPot, 16, 20, -7.0F, -10.875F, -5.0F, 2, 1, 10, 0.0F));
        stirringPot.cubeList.add(new ModelBox(stirringPot, 0, 12, 6.0F, -9.875F, -6.0F, 1, 6, 12, 0.0F));
        stirringPot.cubeList.add(new ModelBox(stirringPot, 0, 12, -7.0F, -9.875F, -6.0F, 1, 6, 12, 0.0F));
        stirringPot.cubeList.add(new ModelBox(stirringPot, 0, 0, -5.0F, -3.875F, -5.0F, 10, 2, 10, 0.1F));
        stirringPot.cubeList.add(new ModelBox(stirringPot, 32, 31, -5.0F, -12.875F, 4.0F, 10, 2, 1, 0.0F));
        stirringPot.cubeList.add(new ModelBox(stirringPot, 32, 31, -5.0F, -12.875F, -5.0F, 10, 2, 1, 0.0F));
        stirringPot.cubeList.add(new ModelBox(stirringPot, 22, 31, 4.0F, -12.875F, -4.0F, 1, 2, 8, 0.0F));
        stirringPot.cubeList.add(new ModelBox(stirringPot, 22, 31, -5.0F, -12.875F, -4.0F, 1, 2, 8, 0.0F));
        stirringPot.cubeList.add(new ModelBox(stirringPot, 30, 3, -6.0F, -14.875F, 5.0F, 12, 2, 1, 0.0F));
        stirringPot.cubeList.add(new ModelBox(stirringPot, 30, 3, -6.0F, -14.875F, -6.0F, 12, 2, 1, 0.0F));
        stirringPot.cubeList.add(new ModelBox(stirringPot, 0, 30, 5.0F, -14.875F, -5.0F, 1, 2, 10, 0.0F));
        stirringPot.cubeList.add(new ModelBox(stirringPot, 0, 30, -6.0F, -14.875F, -5.0F, 1, 2, 10, 0.0F));

        leg0 = new ModelRenderer(this);
        leg0.setRotationPoint(4.1716F, -6.375F, 4.1716F);
        stirringPot.addChild(leg0);
        setRotationAngle(leg0, 0.0F, 0.7854F, 0.0F);
        leg0.cubeList.add(new ModelBox(leg0, 0, 0, -1.5F, 2.5F, 0.0F, 3, 2, 2, 0.0F));
        leg0.cubeList.add(new ModelBox(leg0, 0, 4, -1.0F, 3.5F, 1.0F, 2, 3, 2, 0.0F));
        leg0.cubeList.add(new ModelBox(leg0, 0, 12, -1.0F, 5.5F, 3.0F, 2, 1, 1, 0.0F));

        leg1 = new ModelRenderer(this);
        leg1.setRotationPoint(-4.1716F, -6.375F, 4.1716F);
        stirringPot.addChild(leg1);
        setRotationAngle(leg1, 0.0F, -0.7854F, 0.0F);
        leg1.cubeList.add(new ModelBox(leg1, 0, 0, -1.5F, 2.5F, 0.0F, 3, 2, 2, 0.0F));
        leg1.cubeList.add(new ModelBox(leg1, 0, 4, -1.0F, 3.5F, 1.0F, 2, 3, 2, 0.0F));
        leg1.cubeList.add(new ModelBox(leg1, 0, 12, -1.0F, 5.5F, 3.0F, 2, 1, 1, 0.0F));

        leg2 = new ModelRenderer(this);
        leg2.setRotationPoint(-4.1716F, -6.375F, -4.1716F);
        stirringPot.addChild(leg2);
        setRotationAngle(leg2, 0.0F, 0.7854F, 0.0F);
        leg2.cubeList.add(new ModelBox(leg2, 0, 0, -1.5F, 2.5F, -2.0F, 3, 2, 2, 0.0F));
        leg2.cubeList.add(new ModelBox(leg2, 0, 4, -1.0F, 3.5F, -3.0F, 2, 3, 2, 0.0F));
        leg2.cubeList.add(new ModelBox(leg2, 0, 12, -1.0F, 5.5F, -4.0F, 2, 1, 1, 0.0F));

        leg3 = new ModelRenderer(this);
        leg3.setRotationPoint(4.1716F, -6.375F, -4.1716F);
        stirringPot.addChild(leg3);
        setRotationAngle(leg3, 0.0F, -0.7854F, 0.0F);
        leg3.cubeList.add(new ModelBox(leg3, 0, 0, -1.5F, 2.5F, -2.0F, 3, 2, 2, 0.0F));
        leg3.cubeList.add(new ModelBox(leg3, 0, 4, -1.0F, 3.5F, -3.0F, 2, 3, 2, 0.0F));
        leg3.cubeList.add(new ModelBox(leg3, 0, 12, -1.0F, 5.5F, -4.0F, 2, 1, 1, 0.0F));
    }

    @Override
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        stirringPot.render(p_78088_7_);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

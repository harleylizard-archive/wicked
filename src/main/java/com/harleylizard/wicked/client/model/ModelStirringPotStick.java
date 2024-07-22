package com.harleylizard.wicked.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public final class ModelStirringPotStick extends ModelBase {
    private final ModelRenderer stick;
    private final ModelRenderer stickPart;

    {
        textureWidth = 32;
        textureHeight = 16;

        stick = new ModelRenderer(this);
        stick.setRotationPoint(0.0F, 9.0F, 0.0F);

        stickPart = new ModelRenderer(this);
        stickPart.setRotationPoint(1.0F, 5.0F, 0.0F);
        stick.addChild(stickPart);
        setRotationAngle(stickPart, 0.0F, 0.0F, 0.48F);
        stickPart.cubeList.add(new ModelBox(stickPart, 0, 0, -1.0F, -7.0F, -1.0F, 2, 14, 2, 0.0F));
    }

    @Override
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        stick.render(p_78088_7_);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

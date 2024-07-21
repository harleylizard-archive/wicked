package com.harleylizard.wicked.mixin;

import com.harleylizard.wicked.client.TextureAtlas;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.texture.Stitcher;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(TextureMap.class)
public final class TextureMapMixin implements TextureAtlas {
    @Unique private int width;
    @Unique private int height;

    @Inject(method = "loadTextureAtlas", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/texture/Stitcher;doStitch()V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    public void loadTextureAtlas(IResourceManager p_110571_1_, CallbackInfo ci, @Local Stitcher stitcher) {
        width = stitcher.getCurrentWidth();
        height = stitcher.getCurrentHeight();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}

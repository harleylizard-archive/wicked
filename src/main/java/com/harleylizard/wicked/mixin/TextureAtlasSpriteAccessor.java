package com.harleylizard.wicked.mixin;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(TextureAtlasSprite.class)
public interface TextureAtlasSpriteAccessor {

    @Accessor("originX")
    int getOriginX();

    @Accessor("originY")
    int getOriginY();
}

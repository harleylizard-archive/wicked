package com.harleylizard.wicked.proxy;

import com.harleylizard.wicked.client.renderer.BlockRendererStirringPot;
import cpw.mods.fml.client.registry.RenderingRegistry;

public final class ClientProxy implements Proxy {

    @Override
    public void preInit() {
        RenderingRegistry.registerBlockHandler(BlockRendererStirringPot.ID = RenderingRegistry.getNextAvailableRenderId(), new BlockRendererStirringPot());
    }
}

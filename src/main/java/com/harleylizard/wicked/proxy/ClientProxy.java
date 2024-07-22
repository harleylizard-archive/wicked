package com.harleylizard.wicked.proxy;

import com.harleylizard.wicked.client.ModelCandle;
import com.harleylizard.wicked.client.ModelStirringPot;
import com.harleylizard.wicked.client.renderer.TileStirringPotRenderer;
import com.harleylizard.wicked.common.tile.TileStirringPot;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public final class ClientProxy implements Proxy {

    @Override
    public void preInit() {
        RenderingRegistry.registerBlockHandler(ModelStirringPot.ID = RenderingRegistry.getNextAvailableRenderId(), new ModelStirringPot());
        RenderingRegistry.registerBlockHandler(ModelCandle.ID = RenderingRegistry.getNextAvailableRenderId(), new ModelCandle());

        ClientRegistry.bindTileEntitySpecialRenderer(TileStirringPot.class, new TileStirringPotRenderer());
    }
}

package com.harleylizard.wicked.client.renderer;

import com.harleylizard.wicked.client.Model;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public final class BlockRendererStirringPot implements ISimpleBlockRenderingHandler {
    public static int ID;

    private final Model model = Model.fromJson(
            "{\n" +
            "\t\"elements\": [\n" +
            "\t\t{\n" +
            "\t\t\t\"from\": [0, 0, 0],\n" +
            "\t\t\t\"to\": [16, 16, 16],\n" +
            "\t\t\t\"color\": 1,\n" +
            "\t\t\t\"faces\": {\n" +
            "\t\t\t\t\"north\": {\"uv\": [0, 0, 16, 16], \"texture\": \"minecraft:dirt\"},\n" +
            "\t\t\t\t\"east\": {\"uv\": [0, 0, 16, 16], \"texture\": \"minecraft:dirt\"},\n" +
            "\t\t\t\t\"south\": {\"uv\": [0, 0, 16, 16], \"texture\": \"minecraft:dirt\"},\n" +
            "\t\t\t\t\"west\": {\"uv\": [0, 0, 16, 16], \"texture\": \"minecraft:dirt\"},\n" +
            "\t\t\t\t\"up\": {\"uv\": [0, 0, 16, 16], \"texture\": \"minecraft:dirt\"},\n" +
            "\t\t\t\t\"down\": {\"uv\": [0, 0, 16, 16], \"texture\": \"minecraft:dirt\"}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t]\n" +
            "}"
    );

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        model.drawInventory();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return modelId == ID;
    }

    @Override
    public int getRenderId() {
        return ID;
    }
}

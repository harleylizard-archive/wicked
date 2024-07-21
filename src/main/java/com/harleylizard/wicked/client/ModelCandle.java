package com.harleylizard.wicked.client;

import com.harleylizard.wicked.common.block.Color;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public final class ModelCandle implements ISimpleBlockRenderingHandler {
    public static int ID;

    private final Model[] models = new Model[Color.values().length];

    {
        for (int i = 0; i < models.length; i++) {
            models[i] = Model.fromJson(createModel(Color.values()[i]));
        }
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (modelId == ID) {
            models[world.getBlockMetadata(x, y, z)].drawWorld(block, world, x, y, z);
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return ID;
    }

    private static String createModel(Color color) {
        String name = color.getName();
        String json = "{\n" +
                "\t\"textures\": {\n" +
                "\t\t\"particle\": \"wicked:white_candle\",\n" +
                "\t\t\"white_candle\": \"wicked:white_candle\"\n" +
                "\t},\n" +
                "\t\"elements\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"from\": [6.5, 0, 6.5],\n" +
                "\t\t\t\"to\": [9.5, 8, 9.5],\n" +
                "\t\t\t\"rotation\": {\"angle\": 0, \"axis\": \"y\", \"origin\": [-0.5, 0, -0.5]},\n" +
                "\t\t\t\"faces\": {\n" +
                "\t\t\t\t\"north\": {\"uv\": [0, 8, 3, 16], \"texture\": \"#white_candle\"},\n" +
                "\t\t\t\t\"east\": {\"uv\": [0, 8, 3, 16], \"texture\": \"#white_candle\"},\n" +
                "\t\t\t\t\"south\": {\"uv\": [0, 8, 3, 16], \"texture\": \"#white_candle\"},\n" +
                "\t\t\t\t\"west\": {\"uv\": [0, 8, 3, 16], \"texture\": \"#white_candle\"},\n" +
                "\t\t\t\t\"up\": {\"uv\": [7, 10, 10, 13], \"texture\": \"#white_candle\"},\n" +
                "\t\t\t\t\"down\": {\"uv\": [7, 13, 10, 16], \"texture\": \"#white_candle\"}\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"from\": [8, 8, 7.5],\n" +
                "\t\t\t\"to\": [8, 9, 8.5],\n" +
                "\t\t\t\"rotation\": {\"angle\": -45, \"axis\": \"y\", \"origin\": [8, 8, 8]},\n" +
                "\t\t\t\"faces\": {\n" +
                "\t\t\t\t\"east\": {\"uv\": [1, 7, 2, 8], \"texture\": \"#white_candle\"},\n" +
                "\t\t\t\t\"west\": {\"uv\": [1, 7, 2, 8], \"texture\": \"#white_candle\"}\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"from\": [8, 8, 7.5],\n" +
                "\t\t\t\"to\": [8, 9, 8.5],\n" +
                "\t\t\t\"rotation\": {\"angle\": 45, \"axis\": \"y\", \"origin\": [8, 8, 8]},\n" +
                "\t\t\t\"faces\": {\n" +
                "\t\t\t\t\"east\": {\"uv\": [1, 7, 2, 8], \"texture\": \"#white_candle\"},\n" +
                "\t\t\t\t\"west\": {\"uv\": [1, 7, 2, 8], \"texture\": \"#white_candle\"}\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"from\": [6, 0, 6],\n" +
                "\t\t\t\"to\": [10, 2, 10],\n" +
                "\t\t\t\"faces\": {\n" +
                "\t\t\t\t\"north\": {\"uv\": [3, 14, 7, 16], \"texture\": \"#white_candle\"},\n" +
                "\t\t\t\t\"east\": {\"uv\": [3, 14, 7, 16], \"texture\": \"#white_candle\"},\n" +
                "\t\t\t\t\"south\": {\"uv\": [3, 14, 7, 16], \"texture\": \"#white_candle\"},\n" +
                "\t\t\t\t\"west\": {\"uv\": [3, 14, 7, 16], \"texture\": \"#white_candle\"},\n" +
                "\t\t\t\t\"up\": {\"uv\": [3, 10, 7, 14], \"texture\": \"#white_candle\"}\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"from\": [5, 0.1, 5],\n" +
                "\t\t\t\"to\": [11, 0.1, 11],\n" +
                "\t\t\t\"rotation\": {\"angle\": 0, \"axis\": \"y\", \"origin\": [0, 0.1, 0]},\n" +
                "\t\t\t\"faces\": {\n" +
                "\t\t\t\t\"up\": {\"uv\": [10, 10, 16, 16], \"texture\": \"#white_candle\"},\n" +
                "\t\t\t\t\"down\": {\"uv\": [10, 10, 16, 16], \"texture\": \"#white_candle\"}\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
        return json.replace("white_candle", name + "_candle");
    }
}

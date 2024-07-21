package com.harleylizard.wicked.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public final class ModelStirringPot implements ISimpleBlockRenderingHandler {
    public static int ID;

    private final Model model = Model.fromJson(
            "{\n" +
                    "\t\"parent\": \"builtin/entity\",\n" +
                    "\t\"textures\": {\n" +
                    "\t\t\"particle\": \"wicked:stirring_pot_side\",\n" +
                    "\t\t\"stirring_pot_side\": \"wicked:stirring_pot_side\",\n" +
                    "\t\t\"stirring_pot_top_0\": \"wicked:stirring_pot_top_0\",\n" +
                    "\t\t\"stirring_pot_top_1\": \"wicked:stirring_pot_top_1\"\n" +
                    "\t},\n" +
                    "\t\"elements\": [\n" +
                    "\t\t{\n" +
                    "\t\t\t\"from\": [1, 3, 1],\n" +
                    "\t\t\t\"to\": [15, 11, 15],\n" +
                    "\t\t\t\"rotation\": {\"angle\": 0, \"axis\": \"y\", \"origin\": [8, 8.25, 8]},\n" +
                    "\t\t\t\"faces\": {\n" +
                    "\t\t\t\t\"north\": {\"uv\": [1, 5, 15, 13], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"east\": {\"uv\": [1, 5, 15, 13], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"south\": {\"uv\": [1, 5, 15, 13], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"west\": {\"uv\": [1, 5, 15, 13], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"up\": {\"uv\": [15, 15, 1, 1], \"texture\": \"#stirring_pot_top_1\"},\n" +
                    "\t\t\t\t\"down\": {\"uv\": [43, -1, 29, 13], \"texture\": \"#missing\"}\n" +
                    "\t\t\t}\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"from\": [15, 3, 1],\n" +
                    "\t\t\t\"to\": [1, 11, 15],\n" +
                    "\t\t\t\"rotation\": {\"angle\": 0, \"axis\": \"y\", \"origin\": [22, 8.25, 8]},\n" +
                    "\t\t\t\"faces\": {\n" +
                    "\t\t\t\t\"north\": {\"uv\": [1, 5, 15, 13], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"east\": {\"uv\": [1, 5, 15, 13], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"south\": {\"uv\": [1, 5, 15, 13], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"west\": {\"uv\": [1, 5, 15, 13], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"up\": {\"uv\": [15, 15, 1, 1], \"texture\": \"#stirring_pot_top_1\"},\n" +
                    "\t\t\t\t\"down\": {\"uv\": [43, -1, 29, 13], \"texture\": \"#missing\"}\n" +
                    "\t\t\t}\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"from\": [3, 2, 3],\n" +
                    "\t\t\t\"to\": [13, 3, 13],\n" +
                    "\t\t\t\"rotation\": {\"angle\": 0, \"axis\": \"y\", \"origin\": [8, 8.25, 8]},\n" +
                    "\t\t\t\"faces\": {\n" +
                    "\t\t\t\t\"north\": {\"uv\": [3, 13, 13, 14], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"east\": {\"uv\": [3, 13, 13, 14], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"south\": {\"uv\": [3, 13, 13, 14], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"west\": {\"uv\": [3, 13, 13, 14], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"down\": {\"uv\": [30, 0, 20, 10], \"texture\": \"#missing\"}\n" +
                    "\t\t\t}\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"from\": [3, 11, 3],\n" +
                    "\t\t\t\"to\": [13, 13, 13],\n" +
                    "\t\t\t\"rotation\": {\"angle\": 0, \"axis\": \"y\", \"origin\": [8, 8.25, 8]},\n" +
                    "\t\t\t\"faces\": {\n" +
                    "\t\t\t\t\"north\": {\"uv\": [3, 3, 13, 5], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"east\": {\"uv\": [3, 3, 13, 5], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"south\": {\"uv\": [3, 3, 13, 5], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"west\": {\"uv\": [3, 3, 13, 5], \"texture\": \"#stirring_pot_side\"}\n" +
                    "\t\t\t}\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"from\": [13, 11, 3],\n" +
                    "\t\t\t\"to\": [3, 13, 13],\n" +
                    "\t\t\t\"rotation\": {\"angle\": 0, \"axis\": \"y\", \"origin\": [8, 8.25, 8]},\n" +
                    "\t\t\t\"faces\": {\n" +
                    "\t\t\t\t\"north\": {\"uv\": [3, 3, 13, 5], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"east\": {\"uv\": [3, 3, 13, 5], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"south\": {\"uv\": [3, 3, 13, 5], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"west\": {\"uv\": [3, 3, 13, 5], \"texture\": \"#stirring_pot_side\"}\n" +
                    "\t\t\t}\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"from\": [2, 13, 2],\n" +
                    "\t\t\t\"to\": [14, 15, 14],\n" +
                    "\t\t\t\"rotation\": {\"angle\": 0, \"axis\": \"y\", \"origin\": [8, 8.25, 8]},\n" +
                    "\t\t\t\"faces\": {\n" +
                    "\t\t\t\t\"north\": {\"uv\": [2, 1, 14, 3], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"east\": {\"uv\": [2, 1, 14, 3], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"south\": {\"uv\": [2, 1, 14, 3], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"west\": {\"uv\": [2, 1, 14, 3], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"up\": {\"uv\": [14, 14, 2, 2], \"texture\": \"#stirring_pot_top_0\"},\n" +
                    "\t\t\t\t\"down\": {\"uv\": [14, 2, 2, 14], \"texture\": \"#stirring_pot_top_0\"}\n" +
                    "\t\t\t}\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"from\": [13, 13, 3],\n" +
                    "\t\t\t\"to\": [3, 15, 13],\n" +
                    "\t\t\t\"rotation\": {\"angle\": 0, \"axis\": \"y\", \"origin\": [8, 8.25, 8]},\n" +
                    "\t\t\t\"faces\": {\n" +
                    "\t\t\t\t\"north\": {\"uv\": [2, 1, 14, 3], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"east\": {\"uv\": [2, 1, 14, 3], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"south\": {\"uv\": [2, 1, 14, 3], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"west\": {\"uv\": [2, 1, 14, 3], \"texture\": \"#stirring_pot_side\"}\n" +
                    "\t\t\t}\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"from\": [10, 0, 10],\n" +
                    "\t\t\t\"to\": [13, 2, 13],\n" +
                    "\t\t\t\"rotation\": {\"angle\": 0, \"axis\": \"y\", \"origin\": [8, 1, 8]},\n" +
                    "\t\t\t\"faces\": {\n" +
                    "\t\t\t\t\"north\": {\"uv\": [3, 14, 6, 16], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"east\": {\"uv\": [3, 14, 6, 16], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"south\": {\"uv\": [10, 14, 13, 16], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"west\": {\"uv\": [10, 14, 13, 16], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"down\": {\"uv\": [13, 13, 16, 16], \"texture\": \"#missing\"}\n" +
                    "\t\t\t}\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"from\": [3, 0, 10],\n" +
                    "\t\t\t\"to\": [6, 2, 13],\n" +
                    "\t\t\t\"rotation\": {\"angle\": 0, \"axis\": \"y\", \"origin\": [8, 1, 8]},\n" +
                    "\t\t\t\"faces\": {\n" +
                    "\t\t\t\t\"north\": {\"uv\": [13, 14, 10, 16], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"east\": {\"uv\": [6, 14, 3, 16], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"south\": {\"uv\": [6, 14, 3, 16], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"west\": {\"uv\": [10, 14, 13, 16], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"down\": {\"uv\": [22, 13, 25, 16], \"texture\": \"#missing\"}\n" +
                    "\t\t\t}\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"from\": [3, 0, 3],\n" +
                    "\t\t\t\"to\": [6, 2, 6],\n" +
                    "\t\t\t\"rotation\": {\"angle\": 0, \"axis\": \"y\", \"origin\": [8, 1, 8]},\n" +
                    "\t\t\t\"faces\": {\n" +
                    "\t\t\t\t\"north\": {\"uv\": [10, 14, 13, 16], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"east\": {\"uv\": [10, 14, 13, 16], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"south\": {\"uv\": [3, 14, 6, 16], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"west\": {\"uv\": [3, 14, 6, 16], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"down\": {\"uv\": [13, 13, 16, 16], \"texture\": \"#missing\"}\n" +
                    "\t\t\t}\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"from\": [10, 0, 3],\n" +
                    "\t\t\t\"to\": [13, 2, 6],\n" +
                    "\t\t\t\"rotation\": {\"angle\": 0, \"axis\": \"y\", \"origin\": [8, 1, 8]},\n" +
                    "\t\t\t\"faces\": {\n" +
                    "\t\t\t\t\"north\": {\"uv\": [3, 14, 6, 16], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"east\": {\"uv\": [10, 14, 13, 16], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"south\": {\"uv\": [10, 14, 13, 16], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"west\": {\"uv\": [3, 14, 6, 16], \"texture\": \"#stirring_pot_side\"},\n" +
                    "\t\t\t\t\"down\": {\"uv\": [25, 13, 22, 16], \"texture\": \"#missing\"}\n" +
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
        if (modelId == ID) {
            model.drawWorld(block, world, x, y, z);
            return true;
        }
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

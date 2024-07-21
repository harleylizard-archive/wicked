package com.harleylizard.wicked.client;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.harleylizard.wicked.mixin.TextureAtlasSpriteAccessor;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.util.*;

public final class Model implements Iterable<Model.Cube> {
    private static final int NORTH = 2;
    private static final int EAST = 5;
    private static final int SOUTH = 3;
    private static final int WEST = 4;
    private static final int UP = 1;
    private static final int DOWN = 0;

    private final List<Cube> cubes;
    private final Map<String, String> textures;

    private Model(List<Cube> cubes, Map<String, String> textures) {
        this.cubes = cubes;
        this.textures = textures;
    }

    @Override
    public Iterator<Cube> iterator() {
        return cubes.iterator();
    }

    public void drawInventory() {
        Tessellator tessellator = Tessellator.instance;
        for (Cube cube : this) {
            float fromX = cube.minX - 0.5F;
            float fromY = cube.minY - 0.5F;
            float fromZ = cube.minZ - 0.5F;
            float toX = cube.maxX - 0.5F;
            float toY = cube.maxY - 0.5F;
            float toZ = cube.maxZ - 0.5F;

            Map<Integer, Face> faces = cube.faces;
            float minU;
            float minV;
            float maxU;
            float maxV;
            float[] texture;
            Face face = faces.get(NORTH);
            if (face != null) {
                texture = face.getTexture();
                minU = texture[0];
                minV = texture[1];
                maxU = texture[2];
                maxV = texture[3];
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, 0.0F, -1.0F);
                tessellator.addVertexWithUV(toX, fromY, fromZ, minU, maxV);
                tessellator.addVertexWithUV(fromX, fromY, fromZ, maxU, maxV);
                tessellator.addVertexWithUV(fromX, toY, fromZ, maxU, minV);
                tessellator.addVertexWithUV(toX, toY, fromZ, minU, minV);
                tessellator.draw();
            }

            face = faces.get(SOUTH);
            if (face != null) {
                texture = face.getTexture();
                minU = texture[0];
                minV = texture[1];
                maxU = texture[2];
                maxV = texture[3];
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, 0.0F, 1.0F);
                tessellator.addVertexWithUV(fromX, fromY, toZ, minU, maxV);
                tessellator.addVertexWithUV(toX, fromY, toZ, maxU, maxV);
                tessellator.addVertexWithUV(toX, toY, toZ, maxU, minV);
                tessellator.addVertexWithUV(fromX, toY, toZ, minU, minV);
                tessellator.draw();
            }

            face = faces.get(EAST);
            if (face != null) {
                texture = face.getTexture();
                minU = texture[0];
                minV = texture[1];
                maxU = texture[2];
                maxV = texture[3];
                tessellator.startDrawingQuads();
                tessellator.setNormal(1.0F, 0.0F, 0.0F);
                tessellator.addVertexWithUV(toX, fromY, toZ, minU, maxV);
                tessellator.addVertexWithUV(toX, fromY, fromZ, maxU, maxV);
                tessellator.addVertexWithUV(toX, toY, fromZ, maxU, minV);
                tessellator.addVertexWithUV(toX, toY, toZ, minU, minV);
                tessellator.draw();
            }

            face = faces.get(WEST);
            if (face != null) {
                texture = face.getTexture();
                minU = texture[0];
                minV = texture[1];
                maxU = texture[2];
                maxV = texture[3];
                tessellator.startDrawingQuads();
                tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                tessellator.addVertexWithUV(fromX, fromY, fromZ, minU, maxV);
                tessellator.addVertexWithUV(fromX, fromY, toZ, maxU, maxV);
                tessellator.addVertexWithUV(fromX, toY, toZ, maxU, minV);
                tessellator.addVertexWithUV(fromX, toY, fromZ, minU, minV);
                tessellator.draw();
            }

            face = faces.get(UP);
            if (face != null) {
                texture = face.getTexture();
                minU = texture[0];
                minV = texture[1];
                maxU = texture[2];
                maxV = texture[3];
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, 1.0F, 0.0F);
                tessellator.addVertexWithUV(toX, toY, fromZ, maxU, maxV);
                tessellator.addVertexWithUV(fromX, toY, fromZ, maxU, minV);
                tessellator.addVertexWithUV(fromX, toY, toZ, minU, minV);
                tessellator.addVertexWithUV(toX, toY, toZ, minU, maxV);
                tessellator.draw();
            }

            face = faces.get(DOWN);
            if (face != null) {
                texture = face.getTexture();
                minU = texture[0];
                minV = texture[1];
                maxU = texture[2];
                maxV = texture[3];
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                tessellator.addVertexWithUV(fromX, fromY, fromZ, maxU, maxV);
                tessellator.addVertexWithUV(toX, fromY, fromZ, maxU, minV);
                tessellator.addVertexWithUV(toX, fromY, toZ, minU, minV);
                tessellator.addVertexWithUV(fromX, fromY, toZ, minU, maxV);
                tessellator.draw();
            }
        }
    }

    public void drawWorld(Block block, IBlockAccess access, int x, int y, int z) {
        Tessellator tessellator = Tessellator.instance;
        for (Cube cube : this) {
            float fromX = cube.minX;
            float fromY = cube.minY;
            float fromZ = cube.minZ;
            float toX = cube.maxX;
            float toY = cube.maxY;
            float toZ = cube.maxZ;
            fromX += x;
            fromY += y;
            fromZ += z;
            toX += x;
            toY += y;
            toZ += z;

            Map<Integer, Face> faces = cube.faces;
            float minU;
            float minV;
            float maxU;
            float maxV;
            int i;
            float[] texture;
            Face face = faces.get(NORTH);
            if (face != null) {
                texture = face.getTexture();
                minU = texture[0];
                minV = texture[1];
                maxU = texture[2];
                maxV = texture[3];
                i = block.getMixedBrightnessForBlock(access, x, y, z - 1);
                vertex(tessellator, toX, fromY, fromZ, minU, maxV, 0.0F, 0.0F, -1.0F, i);
                vertex(tessellator, fromX, fromY, fromZ, maxU, maxV, 0.0F, 0.0F, -1.0F, i);
                vertex(tessellator, fromX, toY, fromZ, maxU, minV, 0.0F, 0.0F, -1.0F, i);
                vertex(tessellator, toX, toY, fromZ, minU, minV, 0.0F, 0.0F, -1.0F, i);
            }

            face = faces.get(SOUTH);
            if (face != null) {
                texture = face.getTexture();
                minU = texture[0];
                minV = texture[1];
                maxU = texture[2];
                maxV = texture[3];
                i = block.getMixedBrightnessForBlock(access, x, y, z + 1);
                vertex(tessellator, fromX, fromY, toZ, minU, maxV, 0.0F, 0.0F, 1.0F, i);
                vertex(tessellator, toX, fromY, toZ, maxU, maxV, 0.0F, 0.0F, 1.0F, i);
                vertex(tessellator, toX, toY, toZ, maxU, minV, 0.0F, 0.0F, 1.0F, i);
                vertex(tessellator, fromX, toY, toZ, minU, minV, 0.0F, 0.0F, 1.0F, i);
            }

            face = faces.get(EAST);
            if (face != null) {
                texture = face.getTexture();
                minU = texture[0];
                minV = texture[1];
                maxU = texture[2];
                maxV = texture[3];
                i = block.getMixedBrightnessForBlock(access, x + 1, y, z);
                vertex(tessellator, toX, fromY, toZ, minU, maxV, 1.0F, 0.0F, 0.0F, i);
                vertex(tessellator, toX, fromY, fromZ, maxU, maxV, 1.0F, 0.0F, 0.0F, i);
                vertex(tessellator, toX, toY, fromZ, maxU, minV, 1.0F, 0.0F, 0.0F, i);
                vertex(tessellator, toX, toY, toZ, minU, minV, 1.0F, 0.0F, 0.0F, i);
            }

            face = faces.get(WEST);
            if (face != null) {
                texture = face.getTexture();
                minU = texture[0];
                minV = texture[1];
                maxU = texture[2];
                maxV = texture[3];
                i = block.getMixedBrightnessForBlock(access, x - 1, y, z);
                vertex(tessellator, fromX, fromY, fromZ, minU, maxV, -1.0F, 0.0F, 0.0F, i);
                vertex(tessellator, fromX, fromY, toZ, maxU, maxV, -1.0F, 0.0F, 0.0F, i);
                vertex(tessellator, fromX, toY, toZ, maxU, minV, -1.0F, 0.0F, 0.0F, i);
                vertex(tessellator, fromX, toY, fromZ, minU, minV, -1.0F, 0.0F, 0.0F, i);
            }

            face = faces.get(UP);
            if (face != null) {
                texture = face.getTexture();
                minU = texture[0];
                minV = texture[1];
                maxU = texture[2];
                maxV = texture[3];
                i = block.getMixedBrightnessForBlock(access, x, y + 1, z);
                vertex(tessellator, toX, toY, fromZ, maxU, maxV, 0.0F, 1.0F, 0.0F, i);
                vertex(tessellator, fromX, toY, fromZ, maxU, minV, 0.0F, 1.0F, 0.0F, i);
                vertex(tessellator, fromX, toY, toZ, minU, minV, 0.0F, 1.0F, 0.0F, i);
                vertex(tessellator, toX, toY, toZ, minU, maxV, 0.0F, 1.0F, 0.0F, i);
            }

            face = faces.get(DOWN);
            if (face != null) {
                texture = face.getTexture();
                minU = texture[0];
                minV = texture[1];
                maxU = texture[2];
                maxV = texture[3];
                i = block.getMixedBrightnessForBlock(access, x, y - 1, z);
                vertex(tessellator, fromX, fromY, fromZ, maxU, maxV, 0.0F, -1.0F, 0.0F, i);
                vertex(tessellator, toX, fromY, fromZ, maxU, minV, 0.0F, -1.0F, 0.0F, i);
                vertex(tessellator, toX, fromY, toZ, minU, minV, 0.0F, -1.0F, 0.0F, i);
                vertex(tessellator, fromX, fromY, toZ, minU, maxV, 0.0F, -1.0F, 0.0F, i);
            }
        }
    }

    private void vertex(Tessellator tessellator, float x, float y, float z, float u, float v, float nx, float ny, float nz, int i) {
        tessellator.setNormal(nx, ny, nz);
        tessellator.setBrightness(i);
        float opaque = nx != 0.0F ? 0.625F : nz != 0.0F ? 0.75F : ny == -1.0F ? 0.475F : 1.0F;
        tessellator.setColorOpaque_F(opaque, opaque, opaque);
        tessellator.addVertexWithUV(x, y, z, u, v);
    }

    public static Model fromJson(String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonElement.class).getAsJsonObject();

        Set<Map.Entry<String, JsonElement>> textures = jsonObject.getAsJsonObject("textures").entrySet();
        Map<String, String> map = new HashMap<>(textures.size());
        for (Map.Entry<String, JsonElement> entry : textures) {
            map.put("#" + entry.getKey(), entry.getValue().getAsJsonPrimitive().getAsString());
        }

        JsonArray elements = jsonObject.getAsJsonArray("elements");
        List<Cube> cubes = new ArrayList<>(elements.size());
        for (JsonElement element : elements) {
            jsonObject = element.getAsJsonObject();
            JsonArray from = jsonObject.getAsJsonArray("from");
            JsonArray to = jsonObject.getAsJsonArray("to");

            float minX = from.get(0).getAsFloat() / 16.0F;
            float minY = from.get(1).getAsFloat() / 16.0F;
            float minZ = from.get(2).getAsFloat() / 16.0F;
            float maxX = to.get(0).getAsFloat() / 16.0F;
            float maxY = to.get(1).getAsFloat() / 16.0F;
            float maxZ = to.get(2).getAsFloat() / 16.0F;

            Set<Map.Entry<String, JsonElement>> faces = jsonObject.getAsJsonObject("faces").entrySet();
            Map<Integer, Face> facesMap = new HashMap<>(faces.size());
            for (Map.Entry<String, JsonElement> mapEntry : faces) {
                jsonObject = mapEntry.getValue().getAsJsonObject();
                JsonArray uv = jsonObject.getAsJsonArray("uv");

                int minU = uv.get(0).getAsInt();
                int minV = uv.get(1).getAsInt();
                int maxU = uv.get(2).getAsInt();
                int maxV = uv.get(3).getAsInt();
                String texture = jsonObject.getAsJsonPrimitive("texture").getAsString();
                Face face = new Face(minU, minV, maxU, maxV, map.getOrDefault(texture, "#missing"));
                facesMap.put(getFace(mapEntry.getKey()), face);
            }
            Cube cube = new Cube(minX, minY, minZ, maxX, maxY, maxZ, Collections.unmodifiableMap(facesMap));
            cubes.add(cube);
        }
        return new Model(Collections.unmodifiableList(cubes), Collections.unmodifiableMap(map));
    }

    private static int getFace(String face) {
        switch (face) {
            case "north": return NORTH;
            case "east": return EAST;
            case "south": return SOUTH;
            case "west": return WEST;
            case "up": return UP;
            case "down": return DOWN;
            default: throw new RuntimeException("unknown face " + face);
        }
    }

    public static final class Cube {
        private final float minX;
        private final float minY;
        private final float minZ;
        private final float maxX;
        private final float maxY;
        private final float maxZ;
        private final Map<Integer, Face> faces;

        private Cube(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, Map<Integer, Face> faces) {
            this.minX = minX;
            this.minY = minY;
            this.minZ = minZ;
            this.maxX = maxX;
            this.maxY = maxY;
            this.maxZ = maxZ;
            this.faces = faces;
        }
    }

    public static final class Face {
        private final int minU;
        private final int minV;
        private final int maxU;
        private final int maxV;
        private final String texture;

        private Face(int minU, int minV, int maxU, int maxV, String texture) {
            this.minU = minU;
            this.minV = minV;
            this.maxU=  maxU;
            this.maxV = maxV;
            this.texture = texture.replace("minecraft:", " ").trim();
        }

        private IIcon getIcon() {
            TextureMap textureMap = getTextureMap();

            IIcon missing = textureMap.getAtlasSprite("missingno");
            IIcon icon;
            return texture.equals("#missing") ? missing : (icon = textureMap.getTextureExtry(texture)) == null ? missing : icon;
        }

        private float[] getTexture() {
            IIcon icon = getIcon();

            TextureAtlas textureAtlas = (TextureAtlas) getTextureMap();
            float width = (float) textureAtlas.getWidth();
            float height = (float) textureAtlas.getHeight();

            TextureAtlasSpriteAccessor accessor = (TextureAtlasSpriteAccessor) icon;
            float x = (float) accessor.getOriginX() / width;
            float y = (float) accessor.getOriginY() / height;
            float minU = x + ((float) this.minU / width);
            float minV = y + ((float) this.minV / height);
            float maxU = x + ((float) this.maxU / width);
            float maxV = y + ((float) this.maxV / height);
            return new float[] {
                    minU,
                    minV,
                    maxU,
                    maxV
            };
        }

        private static TextureMap getTextureMap() {
            return ((TextureMap) Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationBlocksTexture));
        }
    }
}

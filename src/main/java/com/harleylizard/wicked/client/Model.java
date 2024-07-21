package com.harleylizard.wicked.client;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.harleylizard.wicked.mixin.TextureAtlasSpriteAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;

import java.util.*;

public final class Model implements Iterable<Model.Cube> {
    private static final int NORTH = 2;
    private static final int EAST = 5;
    private static final int SOUTH = 3;
    private static final int WEST = 4;

    private final List<Cube> cubes;

    private Model(List<Cube> cubes) {
        this.cubes = cubes;
    }

    @Override
    public Iterator<Cube> iterator() {
        return cubes.iterator();
    }

    public void drawInventory() {
        Tessellator tessellator = Tessellator.instance;
        for (Cube cube : this) {
            float fromX = (float) cube.minX / 16.0F - 0.5F;
            float fromY = (float) cube.minY / 16.0F - 0.5F;
            float fromZ = (float) cube.minZ / 16.0F - 0.5F;
            float toX = (float) cube.maxX / 16.0F - 0.5F;
            float toY = (float) cube.maxY / 16.0F - 0.5F;
            float toZ = (float) cube.maxZ / 16.0F - 0.5F;

            Map<Integer, Face> faces = cube.faces;
            float minU;
            float minV;
            float maxU;
            float maxV;
            Face face = faces.get(NORTH);
            float[] texture = face.getTexture();
            minU = texture[0];
            minV = texture[1];
            maxU = texture[2];
            maxV = texture[3];
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, 1.0F);
            tessellator.addVertexWithUV(toX, fromY, fromZ, minU, maxV);
            tessellator.addVertexWithUV(fromX, fromY, fromZ, maxU, maxV);
            tessellator.addVertexWithUV(fromX, toY, fromZ, maxU, minV);
            tessellator.addVertexWithUV(toX, toY, fromZ, minU, minV);
            tessellator.draw();

            face = faces.get(SOUTH);
            texture = face.getTexture();
            minU = texture[0];
            minV = texture[1];
            maxU = texture[2];
            maxV = texture[3];
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, -1.0F);
            tessellator.addVertexWithUV(fromX, fromY, toZ, minU, maxV);
            tessellator.addVertexWithUV(toX, fromY, toZ, maxU, maxV);
            tessellator.addVertexWithUV(toX, toY, toZ, maxU, minV);
            tessellator.addVertexWithUV(fromX, toY, toZ, minU, minV);
            tessellator.draw();

            face = faces.get(EAST);
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

            face = faces.get(WEST);
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
    }

    public static Model fromJson(String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonElement.class).getAsJsonObject();

        JsonArray elements = jsonObject.getAsJsonArray("elements");
        List<Cube> cubes = new ArrayList<>(elements.size());
        for (JsonElement element : elements) {
            jsonObject = element.getAsJsonObject();
            JsonArray from = jsonObject.getAsJsonArray("from");
            JsonArray to = jsonObject.getAsJsonArray("to");

            int minX = from.get(0).getAsInt();
            int minY = from.get(1).getAsInt();
            int minZ = from.get(2).getAsInt();
            int maxX = to.get(0).getAsInt();
            int maxY = to.get(1).getAsInt();
            int maxZ = to.get(2).getAsInt();

            Set<Map.Entry<String, JsonElement>> faces = jsonObject.getAsJsonObject("faces").entrySet();
            Map<Integer, Face> map = new HashMap<>(faces.size());
            for (Map.Entry<String, JsonElement> mapEntry : faces) {
                jsonObject = mapEntry.getValue().getAsJsonObject();
                JsonArray uv = jsonObject.getAsJsonArray("uv");

                int minU = uv.get(0).getAsInt();
                int minV = uv.get(1).getAsInt();
                int maxU = uv.get(2).getAsInt();
                int maxV = uv.get(3).getAsInt();
                Face face = new Face(minU, minV, maxU, maxV, jsonObject.getAsJsonPrimitive("texture").getAsString());
                map.put(getFace(mapEntry.getKey()), face);
            }
            Cube cube = new Cube(minX, minY, minZ, maxX, maxY, maxZ, Collections.unmodifiableMap(map));
            cubes.add(cube);
        }
        return new Model(Collections.unmodifiableList(cubes));
    }

    private static int getFace(String face) {
        switch (face) {
            case "north": return NORTH;
            case "east": return EAST;
            case "south": return SOUTH;
            case "west": return WEST;
            case "up": return 1;
            case "down": return 0;
            default: throw new RuntimeException("unknown face " + face);
        }
    }

    public static final class Cube {
        private final int minX;
        private final int minY;
        private final int minZ;
        private final int maxX;
        private final int maxY;
        private final int maxZ;
        private final Map<Integer, Face> faces;

        private Cube(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, Map<Integer, Face> faces) {
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
            float minU = x;
            float minV = y;
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

package com.harleylizard.wicked.common;

import com.harleylizard.wicked.common.block.Color;
import com.harleylizard.wicked.common.message.CreateStirringPotMessage;
import com.harleylizard.wicked.common.tileentity.TileEntityStirringPot;
import com.harleylizard.wicked.proxy.Proxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

@Mod(modid = Wicked.MOD_ID, name = Wicked.NAME, version = Wicked.VERSION, acceptedMinecraftVersions = "1.7.10", dependencies = "required-after:unimixins@[0.1.17,)")
public final class Wicked {
    public static final String MOD_ID = "wicked";
    public static final String NAME = "Wicked";
    public static final String VERSION = "1.0-SNAPSHOT";

    public static final SimpleNetworkWrapper NETWORK_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);

    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(MOD_ID) {
        @Override
        public Item getTabIconItem() {
            return WickedItems.RAW_MUTTON;
        }
    };

    @SidedProxy(
            clientSide = "com.harleylizard.wicked.proxy.ClientProxy",
            serverSide = "com.harleylizard.wicked.proxy.CommonProxy"
    )
    public static Proxy PROXY;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        WickedBlocks.registerAll();
        WickedItems.registerAll();

        MinecraftForge.EVENT_BUS.register(new Events());
        createOreDictionaries();
        createRecipes();
        createTileEntities();

        int id = 0;
        NETWORK_WRAPPER.registerMessage(CreateStirringPotMessage.class, CreateStirringPotMessage.class, id++, Side.CLIENT);
        PROXY.preInit();
    }

    private void createRecipes() {
        List<ItemStack> sticks = OreDictionary.getOres("stickWood");
        for (ItemStack x : sticks) {
            for (ItemStack y : sticks) {
                GameRegistry.addShapelessRecipe(new ItemStack(WickedItems.BIG_STICK), x, y);
            }
        }
        GameRegistry.addSmelting(WickedItems.RAW_MUTTON, new ItemStack(WickedItems.COOKED_MUTTON), 1.0F);

        ItemStack whiteCandle = new ItemStack(WickedBlocks.CANDLE, 1, Color.WHITE.ordinal());
        GameRegistry.addRecipe(whiteCandle, "X  ", "T  ", "   ", 'X', Items.string, 'T', WickedItems.TALLOW);
        GameRegistry.addRecipe(whiteCandle, " X ", " T ", "   ", 'X', Items.string, 'T', WickedItems.TALLOW);
        GameRegistry.addRecipe(whiteCandle, "  X", "  T", "   ", 'X', Items.string, 'T', WickedItems.TALLOW);
        GameRegistry.addRecipe(whiteCandle, "   ", "X  ", "T  ", 'X', Items.string, 'T', WickedItems.TALLOW);
        GameRegistry.addRecipe(whiteCandle, "   ", " X ", " T ", 'X', Items.string, 'T', WickedItems.TALLOW);
        GameRegistry.addRecipe(whiteCandle, "   ", "  X", "  T", 'X', Items.string, 'T', WickedItems.TALLOW);

        for (Color color : Color.values()) {
            if (color == Color.WHITE) {
                continue;
            }
            for (ItemStack ore : color.getOres()) {
                int i = color.ordinal();
                GameRegistry.addRecipe(new ItemStack(WickedBlocks.CANDLE, 8, i), "XXX", "XDX", "XXX", 'X', whiteCandle, 'D', ore);
            }
        }
        GameRegistry.addShapedRecipe(new ItemStack(WickedBlocks.DOGWOOD_STAIRS, 4, 0), "  X", " XX", "XXX", 'X', new ItemStack(WickedBlocks.PLANKS, 1, 0));
        GameRegistry.addShapedRecipe(new ItemStack(WickedBlocks.HOLLY_STAIRS, 4, 0), "  X", " XX", "XXX", 'X', new ItemStack(WickedBlocks.PLANKS, 1, 1));
        GameRegistry.addShapedRecipe(new ItemStack(WickedBlocks.LINDEN_STAIRS, 4, 0), "  X", " XX", "XXX", 'X', new ItemStack(WickedBlocks.PLANKS, 1, 2));
    }

    private void createOreDictionaries() {
        OreDictionary.registerOre("plankWood", new ItemStack(WickedBlocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("stairWood", WickedBlocks.DOGWOOD_STAIRS);
        OreDictionary.registerOre("stairWood", WickedBlocks.HOLLY_STAIRS);
        OreDictionary.registerOre("stairWood", WickedBlocks.LINDEN_STAIRS);
        OreDictionary.registerOre("slabWood", new ItemStack(WickedBlocks.SLABS, 1, OreDictionary.WILDCARD_VALUE));
    }

    private void createTileEntities() {
        GameRegistry.registerTileEntity(TileEntityStirringPot.class, "stirring_pot");
    }

    public static ResourceLocation resourceLocation(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static final class Events {

        @SubscribeEvent
        public void whenKilled(LivingDropsEvent event) {
            Entity entity = event.entity;
            World world = entity.worldObj;
            if (!world.isRemote && entity instanceof EntitySheep && ((EntitySheep) entity).getGrowingAge() == 0) {
                double x = entity.posX;
                double y = entity.posY;
                double z = entity.posZ;
                ItemStack stack = new ItemStack(WickedItems.RAW_MUTTON, world.rand.nextInt(3));
                EntityItem item = new EntityItem(world, x, y, z, stack);

                event.drops.add(item);
            }
        }
    }
}

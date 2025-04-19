package biggestxuan.bxp2;

import biggestxuan.bxp2.blocks.BxPBlocks;
import biggestxuan.bxp2.capability.IBxPCapability;
import biggestxuan.bxp2.creativeTabs.BxPCreativeTabs;
import biggestxuan.bxp2.effects.BxPEffects;
import biggestxuan.bxp2.fluids.BxPFluids;
import biggestxuan.bxp2.integration.Mekanism.BxPGases;
import biggestxuan.bxp2.integration.Mekanism.BxPInfuseTypes;
import biggestxuan.bxp2.integration.Mekanism.BxPMekFluids;
import biggestxuan.bxp2.integration.thinker.Modifiers.BxPModifiers;
import biggestxuan.bxp2.items.BxPItems;
import biggestxuan.bxp2.network.PacketHandler;
import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import javax.annotation.Nullable;


@Mod(BxP2.MODID)
public class BxP2
{

    public static final String MODID = "bxp2";
    public static final String VERSION = "0.0.15";
    public static final int ID = 1;
    public static boolean isSkyBlock = false;
    public static boolean devMode = true;
    public static final Logger LOGGER = LogUtils.getLogger();

    public BxP2(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerCap);
        BxPBlocks.BLOCKS.register(modEventBus);
        BxPFluids.FLUIDS.register(modEventBus);
        BxPCreativeTabs.CREATIVE_TABS.register(modEventBus);
        BxPItems.ITEMS.register(modEventBus);
        BxPModifiers.REGISTER.register(modEventBus);
        BxPEffects.EFFECTS.register(modEventBus);
        BxPInfuseTypes.INFUSE_TYPES.register(modEventBus);
        BxPGases.GASES.register(modEventBus);
        BxPMekFluids.FLUID.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(PacketHandler::init);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

        // LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public void registerCap(RegisterCapabilitiesEvent event){
        event.register(IBxPCapability.class);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // LOGGER.info("HELLO FROM CLIENT SETUP");
            // LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }

    @Nullable
    public static ResourceLocation RL(String path){
        return ResourceLocation.tryParse(MODID+":"+path);
    }

    @Nullable
    public static ResourceLocation MODRL(String path){
        return ResourceLocation.tryParse(path);
    }

    public static String getName(){
        return MODID+"-"+VERSION;
    }

    public static MutableComponent tr(String text){
        return Component.translatable(text);
    }

    public static String makeDescriptionId(String type, String name) {
        return type + "." + MODID + "." + name;
    }

    @Nullable
    public static Item getItem(ResourceLocation rl){
        return ForgeRegistries.ITEMS.getValue(rl);
    }

    @Nullable
    public static Item getItem(String rl){
        return getItem(MODRL(rl));
    }

    @Nullable
    public static Block getBlock(ResourceLocation rl){
        return ForgeRegistries.BLOCKS.getValue(rl);
    }

    @Nullable
    public static Block getBlock(String rl){
        return getBlock(ResourceLocation.tryParse(rl));
    }

    @Nullable
    public static ItemStack getStack(ResourceLocation rl){
        Item item = getItem(rl);
        return item == null ? null : new ItemStack(item);
    }

    @Nullable
    public static ItemStack getStack(String name){
        return getStack(MODRL(name));
    }
}

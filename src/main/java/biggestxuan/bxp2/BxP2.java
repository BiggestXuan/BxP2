package biggestxuan.bxp2;

import biggestxuan.bxp2.creativeTabs.BxPCreativeTabs;
import biggestxuan.bxp2.fluids.BxPFluids;
import biggestxuan.bxp2.integration.thinker.Modifiers.BxPModifiers;
import biggestxuan.bxp2.items.BxPItems;
import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import javax.annotation.Nullable;


@Mod(BxP2.MODID)
public class BxP2
{

    public static final String MODID = "bxp2";
    public static final String VERSION = "0.0.1";
    public static final int ID = 1;
    public static boolean isSkyBlock = false;
    public static boolean devMode = true;
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public BxP2(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
        BLOCKS.register(modEventBus);
        BxPFluids.FLUIDS.register(modEventBus);
        BxPCreativeTabs.CREATIVE_TABS.register(modEventBus);
        BxPItems.ITEMS.register(modEventBus);
        BxPModifiers.REGISTER.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);


        // context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

        // LOGGER.info("HELLO from server starting");
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

    public static String getName(){
        return MODID+"-"+VERSION;
    }

    public static Component tr(String text){
        return Component.translatable(text);
    }

    public static String makeDescriptionId(String type, String name) {
        return type + "." + MODID + "." + name;
    }
}

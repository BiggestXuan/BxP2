package biggestxuan.bxp2;

import biggestxuan.bxp2.blocks.BxPBlocks;
import biggestxuan.bxp2.blocks.TileEntity.BxPTileEntities;
import biggestxuan.bxp2.capability.IBxPCapability;
import biggestxuan.bxp2.client.ClientCommon;
import biggestxuan.bxp2.client.screen.ATMScreen;
import biggestxuan.bxp2.container.BxPContainers;
import biggestxuan.bxp2.creativeTabs.BxPCreativeTabs;
import biggestxuan.bxp2.damage.BxPDamageType;
import biggestxuan.bxp2.effects.BxPEffects;
import biggestxuan.bxp2.fluids.BxPFluids;
import biggestxuan.bxp2.integration.Mekanism.BxPGases;
import biggestxuan.bxp2.integration.Mekanism.BxPInfuseTypes;
import biggestxuan.bxp2.integration.Mekanism.BxPMekFluids;
import biggestxuan.bxp2.integration.Mekanism.MekaSuit.BxPModules;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.BxPModifiers;
import biggestxuan.bxp2.integration.TConstruct.TinkersSurvival;
import biggestxuan.bxp2.items.BxPItems;
import biggestxuan.bxp2.network.PacketHandler;
import biggestxuan.bxp2.utils.CalendarUtils;
import com.brandon3055.brandonscore.BrandonsCore;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
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

import static biggestxuan.bxp2.container.BxPContainers.ATM_CONTAINER;


@Mod(BxP2.MODID)
public class BxP2
{
    public static boolean devMode = true;
    public static final String MODID = "bxp2";
    public static final String VERSION = "1.0.0";
    public static final int ID = 1;
    public static boolean isSkyBlock = false;
    public static final Logger LOGGER = LogUtils.getLogger();
    public static boolean enableCycleRecipe = false;
    public static String QQGroup = "754879163";

    public BxP2(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerCap);
        MinecraftForge.EVENT_BUS.register(this);
        BxPBlocks.BLOCKS.register(modEventBus);
        BxPFluids.FLUIDS.register(modEventBus);
        BxPCreativeTabs.CREATIVE_TABS.register(modEventBus);
        BxPItems.ITEMS.register(modEventBus);
        BxPModifiers.REGISTER.register(modEventBus);
        BxPEffects.EFFECTS.register(modEventBus);
        BxPInfuseTypes.INFUSE_TYPES.register(modEventBus);
        BxPGases.GASES.register(modEventBus);
        BxPMekFluids.FLUID.register(modEventBus);
        BxPModules.MODULES.register(modEventBus);
        BxPTileEntities.TILES.register(modEventBus);
        BxPContainers.CONTAINERS.register(modEventBus);
        //BxPDamageType.DAMAGE_TYPES.register(modEventBus);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        if(devMode){
            BrandonsCore.inDev = true;
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(PacketHandler::init);
        TinkersSurvival.init();
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
            Minecraft.getInstance().execute(() -> {
                Minecraft.getInstance().getWindow().setTitle(ClientCommon.TITLE);
            });
            MenuScreens.register(ATM_CONTAINER.get(), ATMScreen::new);
            //event.enqueueWork(() -> {
            //    ItemProperties.register(BxPItems.ENCH_SDBZ.get(), MODRL("glint"), (stack, level, entity, seed) -> 1.0f);
            //});
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

    public static MutableComponent tr(String text,Object ... objects){
        return Component.translatable(text,objects);
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
    public static ItemStack getStack(ResourceLocation rl,int count){
        Item item = getItem(rl);
        ItemStack stack = item.getDefaultInstance();
        stack.setCount(count);
        return stack;
    }

    @Nullable
    public static ItemStack getStack(String name,int count){
        return getStack(MODRL(name),count);
    }

    @Nullable
    public static ItemStack getStack(ResourceLocation rl){
        Item item = getItem(rl);
        return item == null ? null : item.getDefaultInstance();
    }

    @Nullable
    public static ItemStack getStack(String name){
        return getStack(MODRL(name));
    }

    public static ItemStack getTconstructCreative(String s){
        ItemStack stack = getStack("tconstruct:creative_slot");
        CompoundTag tag = stack.getOrCreateTag();
        tag.putString("slot",s);
        return stack;
    }

    public static Ingredient getIngredient(String name){
        return Ingredient.of(getStack(name));
    }
}

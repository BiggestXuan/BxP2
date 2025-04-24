package biggestxuan.bxp2.events;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.capability.BxPCapabilityProvider;
import biggestxuan.bxp2.data.DifficultyData;
import biggestxuan.bxp2.items.BxPCatalyst;
import biggestxuan.bxp2.utils.*;
import dev.latvian.mods.kubejs.player.InventoryChangedEventJS;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/4/4
 */
@Mod.EventBusSubscriber(modid = BxP2.MODID)
public class PlayerCommonEvent {
    @SubscribeEvent
    public static void playerLoggedEvent(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if(BxP2.devMode){
            BxP2.LOGGER.info("AAA");
            BxP2.LOGGER.info("{}",player.getCapability(BxPCapabilityProvider.CAPABILITY).isPresent());
            player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(c -> {
                if(c.getPhase() == -1){
                    //c.setPhase(0);
                }
                //c.setMoney(600);
            });
            //BxP2.LOGGER.info(ModUtils.getAllMods());
            BxP2.LOGGER.info("AAA");
            if(player instanceof ServerPlayer serverPlayer){
                BxP2.LOGGER.info("{}", DifficultyData.getInstance(serverPlayer.level()).getDifficulty());
            }
            List<BxPCatalyst> catalysts = BxPCatalyst.getAllCatalyst(BxPCatalyst.ADAPT.ALL);
            catalysts.forEach(e -> {
                BxP2.LOGGER.info("BBB");
                BxP2.LOGGER.info(e.stack.toString());
                BxP2.LOGGER.info(String.valueOf(e.energyRate));
            });
        }
        if(player instanceof ServerPlayer serverPlayer){
            player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(c -> {
                if(c.getPhase() == -1){
                    c.setPhase(0);
                }});
            welcome(player);
            MinecraftServer server = serverPlayer.getServer();
            if(server != null){
                server.getCommands().performPrefixedCommand(server.createCommandSourceStack(),"gamerule sendCommandFeedback "+BxP2.devMode);
                server.setDifficultyLocked(true);
                server.setDifficulty(Difficulty.HARD,true);
                server.getCommands().performPrefixedCommand(server.createCommandSourceStack(),"gamerule keepInventory true");
            }
        }
    }

    @SubscribeEvent
    public static void pickUpEvent(EntityItemPickupEvent event){
        ItemEntity entity = event.getItem();
        if(entity.level().isClientSide) return;
        if(entity.getItem().getTags().findAny().isEmpty()){
            return;
        }
        ItemStack u = UniteUtils.getUniteStack(entity);
        if(u != null){
            if(entity.getItem().getItem().equals(u.getItem())){
                return;
            }
            event.setCanceled(true);
            ItemEntity newEntity = new ItemEntity(entity.level(),entity.getX(),entity.getY(),entity.getZ(),u);
            entity.kill();
            entity.level().addFreshEntity(newEntity);
        }
    }

    @SubscribeEvent
    public static void travelEvent(EntityTravelToDimensionEvent event){
        if (!(event.getEntity() instanceof ServerPlayer player))
            return;
        if(event.getDimension().location().toString().equals("minecraft:the_nether")){
            player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(cap -> {
                if(!cap.canNether()){
                    event.setCanceled(true);
                    Utils.sendMessage(player,BxP2.tr("bxp2.message.cantnether").setStyle(Style.EMPTY.withColor(ChatFormatting.RED)));
                }
            });
        }
        if(event.getDimension().location().toString().equals("minecraft:the_end")){
            player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(cap -> {
                if(!cap.canEnd()){
                    event.setCanceled(true);
                    Utils.sendMessage(player,BxP2.tr("bxp2.message.cantend").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE)));
                }
            });
        }
    }

    @SubscribeEvent
    public static void playerDeath(LivingDeathEvent event){
        if(event.getEntity() instanceof Player player && !event.getEntity().level().isClientSide){
            ItemStack stack = ShopUtils.dropMoney(player);
            if(stack != null){
                ItemEntity entity = new ItemEntity(player.level(),player.getX(),player.getY(),player.getZ(),stack);
                player.level().addFreshEntity(entity);
            }
        }
    }

    private static void welcome(Player player){
        if(BxP2.devMode){
            Utils.sendMessage(player,BxP2.tr("bxp2.message.dev_warning").withStyle(Style.EMPTY.withColor(ChatFormatting.RED)));
        }
        Utils.sendMessage(player,BxP2.tr("bxp2.message.welcome1").append(BxP2.VERSION));
        Utils.sendMessage(player,BxP2.tr("bxp2.message.welcome2").append(DifficultyUtils.getDifficultyColor()));
        Utils.sendMessage(player,BxP2.tr("bxp2.message.welcome3"));
    }
}

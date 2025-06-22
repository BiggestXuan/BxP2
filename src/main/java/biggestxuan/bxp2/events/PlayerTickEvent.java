package biggestxuan.bxp2.events;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.capability.BxPCapabilityProvider;
import biggestxuan.bxp2.integration.TConstruct.Leveling.LevelUtils;
import biggestxuan.bxp2.items.BxPItems;
import biggestxuan.bxp2.utils.PhaseUtils;
import biggestxuan.bxp2.utils.Utils;
import com.github.alexthe666.rats.registry.RatsEffectRegistry;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.item.armor.ModifiableArmorItem;

/**
 * @Author Biggest_Xuan
 * 2025/4/2
 */

@Mod.EventBusSubscriber(modid = BxP2.MODID)
public class PlayerTickEvent {
    @SubscribeEvent
    public static void playerTickEvent(TickEvent.PlayerTickEvent event) {
        var player = event.player;
        Level world = player.level();
        if(event.phase == TickEvent.Phase.START) return;
        if(world.isClientSide){
            if(player.getCapability(BxPCapabilityProvider.CAPABILITY).isPresent()){
                var cap = player.getCapability(BxPCapabilityProvider.CAPABILITY).orElseThrow(NullPointerException::new);
                if(BxP2.devMode){
                   // BxP2.LOGGER.info("{}",cap.getPhase());
                    // BxP2.LOGGER.info("{}",cap.getMoney());
                }
            }
            return;
        }
        /*if(!player.isCreative() && !player.isSpectator()){
            if(player.getCapability(BxPCapabilityProvider.CAPABILITY).isPresent()){
                var cap = player.getCapability(BxPCapabilityProvider.CAPABILITY).orElseThrow(NullPointerException::new);
                Abilities abilities = player.getAbilities();
                if(abilities.flying && !cap.canFly()){
                    player.getAbilities().flying = false;
                    player.getAbilities().mayfly = false;
                    Utils.sendMessage(player,BxP2.tr("bxp2.message.cantfly").setStyle(Style.EMPTY.withColor(ChatFormatting.GOLD)));
                }
            }
        }*/
        MinecraftServer server = ((ServerLevel) world).getServer();
        if(server.getTickCount() % 100 == 0){
            PhaseUtils.setPlayerPhase((ServerPlayer) player);
            player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(cap -> {
                for(ItemStack stack : player.getInventory().items){
                    if(!cap.canEnd() && stack.getItem().equals(BxPItems.BX_ENCH_INGOT.get())){
                        cap.setEnd(true);
                    }
                    if(!cap.canNether() && stack.getItem().equals(BxPItems.BX_INGOT.get())){
                        cap.setNether(true);
                    }
                    /*if(!cap.canFly() && stack.getItem().equals(BxP2.getItem("draconicevolution:awakened_core"))){
                        cap.setFly();
                    }*/
                }
            });
        }
        NonNullList<ItemStack> allItem = Utils.mergeNonNullList(player.getInventory().items,player.getInventory().armor);
        for(ItemStack stack : allItem){
            if((stack.getItem() instanceof ModifiableItem || stack.getItem() instanceof ModifiableArmorItem) && LevelUtils.getToolLevel(stack) <= 0){
                LevelUtils.initStack(stack);
            }
        }
        MobEffectInstance instance = player.getEffect(RatsEffectRegistry.PLAGUE.get());
        if(instance != null && instance.getDuration() < 3){
            player.removeEffect(instance.getEffect());
        }
    }
}

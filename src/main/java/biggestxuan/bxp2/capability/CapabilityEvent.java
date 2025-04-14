package biggestxuan.bxp2.capability;

import biggestxuan.bxp2.BxP2;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @Author Biggest_Xuan
 * 2025/4/12
 */

@Mod.EventBusSubscriber(modid = BxP2.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityEvent {
    @SubscribeEvent
    public static void attachCap(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof ServerPlayer) {
            event.addCapability(BxP2.RL("capability"), new BxPCapabilityProvider());
        }
    }

    @SubscribeEvent
    public static void onTravelToDimension(EntityTravelToDimensionEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player))
            return;
        if (event.getDimension().location().toString().equals("minecraft:overworld") &&
                player.serverLevel().dimension().location().toString().equals("minecraft:the_end"))
            player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(cap -> {
                if (cap instanceof BxPCapability capability) {
                    player.getPersistentData().put("clone_cap", capability.serializeNBT());
                }
            });
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player))
            return;
        player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(cap -> {
            if (cap instanceof BxPCapability capability) {
                BxP2.LOGGER.info("{}",capability.serializeNBT());
                player.getPersistentData().put("clone_cap", capability.serializeNBT());
            }
        });
    }

    @SubscribeEvent
    public static void playerClone(PlayerEvent.Clone event) {
        Player oldPlayer = event.getOriginal();
        Player newPlayer = event.getEntity();
        if(oldPlayer instanceof ServerPlayer && newPlayer instanceof ServerPlayer) {
            newPlayer.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent((cap) -> {
                if (cap instanceof BxPCapability capability) {
                    var tag = oldPlayer.getPersistentData().get("clone_cap");
                    if (tag instanceof CompoundTag compoundTag) {
                        capability.deserializeNBT(compoundTag);
                    }
                }
            });
        }
    }
}

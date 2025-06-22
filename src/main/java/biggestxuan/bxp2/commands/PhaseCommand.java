package biggestxuan.bxp2.commands;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.capability.BxPCapabilityProvider;
import biggestxuan.bxp2.integration.TConstruct.Leveling.LevelUtils;
import com.blamejared.crafttweaker.impl.network.PacketHandler;
import com.blamejared.crafttweaker.impl.network.message.MessageCopy;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Biggest_Xuan
 * 2025/4/23
 */

@Mod.EventBusSubscriber(modid = BxP2.MODID)
public class PhaseCommand {
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("bxp")
                        .then(Commands.literal("phase")
                                .then(Commands.literal("get")
                                        .executes(context -> getPlayerPhase(context.getSource()))
                                )
                                .requires(source -> source.hasPermission(4))
                                .then(Commands.argument("phase", IntegerArgumentType.integer(0))
                                        .executes(context -> setPlayerPhase(
                                                context.getSource(),
                                                IntegerArgumentType.getInteger(context, "phase")
                                        ))
                                )
                        )
                        .then(Commands.literal("hand")
                                .executes(context -> copyItemNameToClipboard(context.getSource()))
                        )
                        .then(Commands.literal("tool").requires(source -> source.hasPermission(4))
                                .then(Commands.literal("level").then(Commands.argument("level",IntegerArgumentType.integer(1))
                                        .executes(context -> setToolLevel(context.getSource(),IntegerArgumentType.getInteger(context,"level"))))
                                )
                                .then(Commands.literal("xp").then(Commands.argument("xp", LongArgumentType.longArg()))
                                        .executes(context -> addToolXp(context.getSource(),LongArgumentType.getLong(context,"xp"))))
                        )
        );
    }

    private static int addToolXp(CommandSourceStack source,long xp){
        if (source.getEntity() instanceof ServerPlayer player) {
            ItemStack stack = player.getMainHandItem();
            LevelUtils.addToolXp(stack,xp,player);
            source.sendSuccess(()->BxP2.tr("success"),true);
            return 1;
        }
        return 0;
    }

    private static int setToolLevel(CommandSourceStack source,int level){
        if (source.getEntity() instanceof ServerPlayer player) {
            ItemStack stack = player.getMainHandItem();
            LevelUtils.setToolLevel(stack,level,player);
            source.sendSuccess(()->BxP2.tr("success"),true);
            return 1;
        }
        return 0;
    }

    private static int copyItemNameToClipboard(CommandSourceStack source) {
        if (source.getEntity() instanceof ServerPlayer player) {
            ItemStack heldStack = player.getMainHandItem();
            if (heldStack.isEmpty()) {
                return 0;
            }
            ResourceLocation itemId = ForgeRegistries.ITEMS.getKey(heldStack.getItem());
            String itemName = itemId.toString();
            itemName = "BxP2.getStack(\""+itemName+"\")";
            PacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(()->player),new MessageCopy(itemName));
            String finalItemName = itemName;
            source.sendSuccess(() -> Component.literal("success! "+ finalItemName)
                    .withStyle(ChatFormatting.GREEN), true);
            return 1;
        }
        source.sendFailure(BxP2.tr("command.phase.fail"));
        return 0;
    }

    private static int getPlayerPhase(CommandSourceStack source) {
        if (source.getEntity() instanceof ServerPlayer player) {
            AtomicInteger currentPhase = new AtomicInteger();
            player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(cap -> {
                currentPhase.set(cap.getPhase());
                source.sendSuccess(() -> BxP2.tr("command.phase.get",cap.getPhase()), false);
            });


            return currentPhase.get();
        }
        source.sendFailure(BxP2.tr("command.phase.fail"));
        return -1;
    }

    private static int setPlayerPhase(CommandSourceStack source, int phase) {
        if (source.getEntity() instanceof ServerPlayer player) {
            player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(cap -> {
                cap.setPhase(phase);
            });
            source.sendSuccess(() -> BxP2.tr("command.phase.success",player.getScoreboardName(),phase), true);
            return 1;
        }
        source.sendFailure(BxP2.tr("command.phase.fail"));
        return 0;
    }

    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        PhaseCommand.register(event);
    }
}
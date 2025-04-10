package biggestxuan.bxp2.events;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.data.DifficultyData;
import biggestxuan.bxp2.utils.DifficultyUtils;
import biggestxuan.bxp2.utils.ModUtils;
import biggestxuan.bxp2.utils.Utils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @Author Biggest_Xuan
 * 2025/4/4
 */
@Mod.EventBusSubscriber(modid = BxP2.MODID)
public class PlayerLoggedEvent {
    @SubscribeEvent
    public static void playerLoggedEvent(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if(BxP2.devMode){
            BxP2.LOGGER.info(ModUtils.getAllMods());
            BxP2.LOGGER.info("AAA");
            if(player instanceof ServerPlayer serverPlayer){
                BxP2.LOGGER.info("{}", DifficultyData.getInstance(serverPlayer.level()).getDifficulty());
            }
        }
        if(player instanceof ServerPlayer serverPlayer){
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

    private static void welcome(Player player){
        Utils.sendMessage(player,BxP2.tr("bxp2.message.welcome1").append(BxP2.VERSION));
        Utils.sendMessage(player,BxP2.tr("bxp2.message.welcome2").append(DifficultyUtils.getDifficultyColor()));
        Utils.sendMessage(player,BxP2.tr("bxp2.message.welcome3"));
    }
}

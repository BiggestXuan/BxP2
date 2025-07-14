package biggestxuan.bxp2.utils;

import biggestxuan.bxp2.Config;
import biggestxuan.bxp2.api.BxPApi;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.ServerStatsCounter;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/6/21
 */
public final class MobUtils {
    private static int getGameDay(int phase){
        return switch (phase){
            case 2 -> 10;
            case 3 -> 22;
            case 4 -> 40;
            case 5 -> 60;
            case 6 -> 70;
            case 7 -> 85;
            case 8 -> 100;
            case 9 -> 105;
            case 10 -> 110;
            case 11 -> 120;
            case 12 -> 150;
            default -> 0;
        };
    }

    public static double getAvgGameDay(LivingEntity living){
        Level level = living.level();
        if(level.isClientSide) return 0F;
        AABB aabb = new AABB(living.getX() - 128, living.getY() - 128, living.getZ() - 128, living.getX() + 128, living.getY() + 128, living.getZ() + 128);
        List<ServerPlayer> list = ((ServerLevel) level).getServer().getPlayerList().getPlayers();
        if(Config.difficulty == 3){
            long time = 0;
            int pCount = 0;
            for(Player player : list){
                if(player instanceof ServerPlayer sp){
                    ServerStatsCounter counter = sp.getStats();
                    time += counter.getValue(Stats.CUSTOM.get(Stats.PLAY_TIME));
                    pCount++;
                }
            }
            return 1D * time / 24000 / pCount * Config.HARD_ENHANCEMENT_RATE;
        }
        if(Config.difficulty >= 1){
            int total = 0;
            if(list.size() == 1){
                return getGameDay(BxPApi.getPlayerCap(list.get(0)).getPhase());
            }
            for(Player player : list){
                int p = BxPApi.getPlayerCap(player).getPhase();
                total += Math.max(p, 1);
            }
            return getGameDay(Math.round(1f * total / list.size())) * (Config.difficulty == 1 ? Config.EASY_ENHANCEMENT_RATE : Config.NORMAL_ENHANCEMENT_RATE);
        }
        return 0;
    }
}

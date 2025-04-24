package biggestxuan.bxp2.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/4/20
 */
public final class WorldUtils {

    public static AABB expandAABB(BlockPos pos, int range){
        return new AABB(new BlockPos(pos.getX()-range,pos.getY()-range,pos.getZ()-range),new BlockPos(pos.getX()+range,pos.getY()+range,pos.getZ()+range));
    }

    public static List<LivingEntity> getNearLiving(LivingEntity living, int distance, boolean includePlayer){
        AABB aabb = expandAABB(living.blockPosition(),distance);
        List<LivingEntity> list = new ArrayList<>();
        if(living.level().isClientSide) return list;
        for(LivingEntity l : living.level().getEntitiesOfClass(LivingEntity.class,aabb)){
            if(l instanceof Player && !includePlayer){
                continue;
            }
            list.add(l);
        }
        return list;
    }

    public static List<Player> getNearPlayer(LivingEntity living, int distance){
        AABB aabb = expandAABB(living.blockPosition(),distance);
        List<Player> list = new ArrayList<>();
        if(living.level().isClientSide) return list;
        list.addAll(living.level().getEntitiesOfClass(Player.class, aabb));
        return list;
    }
}

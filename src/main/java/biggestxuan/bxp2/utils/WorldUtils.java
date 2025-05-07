package biggestxuan.bxp2.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import vazkii.botania.common.item.BotaniaItems;

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

    public static void replaceItemEntityStack(ItemEntity entity, ItemStack stack){
        ItemEntity newEntity = new ItemEntity(entity.level(),entity.getX(),entity.getY(),entity.getZ(),stack);
        entity.level().addFreshEntity(newEntity);
        entity.kill();
    }

    public static List<Player> getNearPlayer(LivingEntity living, int distance){
        AABB aabb = expandAABB(living.blockPosition(),distance);
        List<Player> list = new ArrayList<>();
        if(living.level().isClientSide) return list;
        list.addAll(living.level().getEntitiesOfClass(Player.class, aabb));
        return list;
    }

    public static boolean isTerraWeapon(ItemStack stack){
        if(stack.is(BotaniaItems.terraSword)){
            return true;
        }
        return ModifierUtil.getModifierLevel(stack,new ModifierId("bxp2","terra")) > 0;
    }
}

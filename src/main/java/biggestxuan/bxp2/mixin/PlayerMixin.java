package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.Config;
import biggestxuan.bxp2.utils.ShopUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ItemSteerable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author Biggest_Xuan
 * 2025/4/8
 */

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {

    @Shadow @Final private Inventory inventory;

    protected PlayerMixin(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    @Inject(method = "dropEquipment",at = @At("HEAD"),cancellable = true)
    public void __inject(CallbackInfo ci){
        Player player = (Player)(Object)this;

        if(Config.difficulty == 3 && Config.DEATH_DROP){
            for (int i = 9; i < inventory.items.size(); i++) {
                player.drop(inventory.items.get(i),true,false);
                inventory.items.set(i,ItemStack.EMPTY);
            }
            ci.cancel();
        }
    }
}

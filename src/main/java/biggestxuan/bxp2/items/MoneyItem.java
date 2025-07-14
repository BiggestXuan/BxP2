package biggestxuan.bxp2.items;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.Config;
import biggestxuan.bxp2.capability.BxPCapabilityProvider;
import dev.ftb.mods.ftbteams.api.FTBTeamsAPI;
import dev.ftb.mods.ftbteams.api.Team;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;

import java.util.List;
import java.util.Optional;

/**
 * @Author Biggest_Xuan
 * 2025/4/23
 */
public class MoneyItem extends Item {

    public MoneyItem() {
        super(new Properties().rarity(Rarity.UNCOMMON));
    }

    public static float getMoney(ItemStack stack){
        if(stack.getItem() instanceof MoneyItem money){
            CompoundTag tag = stack.getOrCreateTag();
            return tag.getFloat("money");
        }
        return 0;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level level, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, level, p_41423_, p_41424_);
        Component text = isManually(p_41421_) ? BxP2.tr("desc.bxp2.money1") : BxP2.tr("desc.bxp2.money");
        p_41423_.add(text);
        if(level != null && level.isClientSide){
            Team team = FTBTeamsAPI.api().getClientManager().selfTeam();
            if(team.isValid() && !noLoss(p_41421_)){
                int size = team.getMembers().size();
                float money = getTeamMoney(getMoney(p_41421_),size);
                p_41423_.add(BxP2.tr("desc.bxp2.money_get",size,String.format("%.2f",money)));
            }
        }
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack =  super.getDefaultInstance();
        stack.getOrCreateTag().putInt("money",1);
        return stack;
    }

    @Override
    public Component getName(ItemStack p_41458_) {
        return BxP2.tr("item.bxp2.money",String.format("%.2f",getMoney(p_41458_)));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide){
            ItemStack stack = player.getItemInHand(hand);
            costMoney(stack,player);
            return InteractionResultHolder.success(stack);
        }
        return super.use(level,player,hand);
    }

    private static boolean isManually(ItemStack stack){
        if(stack.getItem() instanceof MoneyItem item){
            CompoundTag tag = stack.getOrCreateTag();
            return tag.getBoolean("manually");
        }
        return false;
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
        if(BxP2.devMode) return;
        if(!isManually(p_41404_)){
            costMoney(p_41404_,p_41406_);
        }
    }

    public void costMoney(ItemStack stack, Entity p){
        float money = getMoney(stack);
        if(money > 0 && p instanceof ServerPlayer player){
            player.getCapability(BxPCapabilityProvider.CAPABILITY).ifPresent(cap -> {
                Optional<Team> team = FTBTeamsAPI.api().getManager().getTeamForPlayer(player);
                float m = money;
                if(team.isPresent()){
                    if(!noLoss(stack)){
                        m = getTeamMoney(m,team.get().getMembers().size());
                    }
                }
                cap.setMoney(cap.getMoney() + m);
                stack.shrink(1);
            });
        }
    }

    private static float getTeamMoney(float value,int amount){
        if(amount <= 1) return value;
        double rate = 1;
        switch (Config.difficulty){
            case 1 -> rate = Config.EASY_TEAM_MONEY_RATE;
            case 2 -> rate = Config.NORMAL_TEAM_MONEY_RATE;
            case 3 -> rate = Config.HARD_TEAM_MONEY_RATE;
        }
        return (float) (value * Math.pow(rate,amount - 1));
    }

    private static boolean noLoss(ItemStack stack){
        if(stack.getItem() instanceof MoneyItem item){
            CompoundTag tag = stack.getOrCreateTag();
            return tag.getBoolean("noLoss");
        }
        return false;
    }

    public static void dropMoney(Entity entity,float money,boolean noLoss){
        if(money < 0.01){
            return;
        }
        ItemStack stack = BxPItems.MONEY.get().getDefaultInstance();
        CompoundTag tag = stack.getOrCreateTag();
        tag.putFloat("money",money);
        if(noLoss){
            tag.putBoolean("noLoss",true);
        }
        entity.level().addFreshEntity(new ItemEntity(entity.level(),entity.getX(),entity.getY(),entity.getZ(),stack));
    }
}

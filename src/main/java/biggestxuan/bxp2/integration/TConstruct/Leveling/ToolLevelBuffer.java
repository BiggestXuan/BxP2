package biggestxuan.bxp2.integration.TConstruct.Leveling;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.utils.Utils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.IToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

/**
 * @Author Biggest_Xuan
 * 2025/5/28
 */
public class ToolLevelBuffer {
    private final ItemStack item;
    private final ToolStack stack;
    private final CompoundTag stats;

    public ToolLevelBuffer(ItemStack stack){
        this.item = stack;
        this.stack = ToolStack.from(stack);
        this.stats = item.getOrCreateTag().getCompound("tic_stats");
    }

    public int getSlot(SlotType type){
        return stack.getPersistentData().getSlots(type);
    }

    public void addSlot(SlotType type){
        int amt = getSlot(type);
        stack.getPersistentData().setSlots(type,amt + 1);
    }

    public void addDamage(double dmg, Player player){
        Utils.sendDevMessage(player, BxP2.tr(String.valueOf(stack.getStats().get(ToolStats.ATTACK_DAMAGE))));
        StatsNBT.Builder sn = StatsNBT.builder();
        StatsNBT originalStats = stack.getStats();
        for(IToolStat<?> s : originalStats.getContainedStats()){
            copyStat(sn,s,originalStats);
        }
        sn.set(ToolStats.ATTACK_DAMAGE,originalStats.get(ToolStats.ATTACK_DAMAGE) + 1);
        if(stack instanceof ToolStackExpand ex){
            ex.setStatNBT(sn.build());
        }
        double attack = stats.getFloat("tconstruct:attack_damage") + dmg;
        stats.putFloat("tconstruct:attack_damage", (float) attack);
        stack.rebuildStats();
        Utils.sendDevMessage(player, BxP2.tr(String.valueOf(stack.getStats().get(ToolStats.ATTACK_DAMAGE))));
    }

    private static <T> void copyStat(StatsNBT.Builder builder, IToolStat<T> stat, StatsNBT originalStats) {
        T value = originalStats.get(stat);
        builder.set(stat, value);
    }
}

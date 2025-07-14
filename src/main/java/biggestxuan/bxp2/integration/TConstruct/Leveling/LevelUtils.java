package biggestxuan.bxp2.integration.TConstruct.Leveling;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.api.BxPApi;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.BxPModifiers;
import net.mehvahdjukaar.dummmmmmy.common.TargetDummyEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.item.armor.ModifiableArmorItem;
import slimeknights.tconstruct.library.tools.nbt.ToolDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.Random;

/**
 * @Author Biggest_Xuan
 * 2025/5/27
 */

@SuppressWarnings("unused")
public final class LevelUtils {
    public static String LEVEL = "bxp_level";
    public static String LEVEL_XP = "bxp_level_xp";
    public static String LEVEL_REQUIRE = "bxp_level_require";
    public static int[] colors = new int[]{0x33bf28,0x68f8d4,0x2763c6,0xbb1ace,0xf5134d,0xfea51a,0xffffff};

    public static int getLevelColor(int level){
        int RANDOM = colors[new Random().nextInt(colors.length)];
        int b = (level - 1) / 5;
        if(b >= 6){
            return RANDOM;
        }
        return switch (b){
            case 0 -> 0xffffff;
            case 1 -> 0x33bf28;
            case 2 -> 0x68f8d4;
            case 3 -> 0x2763c6;
            case 4 -> 0xbb1ace;
            case 5 -> 0xf5134d;
            default -> RANDOM;
        };
    }

    public static MutableComponent getStarToolTip(int level){
        int count = level % 5;
        if(count == 0){
            count = 5;
        }
        if(level == 0) count = 0;
        int color = getLevelColor(level);
        return BxP2.tr("★".repeat(Math.max(0, count))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(color)));
    }

    public static long getRequireXp(int level,ItemStack stack) {
        int[] arr = new int[]{10,13,15,18,22};
        if (level < 0) {
            throw new IllegalArgumentException("等级不能为负值！");
        }
        int baseIndex = level % 5;
        int powerCount = level / 5;

        double result = arr[baseIndex];
        for (int i = 0; i < powerCount; i++) {
            result = Math.pow(result, 1.51);
        }
        if(stack != null){
            ToolStack tool = ToolStack.from(stack);
            int l = tool.getModifierLevel(BxPModifiers.Tool_LIMIT_XP.getId());
            if(l != 0){
                for (int i = 0; i < Math.min(4,l); i++) {
                    result /= 2;
                }
            }
        }
        return (long) result;
    }

    public static void addToolXp(ItemStack stack,float xp,Player player){
        addToolXp(stack,(long) xp,player);
    }

    public static long getToolXp(ItemStack stack){
        CompoundTag tag = stack.getTag();
        if(tag != null){
            long xp = tag.getLong(LEVEL_XP);
            if(xp < 0){
                return 0L;
            }
            return xp;
        }
        return 0L;
    }

    public static void setToolLevel(ItemStack stack,int level,Player player){
        int now = getToolLevel(stack);
        for (int i = now; i <= level; i++) {
            long require = getRequireXp(i,stack);
            addToolXp(stack,require,player);
        }
    }

    public static void addToolXp(ItemStack stack,long xp,Player player){
        if (!(stack.getItem() instanceof ModifiableItem || stack.getItem() instanceof ModifiableArmorItem)){
            return;
        }
        ToolStack tool = ToolStack.from(stack);
        int addXp = tool.getModifierLevel(BxPModifiers.Tool_XP.getId());
        if(addXp > 0){
            addXp = Math.min(addXp,4);
            for (int i = 0; i < addXp; i++) {
                xp *= 2L;
            }
        }
        CompoundTag nbt = stack.getTag();
        if(nbt != null){
            if(nbt.getInt(LEVEL) >= 35) return;
            long now = nbt.getLong(LEVEL_XP);
            if(now < 0){
                nbt.putLong(LEVEL_XP,0);
                now = 0;
            }
            nbt.putLong(LEVEL_XP,now + xp);
            syncItemStack(player,stack);
            now = nbt.getLong(LEVEL_XP);
            long require = getRequireXp(getToolLevel(stack),stack);
            while (now >= require){
                nbt.putLong(LEVEL_XP,now - require);
                nbt.putInt(LEVEL,nbt.getInt(LEVEL)+1);
                now = nbt.getLong(LEVEL_XP);
                require = getRequireXp(getToolLevel(stack),stack);
                levelUp(stack,nbt.getInt(LEVEL),player);
                syncItemStack(player,stack);
            }
        }
    }

    public static void levelUp(ItemStack stack,int level,Player player){
        //new ToolLevelBuffer(stack).addDamage(1f,player);
        ToolStack tool = ToolStack.from(stack);
        ToolDataNBT toolDataNBT = tool.getPersistentData();
        if(level % 4 == 0){
            toolDataNBT.addSlots(SlotType.ABILITY,1);
        }
        if(level % 4 == 0){
            toolDataNBT.addSlots(SlotType.UPGRADE,1);
        }
        if(stack.is(TinkerTags.Items.MELEE_PRIMARY)){
            tool.addModifier(BxPModifiers.WeaponLevelModifier.getId(), + 1);
            if(level % 6 == 0){
                toolDataNBT.addSlots(SlotType.SOUL,1);
            }
        }
        else if(stack.is(TinkerTags.Items.HARVEST_PRIMARY)){
            tool.addModifier(BxPModifiers.ToolLevelModifier.getId(), + 1);
        }
        else if(stack.is(TinkerTags.Items.ARMOR)){
            tool.addModifier(BxPModifiers.ArmorLevelModifier.getId(), + 1);
            if(level % 5 == 0){
                toolDataNBT.addSlots(SlotType.DEFENSE,1);
            }
        }
    }

    public static void refreshXp(ItemStack stack,Player player){
        addToolXp(stack,0,player);
    }

    private static void syncItemStack(Player player,ItemStack stack){
        /*if(player instanceof ServerPlayer sl){
            int slot = player.getInventory().selected;
            sl.connection.send(
                    new ClientboundContainerSetSlotPacket(
                            0,
                            player.containerMenu.incrementStateId(),
                            slot,
                            stack
                    )
            );
        }
         */
    }

    public static void initStack(ItemStack stack){
        if(stack.getItem() instanceof ModifiableItem || stack.getItem() instanceof ModifiableArmorItem){
            CompoundTag tag = stack.getOrCreateTag();
            tag.putInt(LEVEL,1);
            tag.putLong(LEVEL_XP,0L);
        }
    }

    public static int getToolLevel(ItemStack stack){
        if(stack.getItem() instanceof ModifiableItem item || stack.getItem() instanceof ModifiableArmorItem){
            CompoundTag tag = stack.getTag();
            if(tag != null){
                return tag.getInt(LEVEL);
            }
        }
        return -1;
    }

    public static void attackXp(ItemStack stack, float damage, Player attacker,Entity target){
        if(target instanceof TargetDummyEntity){
            return;
        }
        addToolXp(stack, (long) damage,attacker);
    }

    public static void pickXp(ItemStack stack, BlockState state, Player player){
        int parse = BxPApi.getPlayerCap(player).getPhase();
        addToolXp(stack,5L * (parse + 1),player);
    }

    public static void getDamage(ItemStack stack, DamageSource source,Player player,float damage){
        addToolXp(stack, (long) damage,player);
    }
}

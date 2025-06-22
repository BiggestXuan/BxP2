package biggestxuan.bxp2.integration.TConstruct;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.Config;
import mekanism.common.item.gear.ItemMekaTool;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.item.armor.ModifiableArmorItem;
import vazkii.botania.common.item.equipment.tool.terrasteel.TerraShattererItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/5/5
 */

@Mod.EventBusSubscriber(modid = BxP2.MODID)
public class  TinkersSurvival {
    public static boolean isInit = false;

    public static final List<Item> BLACKLISTED_ITEMS = new ArrayList<>();

    public static void init(){
        if(!Config.TinkersSurvival || isInit) return;
        BLACKLISTED_ITEMS.add(Items.WOODEN_PICKAXE);
        BLACKLISTED_ITEMS.add(Items.WOODEN_AXE);
        BLACKLISTED_ITEMS.add(Items.WOODEN_SHOVEL);
        BLACKLISTED_ITEMS.add(Items.WOODEN_HOE);
        BLACKLISTED_ITEMS.add(Items.STONE_PICKAXE);
        BLACKLISTED_ITEMS.add(Items.STONE_AXE);
        BLACKLISTED_ITEMS.add(Items.STONE_SHOVEL);
        BLACKLISTED_ITEMS.add(Items.STONE_HOE);
        BLACKLISTED_ITEMS.add(Items.IRON_PICKAXE);
        BLACKLISTED_ITEMS.add(Items.IRON_AXE);
        BLACKLISTED_ITEMS.add(Items.IRON_SHOVEL);
        BLACKLISTED_ITEMS.add(Items.IRON_HOE);
        BLACKLISTED_ITEMS.add(Items.GOLDEN_PICKAXE);
        BLACKLISTED_ITEMS.add(Items.GOLDEN_AXE);
        BLACKLISTED_ITEMS.add(Items.GOLDEN_SHOVEL);
        BLACKLISTED_ITEMS.add(Items.GOLDEN_HOE);
        BLACKLISTED_ITEMS.add(Items.DIAMOND_PICKAXE);
        BLACKLISTED_ITEMS.add(Items.DIAMOND_AXE);
        BLACKLISTED_ITEMS.add(Items.DIAMOND_SHOVEL);
        BLACKLISTED_ITEMS.add(Items.DIAMOND_HOE);
        BLACKLISTED_ITEMS.add(Items.NETHERITE_PICKAXE);
        BLACKLISTED_ITEMS.add(Items.NETHERITE_AXE);
        BLACKLISTED_ITEMS.add(Items.NETHERITE_SHOVEL);
        BLACKLISTED_ITEMS.add(Items.NETHERITE_HOE);

        BLACKLISTED_ITEMS.add(Items.WOODEN_SWORD);
        BLACKLISTED_ITEMS.add(Items.STONE_SWORD);
        BLACKLISTED_ITEMS.add(Items.IRON_SWORD);
        BLACKLISTED_ITEMS.add(Items.GOLDEN_SWORD);
        BLACKLISTED_ITEMS.add(Items.DIAMOND_SWORD);
        BLACKLISTED_ITEMS.add(Items.NETHERITE_SWORD);
        BLACKLISTED_ITEMS.add(Items.BOW);
        BLACKLISTED_ITEMS.add(Items.CROSSBOW);
        BLACKLISTED_ITEMS.add(Items.TRIDENT);

        BLACKLISTED_ITEMS.add(Items.LEATHER_HELMET);
        BLACKLISTED_ITEMS.add(Items.LEATHER_CHESTPLATE);
        BLACKLISTED_ITEMS.add(Items.LEATHER_LEGGINGS);
        BLACKLISTED_ITEMS.add(Items.LEATHER_BOOTS);
        BLACKLISTED_ITEMS.add(Items.CHAINMAIL_HELMET);
        BLACKLISTED_ITEMS.add(Items.CHAINMAIL_CHESTPLATE);
        BLACKLISTED_ITEMS.add(Items.CHAINMAIL_LEGGINGS);
        BLACKLISTED_ITEMS.add(Items.CHAINMAIL_BOOTS);
        BLACKLISTED_ITEMS.add(Items.IRON_HELMET);
        BLACKLISTED_ITEMS.add(Items.IRON_CHESTPLATE);
        BLACKLISTED_ITEMS.add(Items.IRON_LEGGINGS);
        BLACKLISTED_ITEMS.add(Items.IRON_BOOTS);
        BLACKLISTED_ITEMS.add(Items.GOLDEN_HELMET);
        BLACKLISTED_ITEMS.add(Items.GOLDEN_CHESTPLATE);
        BLACKLISTED_ITEMS.add(Items.GOLDEN_LEGGINGS);
        BLACKLISTED_ITEMS.add(Items.GOLDEN_BOOTS);
        BLACKLISTED_ITEMS.add(Items.DIAMOND_HELMET);
        BLACKLISTED_ITEMS.add(Items.DIAMOND_CHESTPLATE);
        BLACKLISTED_ITEMS.add(Items.DIAMOND_LEGGINGS);
        BLACKLISTED_ITEMS.add(Items.DIAMOND_BOOTS);
        BLACKLISTED_ITEMS.add(Items.NETHERITE_HELMET);
        BLACKLISTED_ITEMS.add(Items.NETHERITE_CHESTPLATE);
        BLACKLISTED_ITEMS.add(Items.NETHERITE_LEGGINGS);
        BLACKLISTED_ITEMS.add(Items.NETHERITE_BOOTS);

        BLACKLISTED_ITEMS.add(Items.SHIELD);

        isInit = true;
    }

    @SubscribeEvent
    public static void onItemUse(PlayerInteractEvent.RightClickItem event) {
        ItemStack stack = event.getItemStack();
        if (isBlackList(stack.getItem(),event.getEntity())) {
            event.setCanceled(true);
            event.getEntity().displayClientMessage(
                    BxP2.tr("message.tinker_must")
                            .withStyle(ChatFormatting.RED),
                    true
            );
        }
    }

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        Player player = event.getEntity();
        ItemStack stack = player.getMainHandItem();
        if (isBlackList(stack.getItem(),player)) {
            event.setCanceled(true);
            player.displayClientMessage(
                    BxP2.tr("message.tinker_must")
                            .withStyle(ChatFormatting.RED),
                    true
            );
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        ItemStack stack = event.getPlayer().getMainHandItem();
        if (isBlackList(stack.getItem(), event.getPlayer())) {
            event.setCanceled(true);
            event.getPlayer().displayClientMessage(
                    BxP2.tr("message.tinker_must")
                            .withStyle(ChatFormatting.RED),
                    true
            );
        }
    }

    @SubscribeEvent
    public static void onArmorEquip(LivingEquipmentChangeEvent event) {
        if (event.getEntity() instanceof Player player) {
            ItemStack newItem = event.getTo();
            if (isBlackList(newItem.getItem(),player)) {
                if (!player.getInventory().add(newItem)) {
                    player.drop(newItem, false);
                }
                event.getEntity().getItemBySlot(event.getSlot()).setCount(0);
                player.displayClientMessage(
                        BxP2.tr("message.tinker_must")
                                .withStyle(ChatFormatting.RED),
                        true
                );
            }
        }
    }

    private static boolean isBlackList(Item item,Player player){
        if(BxP2.devMode || player.isCreative() || !Config.TinkersSurvival){
            return false;
        }
        if(item instanceof TerraShattererItem){
            return false; // Botania
        }
        if(item instanceof ItemMekaTool){
            return false; //Mek
        }
        //if(item instanceof IReaperItem || item instanceof IModularArmor){
        //    return false; //DE
        //}
        if(item instanceof TieredItem && !(item instanceof ModifiableItem)){
            return true;
        }
        if(item instanceof ArmorItem && !(item instanceof ModifiableArmorItem)){
            return true;
        }
        for(var i : BLACKLISTED_ITEMS){
            if(i.equals(item)){
                return true;
            }
        }
        return false;
    }

}

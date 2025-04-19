package biggestxuan.bxp2.utils;

import biggestxuan.bxp2.BxP2;
import net.minecraft.core.Holder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author Biggest_Xuan
 * 2025/4/15
 */
public final class UniteUtils {
    @Nullable
    public static ItemStack getUniteStack(ItemEntity entity){
        ItemStack re = null;
        Map<TagKey<Item>,ItemStack> map = new HashMap<>();
        map.put(ItemTags.create(BxP2.MODRL("forge:raw_materials/lead")),BxP2.getStack("mekanism:raw_lead"));
        map.put(ItemTags.create(BxP2.MODRL("forge:raw_materials/nickel")),BxP2.getStack("thermal:raw_nickel"));
        map.put(ItemTags.create(BxP2.MODRL("forge:raw_materials/tin")),BxP2.getStack("mekanism:raw_tin"));
        map.put(ItemTags.create(BxP2.MODRL("forge:raw_materials/silver")),BxP2.getStack("thermal:raw_silver"));
        map.put(ItemTags.create(BxP2.MODRL("forge:raw_materials/uranium")),BxP2.getStack("mekanism:raw_uranium"));
        map.put(ItemTags.create(BxP2.MODRL("forge:raw_materials/osmium")),BxP2.getStack("mekanism:raw_osmium"));
        ItemStack stack = entity.getItem();
        for(var a : stack.getTags().toList()){
            if(map.containsKey(a)){
                re = map.get(a).copy();
                re.setCount(stack.getCount());
                break;
            }
        }
        return re;
    }
}

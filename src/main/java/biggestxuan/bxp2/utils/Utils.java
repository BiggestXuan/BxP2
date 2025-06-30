package biggestxuan.bxp2.utils;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.integration.TConstruct.Modifiers.BxPModifiers;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;

import java.math.BigInteger;
import java.util.*;

/**
 * @Author Biggest_Xuan
 * 2025/4/3
 */

public final class Utils {
    public static double Random(){
        return Math.random();
    }

    public static boolean isRandom(double chance){
        return Random() <= chance;
    }

    public static int getRandom(int max){
        return new Random().nextInt(max);
    }

    private static String getFlag(String value){
        int c = value.length();
        String o = "";
        if(c <= 6 && c >= 4){
            o = "K";
        }else if(c <= 9 && c >= 7){
            o = "M";
        }else if(c <= 12 && c >= 10){
            o = "G";
        }else if(c <= 15 && c >= 13){
            o = "T";
        }else if(c <= 18 && c >= 16){
            o = "P";
        }else if(c > 18){
            o = "E";
        }
        return o;
    }

    public static String insertNewLines(String input, int maxLength) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        StringBuilder result = new StringBuilder();
        int currentLength = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int charLength = getCharLength(c);
            if (currentLength + charLength > maxLength && currentLength != 0) {
                result.append('\n');
                currentLength = 0;
            }

            result.append(c);
            currentLength += charLength;
        }

        return result.toString();
    }

    public static int getTextLength(String text){
        int currentLength = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            currentLength += getCharLength(c);
        }
        return currentLength;
    }

    public static int getCharLength(char c) {
        if ((c >= '\u4e00' && c <= '\u9fa5') ||
                (c >= '\u3000' && c <= '\u303f') ||
                (c >= '\uff00' && c <= '\uffef')) {
            return 2;
        }
        return 1;
    }

    public static String format(String text){
        return thousandSign(text);
    }

    public static String KMT(BigInteger big){
        return KMT(big.longValue());
    }

    public static String KMT(long text){
        return KMT(String.valueOf(text));
    }

    public static String KMT(String text){
        if(text.length() <= 6){
            return format(text);
        }
        String flag = getFlag(text);
        return formatAfter(text)+flag;
    }

    private static String formatAfter(String value){
        int c = value.length() - 1;
        if(c < 3) return value;
        if(c % 3 == 0){
            return value.charAt(0)+"."+value.substring(1,3);
        }
        if(c % 3 == 1){
            return value.substring(0,2)+"."+value.substring(2,4);
        }
        return value.substring(0,3)+"."+value.substring(3,5);
    }


    public static int getRangeRandom(int min,int max){
        if(min == max) return min;
        Random random = new Random();
        int value = max - min;
        if(value < 0){
            return -(random.nextInt(-value + 1) + min);
        }
        return random.nextInt(max - min + 1) + min;
    }

    public static int[] StringArray2IntArray(String[] array){
        int[] a = new int[array.length];
        for (int i = 0; i < array.length ; i++) {
            a[i] = isNum(array[i]) ? Integer.parseInt(array[i]) : 0;
        }
        return a;
    }

    public static String thousandSign(long text){
        return thousandSign(String.valueOf(text));
    }

    public static <T> List<T> copyList(List<T> list){
        return new ArrayList<>(list);
    }

    public static String longSign(long value){
        if(value < 0){
            return "-" + thousandSign(Math.negateExact(value));
        }
        return "+" + thousandSign(value);
    }

    public static boolean isNum(String s){
        if (s == null) {
            return false;
        }
        try{
            int i = Integer.parseInt(s);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public static String thousandSign(String text){
        int len = text.length();
        ArrayList<String> stringContainer = new ArrayList<>();
        while(len>3){
            stringContainer.add(text.substring(len-3,len));
            len-=3;
        }
        stringContainer.add(text.substring(0,len));
        StringBuilder buffer = new StringBuilder();
        for(int i = stringContainer.size()-1;i>=0;i--){
            buffer.append(stringContainer.get(i)).append(",");
        }
        buffer.deleteCharAt(buffer.length()-1);
        return buffer.toString();
    }

    public static String formatPercent(double value){
        return String.format("%.2f%%", value * 100);
    }

    public static <T> List<List<T>> generateSubsets(Collection<T> c) {
        List<T> list = new ArrayList<>(c);
        List<List<T>> result = new ArrayList<>();
        generateSubsetsHelper(list, 0, new ArrayList<>(), result);
        result.sort(Comparator.comparingInt(List::size));
        return result;
    }

    public static ItemStack[] getItemStackArray(Collection<ItemStack> c){
        ItemStack[] items = new ItemStack[c.size()];
        int i = 0;
        for(ItemStack item : c){
            items[i++] = item;
        }
        return items;
    }

    private static <T> void generateSubsetsHelper(List<T> list, int index, List<T> current, List<List<T>> result) {
        if (index == list.size()) {
            result.add(new ArrayList<>(current));
            return;
        }
        generateSubsetsHelper(list, index + 1, current, result);
        current.add(list.get(index));
        generateSubsetsHelper(list, index + 1, current, result);
        current.remove(current.size() - 1);
    }

    public static void sendMessage(Player player, String message){
        player.displayClientMessage(BxP2.tr(message),false);
    }

    public static boolean playerArmorHasTrait(Player player, Modifier modifier){
        for(ItemStack stack : player.getInventory().armor){
            if(ModifierUtil.getModifierLevel(stack, modifier.getId()) > 0){
                return true;
            }
        }
        return false;
    }

    public static void sendMessage(Player player, Component message){
        player.displayClientMessage(message,false);
    }

    public static void sendDevMessage(Player player, String message){
        sendDevMessage(player,BxP2.tr(message));
    }

    public static void sendDevMessage(Player player, Component message){
        if(!BxP2.devMode) return;
        player.displayClientMessage(message,false);
    }

    public static <T> NonNullList<T> mergeNonNullList(NonNullList<T> i,NonNullList<T> i1){
        NonNullList<T> list = NonNullList.create();
        for(T t : i){
            list.add(t);
        }
        for(T t : i1){
            list.add(t);
        }
        return list;
    }
}

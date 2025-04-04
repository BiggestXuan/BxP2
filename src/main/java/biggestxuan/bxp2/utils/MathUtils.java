package biggestxuan.bxp2.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author Biggest_Xuan
 * 2025/4/3
 */

public final class MathUtils {
    public static double Random(){
        return Math.random();
    }

    public static boolean isRandom(double chance){
        return Random() <= chance;
    }

    public static int getRandom(int max){
        return new Random().nextInt(max);
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
}

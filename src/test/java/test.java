import biggestxuan.bxp2.Config;
import mekanism.api.math.FloatingLong;

/**
 * @Author Biggest_Xuan
 * 2025/4/16
 */
public class test {
    public static void main(String[] args) {
        FloatingLong amount = FloatingLong.create(10000);
        double amt = amount.getValue();
        int q = 2;
        switch (q){
            case 2 -> {
                amt *= 1.5;
            }
            case 3 -> {
                amt *= 1.25;
            }
        }
        System.out.println(amt);
        System.out.println(FloatingLong.create(amt).getValue());
    }
}

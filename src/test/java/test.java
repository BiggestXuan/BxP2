import biggestxuan.bxp2.Config;
import biggestxuan.bxp2.integration.TConstruct.Leveling.LevelUtils;
import mekanism.api.math.FloatingLong;

/**
 * @Author Biggest_Xuan
 * 2025/4/16
 */
public class test {
    public static void main(String[] args) {
        for (int i = 0; i < 35; i++) {
            System.out.println("level:"+i+","+ LevelUtils.getRequireXp(i,null));
        }
    }
}

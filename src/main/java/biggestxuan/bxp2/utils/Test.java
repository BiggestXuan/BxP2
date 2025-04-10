package biggestxuan.bxp2.utils;

import biggestxuan.bxp2.items.BxPCatalyst;

import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/4/7
 */
public class Test {
    public static void main(String[] args) {
        for(List<BxPCatalyst> list : Utils.generateSubsets(BxPCatalyst.getAllCatalyst(BxPCatalyst.ADAPT.BX_FURNACE))){
            System.out.println(list.toString());
        }
    }
}

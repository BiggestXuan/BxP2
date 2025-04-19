package biggestxuan.bxp2.integration.Mekanism;

import mekanism.common.base.IChemicalConstant;

/**
 * @Author Biggest_Xuan
 * 2025/4/18
 */
public enum BxPChemicalConstants implements IChemicalConstant {
    NWS("nws",0xFF8B4513,400,300,5),
    UNS("uns",0xFFF0E68C,400,500,7),
    CONDENSE_UNS("condense_uns",0xFFB4A62B,500,600,7),
    APH("aph",0xFFAFD7C8,300,600,0),
    POTASSIUM_FLUORIDE("potassium_fluoride",0xFFE6E6FA,300,600,1)
    ;

    private final String name;
    private final int color;
    private final float temp;
    private final float density;
    private final int light;

    BxPChemicalConstants(String name,int color,float temp,float density,int light){
        this.color = color;
        this.name = name;
        this.temp = temp;
        this.density = density;
        this.light = light;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public float getTemperature() {
        return temp;
    }

    @Override
    public float getDensity() {
        return density;
    }

    @Override
    public int getLightLevel() {
        return light;
    }
}

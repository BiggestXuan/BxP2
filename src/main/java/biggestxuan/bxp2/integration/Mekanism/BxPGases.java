package biggestxuan.bxp2.integration.Mekanism;

import biggestxuan.bxp2.BxP2;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.gas.attribute.GasAttributes;
import mekanism.api.math.FloatingLong;
import mekanism.common.base.IChemicalConstant;
import mekanism.common.registration.impl.GasDeferredRegister;
import mekanism.common.registration.impl.GasRegistryObject;

/**
 * @Author Biggest_Xuan
 * 2025/4/17
 */

@SuppressWarnings("unused")
public class BxPGases {
    public static final GasDeferredRegister GASES = new GasDeferredRegister(BxP2.MODID);

    public static final GasRegistryObject<Gas> CarbonDioxide = GASES.register("carbon_dioxide",0xA0D1D1);
    public static final GasRegistryObject<Gas> Acetylene = GASES.register("acetylene",0xFF5555);
    public static final GasRegistryObject<Gas> HydrogenCyanide = GASES.register("hydrogen_cyanide",0xC2FF00);
    public static final GasRegistryObject<Gas> Methane = GASES.register("methane",0x70E0FF,new GasAttributes.Fuel(40, FloatingLong.create(11000)));
    public static final GasRegistryObject<Gas> AMMONIA = GASES.register("ammonia",0xC8B3FF);
    public static final GasRegistryObject<Gas> Butene_1	= GASES.register("butene_1",0xFFAA66);
    public static final GasRegistryObject<Gas> Allene = GASES.register("allene",0xFF00FF);
    public static final GasRegistryObject<Gas> Acrylonitrile = GASES.register("acrylonitrile",0xFF4500);
    public static final GasRegistryObject<Gas> Propylamine = GASES.register("propylamine",0x9370DB,new GasAttributes.Fuel(40, FloatingLong.create(160000)));
    //public static final GasRegistryObject<Gas> HydrogenFluoride = GASES.register("hydrogen_fluoride",0x635A16);
    public static final GasRegistryObject<Gas> NWS = GASES.register(BxPChemicalConstants.NWS,new GasAttributes.Radiation(30F));
    public static final GasRegistryObject<Gas> UNS = GASES.register(BxPChemicalConstants.UNS);
    public static final GasRegistryObject<Gas> CONDENSE_UNS = GASES.register(BxPChemicalConstants.CONDENSE_UNS);
    public static final GasRegistryObject<Gas> Uranium_tetrafluoride = GASES.register("uranium_tetrafluoride",0x9ACD32);
    public static final GasRegistryObject<Gas> potassium = GASES.register("potassium",0xFF9933);
    public static final GasRegistryObject<Gas> Potassium_Fluoride = GASES.register(BxPChemicalConstants.POTASSIUM_FLUORIDE);
    public static final GasRegistryObject<Gas> Fluorine = GASES.register("fluorine",0xE6E6FA);
    public static final GasRegistryObject<Gas> Plutonium_Tetrafluoride = GASES.register("plutonium_tetrafluoride",0xFF3860,new GasAttributes.Radiation(30F));
    public static final GasRegistryObject<Gas> Polonium_Dioxide = GASES.register("polonium_dioxide",0x4B0082,new GasAttributes.Radiation(30F));
    public static final GasRegistryObject<Gas> Curium = GASES.register("curium",0x1E90FF,new GasAttributes.Radiation(50F));
    public static final GasRegistryObject<Gas> ActivatedAmericium = GASES.register("activated_americium",0xf2b5b4);
    public static final GasRegistryObject<Gas> ActivatedCurium = GASES.register("activated_curium",0x89bbec);
    public static final GasRegistryObject<Gas> SodiumFluosilicate = GASES.register("sodium_fluosilicate",0xF0E68C);
    public static final GasRegistryObject<Gas> UranylFluosilicate = GASES.register("uranyl_fluosilicate",0x9ACD32);
}

package biggestxuan.bxp2.utils;

import biggestxuan.bxp2.api.OnlyDev;
import vazkii.botania.common.item.equipment.tool.elementium.ElementiumSwordItem;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/4/2
 */

@OnlyDev
public class GenTinkerFile {
    public static final String ID = "radiation_metal"; //
    public static final String ITEM = "bxp2:radiation_ingot";
    public static final String FLUID = "bxp2:molten_radiation_metal";
    public static final String CN = "辐射金属"; //
    public static final String EN = "Radiation Metal"; //
    public static final String COLOR = "1affd9";
    public static final String path1 = "src/main/resources/data/bxp2/tinkering/materials/";
    public static final String path2 = "src/main/resources/data/bxp2/recipes/materials/";
    public static final String path3 = "src/main/resources/data/bxp2/recipes/smeltery/";
    public static final String path4 = "src/main/resources/assets/bxp2/tinkering/materials/";
    public static final String path5 = "src/main/resources/assets/bxp2/mantle/fluid_texture/";
    public static final String path6 = "src/main/resources/assets/bxp2/lang/";
    public static final String path7 = "src/main/resources/assets/bxp2/models/item/";

    public static void main(String[] args) throws Exception{
        genDefinition();
        genStats();
        genTraits();
        genRecipes();
        genSmeltery();
        genItemAssets();
        genFluidAssets();
        addLang();
        addBucketAssets();
    }

    public static void addBucketAssets() throws Exception{
        String name = FLUID.split(":")[1];
        File file = new File(path7+name+"_bucket.json");
        file.createNewFile();
        String s = "{\n" +
                "  \"parent\": \"forge:item/bucket_drip\",\n" +
                "  \"flip_gas\": false,\n" +
                "  \"fluid\": \""+FLUID+"\",\n" +
                "  \"loader\": \"tconstruct:fluid_container\"\n" +
                "}";
        writeFile(file, s);
    }

    public static void addLang() throws Exception{
        addENLang();
        addCNLang();
    }

    public static void addENLang() throws Exception{
        String n = FLUID.split(":")[0]+"."+FLUID.split(":")[1];
        String s = ",\n";
        s += "  \"material.bxp2."+ID+"\": \""+EN+"\",\n" +
                "  \"fluid."+n+"\": \"Molten"+EN+"\",\n" +
                "  \"item."+n+"_bucket\": \"Molten "+EN+" Bucket\",\n" +
                "  \"block."+n+"_fluid\": \"Molten "+EN+" \"";
        s += "\n}";
        appendLang(path6+"en_us.json",s);
    }

    public static void addCNLang() throws Exception{
        String n = FLUID.split(":")[0]+"."+FLUID.split(":")[1];
        String s = ",\n";
        s += "  \"material.bxp2."+ID+"\": \""+CN+"\",\n" +
                "  \"fluid."+n+"\": \"熔融"+CN+"\",\n" +
                "  \"item."+n+"_bucket\": \"熔融"+CN+"桶\",\n" +
                "  \"block."+n+"_fluid\": \"熔融"+CN+"\"";
        s += "\n}";
        appendLang(path6+"zh_cn.json",s);
    }

    public static void genFluidAssets() throws Exception{
        String name = FLUID.split(":")[1];
        File file = new File(path5+name+".json");
        file.createNewFile();
        String s = "{\n" +
                "  \"color\": \""+COLOR+"\",\n" +
                "  \"flowing\": \"tconstruct:fluid/liquid/flowing\",\n" +
                "  \"still\": \"tconstruct:fluid/liquid/still\"\n" +
                "}";
        writeFile(file,s);
    }

    public static void genItemAssets() throws Exception{
        File file = new File(path4+ID+".json");
        file.createNewFile();
        String s = "{\n" +
                "  \"color\": \""+COLOR+"\",\n" +
                "  \"fallbacks\": [\n" +
                "    \"metal\"\n" +
                "  ],\n" +
                "  \"generator\": {\n" +
                "    \"supported_stats\": [\n" +
                "      \"tconstruct:repair_kit\",\n" +
                "      \"tconstruct:head\",\n" +
                "      \"tconstruct:handle\",\n" +
                "      \"tconstruct:binding\",\n" +
                "      \"tconstruct:armor_plating\",\n" +
                "      \"tconstruct:plating_helmet\",\n" +
                "      \"tconstruct:plating_chestplate\",\n" +
                "      \"tconstruct:plating_leggings\",\n" +
                "      \"tconstruct:plating_boots\",\n" +
                "      \"tconstruct:plating_shield\",\n" +
                "      \"tconstruct:maille\",\n" +
                "      \"tconstruct:armor_maille\"\n" +
                "    ]\n" +
                "  }\n" +
                "}";
        writeFile(file, s);
    }

    public static void genSmeltery() throws Exception{
        genSmelteryMelting();
        genSmelteryCasting();
    }

    public static void genSmelteryMelting() throws Exception{
        File file = new File(path3+"melting/"+ID+".json");
        file.createNewFile();
        String s = "{\n" +
                "  \"type\":\"tconstruct:melting\",\n" +
                "  \"ingredient\":{\n" +
                "    \"item\":\""+ITEM+"\"\n" +
                "  },\n" +
                "  \"temperature\": 1000,\n" +
                "  \"time\": 150,\n" +
                "  \"result\":\n" +
                "  {\n" +
                "    \"fluid\": \""+FLUID+"\",\n" +
                "    \"amount\": 90\n" +
                "  }\n" +
                "}";
        writeFile(file, s);
    }

    public static void genSmelteryCasting() throws Exception{
        File file = new File(path3+"casting/"+ID+".json");
        file.createNewFile();
        String s = "{\n" +
                "  \"type\": \"tconstruct:casting_table\",\n" +
                "  \"cast\": {\n" +
                "    \"tag\": \"tconstruct:casts/multi_use/ingot\"\n" +
                "  },\n" +
                "  \"cast_consumed\": false,\n" +
                "  \"fluid\": {\n" +
                "    \"fluid\": \""+FLUID+"\",\n" +
                "    \"amount\": 90\n" +
                "  },\n" +
                "  \"result\": \""+ITEM+"\",\n" +
                "  \"cooling_time\": 60\n" +
                "}";
        writeFile(file,s);
    }

    public static void genRecipes() throws Exception{
        genFix();
        genCast();
        genMelting();
    }

    public static void genMelting() throws Exception{
        File file = new File(path2+ID+"_materialmelting.json");
        file.createNewFile();
        String s = "{\n" +
                "  \"type\":\"tconstruct:material_melting\",\n" +
                "  \"input\": \""+ITEM+"\",\n" +
                "  \"temperature\": 1000,\n" +
                "  \"result\":{\n" +
                "    \"fluid\": \""+FLUID+"\",\n" +
                "    \"amount\": 90\n" +
                "  }\n" +
                "}";
        writeFile(file,s);
    }

    public static void genCast() throws Exception{
        File file = new File(path2+ID+"_materialcast.json");
        file.createNewFile();
        String s = "{\n" +
                "  \"type\": \"tconstruct:material_fluid\",\n" +
                "  \"fluid\": {\n" +
                "    \"fluid\": \""+FLUID+"\",\n" +
                "    \"amount\":90\n" +
                "  },\n" +
                "  \"temperature\":1000,\n" +
                "  \"output\":\"bxp2:"+ID+"\"\n" +
                "}";
        writeFile(file, s);
    }

    public static void genFix() throws Exception{
        File file = new File(path2+ID+"_fix.json");
        file.createNewFile();
        String s = "{\n" +
                "  \"type\": \"tconstruct:material\",\n" +
                "  \"ingredient\": {\n" +
                "    \"item\": \""+ITEM+"\"\n" +
                "  },\n" +
                "  \"value\": 1,\n" +
                "  \"needed\": 1,\n" +
                "  \"material\":\"bxp2:"+ID+"\"\n" +
                "}";
        writeFile(file, s);
    }

    public static void genTraits() throws Exception{
        File file = new File(path1+"/traits/"+ID+".json");
        file.createNewFile();
        String s = "{\n" +
                "  \"perStat\": {\n" +
                "    \"tconstruct:head\": [\n" +
                "      {\n" +
                "        \"level\": 1,\n" +
                "        \"name\": \"\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"tconstruct:grip\": [\n" +
                "      {\n" +
                "        \"level\": 1,\n" +
                "        \"name\": \"\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
        writeFile(file, s);
    }

    public static void genStats() throws Exception{
        File file = new File(path1+"/stats/"+ID+".json");
        file.createNewFile();
        String s = "{\n" +
                "  \"stats\": {\n" +
                "    \"tconstruct:plating_shield\": {\n" +
                "      \"durability\": 255,\n" +
                "      \"toughness\": 0\n" +
                "    },\n" +
                "    \"tconstruct:limb\": {\n" +
                "      \"accuracy\": 0,\n" +
                "      \"draw_speed\": 0,\n" +
                "      \"durability\": 330,\n" +
                "      \"velocity\": 0.15\n" +
                "    },\n" +
                "    \"tconstruct:binding\": {},\n" +
                "    \"tconstruct:grip\": {\n" +
                "      \"accuracy\": 0.0,\n" +
                "      \"durability\": 0.1,\n" +
                "      \"melee_damage\": 2.2\n" +
                "    },\n" +
                "    \"tconstruct:handle\": {\n" +
                "      \"durability\": 0.08,\n" +
                "      \"mining_speed\": 0,\n" +
                "      \"melee_speed\": 0,\n" +
                "      \"melee_damage\": 0.01\n" +
                "    },\n" +
                "    \"tconstruct:head\": {\n" +
                "      \"durability\": 325,\n" +
                "      \"mining_speed\": 5.5,\n" +
                "      \"mining_tier\": \"minecraft:iron\",\n" +
                "      \"melee_attack\": 2.4\n" +
                "    },\n" +
                "    \"tconstruct:plating_helmet\": {\n" +
                "      \"armor\": 2.2,\n" +
                "      \"durability\": 220\n" +
                "    },\n" +
                "    \"tconstruct:plating_chestplate\": {\n" +
                "      \"armor\": 5.3,\n" +
                "      \"durability\": 300\n" +
                "    },\n" +
                "    \"tconstruct:plating_leggings\": {\n" +
                "      \"armor\": 4.3,\n" +
                "      \"durability\": 265\n" +
                "    },\n" +
                "    \"tconstruct:plating_boots\": {\n" +
                "      \"armor\": 2.15,\n" +
                "      \"durability\": 195\n" +
                "    }\n" +
                "  }\n" +
                "}";
        writeFile(file, s);
    }

    public static void genDefinition() throws Exception{
        File file = new File(path1+"/definition/"+ID+".json");
        file.createNewFile();
        String s = "{\n" +
                "  \"craftable\": false,\n" +
                "  \"tier\": 4,\n" +
                "  \"sortOrder\": 0,\n" +
                "  \"hidden\": false\n" +
                "}";
        writeFile(file, s);
    }

    public static void appendLang(String file,String s) throws Exception{
        List<String> lines = Files.readAllLines(Paths.get(file));
        if (!lines.isEmpty()) {
            lines.remove(lines.size() - 1);
        }
        lines.add(s);
        Files.write(Paths.get(file), lines, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static void writeFile(File file,String content) throws Exception{
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.flush();
        fw.close();
    }
}

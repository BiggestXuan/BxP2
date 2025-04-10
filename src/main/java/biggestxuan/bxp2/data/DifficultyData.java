package biggestxuan.bxp2.data;

import net.minecraft.data.DataProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

/**
 * @Author Biggest_Xuan
 * 2025/4/8
 */

public class DifficultyData extends SavedData {
    private static final String NAME = "difficulty";
    private int difficulty = 2;

    public static DifficultyData create() {
        return new DifficultyData();
    }

    @Override
    public CompoundTag save(CompoundTag compoundTag) {
        compoundTag.putInt(NAME,difficulty);
        return compoundTag;
    }

    public static DifficultyData load(CompoundTag compoundTag) {
        DifficultyData data =  DifficultyData.create();
        data.setDifficulty(compoundTag.getInt(NAME));
        return data;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        setDirty();
    }

    public int getDifficulty() {
        return difficulty;
    }

    public static DifficultyData getInstance(Level world) {
        if(world.isClientSide){
            throw new RuntimeException();
        }
        ServerLevel serverWorld = world.getServer().overworld();
        DimensionDataStorage data = serverWorld.getDataStorage();
        return data.computeIfAbsent(DifficultyData::load,DifficultyData::new,NAME);
    }

    public static DifficultyData getInstance(MinecraftServer server){
        ServerLevel serverWorld = server.overworld();
        DimensionDataStorage data = serverWorld.getDataStorage();
        return data.computeIfAbsent(DifficultyData::load,DifficultyData::new,NAME);
    }
}
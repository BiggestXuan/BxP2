package biggestxuan.bxp2.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * @Author Biggest_Xuan
 * 2025/4/12
 */

public interface IBxPCapability extends INBTSerializable<CompoundTag> {
    void setPhase(int phase);
    int getPhase();
    boolean canNether();
    void setNether(boolean nether);
    boolean canEnd();
    void setEnd(boolean end);
    float deathLoss();
    void setDeathLoss(float deathLoss);
    boolean canFly();
    void setFly();
    int getWitherCount();
    void addWitherCount();
    int getDragonCount();
    void addDragonCount();
    float getMoney();
    void setMoney(float money);
}

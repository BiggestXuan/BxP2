package biggestxuan.bxp2.capability;

import net.minecraft.nbt.CompoundTag;

/**
 * @Author Biggest_Xuan
 * 2025/4/12
 */

public class BxPCapability implements IBxPCapability{
    private int phase = -1;
    private boolean canNether = false;
    private boolean canEnd = false;
    private float deathLoss = 0F;
    private boolean canFly = false;
    private int witherCount = 0;
    private int dragonCount = 0;
    private float money = 0;
    private int creativeCount = 0;

    @Override
    public void setPhase(int phase) {
        this.phase = phase;
    }

    @Override
    public int getPhase() {
        return this.phase;
    }

    @Override
    public boolean canNether() {
        return canNether;
    }

    @Override
    public void setNether(boolean nether) {
        this.canNether = nether;
    }

    @Override
    public boolean canEnd() {
        return canEnd;
    }

    @Override
    public void setEnd(boolean end) {
        this.canEnd = end;
    }

    @Override
    public float deathLoss() {
        return this.deathLoss;
    }

    @Override
    public void setDeathLoss(float deathLoss) {
        this.deathLoss = deathLoss;
    }

    @Override
    public boolean canFly() {
        return this.canFly;
    }

    @Override
    public void setFly() {
        this.canFly = true;
    }

    @Override
    public int getWitherCount() {
        return witherCount;
    }

    @Override
    public void addWitherCount() {
        witherCount++;
    }

    @Override
    public int getDragonCount() {
        return dragonCount;
    }

    @Override
    public void addDragonCount() {
        dragonCount++;
    }

    @Override
    public float getMoney() {
        return money;
    }

    @Override
    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public int getBuyCreativeCount() {
        return creativeCount;
    }

    @Override
    public void setBuyCreativeCount(int count) {
        creativeCount = count;
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("phase", phase);
        tag.putBoolean("canNether", canNether);
        tag.putBoolean("canEnd", canEnd);
        tag.putFloat("deathLoss", deathLoss);
        tag.putBoolean("fly", canFly);
        tag.putInt("witherCount",witherCount);
        tag.putInt("dragonCount",dragonCount);
        tag.putFloat("money",money);
        tag.putInt("creativeCount",creativeCount);
        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        phase = tag.getInt("phase");
        canNether = tag.getBoolean("canNether");
        canEnd = tag.getBoolean("canEnd");
        deathLoss = tag.getFloat("deathLoss");
        canFly = tag.getBoolean("fly");
        witherCount = tag.getInt("witherCount");
        dragonCount = tag.getInt("dragonCount");
        money = tag.getFloat("money");
        creativeCount = tag.getInt("creativeCount");
    }
}

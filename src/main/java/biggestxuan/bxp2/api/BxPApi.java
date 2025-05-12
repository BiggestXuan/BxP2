package biggestxuan.bxp2.api;

import biggestxuan.bxp2.capability.BxPCapabilityProvider;
import biggestxuan.bxp2.capability.IBxPCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

/**
 * @Author Biggest_Xuan
 * 2025/5/12
 */
public interface BxPApi {
    BxPApi INSTANCE = new BxPApi(){};

    default IBxPCapability getPlayerCap(Player player){
        return player.getCapability(BxPCapabilityProvider.CAPABILITY).orElse(baseCap);
    };

    IBxPCapability baseCap = new IBxPCapability() {
        @Override
        public void setPhase(int phase) {

        }

        @Override
        public int getPhase() {
            return 0;
        }

        @Override
        public boolean canNether() {
            return false;
        }

        @Override
        public void setNether(boolean nether) {

        }

        @Override
        public boolean canEnd() {
            return false;
        }

        @Override
        public void setEnd(boolean end) {

        }

        @Override
        public float deathLoss() {
            return 0;
        }

        @Override
        public void setDeathLoss(float deathLoss) {

        }

        @Override
        public boolean canFly() {
            return false;
        }

        @Override
        public void setFly() {

        }

        @Override
        public int getWitherCount() {
            return 0;
        }

        @Override
        public void addWitherCount() {

        }

        @Override
        public int getDragonCount() {
            return 0;
        }

        @Override
        public void addDragonCount() {

        }

        @Override
        public float getMoney() {
            return 0;
        }

        @Override
        public void setMoney(float money) {

        }

        @Override
        public int getBuyCreativeCount() {
            return 0;
        }

        @Override
        public void setBuyCreativeCount(int count) {

        }

        @Override
        public boolean isPcl() {
            return false;
        }

        @Override
        public void setPcl(boolean pcl) {

        }

        @Override
        public int[] getClientData() {
            return new int[0];
        }

        @Override
        public void setClientData(int[] data) {

        }

        @Override
        public CompoundTag serializeNBT() {
            return null;
        }

        @Override
        public void deserializeNBT(CompoundTag compoundTag) {

        }
    };
}

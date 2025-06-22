package biggestxuan.bxp2.integration.DraconicEvolution.Explosion;

import biggestxuan.bxp2.BxP2;
import com.brandon3055.brandonscore.handlers.IProcess;
import com.brandon3055.draconicevolution.blocks.reactor.ProcessExplosion;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/5/29
 */
public class ProcessHandler {
    private static final ProcessHandler INSTANCE = new ProcessHandler();
    private final List<IProcess> processes = new ArrayList<>();

    public static ProcessHandler getInstance() {
        return INSTANCE;
    }

    public void addProcess(IProcess process) {
        processes.add(process);
    }

    public void updateProcesses() {
        BxP2.LOGGER.debug("Amount{}", processes.size());
        if(!processes.isEmpty() && processes.get(0) instanceof ProcessExplosion p){
            BxP2.LOGGER.debug("{}", p.isCalculationComplete());
            BxP2.LOGGER.debug("{}", p.isDead());
        }
        processes.removeIf(process -> {
            process.updateProcess();
            return process.isDead();
        });
    }
}

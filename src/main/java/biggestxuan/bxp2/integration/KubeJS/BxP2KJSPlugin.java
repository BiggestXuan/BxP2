package biggestxuan.bxp2.integration.KubeJS;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;

/**
 * @Author Biggest_Xuan
 * 2025/4/6
 */

public class BxP2KJSPlugin extends KubeJSPlugin {
    public void registerBindings(BindingsEvent event) {
        event.add("BxP2",KJSUtils.class);
    }
}

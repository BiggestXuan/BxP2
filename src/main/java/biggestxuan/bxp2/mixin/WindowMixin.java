package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.client.ClientCommon;
import com.mojang.blaze3d.platform.Window;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @Author Biggest_Xuan
 * 2025/4/25
 */

@Mixin(Window.class)
public class WindowMixin {
    @Shadow @Final private long window;

    @Redirect(method = "setTitle",at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwSetWindowTitle(JLjava/lang/CharSequence;)V",remap = false))
    public void __redirect(long titleEncoded, CharSequence window){
        GLFW.glfwSetWindowTitle(titleEncoded, ClientCommon.TITLE);
    }
}

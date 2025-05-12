package biggestxuan.bxp2.utils;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.LevelResource;

import java.io.File;

/**
 * @Author Biggest_Xuan
 * 2025/5/12
 */
public class ServerUtils {
    public static long getWorldSize(ServerLevel world) {
        File worldDir = world.getServer().getWorldPath(LevelResource.ROOT).toFile();
        return getFolderSize(worldDir);
    }

    private static long getFolderSize(File folder) {
        long length = 0;
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    length += file.length();
                } else {
                    length += getFolderSize(file);
                }
            }
        }
        return length;
    }
}

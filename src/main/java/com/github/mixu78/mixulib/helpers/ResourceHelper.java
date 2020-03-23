package com.github.mixu78.mixulib.helpers;

import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class ResourceHelper {
    private static Map<String, Map<String, ResourceLocation>> resourceLocationCache = Maps.newHashMap();

    public static void bindTexture(ResourceLocation texture) {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
    }

    public static ResourceLocation getResource(String modId, String path) {
        if (!resourceLocationCache.containsKey(modId)) {

            ResourceLocation rs = new ResourceLocation(modId,path);
            Map<String, ResourceLocation> rsMap = Maps.newHashMap();
            rsMap.put(path,rs);
            resourceLocationCache.put(modId, rsMap);
            return rs;
        }
        if (!resourceLocationCache.get(modId).containsKey(path)) {

            ResourceLocation rs = new ResourceLocation(modId, path);
            resourceLocationCache.get(modId).put(path, rs);
            return rs;
        }
        return resourceLocationCache.get(modId).get(path);
    }
}

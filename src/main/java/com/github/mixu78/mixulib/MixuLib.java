package com.github.mixu78.mixulib;

import com.github.mixu78.mixulib.proxy.CommonProxy;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

@Mod(
        modid = MixuLib.MODID,
        name = MixuLib.MODNAME,
        version = MixuLib.VERSION
)
public class MixuLib {

    public static final String MODID = "mixulib";
    public static final String MODNAME = "MixuLib";
    public static final String VERSION = "@VERSION@";

    @Mod.Instance(MODID)
    public static MixuLib INSTANCE;

    @SidedProxy(clientSide = "com.github.mixu78.mixulib.proxy.ClientProxy", serverSide = "com.github.mixu78.mixulib.proxy.ServerProxy")
    public static CommonProxy proxy;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = LogManager.getLogger(MODNAME);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}

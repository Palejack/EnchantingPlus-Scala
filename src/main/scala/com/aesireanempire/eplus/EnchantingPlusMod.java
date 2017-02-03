package com.aesireanempire.eplus;

import com.aesireanempire.eplus.network.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(name = EnchantingPlusMod.MODNAME, modid = EnchantingPlusMod.MODID, version = EnchantingPlusMod.VERSION, modLanguage = "java", useMetadata = true)
public class EnchantingPlusMod {

    public static final String MODNAME = "Enchanting Plus";
    public static final String MODID = "eplus";
    public static final String VERSION = "3.1.0a2";

    @SidedProxy(clientSide = "com.aesireanempire.eplus.network.ClientProxy", serverSide = "com.aesireanempire.eplus.network.CommonProxy")
    public static CommonProxy proxy  = null;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        EnchantingPlus.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        EnchantingPlus.init(this, event);
    }
}

package org.lwjgl.implementation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LegacyLWJGL3  {
    public static final Logger LOGGER = LogManager.getLogger();


    public void onInitialize() {
        LOGGER.info("This is definitely a 100% legit legacy LWJGL2 mod!");
    }
}

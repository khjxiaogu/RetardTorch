package com.khjxiaogu.rtorch;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

public class RTCacheCapability {

    public static final Capability<RTCacheAccess> INSTANCE = CapabilityManager.get(new CapabilityToken<RTCacheAccess>() {});



    private RTCacheCapability() {
    }
}
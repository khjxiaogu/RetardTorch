package com.khjxiaogu.rtorch;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.mojang.datafixers.util.Pair;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.LongArrayTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;

public class RTDefaultCache implements RTCacheAccess {


    public static final ResourceLocation ID = new ResourceLocation(Main.MODID,"level_cache");
	private final LazyOptional<RTDefaultCache> capability;
	private static RTCacheAccess last;
	private static ServerLevel lastLevel;
    Map<Long,Integer> cached=new HashMap<>();

    private static LazyOptional<RTCacheAccess> getCapability(Level world) {
        if (world instanceof ServerLevel) {
            return ((ServerLevel) world).getCapability(RTCacheCapability.INSTANCE);
        }
        return LazyOptional.empty();
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == RTCacheCapability.INSTANCE ? capability.cast() : LazyOptional.empty();
    }
    public static RTCacheAccess get(ServerLevel world) {
    	if(world==lastLevel&&last!=null)
    		return last;
    	lastLevel=null;
    	last=null;
        return (last=getCapability(lastLevel=world).resolve().orElse(new RTDefaultCache()));
    }
    public RTDefaultCache() {
        capability = LazyOptional.of(() -> this);
    }
    @Override
    public CompoundTag serializeNBT() {
    	//System.out.println("snbt");
        CompoundTag nbt = new CompoundTag();
        ListTag lt=new ListTag();
        for(Entry<Long, Integer> c:cached.entrySet()) {
        	LongArrayTag lat=new LongArrayTag(new long[] {c.getKey(),c.getValue()});
        	lt.add(lat);
        }
        nbt.put("datan", lt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
    	//System.out.println("dnbt");
    	ListTag lt=nbt.getList("datan",12);
    	for(Tag t:lt) {
    		
    		LongArrayTag lat=(LongArrayTag) t;
    		long[] las=lat.getAsLongArray();
    		cached.put(las[0],(int)las[1]);
    	}

    }
    public static int getCountOfTorchs(ServerLevel l,BlockPos pos) {
    	RTCacheAccess rcc=get(l);
    	return rcc.getCountOfTorch(l, pos);
    	
    }
 
    @Override
	public void addTorch(BlockPos pos) {

		for(Direction d:Direction.values())
			cached.compute(pos.relative(d).asLong(),(p,c)->{
				if(c==null)
					return 1;
				return c+1;
			});
	}
    @Override
  	public void removeTorch(BlockPos pos) {
    	for(Direction d:Direction.values())
    		cached.compute(pos.relative(d).asLong(),(p,c)->{
    			if(c==null)
    				return null;
    			if(c>1)
    				return c-1;
    			return null;
    		});
  	}
	@Override
	public int getCountOfTorch(Level l, BlockPos pos) {
		long key=pos.asLong();
		return cached.getOrDefault(key, 0);
	}
}

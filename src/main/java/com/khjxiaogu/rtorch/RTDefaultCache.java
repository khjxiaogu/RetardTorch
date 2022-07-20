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

public class RTDefaultCache implements ICapabilitySerializable<CompoundTag>,RTCacheAccess {


    public static final ResourceLocation ID = new ResourceLocation(Main.MODID,"level_cache");
	private final LazyOptional<RTDefaultCache> capability;
    Map<Long,Pair<Integer,Long>> cached=new HashMap<>();

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
        return getCapability(world).resolve().orElse(new RTDefaultCache());
    }
    public RTDefaultCache() {
        capability = LazyOptional.of(() -> this);
    }
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        ListTag lt=new ListTag();
        for(Entry<Long, Pair<Integer, Long>> c:cached.entrySet()) {
        	LongArrayTag lat=new LongArrayTag(new long[] {c.getKey(),c.getValue().getFirst(),c.getValue().getSecond()});
        	lt.add(lat);
        }
        nbt.put("data", lt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
    	ListTag lt=nbt.getList("data",12);
    	for(Tag t:lt) {
    		LongArrayTag lat=(LongArrayTag) t;
    		long[] las=lat.getAsLongArray();
    		cached.put(las[0],Pair.of((int)las[1],las[2]));
    	}

    }
    public static int getCountOfTorchs(ServerLevel l,BlockPos pos) {
    	RTCacheAccess rcc=get(l);
    	return rcc.getCountOfTorch(l, pos);
    	
    }
    public static void tickLevel(ServerLevel l) {
    	RTCacheAccess rcc=get(l);
    	rcc.tick(l);
    	
    }
    private int fetch(Level l,BlockPos pos) {
    	if (pos == null||l==null)
			return 0;
		Block tor = Contents.Blocks.torch.get();
		Block wtor = Contents.Blocks.wall_torch.get();
		int cntoftorch = 0;
		
		for (Direction d : Direction.values()) {
			BlockPos p = pos.relative(d);
			if (!l.isLoaded(p))
				continue;
			Block b = l.getBlockState(p).getBlock();
			if (b == tor || b == wtor)
				cntoftorch++;
		}
		return cntoftorch;
    }
    @Override
    public void tick(Level l) {
    	cached.values().removeIf(p->p.getSecond()<l.getGameTime());
    }
	@Override
	public int getCountOfTorch(Level l, BlockPos pos) {
		long key=pos.asLong();
		Pair<Integer, Long> r=cached.get(key);
		if(r==null) {
			int rc=fetch(l,pos);
			cached.put(key, Pair.of(rc,l.getGameTime()+100L));
			return rc;
		}
		return r.getFirst();
	}
}

package com.khjxiaogu.rtorch;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public interface RTCacheAccess extends ICapabilitySerializable<CompoundTag> {
	
	public int getCountOfTorch(Level l, BlockPos pos);

	void addTorch(BlockPos pos);

	void removeTorch(BlockPos pos);
}

package com.khjxiaogu.rtorch;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public interface RTCacheAccess {
	
	public int getCountOfTorch(Level l, BlockPos pos);


	void tick(Level l);
}

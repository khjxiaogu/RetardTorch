package com.khjxiaogu.rtorch;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class Utils {
	public static BlockPos randomPos;
	public static Level randomLevel;
	public Utils() {
	}
	public static int countTorch(Level l,BlockPos pos) {
		if (!(l instanceof ServerLevel))
			return 0;
	
		return RTDefaultCache.getCountOfTorchs((ServerLevel) l, pos);
	}
}

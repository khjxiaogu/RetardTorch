package com.khjxiaogu.rtorch;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class Utils {
	public static BlockPos randomPos;
	public static Level randomLevel;
	public Utils() {
	}
	public static int countTorch(Level l,BlockPos pos) {
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
}

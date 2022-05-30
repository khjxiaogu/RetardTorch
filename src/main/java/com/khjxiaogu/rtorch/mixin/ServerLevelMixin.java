/*
 * Copyright (c) 2022 Khjxiaogu
 *
 * This file is part of RetardTorch.
 *
 * RetardTorch is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * RetardTorch is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with RetardTorch. If not, see <https://www.gnu.org/licenses/>.
 */

package com.khjxiaogu.rtorch.mixin;

import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.khjxiaogu.rtorch.Contents;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.storage.WritableLevelData;
@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin extends Level{

	protected ServerLevelMixin(WritableLevelData pLevelData, ResourceKey<Level> pDimension,
			Holder<DimensionType> pDimensionTypeRegistration, Supplier<ProfilerFiller> pProfiler, boolean pIsClientSide,
			boolean pIsDebug, long pBiomeZoomSeed) {
		super(pLevelData, pDimension, pDimensionTypeRegistration, pProfiler, pIsClientSide, pIsDebug, pBiomeZoomSeed);
	}
	@Inject(at = @At("HEAD"),
			method = "shouldTickBlocksAt",
			remap = true,
			cancellable=true,
			require=1,
			allow=1)
	public void RT$shouldTickBlocksAt(long l,CallbackInfoReturnable<Boolean> cbi) {
		int cntoftorch=0;
		Block tor=Contents.Blocks.torch.get();
		Block wtor=Contents.Blocks.wall_torch.get();
		BlockPos pos=((LevelMixin)(Object)this).RT$pos;
		for(Direction d:Direction.values()) {
			Block b=getBlockState(pos.relative(d)).getBlock();
			if(b==tor||b==wtor)
				cntoftorch++;
		}
		if(cntoftorch>0)
		if(getGameTime()%(cntoftorch+1)!=0) {
			cbi.setReturnValue(false);
		}
	}


}

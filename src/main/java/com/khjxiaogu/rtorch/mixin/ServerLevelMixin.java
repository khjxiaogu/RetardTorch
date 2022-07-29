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
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.khjxiaogu.rtorch.Contents;
import com.khjxiaogu.rtorch.Utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.storage.WritableLevelData;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin extends Level {
	@Shadow(remap=true)
	private ServerChunkCache chunkSource;
	protected ServerLevelMixin(WritableLevelData pLevelData, ResourceKey<Level> pDimension,
			Holder<DimensionType> pDimensionTypeRegistration, Supplier<ProfilerFiller> pProfiler, boolean pIsClientSide,
			boolean pIsDebug, long pBiomeZoomSeed) {
		super(pLevelData, pDimension, pDimensionTypeRegistration, pProfiler, pIsClientSide, pIsDebug, pBiomeZoomSeed);
	}

	@Inject(at = @At("HEAD"), method = "shouldTickBlocksAt", remap = true, cancellable = true, require = 1, allow = 1)
	public void RT$shouldTickBlocksAt(long l, CallbackInfoReturnable<Boolean> cbi) {
		if(chunkSource.chunkMap.getDistanceManager().inBlockTickingRange(l)) {
			int cntoftorch = Utils.countTorch(this,((LevelMixin) (Object) this).RT$pos);
			((LevelMixin) (Object) this).RT$pos=null;
			if (cntoftorch > 0)
				if (getGameTime() % (cntoftorch + 1) != 0) {
					cbi.setReturnValue(false);
					return;
				}
			cbi.setReturnValue(true);
			return;
		}
		cbi.setReturnValue(false);
		return;
	}

	/*@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;isRandomlyTicking()Z", ordinal = 0,remap=true), method = "tickChunk", remap = true, require = 1, allow = 1, locals = LocalCapture.CAPTURE_FAILHARD)
	public void RT$randomTickingBlock(LevelChunk pChunk, int pRandomTickSpeed, CallbackInfo cbi,ChunkPos cp,boolean f,int i,int j,ProfilerFiller k,LevelChunkSection[] l,int m,int n,LevelChunkSection o,int p,int q,BlockPos pos) {
		Utils.randomPos=pos;
		Utils.randomLevel=this;
	}
	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;isRandomlyTicking()Z", ordinal = 0,remap=true), method = "tickChunk", remap = true, require = 1, allow = 1, locals = LocalCapture.CAPTURE_FAILHARD)
	public void RT$randomTickingFluid(LevelChunk pChunk, int pRandomTickSpeed, CallbackInfo cbi,ChunkPos cp,boolean f,int i,int j,ProfilerFiller k,LevelChunkSection[] l,int m,int n,LevelChunkSection o,int p,int q,BlockPos pos) {
		Utils.randomPos=pos;
		Utils.randomLevel=this;
	}
	@Inject(at = @At("TAIL"), method = "tickChunk", remap = true, require = 1, allow = 1)
	public void RT$randomTickingEnd(LevelChunk pChunk, int pRandomTickSpeed, CallbackInfo cbi) {
		Utils.randomPos=null;
		Utils.randomLevel=null;
	}*/
}

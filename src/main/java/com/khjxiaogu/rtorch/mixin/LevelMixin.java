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

import java.util.Iterator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.core.BlockPos;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(Level.class)
public abstract class LevelMixin{

	public BlockPos pos;
	@Shadow(remap=true)
	public abstract BlockState getBlockState(BlockPos pPos);

	
	@Inject(at = @At(value="INVOKE",target="Lnet/minecraft/world/level/block/entity/TickingBlockEntity;isRemoved()Z"),
			method = "tickBlockEntities",
			remap = true,
			locals=LocalCapture.CAPTURE_FAILHARD)
	public void RT$tickBlockEntities(CallbackInfo cb,ProfilerFiller profilerfiller,Iterator<TickingBlockEntity> iterator,TickingBlockEntity tickingblockentity) {
		pos=tickingblockentity.getPos();
	}
}

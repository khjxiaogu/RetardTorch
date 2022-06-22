package com.khjxiaogu.rtorch.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.khjxiaogu.rtorch.Utils;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunkSection;
/**
 * Does not let our slow down affect other vanilla logics, which may cause a disaster
 * */
@Mixin(LevelChunkSection.class)
public class LevelChunkSectionMixin {

	public LevelChunkSectionMixin() {
	}
	@Inject(at=@At("HEAD"),method="recalcBlockCounts",remap=true,require=1,allow=1)
	public void RT$recalcBlockCounts(CallbackInfo cbi) {
		Utils.randomPos=null;
		Utils.randomLevel=null;
	}
	
	@Inject(at=@At("HEAD"),method="Lnet/minecraft/world/level/chunk/LevelChunkSection;setBlockState(IIILnet/minecraft/world/level/block/state/BlockState;Z)Lnet/minecraft/world/level/block/state/BlockState;",remap=true,require=1,allow=1)
	public void RT$setBlockState(int pX, int pY, int pZ, BlockState pState, boolean pUseLocks,CallbackInfoReturnable<BlockState> cbi) {
		Utils.randomPos=null;
		Utils.randomLevel=null;
	}
}

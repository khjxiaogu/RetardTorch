package com.khjxiaogu.rtorch.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.collect.ImmutableMap;
import com.khjxiaogu.rtorch.Utils;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase;
import net.minecraft.world.level.block.state.properties.Property;

@Mixin(BlockStateBase.class)
public abstract class BlockStateBaseMixin extends StateHolder<Block, BlockState> {

	protected BlockStateBaseMixin(Block pOwner, ImmutableMap<Property<?>, Comparable<?>> pValues,
			MapCodec<BlockState> pPropertiesCodec) {
		super(pOwner, pValues, pPropertiesCodec);
	}

	@Shadow(remap = true)
	public abstract Block getBlock();

	@Shadow(remap = true)
	protected abstract BlockState asState();

	@Inject(at = @At("HEAD"), method = "isRandomlyTicking", remap = true, require = 1, allow = 1, cancellable = true)
	public void RT$isRandomlyTicking(CallbackInfoReturnable<Boolean> cbi) {
		Level rl = Utils.randomLevel;
		BlockPos pos = Utils.randomPos;
		Utils.randomLevel = null;
		Utils.randomPos = null;
		if (!this.getBlock().isRandomlyTicking(asState())) {
			cbi.setReturnValue(false);
			return;
		}
		int cntoftorch = Utils.countTorch(rl, pos);
		if (cntoftorch > 0)
			if (rl.getRandom().nextInt(cntoftorch + 1) != 0) {
				cbi.setReturnValue(false);
				return;
			}
		cbi.setReturnValue(true);
	}

}

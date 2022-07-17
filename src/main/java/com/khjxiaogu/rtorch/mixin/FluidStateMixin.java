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
import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

@Mixin(FluidState.class)
public abstract class FluidStateMixin extends StateHolder<Fluid, FluidState> {
	@Shadow(remap = true)
	public abstract Fluid getType();

	protected FluidStateMixin(Fluid pOwner, ImmutableMap<Property<?>, Comparable<?>> pValues,
			MapCodec<FluidState> pPropertiesCodec) {
		super(pOwner, pValues, pPropertiesCodec);
	}

	@Inject(at = @At("HEAD"), method = "isRandomlyTicking", remap = true, require = 1, allow = 1, cancellable = true)
	public void RT$isRandomlyTicking(CallbackInfoReturnable<Boolean> cbi) {
		Level rl = Utils.randomLevel;
		BlockPos pos = Utils.randomPos;
		Utils.randomLevel = null;
		Utils.randomPos = null;
		if (!((FluidAccess) this.getType()).callIsRandomlyTicking()) {
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

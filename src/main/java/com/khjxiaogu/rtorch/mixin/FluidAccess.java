package com.khjxiaogu.rtorch.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.level.material.Fluid;

@Mixin(Fluid.class)
public interface FluidAccess {

	@Invoker(remap=true)
	public abstract boolean callIsRandomlyTicking();
}

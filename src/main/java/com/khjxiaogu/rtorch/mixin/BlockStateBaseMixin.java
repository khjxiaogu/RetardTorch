package com.khjxiaogu.rtorch.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.collect.ImmutableMap;
import com.khjxiaogu.rtorch.Utils;
import com.mojang.serialization.MapCodec;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase;
import net.minecraft.world.level.block.state.properties.Property;

@Mixin(BlockStateBase.class)
public class BlockStateBaseMixin extends StateHolder<Block, BlockState>{

	protected BlockStateBaseMixin(Block pOwner, ImmutableMap<Property<?>, Comparable<?>> pValues,
			MapCodec<BlockState> pPropertiesCodec) {
		super(pOwner, pValues, pPropertiesCodec);
	}
	@Inject(at=@At("HEAD"),method="isRandomlyTicking", remap = true, require = 1, allow = 1,cancellable=true)
    public void RT$isRandomlyTicking(CallbackInfoReturnable<Boolean> cbi) {
		int cntoftorch = Utils.countTorch(Utils.randomLevel,Utils.randomPos);
		if (cntoftorch > 0)
			if (Utils.randomLevel.getRandom().nextInt(cntoftorch + 1) != 0) {
				cbi.setReturnValue(false);
			}
		//clean up to avoid problems
		Utils.randomLevel=null;
		Utils.randomPos=null;
     }

}

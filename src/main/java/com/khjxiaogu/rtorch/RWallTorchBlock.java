package com.khjxiaogu.rtorch;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;

public class RWallTorchBlock extends WallTorchBlock {

	public RWallTorchBlock(Properties p_58123_, ParticleOptions p_58124_) {
		super(p_58123_, p_58124_);
	}

	/**
	 * Called periodically clientside on blocks near the player to show effects
	 * (like furnace fire particles).
	 */
	@Override
	public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, Random pRand) {
		Direction direction = pState.getValue(FACING);
		double d0 = pPos.getX() + 0.5D;
		double d1 = pPos.getY() + 0.7D;
		double d2 = pPos.getZ() + 0.5D;
		Direction direction1 = direction.getOpposite();
		pLevel.addParticle(this.flameParticle, d0 + 0.27D * direction1.getStepX(), d1 + 0.22D,
				d2 + 0.27D * direction1.getStepZ(), -pRand.nextGaussian(0.04, 0.04)* direction1.getStepX(), 0.04,
				-pRand.nextGaussian(0.04, 0.04)* direction1.getStepZ());
	}
	@Override
	public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, LivingEntity pPlacer, ItemStack pStack) {
		super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
		if(!pLevel.isClientSide)
		RTDefaultCache.get((ServerLevel) pLevel).addTorch(pPos);
	}

	@Override
	public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
		super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
		if(!pLevel.isClientSide)
			RTDefaultCache.get((ServerLevel) pLevel).removeTorch(pPos);
	}
}

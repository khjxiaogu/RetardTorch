package com.khjxiaogu.rtorch;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;

public class RTorchBlock extends TorchBlock {

	public RTorchBlock(Properties pProperties, ParticleOptions pFlameParticle) {
		super(pProperties, pFlameParticle);
	}

	@Override
	public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, Random pRand) {
	      double d0 = pPos.getX() + 0.5D;
	      double d1 = pPos.getY() + 0.7D;
	      double d2 = pPos.getZ() + 0.5D;
	      pLevel.addParticle(this.flameParticle, d0, d1, d2,pRand.nextGaussian(0.04, 0.04),0.04,pRand.nextGaussian(0.04, 0.04));
	}

}

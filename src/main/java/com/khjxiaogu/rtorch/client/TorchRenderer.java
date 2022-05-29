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

package com.khjxiaogu.rtorch.client;

import com.khjxiaogu.rtorch.Contents;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.ModelDataManager;
import net.minecraftforge.client.model.data.IModelData;

public class TorchRenderer<E extends LivingEntity, M extends HumanoidModel<E>> extends RenderLayer<E, M> {
    public TorchRenderer(RenderLayerParent<E, M> pRenderer) {
		super(pRenderer);
	}



	public static boolean rendersAssigned = false;



	@Override
	public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, E pLivingEntity,
			float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw,
			float pHeadPitch) {
		BlockState bs=Contents.Blocks.torch.get().defaultBlockState();
		pMatrixStack.pushPose();
		pMatrixStack.translate(-0.5, 0.9375, -0.4375);
		pMatrixStack.pushPose();
		pMatrixStack.mulPose(new Quaternion(40,0,0,true));
		IModelData imd=Minecraft.getInstance().getBlockRenderer().getBlockModel(bs).getModelData(pLivingEntity.getLevel(),new BlockPos(0,0,0), bs,ModelDataManager.getModelData(pLivingEntity.getLevel(),new BlockPos(0,0,0)));
		Minecraft.getInstance().getBlockRenderer().renderSingleBlock(bs, pMatrixStack, pBuffer, pPackedLight,OverlayTexture.NO_OVERLAY,imd);
		pMatrixStack.popPose();
		pMatrixStack.popPose();
	}
}
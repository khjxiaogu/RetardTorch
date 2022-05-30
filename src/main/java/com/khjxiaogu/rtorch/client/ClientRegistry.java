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
import com.khjxiaogu.rtorch.Main;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent.AddLayers;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Main.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistry {
	@SuppressWarnings("unused")
	@SubscribeEvent
	public static void onClientSetupEvent(FMLClientSetupEvent event) {
		ItemBlockRenderTypes.setRenderLayer(Contents.Blocks.wall_torch.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(Contents.Blocks.torch.get(), RenderType.cutout());

	}
	@SuppressWarnings("unused")
	@SubscribeEvent
	public static void onAddLayer(AddLayers event) {
		PlayerRenderer render = (PlayerRenderer) event.getSkin("default");
        render.addLayer(new TorchRenderer<>(render));
        render = (PlayerRenderer) event.getSkin("slim");
        render.addLayer(new TorchRenderer<>(render));

	}
	@SuppressWarnings("unused")
	@SubscribeEvent
	public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
		Contents.Blocks.PREGISTER.getEntries().forEach(registryObject -> 
		Minecraft.getInstance().particleEngine.register((SimpleParticleType) registryObject.get(),
                FlameParticle.Provider::new));
	}
}
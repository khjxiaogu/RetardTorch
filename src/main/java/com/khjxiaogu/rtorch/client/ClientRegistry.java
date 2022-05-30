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

import java.util.function.Function;

import com.khjxiaogu.rtorch.Contents;
import com.khjxiaogu.rtorch.Main;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
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
		addHumanlikeRender(render);
        render = (PlayerRenderer) event.getSkin("slim");
        addHumanlikeRender(render);
        //human like
        addHumanlikeRender(EntityType.ARMOR_STAND,event);
        addHumanlikeRender(EntityType.CREEPER,event);
        addHumanlikeRender(EntityType.DROWNED,event);
        addHumanlikeRender(EntityType.EVOKER,event);
        addHumanlikeRender(EntityType.VINDICATOR,event);
        addHumanlikeRender(EntityType.HUSK,event);
        addHumanlikeRender(EntityType.ILLUSIONER,event);
        addHumanlikeRender(EntityType.PIGLIN,event);
        addHumanlikeRender(EntityType.PIGLIN_BRUTE,event);
        addHumanlikeRender(EntityType.PILLAGER,event);
        addHumanlikeRender(EntityType.GIANT,event);
        addHumanlikeRender(EntityType.SKELETON,event);
       
        addHumanlikeRender(EntityType.VILLAGER,event);
        addHumanlikeRender(EntityType.STRAY,event);
        addHumanlikeRender(EntityType.WANDERING_TRADER,event);
        addHumanlikeRender(EntityType.WITCH,event);
        addHumanlikeRender(EntityType.WITHER_SKELETON,event);
        addHumanlikeRender(EntityType.ZOMBIE,event);
        addHumanlikeRender(EntityType.ZOMBIE_VILLAGER,event);
        addHumanlikeRender(EntityType.ZOMBIFIED_PIGLIN,event);
        //cow like
        addCowlikeRender(EntityType.COW,event);
        addCowlikeRender(EntityType.MOOSHROOM,event);
        addCowlikeRender(EntityType.HOGLIN,event);
        addCowlikeRender(EntityType.ZOGLIN,event);
        addCowlikeRender(EntityType.PANDA,event);
        addCowlikeRender(EntityType.RAVAGER,event);
        
        addSheeplikeRender(EntityType.SHEEP,event);
        addPiglikeRender(EntityType.GOAT,event);
        addPiglikeRender(EntityType.PIG,event);
        
        addHorselikeRender(EntityType.DONKEY,event);
        addHorselikeRender(EntityType.MULE,event);
        addHorselikeRender(EntityType.TRADER_LLAMA,event);
        addHorselikeRender(EntityType.LLAMA,event);
        addHorselikeRender(EntityType.HORSE,event);
        addHorselikeRender(EntityType.SKELETON_HORSE,event);
        addHorselikeRender(EntityType.ZOMBIE_HORSE,event);
        
        //others
        addPiglikeRender(EntityType.GHAST,event);
        addRender(EntityType.FOX,-8,24,12,130,0,0,event);
        addChickenlikeRender(EntityType.CHICKEN,event);
        addChickenlikeRender(EntityType.BEE,event);
        addRender(EntityType.SQUID,-8,8,-7,0,0,0,event);
        addRender(EntityType.GLOW_SQUID,-8,8,-7,0,0,0,event);
        addRender(EntityType.CAVE_SPIDER,-8,21,18,130,0,0,event);
        addPiglikeRender(EntityType.SHULKER,event);
        addHumanlikeRender(EntityType.VEX,event);
        addRender(EntityType.POLAR_BEAR,-8,15,15,130,0,0,event);
        addRender(EntityType.IRON_GOLEM,-8,11,-7,40,0,0,event);
        addRender(EntityType.BLAZE,-8,-1,-7,0,0,0,event);
        addRender(EntityType.TURTLE,-8,27,15,130,0,0,event);
        addRender(EntityType.SPIDER,-8,21,18,130,0,0,event);
        addRender(EntityType.STRIDER,-8,12,12,130,0,0,event);
        addRender(EntityType.ENDERMAN,-8,1,-7,40,0,0,event);
        addRender(EntityType.MAGMA_CUBE,-8,20,8,180,0,0,event);
        addRender(EntityType.SLIME,-8,20,8,180,0,0,event);
        addRender(EntityType.WOLF,-8,21,12,130,0,0,event);
        addRender(EntityType.SNOW_GOLEM,-8,16,-4,40,0,0,event);
        addCatRender(EntityType.OCELOT,event);
        addCatRender(EntityType.CAT,event);
        addFishRender(EntityType.SALMON,event);
        addFishRender(EntityType.COD,event);
        addFishRender(EntityType.AXOLOTL,event);
        addSmallFishRender(EntityType.TROPICAL_FISH,event);
        addSmallFishRender(EntityType.DOLPHIN,event);
        addSmallFishRender(EntityType.SILVERFISH,event);
        addSmallFishRender(EntityType.ENDERMITE,event);
        addSmallFishRender(EntityType.PARROT,event);
        addSmallFishRender(EntityType.PUFFERFISH,event);
        addRender(EntityType.RABBIT,-8,26,-4,45,0,0,event);
        addRender(EntityType.PHANTOM,-8,5,-6,40,0,0,event);
        addRender(EntityType.WITHER,-8,10,-5,40,0,0,event);
        //ok above
       
        
       // addRender(EntityType.ELDER_GUARDIAN,-8,20,8,180,0,0,event);
        //addCowlikeRender(EntityType.ENDER_DRAGON,event);
        
        
        //addRender(EntityType.GUARDIAN,-8,20,8,180,0,0,event);
        
	}
	private static <T extends LivingEntity> void addHumanlikeRender(EntityType<T> t,AddLayers event) {
		addHumanlikeRender(event.getRenderer(t));
	}
	private static <T extends LivingEntity> void addHorselikeRender(EntityType<T> t,AddLayers event) {
		addLayer(event.getRenderer(t),r->new TorchRenderer<>(r,-8,12,13,130,0,0));
	}
	private static <T extends LivingEntity,M extends EntityModel<T>> void addHumanlikeRender(LivingEntityRenderer<T,M> rx) {
		addLayer(rx,r->new TorchRenderer<>(rx,-8,15,-7,40,0,0));
	}
	private static <T extends LivingEntity> void addCowlikeRender(EntityType<T> t,AddLayers event) {
		addLayer(event.getRenderer(t),r->new TorchRenderer<>(r,-8,12,13,130,0,0));
	}
	private static <T extends LivingEntity> void addPiglikeRender(EntityType<T> t,AddLayers event) {
		addLayer(event.getRenderer(t),r->new TorchRenderer<>(r,-8,18,12,130,0,0));
	}
	private static <T extends LivingEntity> void addSheeplikeRender(EntityType<T> t,AddLayers event) {
		addLayer(event.getRenderer(t),r->new TorchRenderer<>(r,-8,15,11,130,0,0));
	}
	private static <T extends LivingEntity> void addChickenlikeRender(EntityType<T> t,AddLayers event) {
		addLayer(event.getRenderer(t),r->new TorchRenderer<>(r,-8,22,8,130,0,0));
	}
	private static <T extends LivingEntity> void addCatRender(EntityType<T> t,AddLayers event) {
		addLayer(event.getRenderer(t),r->new TorchRenderer<>(r,-8,20,0,40,0,0));
	}
	private static <T extends LivingEntity> void addFishRender(EntityType<T> t,AddLayers event) {
		addLayer(event.getRenderer(t),r->new TorchRenderer<>(r,-8,26,-1,40,0,0));
	}
	private static <T extends LivingEntity> void addSmallFishRender(EntityType<T> t,AddLayers event) {
		addLayer(event.getRenderer(t),r->new TorchRenderer<>(r,-8,26,-6,40,0,0));
	}
	public static <T extends LivingEntity> void addRender(EntityType<T> t,int x,int y,int z,int rx,int ry,int rz,AddLayers event) {
		addLayer(event.getRenderer(t),r->new TorchRenderer<>(r,x,y,z,rx,ry,rz));
	}
	private static <T extends LivingEntity,M extends EntityModel<T>> void addLayer(LivingEntityRenderer<T,M> r,Function<LivingEntityRenderer<T,M>,RenderLayer<T,M>> l) {
		r.addLayer(l.apply(r));
	}
	@SuppressWarnings("unused")
	@SubscribeEvent
	public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
		Contents.Blocks.PREGISTER.getEntries().forEach(registryObject -> 
		Minecraft.getInstance().particleEngine.register((SimpleParticleType) registryObject.get(),
                FlameParticle.Provider::new));
	}
}
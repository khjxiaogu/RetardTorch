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

package com.khjxiaogu.rtorch;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Contents {


	public static class Blocks {
		public static final DeferredRegister<Block> BREGISTER = DeferredRegister
				.create(ForgeRegistries.BLOCKS, Main.MODID);
		public static final DeferredRegister<Item> IREGISTER = DeferredRegister
				.create(ForgeRegistries.ITEMS, Main.MODID);
		public static final DeferredRegister<ParticleType<?>> PREGISTER = DeferredRegister
				.create(ForgeRegistries.PARTICLE_TYPES, Main.MODID);
		public static final SimpleParticleType BatParticle = new SimpleParticleType(false);
		static{
			PREGISTER.register("bat",BatParticle.delegate);
		}
		public static final RegistryObject<TorchBlock> torch=BREGISTER.register("torchrino",()->new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50886_) -> {
		      return 14;
		   }).sound(SoundType.WOOD),BatParticle));
		public static final RegistryObject<WallTorchBlock> wall_torch=BREGISTER.register("wall_torchrino",()->new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50886_) -> {
		      return 14;
		   }).sound(SoundType.WOOD),BatParticle));
		public static final RegistryObject<StandingAndWallBlockItem> item=IREGISTER.register("torchrino",()->new StandingAndWallBlockItem(torch.get(),wall_torch.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
		
		public static void init() {
			
		
		}

	}

}

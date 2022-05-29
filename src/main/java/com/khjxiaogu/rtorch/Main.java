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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Main.MODID)
public class Main {

	public static final String MODID = "rtorch";
	public static final String MODNAME = "RetardTorch";
	public static final Logger logger = LogManager.getLogger(MODNAME);
	public static ResourceLocation rl(String path) {
		return new ResourceLocation(MODID, path);
	}

	public Main() {
		IEventBus mod = FMLJavaModLoadingContext.get().getModEventBus();
		mod.addListener(this::processIMC);
		mod.addListener(this::enqueueIMC);
		Contents.Blocks.BREGISTER.register(mod);
		Contents.Blocks.IREGISTER.register(mod);
		Contents.Blocks.PREGISTER.register(mod);
	}



	private void enqueueIMC(final InterModEnqueueEvent event) {
	}

	private void processIMC(final InterModProcessEvent event) {

	}
}

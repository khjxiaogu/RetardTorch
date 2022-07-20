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

package com.khjxiaogu.rtorch.events;

import com.khjxiaogu.rtorch.Contents;
import com.khjxiaogu.rtorch.Main;
import com.khjxiaogu.rtorch.RTCacheAccess;
import com.khjxiaogu.rtorch.RTDefaultCache;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {

	@SubscribeEvent
	public static void onAttack(LivingAttackEvent event) {
		if(event.getSource().getEntity() instanceof LivingEntity) {
			LivingEntity l=(LivingEntity) event.getSource().getEntity();
			Item i=l.getMainHandItem().getItem();
			if(i==Contents.Blocks.item.get())
				if(l.getItemBySlot(EquipmentSlot.LEGS).isEmpty()) {
					if(event.getEntityLiving() instanceof Mob) {
						Mob m=(Mob) event.getEntityLiving();
						m.setGuaranteedDrop(EquipmentSlot.LEGS);
						m.setPersistenceRequired();
						
					}
						
					event.getEntityLiving().setItemSlot(EquipmentSlot.LEGS,new ItemStack(i));
					l.getMainHandItem().shrink(1);
				}
		}
	}
    public static void register(RegisterCapabilitiesEvent event) {
        event.register(RTCacheAccess.class);
    }
    public static void register(AttachCapabilitiesEvent<Level> event) {
        event.addCapability(RTDefaultCache.ID,new RTDefaultCache());
    }
    public static void tick(TickEvent.WorldTickEvent event) {
    	if(event.phase==TickEvent.Phase.START&&event.side==LogicalSide.SERVER&&event.world instanceof ServerLevel) {
    		if(event.world.getGameTime()%100==0)
    			RTDefaultCache.tickLevel((ServerLevel) event.world);
    	}
    }
}

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

package com.khjxiaogu.rtorch.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.khjxiaogu.rtorch.Contents;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity{	
	protected PlayerMixin(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
		super(p_20966_, p_20967_);
	}

	@Inject(at = @At("HEAD"),
			method = "tick",
			remap = true,
			cancellable=true,
			require=1,
			allow=1)
	public void RT$tick(CallbackInfo cb) {
		
		if(super.getLevel().getGameTime()%2==0)
			if(getItemBySlot(EquipmentSlot.LEGS).getItem().equals(Contents.Blocks.item.get())) {
				MobEffectInstance dslow=super.getEffect(MobEffects.DIG_SLOWDOWN);
				if(dslow==null||dslow.getDuration()<10) {
					super.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,10,5));
				}
					
				cb.cancel();
			}
	}


}

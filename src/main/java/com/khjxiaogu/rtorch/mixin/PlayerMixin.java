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
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

@Mixin({Player.class,Mob.class,
	Bat.class,Bee.class,Cat.class,Dolphin.class,Fox.class,Panda.class,PolarBear.class,Pufferfish.class,Wolf.class,AbstractHorse.class,AbstractSchoolingFish.class,
	Creeper.class,Endermite.class,Phantom.class,Shulker.class,Silverfish.class,Skeleton.class,Slime.class,Spider.class,Strider.class,Vex.class,Zombie.class,ZombieVillager.class})
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

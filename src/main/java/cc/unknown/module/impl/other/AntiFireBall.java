package cc.unknown.module.impl.other;

import org.apache.commons.lang3.RandomUtils;

import cc.unknown.event.impl.EventLink;
import cc.unknown.event.impl.move.LivingEvent;
import cc.unknown.module.impl.Module;
import cc.unknown.module.impl.api.Category;
import cc.unknown.module.impl.api.Register;
import cc.unknown.module.setting.impl.BooleanValue;
import cc.unknown.module.setting.impl.DoubleSliderValue;
import cc.unknown.module.setting.impl.SliderValue;
import cc.unknown.utils.player.RotationUtils;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFireball;

@Register(name = "AntiFireBall", category = Category.Other)
public class AntiFireBall extends Module {
	
	private DoubleSliderValue speed = new DoubleSliderValue("Rotation Speed", 98, 98, 1, 180, 1);
	private SliderValue range = new SliderValue("Range", 6.0, 1.0, 6.0, 0.01);
	private BooleanValue gcd = new BooleanValue("Gcd", false);
	
	public AntiFireBall() {
		this.registerSetting(speed, range, gcd);
	}

	@EventLink
	public void onUpdate(LivingEvent event) {
		for (Entity entity : mc.theWorld.loadedEntityList) {
			if (entity instanceof EntityFireball) {
				EntityFireball fire = (EntityFireball) entity;
				if (mc.thePlayer.getDistanceToEntity(fire) < range.getInput()) {
					RotationUtils.setTargetRotation(RotationUtils.limitAngleChange(RotationUtils.getServerRotation(),
							RotationUtils.getRotations(fire), RandomUtils.nextFloat(speed.getInputMinToFloat(), speed.getInputMaxToFloat())));
					
					if (gcd.isToggled()) {
						RotationUtils.targetRotation.fixedSensitivity(mc.gameSettings.mouseSensitivity);
					}

					KeyBinding.onTick(mc.gameSettings.keyBindAttack.getKeyCode());
				}
			}
		}
	}
}

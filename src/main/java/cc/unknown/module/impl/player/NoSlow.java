package cc.unknown.module.impl.player;

import cc.unknown.event.impl.EventLink;
import cc.unknown.event.impl.network.PacketEvent;
import cc.unknown.event.impl.network.PacketEvent.Type;
import cc.unknown.module.Module;
import cc.unknown.module.impl.Category;
import cc.unknown.module.impl.api.Register;
import cc.unknown.module.setting.impl.ModeValue;
import cc.unknown.module.setting.impl.SliderValue;
import net.minecraft.item.ItemBow;
import net.minecraft.network.Packet;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;

@Register(name = "NoSlow", category = Category.Player)
public class NoSlow extends Module {
	public ModeValue mode = new ModeValue("Mode", "Grim", "Grim", "C16", "Vanilla", "No Item Release");
	public SliderValue vForward = new SliderValue("Vanilla forward", 1.0, 0.2, 1.0, 0.1);
	public SliderValue vStrafe = new SliderValue("Vanilla strafe", 1.0, 0.2, 1.0, 0.1);

	public NoSlow() {
		this.registerSetting(mode, vForward, vStrafe);
	}

	@EventLink
	public void onPacket(PacketEvent e) {
		if (e.getType() == Type.SEND) {
			final Packet<INetHandlerPlayServer> p = (Packet<INetHandlerPlayServer>) e.getPacket();
			if (mode.is("No Item Release")) {
				if (p instanceof C07PacketPlayerDigging) {
					C07PacketPlayerDigging wrapper = (C07PacketPlayerDigging) p;
					if (wrapper.getStatus() == C07PacketPlayerDigging.Action.RELEASE_USE_ITEM) {
						if (!(mc.thePlayer.getHeldItem().getItem() instanceof ItemBow)) {
							e.setCancelled(true);
						}
					}
				}
			}
		}
	}
}

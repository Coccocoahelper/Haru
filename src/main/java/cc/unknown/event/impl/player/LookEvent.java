package cc.unknown.event.impl.player;

import cc.unknown.event.Event;

public class LookEvent extends Event {

	private float pitch, prevPitch, yaw, prevYaw;

	public LookEvent(float pitch, float yaw) {
	    this.pitch = pitch;
	    this.yaw = yaw;
    }

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public float getPrevPitch() {
		return prevPitch;
	}

	public void setPrevPitch(float prevPitch) {
		this.prevPitch = prevPitch;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public float getPrevYaw() {
		return prevYaw;
	}

	public void setPrevYaw(float prevYaw) {
		this.prevYaw = prevYaw;
	}

}
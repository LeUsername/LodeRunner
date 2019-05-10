package impl;

import service.HoleService;

public class HoleImpl implements Cloneable, HoleService {
	private int x;
	private int y;
	private Integer temps;

	public HoleImpl(int i, int j, int tps) {
		x = i;
		y = j;
		temps = tps;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Integer getTemps() {
		return temps;
	}

	public void setTemps(Integer temps) {
		this.temps = temps;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

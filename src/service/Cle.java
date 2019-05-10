package service;

public class Cle {
	public final int x;
	public final int y;

	public Cle(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Cle))
			return false;
		Cle key = (Cle) o;
		return x == key.x && y == key.y;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}
}

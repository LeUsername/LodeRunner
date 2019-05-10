package decorators;

import service.HoleService;

public class HoleDecorator implements HoleService {
	private HoleService delegate;

	public HoleDecorator(HoleService delegate) {
		this.delegate = delegate;
	}

	public HoleService getDelegate() {
		return delegate;
	}

	@Override
	public int getX() {
		return delegate.getX();
	}

	@Override
	public void setX(int x) {
		delegate.setX(x);
	}

	@Override
	public int getY() {
		return delegate.getY();
	}

	@Override
	public void setY(int y) {
		delegate.setY(y);
	}

	@Override
	public Integer getTemps() {
		return delegate.getTemps();
	}

	@Override
	public void setTemps(Integer temps) {
		delegate.setTemps(temps);
	}

}

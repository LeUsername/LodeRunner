package decorators;

import service.Cell;
import service.ScreenService;

public class ScreenDecorator implements ScreenService {
	private final ScreenService delegate;

	public ScreenDecorator(ScreenService delegate) {
		this.delegate = delegate;
	}

	protected ScreenService getDelegate() {
		return delegate;
	}

	public int getHeight() {
		return delegate.getHeight();
	}

	@Override
	public int getWidth() {
		return delegate.getWidth();
	}

	@Override
	public Cell getCellNature(int x, int y) {
		return delegate.getCellNature(x, y);
	}

	@Override
	public void init(int w, int h) {
		delegate.init(w, h);

	}

	@Override
	public void Dig(int x, int y) {
		delegate.Dig(x, y);
	}

	@Override
	public void Fill(int x, int y) {
		delegate.Fill(x, y);
	}

	@Override
	public void Lad(int x, int y) {
		delegate.Lad(x, y);
		
	}

}

package decorators;

import java.util.HashMap;

import impl.CellContentImpl;
import service.Cell;
import service.Cle;
import service.EditableScreenService;
import service.EnvironmentService;

public class EnvironmentDecorator implements EnvironmentService{
	protected final EnvironmentService delegate;

	public EnvironmentDecorator(EnvironmentService delegate) {
		this.delegate = delegate;
	}

	@Override
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
	public void Dig(int x, int y) {
		delegate.Dig(x, y);
	}

	@Override
	public void Fill(int x, int y) {
		delegate.Fill(x, y);
	}

	@Override
	public CellContentImpl getCellContent(int x, int y) {
		return delegate.getCellContent(x, y);
	}

	@Override
	public void init(int h, int w) {
		delegate.init(h, w);
	}

	@Override
	public void init(EditableScreenService e, int h, int w, HashMap<Cle, CellContentImpl> cellules) {
		delegate.init(e, h, w, cellules);
	}

	@Override
	public void setCellContent(int x, int y, CellContentImpl c) {
		delegate.setCellContent(x, y, c);
	}

	@Override
	public HashMap<Cle, CellContentImpl> getAllCellContent() {
		return delegate.getAllCellContent();
	}

	@Override
	public void Lad(int x, int y) {
delegate.Lad(x, y);
	}

}

package impl;

import java.util.ArrayList;
import java.util.HashMap;

import service.Cell;
import service.Cle;
import service.EditableScreenService;
import service.EnvironmentService;

public class EnvironmentImpl extends ScreenImpl implements EnvironmentService {
	protected HashMap<Cle, CellContentImpl> cellulesContent = new HashMap<>();
	private EditableScreenService e;

	@Override
	public void init(EditableScreenService e, int w, int h, HashMap<Cle, CellContentImpl> cellulesContent) {
		super.init(w, h);
		for (int i = 0; i < e.getWidth(); i++) {
			for (int j = 0; j < e.getHeight(); j++) {
				carte.put(new Cle(i, j), e.getCellNature(i, j));
			}
		}
		// for (int y = height - 1; y >= 0; y--) {
		// for (int x = 0; x < width; x++) {
		// switch (carte.get((new Cle(x, y)))) {
		// case EMP:
		// System.out.print(" ");
		// break;
		// case LAD:
		// System.out.print("H");
		// break;
		// case HOL:
		// System.out.print("U");
		// break;
		// case PLT:
		// System.out.print("-");
		// break;
		// case HDR:
		// System.out.print("T");
		// break;
		// case MTL:
		// System.out.print("M");
		// break;
		// }
		// }
		// System.out.print("\n");
		// }
		if(cellulesContent == null)
			this.cellulesContent = new HashMap<Cle, CellContentImpl>();
		else
			this.cellulesContent = cellulesContent;
		for (int i = 0; i < e.getWidth(); i++) {
			for (int j = 0; j < e.getHeight(); j++) {
				Cle courante = new Cle(i, j);
				if (this.cellulesContent.get(courante) == null) {
					this.cellulesContent.put(courante, new CellContentImpl(new ArrayList<>(), new ArrayList<>()));
				}
			}
		}
		this.e = e;
	}

	@Override
	public void init(int w, int h) {
		super.init(w, h);
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				carte.put(new Cle(i, j), Cell.EMP);
			}
		}
		this.e = null;
	}

	@Override
	public CellContentImpl getCellContent(int x, int y) {
		return cellulesContent.get(new Cle(x, y));
	}

	@Override
	public void setCellContent(int x, int y, CellContentImpl c) {
		cellulesContent.put(new Cle(x, y), c);
	}

	@Override
	public HashMap<Cle, CellContentImpl> getAllCellContent() {
		return cellulesContent;
	}

	public EditableScreenService getEditableScreen() {
		return e;
	}
}

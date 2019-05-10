package decorators;

import java.util.ArrayList;

import impl.ItemImpl;
import service.CellContentService;
import service.CharacterService;

public class CellContentDecorator implements CellContentService {
	private CellContentService delegate;

	public CellContentDecorator(CellContentService delegate) {
		this.delegate = delegate;
	}

	public CellContentService getDelegate() {
		return delegate;
	}

	@Override
	public ArrayList<CharacterService> getCharacter() {
		return delegate.getCharacter();
	}

	@Override
	public ArrayList<ItemImpl> getItem() {
		return delegate.getItem();
	}

}

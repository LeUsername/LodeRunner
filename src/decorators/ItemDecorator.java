package decorators;

import service.ItemService;
import service.ItemType;

public class ItemDecorator implements ItemService {
	private ItemService delegate;

	public ItemDecorator(ItemService delegate) {
		this.delegate = delegate;
	}

	@Override
	public int getId() {
		return delegate.getId();
	}

	@Override
	public ItemType getType() {
		return delegate.getType();
	}

	@Override
	public int getWdt() {
		return delegate.getWdt();
	}

	@Override
	public int getHgt() {
		return delegate.getHgt();
	}

}

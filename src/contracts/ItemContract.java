package contracts;

import decorators.ItemDecorator;
import service.ItemService;

public class ItemContract extends ItemDecorator {

	public ItemContract(ItemService delegate) {
		super(delegate);
	}

}

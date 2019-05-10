package contracts;

import decorators.CellContentDecorator;
import service.CellContentService;

public class CellContentContract extends CellContentDecorator {

	public CellContentContract(CellContentService delegate) {
		super(delegate);
	}
}

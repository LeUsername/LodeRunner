package contracts;

import decorators.HoleDecorator;
import service.HoleService;

public class HoleContract extends HoleDecorator {

	public HoleContract(HoleService delegate) {
		super(delegate);
	}

	@Override
	public void setX(int x) {
	}

	@Override
	public void setY(int y) {
	}

	@Override
	public void setTemps(Integer temps) {
	}
}

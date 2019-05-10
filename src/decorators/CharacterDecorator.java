package decorators;

import service.CharacterService;
import service.EnvironmentService;

public class CharacterDecorator implements CharacterService {
	protected CharacterService delegate;

	public CharacterDecorator(CharacterService cs) {
		this.delegate = cs;
	}
	
	public CharacterService getDelegate() {
		return delegate;
	}

	@Override
	public EnvironmentService getEnvi() {
		return delegate.getEnvi();
	}

	@Override
	public int getHgt() {

		return delegate.getHgt();
	}

	@Override
	public int getWdt() {

		return delegate.getWdt();
	}

	@Override
	public void init(int w, int h, EnvironmentService envi) {
		delegate.init(w, h, envi);

	}

	@Override
	public void GoLeft() {
		delegate.GoLeft();

	}

	@Override
	public void GoUp() {
		delegate.GoUp();
	}

	@Override
	public void GoRight() {
		delegate.GoRight();
	}

	@Override
	public void GoDown() {
		delegate.GoDown();
	}

}

package decorators;

import service.EngineService;
import service.EnvironmentService;
import service.PlayerService;

public class PlayerDecorator extends CharacterDecorator implements PlayerService {

	public PlayerDecorator(PlayerService cs) {
		super(cs);
	}

	@Override
	public EngineService getEngine() {
		return ((PlayerService)delegate).getEngine();
	}

	@Override
	public void step() {
		((PlayerService)delegate).step();
	}

	@Override
	public EnvironmentService getEnvi() {
		return ((PlayerService)delegate).getEnvi();
	}

	@Override
	public int getHgt() {
		return ((PlayerService)delegate).getHgt();
	}

	@Override
	public int getWdt() {
		return ((PlayerService)delegate).getWdt();
	}

	@Override
	public void GoLeft() {
		((PlayerService)delegate).GoLeft();
	}

	@Override
	public void GoUp() {
		((PlayerService)delegate).GoUp();
	}

	@Override
	public void GoRight() {
		((PlayerService)delegate).GoRight();
	}

	@Override
	public void GoDown() {
		((PlayerService)delegate).GoDown();
	}

	@Override
	public void init(int w, int h, EnvironmentService envi, EngineService e) {
		((PlayerService)delegate).init(w, h, envi, e);

	}

	@Override
	public void init(int w, int h, EnvironmentService envi) {
		((PlayerService)delegate).init(w, h, envi);

	}

//	@Override
//	public int getScore() {
//		return ((PlayerService)delegate).getScore();
//	}

}

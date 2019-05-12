package decorators;

import service.CharacterService;
import service.EnvironmentService;
import service.GuardService;
import service.Move;

public class GuardDecorator extends CharacterDecorator implements GuardService {

	public GuardDecorator(GuardService cs) {
		super(cs);
	}

	@Override
	public int getId() {
		return ((GuardService) delegate).getId();
	}

	@Override
	public CharacterService getTarget() {
		return ((GuardService) delegate).getTarget();
	}

	@Override
	public Move getBehaviour() {
		return ((GuardService) delegate).getBehaviour();
	}

	@Override
	public int getTimeInHole() {
		return ((GuardService) delegate).getTimeInHole();
	}

	@Override
	public void climbLeft() {
		((GuardService) delegate).climbLeft();
	}

	@Override
	public void climbRight() {
		((GuardService) delegate).climbRight();
	}

	@Override
	public void step() {
		((GuardService) delegate).step();
	}

	@Override
	public void init(int w, int h, EnvironmentService envi, CharacterService target, int x, int y) {
		((GuardService) delegate).init(w, h, envi, target,x,y);
	}

	@Override
	public int getXPos() {
		return ((GuardService) delegate).getXPos();
	}

	@Override
	public int getYPos() {
		return ((GuardService) delegate).getYPos();
	}

	@Override
	public boolean Alive() {
		return ((GuardService) delegate).Alive();	}

	@Override
	public void Dead() {
		
		((GuardService) delegate).Dead();
	}

}

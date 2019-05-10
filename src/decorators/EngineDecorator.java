package decorators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import impl.HoleImpl;
import impl.ItemImpl;
import service.Cle;
import service.Command;
import service.EditableScreenService;
import service.EngineService;
import service.EnvironmentService;
import service.GuardService;
import service.PlayerService;
import service.Status;

public class EngineDecorator implements EngineService {
	protected final EngineService delegate;

	public EngineDecorator(EngineService delegate) {
		this.delegate = delegate;
	}

	@Override
	public EnvironmentService getEnvironment() {
		return delegate.getEnvironment();
	}

	@Override
	public PlayerService getPlayer() {
		return delegate.getPlayer();
	}

	@Override
	public ArrayList<GuardService> getGuards() {
		return delegate.getGuards();
	}

	@Override
	public HashMap<Cle, ItemImpl> getTreasures() {
		return delegate.getTreasures();
	}

	@Override
	public Status getStatus() {
		return delegate.getStatus();
	}

	@Override
	public Command getNextCommand() {
		return delegate.getNextCommand();
	}

	@Override
	public void init(ArrayList<EditableScreenService> e) {
		delegate.init(e);
	}

	@Override
	public void step(Scanner scan) {
		delegate.step(scan);
	}

	@Override
	public ArrayList<HoleImpl> getHoles() {
		return delegate.getHoles();
	}

	@Override
	public int getLife() {
		return delegate.getLife();
	}

	@Override
	public void Loose() {
		delegate.Loose();
		
	}

	@Override
	public void Winning() {
		delegate.Winning();
		
	}

	@Override
	public int GetLevel() {
		
		return delegate.GetLevel();
	}

	@Override
	public int Nb_LevelMax() {
		return delegate.Nb_LevelMax();
	}

}

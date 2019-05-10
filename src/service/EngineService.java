package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import impl.HoleImpl;
import impl.ItemImpl;


public interface EngineService {
	/* Observators */

	// const
	public EnvironmentService getEnvironment();

	// const
	public PlayerService getPlayer();
	public int getLife();
	public void Loose();
	public ArrayList<GuardService> getGuards();

	public HashMap<Cle, ItemImpl> getTreasures();

	public Status getStatus();

	public Command getNextCommand();
		
	public ArrayList<HoleImpl> getHoles();

	public void init(ArrayList<EditableScreenService> e);
	public void Winning();
	public int GetLevel();
	public int Nb_LevelMax();
	
	/* Operator */
	// post: (\exists Tresor t \in get(getTreasures(E)@pre,getWdt(getPlayer(E)),getHgt(getPlayer(E)))) => t \not \in get(getTreasures(E),getWdt(getPlayer(E)),getHgt(getPlayer(E)))
	// post: (\forall Hole h \in getHoles(E) \and getTemps(h)@pre<14 ) => (getTemps(h)@pre+1==getTemps(h) \and h \in getHoles(E))
	// post: (\forall Hole h \in getHoles(E)@pre (getTemps(h)@pre==14) => (\not \exists h \in getHoles(E)) \and )
	// post: \forall Guard g \in getGuards(E)@pre \exists Hole h \in getHoles(E)@pre (getTemps(h)==14 \and getX(h) == getWdt(g) \and  getY(h) == getHgt(g))
	//			=> \not exists g \in getGuards(E)
	public void step(Scanner scan);

	/* Invariants */
	// (\forall Guard g \in getGuards(E) g \in getCharacters(getCellContent(getEnvironment(E),getWdt(g),getHgt(g))))
	// (\for Player p \in getPlayer(E) p \in getCharacters(getCellContent(getEnvironment(E),getWdt(p),getHgt(p))))
	// (\forall Tresor t \in getTreasures(E) t \in getItem(getCellContent(getEnvironment(E),getWdt(t),getHgt(t))))
	// (len(getTreasures(E))==0) => getStatus(E)==WIN
	// (\forall Hole h \in getHoles(E) getCellNature(getEnvironment(E),getX(g),getY(g))==HOL)
	// getCellNature(getEnvironment(E), getWdt(getPlayer(E)), getHgt(getPlayer(E))) == PLT => getStatus(E)==LOSE
	// (\forall Guard g1 \not \exists Guard g2 \in getGuards(E) GuardService::getId(g1)==GuardService::getId(g2) \and g1!=g2)
	
}

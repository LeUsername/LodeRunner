package service;

public interface PlayerService extends CharacterService {
	/* Observators */
	public EngineService getEngine();
	
//	public int getScore();
	/* Constructor */
	public void init(int h, int w, EnvironmentService envi, EngineService e);

	/* Invariants */

	/* Operators */
	/**on ne peut pas creuser un trou à droite dans un trou qui va se reboucher
	 * 
	 */
	// post: getNextCommand(getEngine(C))@pre==DIGR
	//			\and \exists Hole h \in getHoles(getEngine(C))@pre
	//			\and getTemps(h)==14 \and getX(h)==getWdt(C)+1 \and getY(h)==getHgt(C)-1
	//			=> (Environment::getCellNature(getEnvi(C), getWdt(C)+1, getHgt(C)-1)==PLT
	//				\and \not \exists Hole h \in getHoles(getEngine(C)) \and getX(h)==getWdt(C)+1 \and getY(h)==getHgt(C)-1
	/**on ne peut pas creuser un trou à gauche dans un trou qui va se reboucher
	 * 
	 */
	// post: getNextCommand(getEngine(C))@pre==DIGL 
	//			\and \exists Hole h \in getHoles(getEngine(C))@pre
	//			\and getTemps(h)==14 \and getX(h)==getWdt(C)-1 \and getY(h)==getHgt(C)-1
	//			=> (Environment::getCellNature(getEnvi(C), getWdt(C)-1, getHgt(C)-1)==PLT
	//				\and \not \exists Hole h \in getHoles(getEngine(C)) \and getX(h)==getWdt(C)-1 \and getY(h)==getHgt(C)-1
	/**
	 * Si la commande suivante est DIGL et que la case en-dessous n'est pas libre
	 * et que la case à gauche est libre
	 * et la case en bas à gauche est PLT alors cette case est creusée
	 */
	// post: getNextCommand(getEngine(C))@pre==DIGL 
	//			\and (Environment::getCellNature(getEnvi(C)@pre, getWdt(C), getHgt(C)-1) \in {LAD, PLT, MTL}
	//			\or \exists Character c in Environment::getCellNature(getEnvi(C)@pre, getWdt(C), getHgt(C)-1))
	//			\and Environment::getCellNature(getEnvi(C)@pre, getWdt(C)-1, getHgt(C)) \in {EMP, LAD, HDR, HOL}
	//			\and Environment::getCellNature(getEnvi(C)@pre, getWdt(C)-1, getHgt(C)-1)==PLT
	//			=> Environment::getCellNature(getEnvi(C), getWdt(C)-1, getHgt(C)-1)==HOL
	/**
	 * Si la commande suivante est DIGL et qu'elle est possible, alors getHoles va contenir un nouveau trou de coordonnées
	 * x,y et de temps 0
	 */
	// post: getNextCommand(getEngine(C))@pre==DIGL 
	//			\and (Environment::getCellNature(getEnvi(C)@pre, getWdt(C), getHgt(C)-1) \in {LAD, PLT, MTL}
	//			\or \exists Character c in Environment::getCellNature(getEnvi(C)@pre, getWdt(C), getHgt(C)-1))
	//			\and Environment::getCellNature(getEnvi(C)@pre, getWdt(C)-1, getHgt(C)) \in {EMP, LAD, HDR, HOL}
	//			\and Environment::getCellNature(getEnvi(C)@pre, getWdt(C)-1, getHgt(C)-1)==PLT
	//			=> Engine::getCellHoles(getEngine(C))==(Engine::getCellHoles(getEngine(C))@pre \\union Hole(getWdt(C)-1, getHgt(C)-1, 0)) 
	/**
	 * Si la commande suivante est DIGR et que la case en-dessous n'est pas libre
	 * et que la case à droite est libre
	 * et la case en bas à droite est PLT alors cette case est creusée
	 */
	// post: getNextCommand(getEngine(C))@pre==DIGR 
	//			\and (Environment::getCellNature(getEnvi(C)@pre, getWdt(C), getHgt(C)-1) \in {LAD, PLT, MTL}
	//			\or \exists Character c in Environment::getCellNature(getEnvi(C)@pre, getWdt(C), getHgt(C)-1))
	//			\and Environment::getCellNature(getEnvi(C)@pre, getWdt(C)+1, getHgt(C)) \in {EMP, LAD, HDR, HOL}
	//			\and Environment::getCellNature(getEnvi(C)@pre, getWdt(C)+1, getHgt(C)-1)==PLT
	//			=> Environment::getCellNature(getEnvi(C), getWdt(C)+1, getHgt(C)-1)==HOL
	/**
	 * Si la commande suivante est DIGR et qu'elle est possible, alors getHoles va contenir un nouveau trou de coordonnées
	 * x,y et de temps 0
	 */
	// post: getNextCommand(getEngine(C))@pre==DIGR 
	//			\and (Environment::getCellNature(getEnvi(C)@pre, getWdt(C), getHgt(C)-1) \in {LAD, PLT, MTL}
	//			\or \exists Character c in Environment::getCellNature(getEnvi(C)@pre, getWdt(C), getHgt(C)-1))
	//			\and Environment::getCellNature(getEnvi(C)@pre, getWdt(C)+1, getHgt(C)) \in {EMP, LAD, HDR, HOL}
	//			\and Environment::getCellNature(getEnvi(C)@pre, getWdt(C)+1, getHgt(C)-1)==PLT
	//			=> Engine::getCellHoles(getEngine(C))==(Engine::getCellHoles(getEngine(C))@pre \\union Hole(getWdt(C)+1, getHgt(C)-1, 0))
	public void step();

}

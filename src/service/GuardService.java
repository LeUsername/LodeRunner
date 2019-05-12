package service;

public interface GuardService extends CharacterService {
	/* Observators */
	// const
	public int getId();

	public CharacterService getTarget();

	public Move getBehaviour();

	public int getTimeInHole();

	public int getXPos();
	public int getYPos();
	public boolean Alive();
	public void Dead();
	/* Invariants */
	/**
	 * Si le garde est sur LAD et que la cible est au-dessus, la commande du garde est d'aller vers le haut
	 */
	// ((getCellNature(getEnvi(G),getWdt(G),getHgt(G)==LAD)) \and (getHgt(getTarget(G)) >= getHgt(G))) => getBehaviour()==UP
	/**
	 * Si le garde est sur LAD et que la cible est strictement en-dessous, la commande du garde est d'aller vers le bas
	 */
	// ((getCellNature(getEnvi(G),getWdt(G),getHgt(G)==LAD)) \and (getHgt(getTarget(G)) < getHgt(G))) => getBehaviour()==DOWN
	/**
	 * Si le garde est sur HOL/HDR ou au-dessus d'une case non-libre, sa commande est :																												 - 
	 */
	/**
	 * - LEFT si la cible est strictement à gauche
	 */
	// ((getCellNature(getEnvi(G),getWdt(G),getHgt(G)) \in {HOL/HDR}
	//   \or (getCellNature(getEnvi(G),getWdt(G),getHgt(G)-1)==EMP 
	//   \or \not \exists Character c \in getCharacters(getCellContent(getEnvi(G),getWdt(G),getHgt(G)-1)) 
	//   \and (getWdt(getTarget(G)) < getWdt(G)))) => getBehaviour()==LEFT
	/**
	 * - RIGHT si la cible est strictement à droite
	 */
	// ((getCellNature(getEnvi(G),getWdt(G),getHgt(G)) \in {HOL/HDR}
	//   \or (getCellNature(getEnvi(G),getWdt(G),getHgt(G)-1)==EMP 
	//   \or \not \exists Character c \in getCharacters(getCellContent(getEnvi(G),getWdt(G),getHgt(G)-1)) 
	//   \and (getWdt(getTarget(G)) > getWdt(G)))) => getBehaviour()==RIGHT
	/**
	 * - NEUTRAL sinon
	 */
	// ((getCellNature(getEnvi(G),getWdt(G),getHgt(G)) \in {HOL/HDR}
	//   \or (getCellNature(getEnvi(G),getWdt(G),getHgt(G)-1)==EMP 
	//   \or \not \exists Character c \in getCharacters(getCellContent(getEnvi(G),getWdt(G),getHgt(G)-1)) 
	//   \and (getWdt(getTarget(G)) == getWdt(G)))) => getBehaviour()==NEUTRAL
	
	// Dmin(x,y)= x if x>y else y
	
	/**
	 * Si le garde est sur LAD et que la case en-dessous est non-libre et que la cible est à droite alors la commande est RIGHT
	 */
	// ((getCellNature(getEnvi(G),getWdt(G),getHgt(G)) == LAD) 
	//   \and ((getCellNature(getEnvi(G),getWdt(G),getHgt(G)-1) \not \in {EMP,LAD,HDR,HOL} 
	//   \or \exists Character c \in getCharacter(getCellContent(getEnvi(G),getWdt(G),getHgt(G)-1)))))
	//   \and Dmin(|getWdt(G)-getWdt(getTarget(G))|, |getHgt(G)-getHgt(getTarget(G))|)=|getWdt(G)-getWdt(getTarget(G))| \and (getWdt(getTarget(G)) > getWdt(G)))) => getBehaviour()==RIGHT
	/**
	 * Si le garde est sur LAD et que la case en-dessous est non-libre et que la cible est à droite alors la commande est LEFT
	 */
	// ((getCellNature(getEnvi(G),getWdt(G),getHgt(G)) == LAD) 
	//   \and ((getCellNature(getEnvi(G),getWdt(G),getHgt(G)-1) \not \in {EMP,LAD,HDR,HOL} 
	//   \or \exists Character c \in getCharacter(getCellContent(getEnvi(G),getWdt(G),getHgt(G)-1)))))
	//   \and Dmin(|getWdt(G)-getWdt(getTarget(G))|, |getHgt(G)-getHgt(getTarget(G))|)=|getWdt(G)-getWdt(getTarget(G))| \and (getWdt(getTarget(G)) <= getWdt(G)))) => getBehaviour()==LEFT
	/**
	 * Si le garde est sur LAD et que la case en-dessous est non-libre et que la cible est à droite alors la commande est UP
	 */
	// ((getCellNature(getEnvi(G),getWdt(G),getHgt(G)) == LAD) 
	//   \and ((getCellNature(getEnvi(G),getWdt(G),getHgt(G)-1) \not \in {EMP,LAD,HDR,HOL} 
	//   \or \exists Character c \in getCharacter(getCellContent(getEnvi(G),getWdt(G),getHgt(G)-1)))))
	//   \and Dmin(|getWdt(G)-getWdt(getTarget(G))|, |getHgt(G)-getHgt(getTarget(G))|)=|getHgt(G)-getHgt(getTarget(G))| \and (getHgt(getTarget(G)) > getHgt(G))) => getBehaviour()==UP
	/**
	 * Si le garde est sur LAD et que la case en-dessous est non-libre et que la cible est à droite alors la commande est DOWN
	 */
	// ((getCellNature(getEnvi(G),getWdt(G),getHgt(G)) == LAD) 
	//   \and ((getCellNature(getEnvi(G),getWdt(G),getHgt(G)-1) \not \in {EMP,LAD,HDR,HOL} 
	//   \or \exists Character c \in getCharacter(getCellContent(getEnvi(G),getWdt(G),getHgt(G)-1)))))
	//   \and Dmin(|getWdt(G)-getWdt(getTarget(G))|, |getHgt(G)-getHgt(getTarget(G))|)=|getHgt(G)-getHgt(getTarget(G))| \and (getHgt(getTarget(G)) < getHgt(G))) => getBehaviour()==DOWN
	
	/* Constructor */
	public void init(int w, int h, EnvironmentService envi, CharacterService target,int x , int y);
	
	/* Operators */
	
	// pre: Environment::CellNature(getEnvi(G), getWdt(G), getHgt(G))==HOL
	// post: getWdt(G) == getWdt(G)@pre-1 \and getHgt(G) == getHgt(G)@pre+1
	public void climbLeft();

	// pre: Environment::CellNature(getEnvi(G), getWdt(G), getHgt(G))==HOL
	// post: getWdt(G) == getWdt(G)@pre+1 \and getHgt(G) == getHgt(G)@pre+1
	public void climbRight();

	// post: Environment::getCellNature(getEnvi(G), getWdt(G), getHgt(G))==HOL
	//		\and getTimeInHole(G)@pre<Garde::TIMEBEFOREOUT 
	//		=> getTimeInHole(G)==getTimeInHole(G)@pre+1
	// post: Environment::getCellNature(getEnvi(G), getWdt(G), getHgt(G))==HOL
	//		\and getTimeInHole(G)==Garde::TIMEBEFOREOUT 
	//		\and getBehaviour(G)==LEFT 
	//		=> ClimbLeft(G)
	// post: Environment::getCellNature(getEnvi(G), getWdt(G), getHgt(G))==HOL
	//		\and getTimeInHole(G)==Garde::TIMEBEFOREOUT 
	//		\and getBehaviour(G)==Right
	//		=> ClimbRight(G)
	// post: \not Environment::getCellNature(getEnvi(G), getWdt(G), getHgt(G))==HOL \and ((Environment::getCellNature(getEnvi(C), getWdt(C), getHgt(C)@pre) \not \in {LAD,HDR}
	// 			\and Environment::getCellNature(getEnvi(C), getWdt(C), getHgt(C)@pre-1) \not \in {PLT,MTL,LAD}
	//			\and \not \exists Character c \in Environment::getCellContent(getEnvi(C), getWdt(C), getHgt(C)@pre-1))
	//			\and getBehaviour(G)==RIGHT
	//			=> GoRight(G)
	// post: \not Environment::getCellNature(getEnvi(G), getWdt(G), getHgt(G))==HOL \and ((Environment::getCellNature(getEnvi(C), getWdt(C), getHgt(C)@pre) \not \in {LAD,HDR}
	// 			\and Environment::getCellNature(getEnvi(C), getWdt(C), getHgt(C)@pre-1) \not \in {PLT,MTL,LAD}
	//			\and \not \exists Character c \in Environment::getCellContent(getEnvi(C), getWdt(C), getHgt(C)@pre-1))
	//			\and getBehaviour(G)==UP
	//			=> GoUp(G)
	// post: \not Environment::getCellNature(getEnvi(G), getWdt(G), getHgt(G))==HOL \and ((Environment::getCellNature(getEnvi(C), getWdt(C), getHgt(C)@pre) \not \in {LAD,HDR}
	// 			\and Environment::getCellNature(getEnvi(C), getWdt(C), getHgt(C)@pre-1) \not \in {PLT,MTL,LAD}
	//			\and \not \exists Character c \in Environment::getCellContent(getEnvi(C), getWdt(C), getHgt(C)@pre-1))
	//			\and getBehaviour(G)==DOWN
	//			=> goDown(G)
	// post: \not Environment::getCellNature(getEnvi(G), getWdt(G), getHgt(G))==HOL \and ((Environment::getCellNature(getEnvi(C), getWdt(C), getHgt(C)@pre) \not \in {LAD,HDR}
	// 			\and Environment::getCellNature(getEnvi(C), getWdt(C), getHgt(C)@pre-1) \not \in {PLT,MTL,LAD}
	//			\and \not \exists Character c \in Environment::getCellContent(getEnvi(C), getWdt(C), getHgt(C)@pre-1))
	//			\and getBehaviour(G)==LEFT
	//			=> goLeft(G)
	public void step();
}

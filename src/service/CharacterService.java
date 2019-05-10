package service;

public interface CharacterService {
	/* Observators */

	// const
	public EnvironmentService getEnvi();

	public int getHgt();

	public int getWdt();

	/* Constructor */
	// pre: getCellNature(getEnvi(C),w,h)=EMP
	public void init(int w, int h, EnvironmentService envi);

	/* Invariants */
	// \exists Character x \in getCharacter(getCellContent(getEnvi(C), getWdt(C), getHgt(C))) => x = C
	
	/* Observations */ 
	/**
	 * On ne peut pas aller à gauche si on est au bout de l'écran
	 */
	// post: (getWdt(C)@pre == 0) => (getWdt(C)==getWdt(C)@pre)
	/**
	 * On ne peut pas aller à gauche si plateforme ou metal
	 */
	// post: (Environment::getCellNature(getEnvi(C)@pre, getWdt(C)@pre-1, getHgt(C)@pre) \in {MTL,PLT}) => (getWdt(C)==getWdt(C)@pre)
	/**
	 * Si on n'est pas sur une échelle/rampe et que la case desssous est traversable alors on tombe d'une case
	 */
	// post: (Environment::getCellNature(getEnvi(C)@pre, getWdt(C)@pre, getHgt(C)@pre) \not \in {LAD,HDR}
	// 			\and Environment::getCellNature(getEnvi(C), getWdt(C)@pre, getHgt(C)@pre-1) \not \in {PLT,MTL,LAD}
	//			\and \not \exists Character c \in Environment::getCellContent(getEnvi(C), getWdt(C)@pre, @pre-1)
	//			=> (getWdt(C)==getWdt(C)@pre \and getHgt(C)==getHgt(C)@pre-1)
	/**
	 * Si on n'est pas au bout de l'écran et que la case à gauche est traversable 
	 * et qu'il n'y a personne à gauche 
	 * alors on se déplace d'une case vers la gauche
	 */
	// post: getWdt(C)@pre !=0 \and Environment::getCellNature(getEnvi(C), getWdt(C)@pre-1, getHgt(C)) \not \in {MLT, PLT} 
	//		\and \not (\exists Character c \in Environment::getCellContent(getEnvi(C),getWdt(C)@pre-1,getHgt(C)))
	//		=> getWdt(C)==getWdt(C)@pre-1
	public void GoLeft();

	/**
	 * Pas de déplacement latéral quand on va vers le haut
	 */
	// post: getWdt(C)==getWdt(C)@pre
	/**
	 * On ne peut pas aller en haut si on est au bout de l'écran
	 */
	// post: (getHgt(C)@pre == Environment::getHgt(getEnvi(C)-1)) => (getHgt(C)==getHgt(C)@pre)
	/**
	 * Si la case au-dessus est occupée on ne peut pas monter
	 */
	// post: (Environment::getCellNature(getEnvi(C), getWdt(C), getHgt(C)@pre+1) \in {MTL, PLT, HDR}
	//		\or \exists Character c \in Environment::getCellContent(getEnvi(C),getWdt(C), getHdt(C)@pre+1)) => (getHgt(C)==getHgt(C)@pre)
	/**
	 * Si on n'est pas sur une échelle/rampe et que la case desssous est traversable alors on tombe d'une case
	 */
	// post: ((Environment::getCellNature(getEnvi(C), getWdt(C), getHgt(C)@pre) \not \in {LAD,HDR}
	// 			\and Environment::getCellNature(getEnvi(C), getWdt(C), getHgt(C)@pre-1) \not \in {PLT,MTL,LAD}
	//			\and \not \exists Character c \in Environment::getCellContent(getEnvi(C), getWdt(C), getHgt(C)@pre-1))
	//			=> getHgt(C)==getHgt(C)@pre-1)
	/**
	 * Si on n'est pas au bout de l'écran et (que la case en haut est une échelle ou que l'on se trouve sur une échelle et que la case au-dessus est accessible) alors on monte d'une case 
	 */
	// post: getHgt(C)@pre !=Environment::getHgt(C)-1 
	//			\and (Environment::getCellNature(getEnvi(C), getWdt(C), getHgt(C)@pre+1)==LAD
	//			\or Environment::getCellNature(getEnvi(C), getWdt(C), getHgt(C)@pre)==LAD \and Environment::getCellNature(getEnvi(C),getWdt(C),getHgt(C)@pre+1) \in {EMP, LAD})
	//				=> getHgt(C)==getHgt(C)@pre+1
	public void GoUp();

	/**
	 * Si on se trouve au bout de l'écran on ne peut pas aller à droite
	 */
	// post: (getWdt(C)@pre == Environment::getWdt(getEnvi(C)-1)) => (getWdt(C)==getWdt(C)@pre)
	/**
	 * Si on est dans trou, il n'est pas possible bouger latéralement
	 */
	// post: (Environment::getCellNature(getEnvi(C), getWdt(C)@pre, getHgt(C)@pre) \in {HOL}) => (getWdt(C)==getWdt(C)@pre)
	/**
	 * Si on n'est pas sur une échelle/rampe et que la case desssous est traversable alors on tombe d'une case
	 */
	// post: ((Environment::getCellNature(getEnvi(C), getWdt(C)@pre, getHgt(C)@pre) \not \in {LAD,HDR}
	// 			\and Environment::getCellNature(getEnvi(C), getWdt(C)@pre, getHgt(C)@pre-1) \not \in {PLT,MTL,LAD}
	//			\and \not \exists Character c \in Environment::getCellContent(getEnvi(C), getWdt(C), getHgt(C)-1))
	//			=> getHgt(C)==getHgt(C)@pre-1)
	/**
	 * Si on ne se trouve pas au bout de l'écran et que la case à droite n'est pas métal/plateforme 
	 * et qu'il n'y a personne à droite alors on va à droite
	 */
	// post: getWdt(C)@pre !=Environment::getWdt(getEnvi(C)-1) \and Environment::getCellNature(getEnvi(C), getWdt(C)+1, getHgt(C)) \not \in {MLT, PLT} 
	//			\and \not (\exists Character c \in Environment::getCellContent(getEnvi(C),getWdt(C)+1,getHgt(C)))
	//			=> getWdt(C)==getWdt(C)@pre+1
	public void GoRight();

	/**
	 * Pas de déplacement latéral quand on va vers le bas
	 */
	// post: getWdt(C)==getWdt(C)@pre
	/**
	 * Si on est au bout de l'écran on ne peut pas descendre plus
	 */
	// post: (getHgt(C)@pre == 0)) => (getHgt(C)==getHgt(C)@pre)
	/**
	 * Si la case en-dessous n'est pas traversable alors on change pas de y
	 */
	// post: (Environment::getCellNature(getEnvi(C), getWdt(C), getHgt(C)@pre-1) \in {MTL, PLT}
	//		\or \exists Character c \in Environment::getCellContent(getEnvi(C), getWdt(C), getHdt(C)@pre-1)) => (getHgt(C)==getHgt(C)@pre)
	/**
	 * Si on n'est pas sur une échelle/rampe et que la case desssous est traversable alors on tombe d'une case
	 */
	// post: ((Environment::getCellNature(getEnvi(C), getWdt(C), getHgt(C)@pre) \not \in {LAD,HDR}
	// 			\and Environment::getCellNature(getEnvi(C), getWdt(C), getHgt(C)@pre-1) \not \in {PLT,MTL,LAD}
	//			\and \not \exists Character c \in Environment::getCellContent(getEnvi(C), getWdt(C), getHgt(C)@pre-1))
	//			=> getHgt(C)==getHgt(C)@pre-1)
	/**
	 * Si on n'est pas sur le bout de l'écran et que l'on se trouve sur une case vide/rampe/échelle/trou
	 * et que la case en-dessous est vide/échelle/rampe
	 * alors le personnage descend
	 */
	// post: getHgt(C)@pre != 0 \and Environment::getCellNature(getEnvi(C), getWdt(C), getHgt(C)@pre) \in {LAD, HDR, EMP}
	//			\and Environment::getCellNature(getEnvi(C), getWdt(C), getHgt(C)@pre-1) \in {EMP,LAD,HDR,HOL}
	//			\or \exists Character c in Environment::getCellContent(getEnvi(C), getWdt(C), getHgt(C)-1)
	//				=> getHgt(C)==getHgt(C)@pre-1 \and getWdt(C)==getWdt(C)@pre
	public void GoDown();

}

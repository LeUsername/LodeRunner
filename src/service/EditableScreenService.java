package service;

import java.util.ArrayList;

public interface EditableScreenService extends ScreenService {
	/* Observators */
	public boolean isPlayable();
	public int GetxJoueur();
	public int GetyJoueur();
	public ArrayList<Cle> GetposGardes();
	public ArrayList<Cle> GetposItems();
	/* Invariants */
	// inv: isPlayable(S) == \forall(x,y) \in [0; getWidth(S)][0; getHeigth(S)]
	// getCellNature(S,x,y) != HOL \and
	// \forall x \in [0; getWidth(S)] getCellNature(S,x,0) != MTL

	/* Operators */
	// pre: 0<=y<Height(S) \and 0<=x<Width(S)
	// post: getCellNature(S,x,y)==C
	// post: \forall(x,y) \in [0; getWidth(S)][0; getHeigth(S)]
	// (x!=u \or y!=v) => getCellNature(S,x,y)==getCellNature(x,y)@pre
	public void init(int w, int h,int xJoueur, int yJoueur, ArrayList<Cle> posGardes, ArrayList<Cle> posItems);

	public void setNature(int x, int y, Cell C);

	public void init(EnvironmentService e);
}

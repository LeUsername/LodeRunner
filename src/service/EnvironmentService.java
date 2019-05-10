package service;

import java.util.HashMap;

import impl.CellContentImpl;

public interface EnvironmentService extends ScreenService {
	// Observators
	// pre: 0<=y<getHeight(S) \and 0<=x<getWidth(S)
	public CellContentImpl getCellContent(int x, int y);

	// pre: 0<=y<getHeight(S) \and 0<=x<getWidth(S)
	// post: \forall(i,j) in [0; getWidth(E)] \minus x [0; getHeight(E)] \minus y
	//			(getCellContent(S,i,j)@pre==getgetCellContent(S,i,j))
	// post: getCellContent(setCellContent(x,y, C),x,y) == C
	public void setCellContent(int x, int y, CellContentImpl c);
	
	public HashMap<Cle, CellContentImpl> getAllCellContent();
	
	// Invariants
	// \forall(x,y) \in [0; getWidth(E)][0; getHeight(E)]
	// 	\forall (c1,c2) in getCharacters(getCellContent(E,x,y)) c1==c2
	// \forall(x,y) in [0; getWidth(E)][0; getHeight(E)] getCellNature(E,x,y) in {MTL,PLR] => getCellContent(x,y)==null
	// \forall(x,y) in [0; getWidth(E)][0; getHeight(E)]
	//	\exists TRESOR t in getItem(getCellContent(E,x,y)) => getCellNature(E,x,y)==EMP and getCellNature(E,x,y-1) in {PLT,MTL}
	
	// post: \forall (x,y) in [0; getWidth(E)][0; getHeight(E)] getCellNature(E,x,y)==EditableScreen::getCellNature(e,x,y)
	public void init(EditableScreenService e, int w, int h, HashMap<Cle, CellContentImpl> cellules);
}

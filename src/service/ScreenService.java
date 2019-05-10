package service;

public interface ScreenService {

	/* Observators */
	// const
	public int getHeight();

	// const
	public int getWidth();

	// pre: 0<=y<getHeight(S) \and 0<=x<getWidth(S) 
	public Cell getCellNature(int x, int y);

	/* Constructor */
	// pre: h>0 \and w>0
	// post: getWidth()==w \and getHeight()==h
	// post: \forall (x,y) \in [0; getWidth(S)][0; getHeigth(S)] getCellNature(S,x,y)==EMP
	public void init(int w, int h);

	/* Operators */
	// pre: getCellNature(x,y)==PLT
	// post: getCellNature(S,x,y) == HOL
	// post: \forall(x,y) in [0; getWidth(S)]\minus u [0; getHeigth(S)]\minus v
	// getCellNature(Dig(S,u,v),x,y)==getCellNature(x,y)@pre
	public void Dig(int x, int y);

	// pre: getCellNature(x,y)==HOL
	// post: getCellNature(S,x,y) == PLT
	// post: \forall(x,y) \in [0; getWidth(S)]\minus u [0; getHeigth(S)]\minus v
	// (x!=u \or y!=v) => getCellNature(Fill(S,u,v),x,y)==getCellNature(x,y)@pre
	public void Fill(int x, int y);
	public void Lad(int x,int y);
}

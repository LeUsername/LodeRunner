package contracts;

import java.util.ArrayList;

import decorators.PlayerDecorator;
import error.PostCondError;
import impl.ItemImpl;
import service.Cell;
import service.Command;
import service.EnvironmentService;
import service.PlayerService;

public class PlayerContract extends PlayerDecorator {

	public PlayerContract(PlayerService delegate) {
		super(delegate);
	}

	public void checkInvariant() {
		// Pas d'invariants ?
		// Ou est-ce que ce sont les mêmes que pour character ?
	}

	public void step() {
		Command command_at_pre = getEngine().getNextCommand();
		EnvironmentService environment_at_pre = getEnvi();
		Cell cell_enbas = null;
		int c = 0;
		if (getHgt() > 0) {
			cell_enbas = environment_at_pre.getCellNature(getWdt(), getHgt() - 1);
			c = environment_at_pre.getCellContent(getWdt(), getHgt() - 1).getCharacter().size();
		}
		Cell cell_gauche = null;
		int c_gauche = 0;
		int f_gauche = 0;
		ArrayList<ItemImpl> i_gauche = new ArrayList<>();
		int nb_i_droite = 0;
		if (getWdt() > 0) {
			cell_gauche = environment_at_pre.getCellNature(getWdt() - 1, getHgt());
			c_gauche = environment_at_pre.getCellContent(getWdt() - 1, getHgt()).getCharacter().size();
			f_gauche = environment_at_pre.getCellContent(getWdt() - 1, getHgt()).getItem().size();
			i_gauche = environment_at_pre.getCellContent(getWdt() - 1, getHgt()).getItem();
			nb_i_droite = environment_at_pre.getCellContent(getWdt() - 1, getHgt()).getItem().size();
		}
		Cell cell_droite = null;
		int c_droite = 0;
		int f_droite = 0;
		int nb_i_gauche = 0;
		ArrayList<ItemImpl> i_droite =new ArrayList<>();
		if (getWdt() < getEnvi().getWidth() - 1) {
			cell_droite = environment_at_pre.getCellNature(getWdt() + 1, getHgt());
			c_droite = environment_at_pre.getCellContent(getWdt() + 1, getHgt()).getCharacter().size();
			f_droite = environment_at_pre.getCellContent(getWdt() + 1, getHgt()).getItem().size();
			nb_i_gauche = environment_at_pre.getCellContent(getWdt() + 1, getHgt()).getItem().size();
			i_droite = environment_at_pre.getCellContent(getWdt() + 1, getHgt()).getItem();
		}
		Cell cell_digL = null;
		if (getWdt() > 0 && getHgt() > 0)
			cell_digL = environment_at_pre.getCellNature(getWdt() - 1, getHgt() - 1);
		Cell cell_digR = null;
		int f = 0;
		if (getWdt() < getEnvi().getWidth() - 1 && getHgt() > 0) {
			cell_digR = environment_at_pre.getCellNature(getWdt() + 1, getHgt() - 1);
			f = environment_at_pre.getCellContent(getWdt() + 1, getHgt() - 1).getItem().size();}
		Cell cell_LadL = null;
		if (getWdt() > 0 && getHgt() < getEnvi().getHeight() - 1)
			cell_LadL = environment_at_pre.getCellNature(getWdt() - 1, getHgt() + 1);
		Cell cell_LadR = null;
		if (getWdt() < getEnvi().getWidth() - 1 && getHgt() < getEnvi().getHeight() - 1)
			cell_LadR = environment_at_pre.getCellNature(getWdt() + 1, getHgt() + 1);
		checkInvariant();
		super.step();
		checkInvariant();
		if (command_at_pre == Command.DigL) {
			if (cell_enbas == Cell.LAD || cell_enbas == Cell.MTL || cell_enbas == Cell.PLT || c > 0) {
				if (cell_gauche == Cell.EMP || cell_gauche == Cell.LAD || cell_gauche == Cell.HDR
						|| cell_gauche == Cell.HOL) {
					if (cell_digL == Cell.PLT) {
						if (!(getEnvi().getCellNature(getWdt() - 1, getHgt() - 1) == Cell.HOL)) {
							throw new PostCondError("Un trou aurait du être creusé en bas à gauche");
						}
					}
				}
			}
		}
		if (command_at_pre == Command.DigR) {
			if (cell_enbas == Cell.LAD || cell_enbas == Cell.MTL || cell_enbas == Cell.PLT || c > 0) {
				if (cell_droite == Cell.EMP || cell_droite == Cell.LAD || cell_droite == Cell.HDR
						|| cell_droite == Cell.HOL) {
					if (cell_digR == Cell.PLT) {
						if (!(getEnvi().getCellNature(getWdt() + 1, getHgt() - 1) == Cell.HOL)) {
							throw new PostCondError("Un trou aurait du être creusé en bas à droite");
						}
					}
				}
			}
		}
		if (command_at_pre == Command.FillR) {
			if (cell_enbas == Cell.LAD || cell_enbas == Cell.MTL || cell_enbas == Cell.PLT || c > 0) {
				if (f == 0) {
					if (cell_digR == Cell.HOL || cell_digR == Cell.EMP) {
						if (!(getEnvi().getCellNature(getWdt() + 1, getHgt() - 1) == Cell.PLT)) {
							throw new PostCondError("Un trou aurait du être creusé en bas à droite");
						}
					}
				}
			}
		}
		if (command_at_pre == Command.FillL) {
			if (cell_enbas == Cell.LAD || cell_enbas == Cell.MTL || cell_enbas == Cell.PLT || c > 0) {
				if (f == 0) {
					if (cell_digL == Cell.HOL || cell_digL == Cell.EMP) {
						if (!(getEnvi().getCellNature(getWdt() - 1, getHgt() - 1) == Cell.PLT)) {
							throw new PostCondError("Un trou aurait du être creusé en bas à droite");
						}
					}
				}
			}
		}
		if (command_at_pre == Command.LadL) {
			if (cell_enbas == Cell.LAD || cell_enbas == Cell.MTL || cell_enbas == Cell.PLT || c > 0) {
				if (cell_LadL == Cell.EMP) {
					if (f_gauche == 0 && c_gauche == 0) {
						if (cell_gauche == Cell.EMP || cell_gauche == Cell.PLT) {
							if (!(getEnvi().getCellNature(getWdt() - 1, getHgt()) == Cell.LAD)) {
								throw new PostCondError("l'echelle n'a pas été construite");
							}
							if (nb_i_gauche > 0) {
								if (!((environment_at_pre.getCellContent(getWdt() - 1, getHgt() + 1).getItem()).get(0)
										.getId() == i_gauche.get(0).getId()
										&& environment_at_pre.getCellContent(getWdt() - 1, getHgt()).getItem()
												.size() == 0)) {
									throw new PostCondError("le tresor n'as pas été bougé");
								}

							}
						}
					}
				}
			}
		}
		if (command_at_pre == Command.LadR) {
			if (cell_enbas == Cell.LAD || cell_enbas == Cell.MTL || cell_enbas == Cell.PLT || c > 0) {
				if (cell_LadR == Cell.EMP) {
					if (f_droite == 0 && c_droite == 0) {
						if (cell_droite == Cell.EMP || cell_droite == Cell.PLT) {
							if (!(getEnvi().getCellNature(getWdt() + 1, getHgt()) == Cell.LAD)) {
								throw new PostCondError("l'echelle n'a pas été construite");
							}
							if (nb_i_droite > 0 && (environment_at_pre.getCellContent(getWdt() + 1, getHgt() + 1).getItem()).size() > 0) {
								if (!((environment_at_pre.getCellContent(getWdt() + 1, getHgt() + 1).getItem()).get(0)
										.getId() == i_droite.get(0).getId()
										&& environment_at_pre.getCellContent(getWdt() + 1, getHgt()).getItem()
												.size() == 0)) {
									throw new PostCondError("le tresor n'as pas été bougé");
								}

							}
						}
					}
				}
			}
		}

	}
}

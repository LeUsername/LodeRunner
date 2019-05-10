package contracts;

import java.util.ArrayList;

import decorators.CharacterDecorator;
import error.InvariantError;
import error.PostCondError;
import error.PreCondError;
import impl.CellContentImpl;
import service.Cell;
import service.CharacterService;
import service.EnvironmentService;

public class CharacterContract extends CharacterDecorator implements CharacterService {
	public ArrayList<Cell> moving = new ArrayList<>();
	public ArrayList<Cell> notmoving = new ArrayList<>();
	public ArrayList<Cell> badbase = new ArrayList<>();
	public ArrayList<Cell> base = new ArrayList<>();

	public CharacterContract(CharacterService cs) {
		super(cs);
		moving.add(Cell.EMP);
		moving.add(Cell.HOL);
		moving.add(Cell.LAD);
		moving.add(Cell.HDR);
		notmoving.add(Cell.MTL);
		notmoving.add(Cell.PLT);
		notmoving.add(Cell.FAK);
		badbase.add(Cell.PLT);
		badbase.add(Cell.MTL);
		badbase.add(Cell.LAD);
		base.add(Cell.PLT);
		base.add(Cell.MTL);
		base.add(Cell.LAD);

	}

	public void checkInvariant() {

		if (!(moving.contains(delegate.getEnvi().getCellNature(delegate.getWdt(), delegate.getHgt())))) {
			throw new InvariantError("mauvaise nature case perso");
		}
		// System.out.println(delegate.getEnvi().getCellContent(delegate.getWdt(),
		// delegate.getHgt()) == null);
		// System.out.println(delegate.getEnvi().getCellContent(delegate.getHgt(),
		// delegate.getWdt()) == null);
		if (!(delegate.getEnvi().getCellContent(delegate.getWdt(), delegate.getHgt()).getCharacter()
				.contains(delegate))) {
			// System.out.println(this);
			// System.out.println(
			// delegate.getEnvi().getCellContent(delegate.getWdt(),
			// delegate.getHgt()).getCharacter().get(0));
			throw new InvariantError("mauvaise nature case perso");
		}

	}

	public EnvironmentService getEnvi() {
		checkInvariant();
		EnvironmentService env = delegate.getEnvi();
		checkInvariant();
		return env;
	}

	@Override
	public int getHgt() {
		checkInvariant();
		int res = delegate.getHgt();
		checkInvariant();
		return res;
	}

	@Override
	public int getWdt() {
		checkInvariant();
		int res = delegate.getWdt();
		checkInvariant();
		return res;
	}

	@Override
	public void init(int w, int h, EnvironmentService envi) {
		if (!(Cell.EMP == (envi.getCellNature(w, h)))) {
			throw new PreCondError("cellule non vide");
		}
		super.init(w, h, envi);
		checkInvariant();
		if (!(delegate.getHgt() == h && delegate.getWdt() == w)) {
			throw new PostCondError("Le personnage n'a pas été initialisé sur les cases prévues");
		}
	}

	public void GoLeft() {
		checkInvariant();
		int hgt = super.getHgt();
		int wdt = super.getWdt();
		CellContentImpl contentenbas = super.getEnvi().getCellContent(wdt, hgt - 1);
		int a = super.getEnvi().getCellContent(wdt, hgt - 1).getCharacter().size();

		Cell natureEnBas = super.getEnvi().getCellNature(wdt, hgt - 1);
		Cell nature_at_pre = super.getEnvi().getCellNature(wdt, hgt);
		CellContentImpl contentagauche = super.getEnvi().getCellContent(wdt - 1, hgt);
		int b = contentagauche.getCharacter().size();
		super.GoLeft();
		checkInvariant();
		if (natureEnBas != Cell.EMP && natureEnBas != Cell.HOL && natureEnBas != Cell.FAK) {
			if (!(super.getHgt() == hgt)) {
				throw new PostCondError("Le y a été modifié alors que le personnage a réussi à aller vers la gauche");
			}
		}
		if (wdt == 0) {
			if (!(super.getWdt() == wdt)) {
				throw new PostCondError("perso left malgre x=0");
			}
		}
		if (notmoving.contains(getEnvi().getCellNature(wdt - 1, hgt))) {
			if (!(super.getWdt() == wdt)) {
				throw new PostCondError("perso left malgre mauvaise nature de la case à gauche");
			}

		}
		if ((nature_at_pre != Cell.LAD && nature_at_pre != Cell.HDR)
				&& (natureEnBas == Cell.EMP || natureEnBas == Cell.HOL || natureEnBas == Cell.FAK)
				&& contentenbas.getCharacter().size() == 0) {
			if (!(super.getHgt() == hgt - 1 && super.getWdt() == wdt)) {
				throw new PostCondError("Le personnage n'est pas bien tombé");
			}
		}
		if (wdt > 0
				&& (Cell.MTL != (super.getEnvi().getCellNature(wdt - 1, hgt))
						&& Cell.PLT != (super.getEnvi().getCellNature(wdt - 1, hgt))
						&& Cell.FAK != (super.getEnvi().getCellNature(wdt - 1, hgt)))
				&& (base.contains(super.getEnvi().getCellNature(wdt, hgt - 1))
						|| Cell.LAD == super.getEnvi().getCellNature(wdt, hgt)
						|| Cell.HDR == super.getEnvi().getCellNature(wdt, hgt) || a > 0)
				&& b == 0) {
			if (!(super.getWdt() == wdt - 1)) {
				System.out.println(contentenbas.getCharacter().size() > 0);
				throw new PostCondError("Le personnage aurait du aller vers la gauche");
			}
		}
	}

	@Override
	public void GoUp() {
		checkInvariant();
		int hgt = super.getHgt();
		int wdt = super.getWdt();
		CellContentImpl contentenbas = super.getEnvi().getCellContent(wdt, hgt - 1);
		CellContentImpl contentEnHaut = super.getEnvi().getCellContent(wdt, hgt + 1);
		Cell natureEnHaut = super.getEnvi().getCellNature(wdt, hgt + 1);
		Cell natureEnBas = super.getEnvi().getCellNature(wdt, hgt - 1);
		Cell nature_at_pre = super.getEnvi().getCellNature(wdt, hgt);
		super.GoUp();
		checkInvariant();
		if (!(super.getWdt() == wdt)) {
			throw new PostCondError("Le x a été modifié lors d'un déplacement vers le haut");
		}
		if (hgt == super.getEnvi().getHeight() - 1) {
			if (!(super.getHgt() == hgt)) {
				throw new PostCondError("perso up malgre y=max");
			}
		}
		if (!((natureEnHaut == Cell.HDR || natureEnHaut == Cell.MTL || natureEnHaut == Cell.PLT
				|| natureEnHaut == Cell.FAK) || contentEnHaut.getCharacter().size() > 0)) {
			throw new PostCondError("Le y a été modifié alors que le personnage n'aurait pas du");
		}
		if ((nature_at_pre != Cell.LAD && nature_at_pre != Cell.HDR) && (!moving.contains(natureEnBas))
				&& contentenbas.getCharacter().size() == 0) {
			if (!(super.getHgt() == hgt - 1 && super.getWdt() == wdt)) {
				throw new PostCondError("Le personnage n'est pas bien tombé");
			}
		}
		if (hgt == super.getEnvi().getHeight() - 1 && (natureEnHaut == Cell.LAD
				|| (nature_at_pre == Cell.LAD && (natureEnHaut == Cell.LAD || natureEnHaut == Cell.EMP)))) {
			if (!(super.getHgt() == hgt + 1)) {
				throw new PostCondError("Le personnage aurait du monter");
			}
		}
	}

	@Override
	public void GoRight() {
		checkInvariant();
		int hgt = super.getHgt();
		int wdt = super.getWdt();
		int a = super.getEnvi().getCellContent(wdt, hgt - 1).getCharacter().size();
		CellContentImpl contentenbas = super.getEnvi().getCellContent(wdt, hgt - 1);
		Cell natureEnBas = super.getEnvi().getCellNature(wdt, hgt - 1);
		Cell nature_at_pre = super.getEnvi().getCellNature(wdt, hgt);
		CellContentImpl contentadroite = super.getEnvi().getCellContent(wdt + 1, hgt);
		int b = contentadroite.getCharacter().size();
		super.GoRight();
		checkInvariant();
		if (natureEnBas != Cell.EMP && natureEnBas != Cell.HOL && natureEnBas != Cell.FAK) {
			if (!(super.getHgt() == hgt)) {
				throw new PostCondError("Le y a été modifié alors que le personnage a réussi à aller vers la gauche");
			}
		}
		if (wdt == super.getEnvi().getWidth() - 1) {
			if (!(super.getWdt() == wdt)) {
				throw new PostCondError("perso right malgre x=max");
			}
		}
		if (notmoving.contains(getEnvi().getCellNature(getWdt() + 1, getHgt()))) {
			if (!(super.getWdt() == wdt)) {
				throw new PostCondError("perso right malgre mauvaise nature de la case à droite");
			}

		}
		if ((nature_at_pre != Cell.LAD && nature_at_pre != Cell.HDR)
				&& (natureEnBas == Cell.EMP || natureEnBas == Cell.HOL || natureEnBas == Cell.FAK)
				&& contentenbas.getCharacter().size() == 0) {
			if (!(super.getHgt() == hgt - 1 && super.getWdt() == wdt)) {
				throw new PostCondError("Le personnage n'est pas bien tombé");
			}

		}
		if (wdt < super.getEnvi().getWidth() - 1
				&& (Cell.MTL != (super.getEnvi().getCellNature(wdt + 1, hgt))
						&& Cell.PLT != (super.getEnvi().getCellNature(wdt + 1, hgt))
						&& Cell.PLT != (super.getEnvi().getCellNature(wdt + 1, hgt)))
				&& (base.contains(super.getEnvi().getCellNature(wdt, hgt - 1))
						|| Cell.LAD == super.getEnvi().getCellNature(wdt, hgt)
						|| Cell.HDR == super.getEnvi().getCellNature(wdt, hgt) || a > 0)
				&& b == 0) {
			if (!(super.getWdt() == wdt + 1)) {
				System.out.println(contentenbas.getCharacter().size() > 0);
				throw new PostCondError("Le personnage aurait du aller vers la droite");
			}
		}

	}

	public void GoDown() {
		checkInvariant();
		int hgt = super.getHgt();
		int wdt = super.getWdt();
		CellContentImpl contentenbas = super.getEnvi().getCellContent(wdt, hgt - 1);
		Cell natureEnBas = super.getEnvi().getCellNature(wdt, hgt - 1);
		Cell nature_at_pre = super.getEnvi().getCellNature(wdt, hgt);
		super.GoRight();
		checkInvariant();
		if (!(super.getWdt() == wdt)) {
			throw new PostCondError("x modifier alors qu'on GoDown");
		}
		if (hgt == 0) {
			if (!(super.getHgt() == 0)) {
				throw new PostCondError("y<0");
			}
		}
		if ((natureEnBas != Cell.MTL && natureEnBas != Cell.PLT) && contentenbas.getCharacter().size() == 0) {
			if (!(super.getHgt() == hgt)) {
				throw new PostCondError("Le personnage a traverse une case qu'il n'aurait pas du");
			}
		}
		if ((nature_at_pre != Cell.LAD && nature_at_pre != Cell.HDR)
				&& (!badbase.contains(natureEnBas) && contentenbas.getCharacter().size() == 0)) {
			if (!(super.getHgt() == hgt - 1)) {
				throw new PostCondError("le personnage n'est pas tombe alors qu'il aurait du");
			}
		}
		if (hgt != 0
				&& (nature_at_pre == Cell.LAD || nature_at_pre == Cell.HDR || nature_at_pre == Cell.EMP
						|| nature_at_pre == Cell.FAK)
				&& (natureEnBas == Cell.HOL || natureEnBas == Cell.FAK || natureEnBas == Cell.LAD
						|| natureEnBas == Cell.HDR || natureEnBas == Cell.EMP)) {
			if (!(super.getHgt() == hgt - 1 && super.getWdt() == wdt)) {
				throw new PostCondError("Le personnage n'est pas descendu alors qu'il aurait du");
			}
		}
	}

}

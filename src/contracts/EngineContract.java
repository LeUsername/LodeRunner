package contracts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import decorators.EngineDecorator;
import error.InvariantError;
import error.PostCondError;
import impl.HoleImpl;
import impl.ItemImpl;
import service.Cell;
import service.Cle;
import service.EngineService;
import service.GuardService;
import service.PlayerService;
import service.Status;

public class EngineContract extends EngineDecorator {

	public EngineContract(EngineService es) {
		super(es);
	}

	public void checkInvariant() {
		for (GuardService g : getGuards()) {
			if (g.Alive() != false) {
				if (!(getEnvironment().getCellContent(g.getWdt(), g.getHgt()).getCharacter().contains(g))) {
					throw new InvariantError("Environnement n'est pas synchronisé sur les gardes");
				}
			}
		}
		for (ItemImpl i : getTreasures().values()) {
			if (i != null) {
				if (!(getEnvironment().getCellContent(i.getWdt(), i.getHgt()).getItem().contains(i))) {
					throw new InvariantError("Environnement n'est pas synchronisé sur les objets");
				}
			}
		}
		if (!(getEnvironment().getCellContent(getPlayer().getWdt(), getPlayer().getHgt()).contient(getPlayer()))) {
			throw new InvariantError("Environnement n'est pas synchronisé sur le joueur");
		}
		if (!(getTreasures().isEmpty() == (getStatus() == Status.WIN))) {
			// System.out.println(getTreasures().size());
			// System.out.println(getStatus() == Status.WIN);
			throw new InvariantError("La partie n'est pas terminée alors qu'il n'existe plus de trésors");
		}
		for (HoleImpl h : getHoles()) {
			if (!(getEnvironment().getCellNature(h.getX(), h.getY()) == Cell.HOL)) {
				throw new InvariantError("Environnement pas synchronisé sur les trous");
			}
		}
		PlayerService p = getPlayer();
		if (getEnvironment().getCellNature(p.getWdt(), p.getHgt()) == Cell.PLT) {
			if (!(getStatus() == Status.LOSE)) {
				throw new PostCondError("Le joueur est décédé mais la partie n'est pas finie");
			}
		}
		for (int i = 0; i < getGuards().size(); i++) {
			for (int j = i + 1; j < getGuards().size(); j++) {
				if (!(!getGuards().get(i).equals(getGuards().get(j)))) {
					throw new InvariantError("Deux gardes se trouvent sur la même case");
				}
			}
		}
	}

	public void step(Scanner scan) {
		HashMap<Cle, ItemImpl> treasures_at_pre = getTreasures();
		ArrayList<HoleImpl> holes_at_pre = new ArrayList<>();
		Iterator<HoleImpl> iterateur = getHoles().iterator();
		while (iterateur.hasNext()) {
			try {
				holes_at_pre.add((HoleImpl) iterateur.next().clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		checkInvariant();
		super.step(scan);
		checkInvariant();
		ItemImpl caseCourante = treasures_at_pre.get(new Cle(getPlayer().getWdt(), getPlayer().getHgt()));
		if (caseCourante != null) {
			if (!(getTreasures().get(new Cle(getPlayer().getWdt(), getPlayer().getHgt())) == null))
				throw new PostCondError("Un trésor est encore sur la case du joueur");
		}
		for (HoleImpl h_at_pre : holes_at_pre) {
			if (h_at_pre.getTemps() < 14) {
				for (HoleImpl h_at_post : getHoles()) {
					if (h_at_pre.getX() == h_at_post.getX() && h_at_pre.getY() == h_at_post.getY()) {
						if (!(h_at_post.getTemps() == h_at_pre.getTemps() + 1)) {
							throw new PostCondError("Un trou a été mal incrémenté");
						}
					}
				}
			} else {
				if (!(getEnvironment().getCellNature(h_at_pre.getX(), h_at_pre.getY()) == Cell.PLT)) {
					throw new PostCondError("Un trou n'a pas été rebouché");
				}
				for (HoleImpl h_at_post : getHoles()) {
					if (!(h_at_post.getX() != h_at_pre.getX() || h_at_post.getY() != h_at_pre.getY())) {
						// System.out.println(h_at_post.getX() + " " + h_at_post.getY());
						// System.out.println(h_at_pre.getX() + " " + h_at_pre.getY());
						throw new PostCondError("Un trou qui a été rebouché est encore dans la liste des trous");
					}
				}
			}
		}
	}
}

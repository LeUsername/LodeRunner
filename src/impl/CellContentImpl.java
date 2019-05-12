package impl;

import java.util.ArrayList;

import service.CharacterService;

public class CellContentImpl {
	private final ArrayList<CharacterService> character;
	private final ArrayList<ItemImpl> item;

	public CellContentImpl(ArrayList<CharacterService> c, ArrayList<ItemImpl> i) {
		character = c;
		item = i;
	}

	public ArrayList<CharacterService> getCharacter() {
		return character;
	}

	public ArrayList<ItemImpl> getItem() {
		return item;
	}

	public boolean contient(Object c) {
		if (!(c instanceof CharacterService)) {
			CharacterService p = (CharacterService) c;
			return character.contains(p);
		} else {

			return character.contains(c);
		}
	}
}

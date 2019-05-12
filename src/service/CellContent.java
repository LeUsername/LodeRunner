package service;

import java.util.ArrayList;

public class CellContent {
	private final ArrayList<CharacterService> character;
	private final ArrayList<Item> item;

	public CellContent(ArrayList<CharacterService> c, ArrayList<Item> i) {
		character = c;
		item = i;
	}

	public ArrayList<CharacterService> getCharacter() {
		return character;
	}

	public ArrayList<Item> getItem() {
		return item;
	}
}

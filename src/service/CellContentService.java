package service;

import java.util.ArrayList;

import impl.ItemImpl;

public interface CellContentService {
	public ArrayList<CharacterService> getCharacter();

	public ArrayList<ItemImpl> getItem();

//	public boolean contient(Object c) {
//		if (!(c instanceof CharacterService)) {
//			CharacterService p = (CharacterService) c;
//			System.out.println("if");
//			return character.contains(p);
//		} else {
//			System.out.println("else");
//			System.out.println(character.get(0));
//			return character.contains(c);
//		}
//	}
}

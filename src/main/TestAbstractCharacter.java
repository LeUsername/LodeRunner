package main;

import org.junit.Before;

import service.CharacterService;

public abstract class TestAbstractCharacter {
	private CharacterService character;

	public TestAbstractCharacter() {
		character = null;
	}

	protected void setEcran(CharacterService character) {
		this.character = character;
	}

	protected CharacterService getEcran() {
		return character;
	}

	@Before
	public abstract void beforeTests();
}

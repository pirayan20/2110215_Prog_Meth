package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import role.base.PreRoundActable;
import role.base.PreTurnActable;
import role.base.Role;
import role.derived.Ordinary;

public class OrdinaryTest {

	private Role role;

	@BeforeEach
	public void setUp() throws Exception {
		role = new Ordinary();
	}

	@Test
	public void testRole() {
		assertTrue(role instanceof Role);
		assertFalse(role instanceof PreRoundActable);
		assertFalse(role instanceof PreTurnActable);
	}

	@Test
	public void testToString() {
		assertEquals("Ordinary", role.toString());
	}


}

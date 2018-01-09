package be.vdab.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Saus {

	private long id;
	private String naam;
	private List<String> ingredienten = new ArrayList<>();

	public Saus(long id, String naam, Collection<String> ingredienten) {
		this.id = id;
		this.naam = naam;
		this.ingredienten.addAll(ingredienten);
	}

	public Saus(long id, String naam, String ingredienten) {
		this(id, naam, Arrays.asList(ingredienten.split(",")));
	}

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public List<String> getIngredienten() {
		return ingredienten;
	}

	public void addIngredient(String ingredient) {
		ingredienten.add(ingredient);
	}
}

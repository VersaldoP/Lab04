package it.polito.tdp.lab04.model;

public class Corso {
	
	private String codins;
	private int numerocrediti;
	private String nome;
	private int periododidattico;
	
	
	public Corso(String codins, int numerocrediti, String nome, int periododidattico) {
		super();
		this.codins = codins;
		this.numerocrediti = numerocrediti;
		this.nome = nome;
		this.periododidattico = periododidattico;
	}
	public String getCodins() {
		return codins;
	}
	public int getNumerocrediti() {
		return numerocrediti;
	}
	public String getNome() {
		return nome;
	}
	public int getPeriododidattico() {
		return periododidattico;
	}
	

}

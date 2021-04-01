package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	public CorsoDAO corsoDAO;
	public StudenteDAO studenteDAO;
	
	public Model() {
		 corsoDAO = new CorsoDAO();
		 studenteDAO = new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi(){
		return corsoDAO.getTuttiICorsi();
	}
	
	public Studente getStudente(Integer i){
		return studenteDAO.getStudente(i);
		
	}
	public List<Studente> getListaStudenti(String c){
		return studenteDAO.getListaStudenti(c);
	}

}

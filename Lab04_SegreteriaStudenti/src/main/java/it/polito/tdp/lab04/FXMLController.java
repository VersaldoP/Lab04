/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboCorsi;

    @FXML
    private Button btnIscritticorsi;

    @FXML
    private TextField txtmatricolaStudente;

    @FXML
    private Button btnCheck;

    @FXML
    private TextField txtnomeStudente;

    @FXML
    private TextField txtcognomeStudente;

    @FXML
    private Button btnCercacorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;
    
    @FXML
    private Button btnCerca;

    @FXML
    void doCerca(ActionEvent event) {
    	txtResult.clear();

    	int matricola;
    	String corso = comboCorsi.getValue();
    	try {
    		matricola = Integer.parseInt(txtmatricolaStudente.getText());
    		
    		}
    	catch(NumberFormatException e ){
    		txtResult.setText("Devi inserire un numero matricola ");
    		return;    	
    		}
    	if(txtmatricolaStudente.getText().length()!=6) {
    		txtResult.setText("Devi inserire un numero matricola di 6 cifre ");
    		return;
    	}
    	Studente studente = model.getStudente(matricola);
    	if( studente!=null) {
    		if(corso!=null) {
        		if(!corso.equals(" ")) {
        			
        			 if(model.cerca(matricola,corso)) {
        				 txtResult.setText("Lo studente è iscritto al corso");
        				 return;
        				 
        			 }else {
        				 txtResult.setText("Lo studente NON è iscritto al corso");
        				 return;
        			 }
        			
        		}
        		
        		else {
        			txtResult.setText("Hai selezionato il corso nullo");
        			return;
        		}
        		
        	}
        	else {
        		txtResult.setText("Non hai selezionato nessun corso ");
        		return;
    		
    		
        	}
    	}
    	else {
    		txtResult.setText("la matricola inserita non ha trovato corrispondenze");
    		return;
    	}
    	
    	
    	
    	
    	
    }

    @FXML
    void doCercacorsi(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	String matricolaString = txtmatricolaStudente.getText();
    	int matricola;
    	
    	try {
    		matricola = Integer.parseInt(matricolaString);
    		
    		}
    	catch(NumberFormatException e ){
    		txtResult.setText("Devi inserire un numero matricola ");
    		return;    	
    		}
    	if(matricolaString.length()!=6) {
    		txtResult.setText("Devi inserire un numero matricola di 6 cifre ");
    		return;
    	}
    	Studente studente = model.getStudente(matricola);
//    	System.out.println(studente);
    	if( studente!=null) {
    		List<Corso> corsiStudente = new LinkedList<Corso>();
    		corsiStudente= model.getCorsiStudente(studente);
    		for(Corso c: corsiStudente) {
    			txtResult.appendText(c.getNome()+"\n");
    		}
    		
    	}
    	else {
    		txtResult.setText("la matricola inserita non ha trovato corrispondenze");
    	}

    }
    
    @FXML
    void doCheck(ActionEvent event) {
    	txtResult.clear();
    	
    	int matricola;
    	
    	try {
    		matricola = Integer.parseInt(txtmatricolaStudente.getText());
    		
    		}
    	catch(NumberFormatException e ){
    		txtResult.setText("Devi inserire un numero matricola ");
    		return;    	
    		}
    	if(txtmatricolaStudente.getText().length()!=6) {
    		txtResult.setText("Devi inserire un numero matricola di 6 cifre ");
    		return;
    	}
    	Studente studente = model.getStudente(matricola);
//    	System.out.println(studente);
    	if( studente!=null) {
    		txtnomeStudente.setText(studente.getNome());
    		txtcognomeStudente.setText(studente.getCognome());
    	}
    	else {
    		txtResult.setText("la matricola inserita non ha trovato corrispondenze");
    	}
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	
    	
    	txtResult.clear();
    	String matricolaString = txtmatricolaStudente.getText();
    	int matricola;
    	
    	try {
    		matricola = Integer.parseInt(matricolaString);
    		
    		}
    	catch(NumberFormatException e ){
    		txtResult.setText("Devi inserire un numero matricola ");
    		return;    	
    		}
    	if(matricolaString.length()!=6) {
    		txtResult.setText("Devi inserire un numero matricola di 6 cifre ");
    		return;
    	}
    	Studente studente = model.getStudente(matricola);
//    	System.out.println(studente);
    	if( studente!=null) {
    		String corso = comboCorsi.getValue();
    		  
        	if(corso!=null) {
        		if(!corso.equals(" ")) {
        			
        			if (model.getListaStudenti(corso).contains(studente)) {
        				txtResult.setText("Lo Studente è già iscritto al corso");
        				return;
        			}
        			else {
        				model.iscriviStudente(corso,studente);
        				txtResult.setText("Studente "+studente.getMatricola()+" iscritto al corso : "+corso);
        				return;
        			}
        			
//        			
//        		StringBuilder sb = new StringBuilder();
//        		for(Studente s :model.getListaStudenti(corso)) {
//        			sb.append(String.format("%-11d",s.getMatricola()));
//        			sb.append(String.format("%-50s ",s.getNome()));
//        			sb.append(String.format("%-50s ",s.getCognome()));
//        			sb.append(String.format("%-50s \n",s.getCds()));
//        		
//        			
//        		}
//        		txtResult.appendText(sb.toString());
        		}
        		else {
        			txtResult.setText("Hai selezionato il corso nullo");
        		}
        		
        	}
        	else {
        		txtResult.setText("Non hai selezionato nessun corso ");
        		
        	}
    		
    	}
    	else {
    		txtResult.setText("la matricola inserita non ha trovato corrispondenze");
    	}
    	
    	
    	
    	
    	

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtmatricolaStudente.clear();
    	txtnomeStudente.clear();
    	txtcognomeStudente.clear();
    	txtResult.clear();

    }

    @FXML
    void doIscritticorsi(ActionEvent event) {
    	txtResult.clear();
    	
    	String corso = comboCorsi.getValue();
  
    	if(corso!=null) {
    		if(!corso.equals(" ")) {
    			
    		StringBuilder sb = new StringBuilder();
    		for(Studente s :model.getListaStudenti(corso)) {
    			sb.append(String.format("%-11d",s.getMatricola()));
    			sb.append(String.format("%-50s ",s.getNome()));
    			sb.append(String.format("%-50s ",s.getCognome()));
    			sb.append(String.format("%-50s \n",s.getCds()));
    		
    			
    		}
    		txtResult.appendText(sb.toString());
    		}
    		else {
    			txtResult.setText("Hai selezionato il corso nullo");
    		}
    		
    	}
    	else {
    		txtResult.setText("Non hai selezionato nessun corso ");
    		
    	}
    	
    	
    	
    	
    	
    	
    }
   

    @FXML
    void initialize() {
        assert comboCorsi != null : "fx:id=\"comboCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscritticorsi != null : "fx:id=\"btnIscritticorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtmatricolaStudente != null : "fx:id=\"matricolaStudente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtnomeStudente != null : "fx:id=\"nomeStudente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtcognomeStudente != null : "fx:id=\"cognomeStudente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercacorsi != null : "fx:id=\"btnCercacorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.model= model;
		for(Corso c :model.getTuttiICorsi()) {
			comboCorsi.getItems().add(c.getNome());
		}
		comboCorsi.getItems().add(" ");
		/*
		 * sarebbe ,meglio usare una lambda expression per 
		comboCorsi.getItems().addAll(CorsoDAO.getTuttiICorsi().stream().ma)
		*/
	}
}

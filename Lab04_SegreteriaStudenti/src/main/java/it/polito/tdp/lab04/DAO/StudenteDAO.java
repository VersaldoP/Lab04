package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	
	
	
	public  Studente getStudente(Integer id) {
		
		 String sql ="SELECT * FROM studente WHERE matricola=?";
	    Studente studente = null;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				studente = new Studente(rs.getInt("matricola"),rs.getString("nome"),
						rs.getString("cognome"),rs.getString("cds"));

			}

			conn.close();
			st.close();
			rs.close();
			return studente;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
		
		
		
		public List<Studente> getListaStudenti(String corso){
			
			 String sql = "SELECT s.matricola,s.cognome,s.nome,s.CDS "+
					"FROM studente s,iscrizione i,corso c "+
					"WHERE i.codins=c.codins "+
					"AND i.matricola=s.matricola "+
					"AND c.nome=? "+
					"GROUP BY s.matricola,s.cognome,s.nome,s.CDS";
			
			 List<Studente> studenti = new LinkedList<Studente>();
			 try {
				 Connection conn = ConnectDB.getConnection();
				 PreparedStatement st = conn.prepareStatement(sql);
				 st.setString(1,corso);
				 ResultSet rs= st.executeQuery();
	
				 while (rs.next()) {
					 Studente s = new Studente(rs.getInt("matricola"),rs.getString("nome"),							 
							 rs.getString("cognome"),rs.getString("CDS"));
					 studenti.add(s);
				
				 }
				 st.close();
				 rs.close();
				 conn.close();

					
				 
			 }
			 catch(SQLException e) {
				 throw new RuntimeException("Errore Db Lista Studenti ", e);
				 
			 }
		
			 
			 
			 return studenti;
			 
			
		}



		public List<Corso> getCorsiStudente(Studente studente) {
			
			String sql = "SELECT  c.codins, c.crediti, c.nome, c.pd "
					+ "FROM  corso c,  iscrizione i,  studente s "
					+ "WHERE i.matricola= s.matricola "
					+ "AND  i.codins=c.codins "
					+"AND s.matricola=? "
					+ "GROUP BY c.nome";
			
			 List<Corso> corsi = new LinkedList<Corso>();
			 try {
				 Connection conn = ConnectDB.getConnection();
				 PreparedStatement st = conn.prepareStatement(sql);
				 st.setInt(1,studente.getMatricola());
				
				 ResultSet rs= st.executeQuery();
				 
				 while (rs.next()) {
					 
					 

						String codins = rs.getString("codins");
						int numeroCrediti = rs.getInt("crediti");
						String nome = rs.getString("nome");
						int periodoDidattico = rs.getInt("pd");
						
						Corso c = new Corso(codins,numeroCrediti,nome,periodoDidattico);
						corsi.add(c);
					
				
				 }
				 st.close();
				 rs.close();
				 conn.close();

				
				 
			 }
			 catch(SQLException e) {
				 throw new RuntimeException("Errore Db Corsi Studente", e);
				 
			 }
		
			 
			 
			 return corsi;
			
			
			
		}



		public static boolean cerca(int matricola, String c) {
			
			String sql= "SELECT s.matricola,s.cognome,s.nome,s.CDS "+
					 "FROM studente s,iscrizione i,corso c "+
					 "WHERE i.codins=c.codins "+
					 "AND c.nome=? "+
					 "AND i.matricola=s.matricola "+
					 "AND i.matricola=? "+				
					 "GROUP BY s.matricola,s.cognome,s.nome,s.CDS ";
			
			
			
			 try {
				 Connection conn = ConnectDB.getConnection();
				 PreparedStatement st = conn.prepareStatement(sql);
				 st.setString(1,c);
				 st.setInt(2,matricola);
				 
				
				 ResultSet rs= st.executeQuery();
				 
				if (rs.next()) {
					st.close();
					 rs.close();
					 conn.close();
					 
					return true;
					
				
				 }
				else {
					st.close();
					 rs.close();
					 conn.close();
					 return false;
				}
				 

				
				 
			 }
			 catch(SQLException e) {
				 throw new RuntimeException("Errore Db Cerca studenti", e);
				 
			 }
			
			
		}



	



		public static void iscrivi(String corso, Studente studente) {
			// TODO Auto-generated method stub
			String sql= "INSERT INTO iscrizione (matricola,codins) "
					+ "(SELECT  ?,c.codins "
					+ "FROM corso c  "
					+ "WHERE c.nome=?);";
					
			
			
			
			 try {
				 Connection conn = ConnectDB.getConnection();
				 PreparedStatement st = conn.prepareStatement(sql);
				 st.setInt(1,studente.getMatricola());
				 st.setString(2,corso);
				 
				
				 ResultSet rs= st.executeQuery();
				 
//				if (rs.next()) {
//					st.close();
//					 rs.close();
//					 conn.close();
//					 
//					return ;
//					
//				
//				 }
//				else {
					st.close();
					 rs.close();
					 conn.close();
					 return ;
//				}
				 

				
				 
			 }
			 catch(SQLException e) {
				 throw new RuntimeException("Errore Db Cerca studenti", e);
				 
			 }
			
			
			
		}



		
	
}

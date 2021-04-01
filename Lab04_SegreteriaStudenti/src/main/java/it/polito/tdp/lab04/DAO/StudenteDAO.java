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
			
//			 String sql = "SELECT s.matricola,s.cognome,s.nome,s.CDS "+
//					"FROM studente s,iscrizione i "+
//					"WHERE i.codins=? "+
//					"AND i.matricola=s.matricola "+
//					"GROUP BY s.matricola,s.cognome,s.nome,s.CDS";
//			 List<Studente> studenti = new LinkedList<Studente>();
//			 try {
//				 Connection conn = ConnectDB.getConnection();
//				 PreparedStatement st = conn.prepareStatement(sql);
//				 st.setString(1,corso);
//				 ResultSet rs= st.executeQuery();
//				 System.out.println("Controllll");
//				 while (rs.next()) {
//					 Studente s = new Studente(rs.getInt("matricola"),rs.getString("nome"),
//							 
//							 rs.getString("cognome"),rs.getString("CDS"));
//					 studenti.add(s);
//					 System.out.println("Controllll");
//				 }
//				 st.close();
//				 rs.close();
//				 conn.close();

					String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS "
							+ "FROM studente s, iscrizione i "
							+ "WHERE s.matricola = i.matricola "
							+ "AND i.codins = ?";
					List<Studente>result = new LinkedList<Studente>();

					try {
						Connection conn = ConnectDB.getConnection();
						PreparedStatement st= conn.prepareStatement(sql);
						st.setString(1,corso);
						ResultSet rs = st.executeQuery();
						
						while(rs.next()) {
							Studente s = new Studente(rs.getInt("matricola"),rs.getString("nome"),
									rs.getString("cognome"),rs.getString("CDS"));
							result.add(s);
							 System.out.println("Controllll");
							
						}
				 
			 }
			 catch(SQLException e) {
				 
			 }
			 
			 
			 return result;
			 
			
		}
	
}

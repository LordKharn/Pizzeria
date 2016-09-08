package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;

import Models.Kunde;

public class KundeDAO {
	private Connection dbConnect;
	
	public KundeDAO(){
		this.dbConnect = (Connection) DAOFactory.createConnection();
	}
	
	public void deleteKunde(int id) {
		String sql = "delete from kunde where KundenNr=" + id;
				
		if(this.dbConnect != null){
			try{
				PreparedStatement preStm = this.dbConnect.prepareStatement(sql);
				preStm.executeUpdate();
				preStm.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertKunde(Kunde kunde) {	
		String sql = "INSERT INTO kunde (Name, Strasse, Ort, Plz) VALUES ('" + kunde.getName() + "' , '" + kunde.getStrasse() + "' , '" + kunde.getOrt() + "' , " + kunde.getPlz() + ")";
		if(this.dbConnect != null){
			try{
				PreparedStatement preStm = this.dbConnect.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				preStm.executeUpdate();
				ResultSet rs = preStm.getGeneratedKeys();
				rs.first();
				kunde.setKundenNummer(rs.getInt(1));
				preStm.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateKunde(Kunde kunde) {
		String sql ="UPDATE kunde SET Name='" + kunde.getName() + "', Strasse='" + kunde.getStrasse() + "', Ort='" + kunde.getOrt() + "', Plz=" + kunde.getPlz() + " WHERE KundenNr=" + kunde.getKundenNummer() + "";
		if(this.dbConnect != null){
			try{
				PreparedStatement preStm = this.dbConnect.prepareStatement(sql);
				preStm.executeUpdate();
				preStm.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Kunde findKunde(int id) {
		String sql ="SELECT * FROM Kunde WHERE KundenNR= " + id;
		Kunde kunde = null;
		if(this.dbConnect != null){
			try{
				// Updated Selected Data into DB without 2nd Statement
				//PreparedStatement preStm = this.dbConnect.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				//rs.updateString("Name", "Kaninchen");
				//rs.updateRow();
				PreparedStatement preStm = this.dbConnect.prepareStatement(sql);
				ResultSet rs = preStm.executeQuery();
				if(rs.first()){
					kunde = new Kunde(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
				}
				preStm.close();
				return kunde;
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return kunde;
	}
	
	public ArrayList<Kunde> findKunde(String name) {
		String sql ="SELECT * FROM Kunde WHERE Name LIKE '%" + name + "%'";
		ArrayList<Kunde> kunde = new ArrayList<Kunde>();
		if(this.dbConnect != null){
			try{
				PreparedStatement preStm = this.dbConnect.prepareStatement(sql);
				ResultSet rs = preStm.executeQuery();
				while(rs.next()){
					kunde.add(new Kunde(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
				}
				preStm.close();
				return kunde;
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return kunde;
	}
	
	public ArrayList<Kunde> selectAllKunden() {
		String sql ="SELECT * FROM Kunde";
		ArrayList<Kunde> kunde = new ArrayList<Kunde>();
		if(this.dbConnect != null){
			try{
				PreparedStatement preStm = this.dbConnect.prepareStatement(sql);
				ResultSet rs = preStm.executeQuery();
				while(rs.next()){
					kunde.add(new Kunde(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
				}
				preStm.close();
				return kunde;
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return kunde;
	}
	
	
}
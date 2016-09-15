package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Backend.KonstantInstanceSaver;

import java.sql.Connection;

import Models.Bestellung;
import Models.Kunde;

public class BestellungDAO {
	private Connection dbConnect;
	
	public BestellungDAO(){
		this.dbConnect = (Connection) DAOFactory.createConnection();
	}
	
	public void insertBestellung(Bestellung bestellung){
		String sql="INSERT INTO bestellung (KundenNr,Status) VALUES (" + bestellung.getKunde().getKundenNummer() + ",'Neu')";
		
		// Um java.lang.NullPointerException abzufangen wenn kein Bonusgetraenk
		if(bestellung.getBonusGetraenke() != null){
			sql="INSERT INTO bestellung (KundenNr,Bonusgetraenk,Status) VALUES (" + bestellung.getKunde().getKundenNummer() + ", " + bestellung.getBonusGetraenke().getId() + ",'Neu')";
		}
		if(this.dbConnect != null){
			try{
				PreparedStatement preStm = this.dbConnect.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				preStm.executeUpdate();
				ResultSet rs = preStm.getGeneratedKeys();
				rs.first();
				bestellung.setId(rs.getInt(1));
				preStm.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateBestellung(){
		String sql="UPDATE bestellung SET Status='Fertig'";
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
	public ArrayList<Bestellung> neueBestellungen(){
		String sql="SELECT b.ID, k.KundenNr, k.Name, k.Strasse, k.Ort, k.Plz, b.Bonusgetraenk FROM bestellung b, kunde k, pizza p WHERE Status ='Neu' AND b.KundenNr = k.KundenNr AND p.Bestellungsid = b.ID GROUP BY b.id";
		ArrayList<Bestellung> bestellung = new ArrayList<Bestellung>();
		if(this.dbConnect != null){
			try{
				PreparedStatement preStm = this.dbConnect.prepareStatement(sql);
				ResultSet rs = preStm.executeQuery();
				while(rs.next()){
					if(rs.getInt(7) != 0){
						bestellung.add(new Bestellung(rs.getInt(1), new Kunde(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)), KonstantInstanceSaver.getGetraenk(rs.getInt(7))));
					}
					else {	
						bestellung.add(new Bestellung(rs.getInt(1), new Kunde(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6))));
					}
				}
				preStm.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return bestellung;
	}
}
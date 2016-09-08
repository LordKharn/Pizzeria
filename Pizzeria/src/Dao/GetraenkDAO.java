package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.Connection;

import Models.Getraenk;

public class GetraenkDAO {
	private Connection dbConnect;
	
	public GetraenkDAO(){
		this.dbConnect = (Connection) DAOFactory.createConnection();
	}
	
//	public Getraenk findGetraenke(int id) {
//		String sql ="SELECT * FROM getraenk WHERE ID= " + id;
//		Getraenk getraenk = null;
//		if(this.dbConnect != null){
//			try{
//				PreparedStatement preStm = this.dbConnect.prepareStatement(sql);
//				ResultSet rs = preStm.executeQuery();
//				rs.first();
//				getraenk = new Getraenk(rs.getInt(1),rs.getString(2),rs.getDouble(3));
//				preStm.close();
//				return getraenk;
//			}
//			catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return getraenk;
//	}
	
	public void insertGetraenkBestellt(Getraenk getraenk, int bestellId){
		String sql ="INSERT INTO getraenkebestellung (GetraenkID, BestellungsID) VALUES (" + getraenk.getId() + "," + bestellId + ")";
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
	
	public HashMap<Integer,Getraenk> selectAllGetraenk(){
		String sql="SELECT * FROM getraenk";
		HashMap<Integer,Getraenk> getraenke = new HashMap<Integer,Getraenk>();
		if(this.dbConnect != null){
			try{
				PreparedStatement preStm = this.dbConnect.prepareStatement(sql);
				ResultSet rs = preStm.executeQuery();
				while(rs.next()){
					getraenke.put(rs.getInt(1),(new Getraenk(rs.getInt(1),rs.getString(2),rs.getDouble(3))));
				}
				preStm.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return getraenke;
	}
	
}

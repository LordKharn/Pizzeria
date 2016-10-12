package Backend;

import java.util.ArrayList;

import Models.Belag;
import Models.Bestellung;
import Models.Getraenk;
import Models.Pizza;

public class Preisberechnung {
	
	public static void preisBerechnung(Pizza pizza){
		double rabatt = 0.10;//Rabatt % in dezimal
		int discountamount = 3;
		
		pizza.setPreis(pizza.getPizzaGroesse().getPreis());
		
		ArrayList<Belag> belaege = pizza.getBelag();
		
		ArrayList<Belag> einfach = new ArrayList<Belag>();
		ArrayList<Belag> speziell = new ArrayList<Belag>();
		
		for(int i = 0; i < belaege.size(); i++){
			if(belaege.get(i).getBelagsTyp().equals("Einfach")){
				einfach.add(belaege.get(i));
			}else{
				speziell.add(belaege.get(i));
			}
		}
		//einfach
		for(int i = 0; i < einfach.size(); i++){
			double belagspreis = einfach.get(i).getPreis(pizza.getPizzaGroesse().getGroesse());
			if(discountamount < 1){
				belagspreis -= belagspreis * rabatt;
			}
			pizza.setPreis(pizza.getPreis()+belagspreis);
			discountamount --;
		}
		//speziell
		for(int i = 0; i < speziell.size(); i++){
			double belagspreis = speziell.get(i).getPreis(pizza.getPizzaGroesse().getGroesse());
			if(discountamount < 1){
				belagspreis -= belagspreis * rabatt;
			}
			pizza.setPreis(pizza.getPreis()+belagspreis);
			discountamount --;
		}
	}
	
	public static void preisBerechnung(Bestellung bestellung){
		ArrayList<Pizza> pizzen = bestellung.getPizzen();
		ArrayList<Getraenk> getränke = bestellung.getGetraenke();
		double gesamtpreis = 0;
		for(int i = 0; i < pizzen.size(); i++){
			gesamtpreis += pizzen.get(i).getPreis();
		}
		for(int i = 0; i < getränke.size(); i++){
			gesamtpreis += getränke.get(i).getPreis();
		}
		bestellung.setPreis(gesamtpreis);
	}
	
	public static void komplettPreisBerechnung(Bestellung bestellung){
		ArrayList<Pizza> pizzen = bestellung.getPizzen();
		for(int i = 0; i < pizzen.size(); i++){
			preisBerechnung(pizzen.get(i));
		}
		preisBerechnung(bestellung);
	}
}

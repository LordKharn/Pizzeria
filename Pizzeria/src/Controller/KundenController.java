package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ResourceBundle;
import Backend.KonstantInstanceSaver;
import Backend.Preisberechnung;
import Dao.DAOFactory;
import Models.Belag;
import Models.Bestellung;
import Models.Getraenk;
import Models.Kunde;
import Models.Pizza;
import application.Main;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;



public class KundenController implements Initializable  {
	private Bestellung initBestellung = new Bestellung();
	@FXML
	private TableView<Kunde> KundenTabelle;
	@FXML
	private TableColumn<Kunde, String> KundenName;
    @FXML
    private TableColumn<Kunde, String> KundenOrt;
    @FXML
    private TableColumn<Kunde, Integer> KundenId;
    @FXML
    private TableColumn<Kunde, String> KundenStrasse;
    @FXML
    private Button listAllKunden;
    @FXML
    private TableColumn<Kunde, Integer> KundenPlz;
    @FXML
    private TextField sucheKunde;
    @FXML
    private Button sucheKundeButton;
    @FXML
    private TextField sucheKundeName;
    @FXML
    private AnchorPane kundenAnlegenTabelle;
    @FXML
    private TextField kundenOrtAnlegen;
    @FXML
    private TextField kundenNameAnlegen;
    @FXML
    private TextField kundenPlzAnlegen;
    @FXML
    private TextField kundenStrasseAnlegen;
    @FXML
    private Button kundenAnlegen;
    @FXML
    private Tab bestellen;
    @FXML
    private TabPane tabPane;
    @FXML
    private ChoiceBox<String> pizzaGroesse;
    @FXML
    private GridPane bestellButtonsEinfach;
    @FXML
    private GridPane bestellButtonsSpeziel;
    @FXML
    private GridPane bestellGetraenke;
	private Main mainApp;
    @FXML
    private TreeTableView<Bestellung> bestellungsAnzeige;
    @FXML
    private TreeTableColumn<Bestellung, String> bestellung;
    @FXML
    private TreeTableColumn<Double, Double> Preis;
    
    TreeItem<Bestellung> root = new TreeItem<>(initBestellung);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		root.setExpanded(true);
		bestellungsAnzeige.setRoot(root);
		bestellungsAnzeige.setShowRoot(false);
		
		bestellButtonsEinfach.setVisible(false);
    	bestellButtonsSpeziel.setVisible(false);
    	bestellGetraenke.setVisible(false);
		KundenId.setCellValueFactory(new PropertyValueFactory<Kunde, Integer>("kundenNummer"));
		KundenName.setCellValueFactory(new PropertyValueFactory<Kunde, String>("name"));
		KundenStrasse.setCellValueFactory(new PropertyValueFactory<Kunde, String>("strasse"));
		KundenOrt.setCellValueFactory(new PropertyValueFactory<Kunde, String>("ort"));
		KundenPlz.setCellValueFactory(new PropertyValueFactory<Kunde, Integer>("plz"));
		
		ObservableList<String> pizzaGr = FXCollections.observableArrayList(KonstantInstanceSaver.getPizzaGroessen().keySet());
		Collections.sort(pizzaGr);
		Collections.reverse(pizzaGr);
		pizzaGroesse.setItems(pizzaGr);

		pizzaGroesse.valueProperty().addListener(new ChangeListener<String>() {
                    @Override public void changed(ObservableValue ov, String t, String pizzaGr) {
                    	pizzaAuswahl(pizzaGr);
                    	bestellButtonsEinfach.setVisible(true);
                    	bestellButtonsSpeziel.setVisible(true);
                    	bestellGetraenke.setVisible(true);
                    	pizzaGroesse.setVisible(false);
                    }    
		});
		
		Iterator<Getraenk> itG = KonstantInstanceSaver.getGetraenke().values().iterator();
		Iterator<Belag> it = KonstantInstanceSaver.getBelaege().values().iterator();
		
		ArrayList<Belag> einfach= new ArrayList<Belag>();
		ArrayList<Belag> speziell = new ArrayList<Belag>();
		
		while(it.hasNext()){
			Belag belag = it.next();
			if(belag.getBelagsTyp().equals("Einfach")){
				einfach.add(belag);
			}
			else {
				speziell.add(belag);
			}
		}
		
		int z = 0;
		for(int spalte = 0; spalte < bestellButtonsEinfach.getColumnConstraints().size() ; spalte++) {
			for(int zeile = 0; zeile < bestellButtonsEinfach.getRowConstraints().size() ; zeile++) {
				if(z < einfach.size()) {
					
					Button button = new Button(einfach.get(z).getName());
					final int belagId = einfach.get(z).getId();
					button.setMaxWidth(140);
					button.setMaxHeight(50);
					button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				          @Override
				          public void handle(MouseEvent e) {
				        	  belagHinzufügen(belagId);
				          }
				        });
					bestellButtonsEinfach.add(button,spalte,zeile);
					z++;
				}	
			}
		}
		
		z = 0;
		for(int spalte = 0; spalte < bestellButtonsSpeziel.getColumnConstraints().size() ; spalte++) {
			for(int zeile = 0; zeile < bestellButtonsSpeziel.getRowConstraints().size() ; zeile++) {
				if(z < speziell.size()) {
					Button button = new Button(speziell.get(z).getName());
					final int belagId = speziell.get(z).getId();
					button.setMaxWidth(140);
					button.setMaxHeight(50);
					button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				          @Override
				          public void handle(MouseEvent e) {
				        	  belagHinzufügen(belagId);
				          }
				        });
					bestellButtonsSpeziel.add(button,spalte,zeile);
					z++;
				}	
			}
		}
			
		for(int zeile = 0; zeile < bestellGetraenke.getRowConstraints().size() ; zeile++) {
			if(itG.hasNext()) {
				Getraenk getraenk = itG.next();
				Button button = new Button(getraenk.getName());
				final int getraenkId = getraenk.getId();
				button.setMaxWidth(140);
				button.setMaxHeight(50);
				button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			          @Override
			          public void handle(MouseEvent e) {
			        	  getraenkHinzufügen(getraenkId);
			          }
			        });
				bestellGetraenke.add(button,0,zeile);
			}	
		}
		
		
		
	}
	
	@FXML
	void listAllKunden(ActionEvent event){
		KundenTabelle.setItems(mainApp.getKunden());
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

    @FXML
    void sucheKundeButton(ActionEvent event) {
    	//TODO Bessere möglichkeit ?
    	try {
			if(!sucheKunde.getText().isEmpty()) {
				KundenTabelle.setItems(mainApp.getKunde(Integer.parseInt(sucheKunde.getText())));
			}
			else {

			}
		} 
    	catch (NumberFormatException e) 
    	{

		}
    }

    @FXML
    void sucheKundeName(KeyEvent event) {
    	KundenTabelle.setItems(mainApp.getKunde(sucheKundeName.getText()));
    }
    
    @FXML
    void kundenOrtAnlegen(ActionEvent event) {

    }

    @FXML
    void kundenPlzAnlegen(ActionEvent event) {

    }

    @FXML
    void kundenStrasseAnlegen(ActionEvent event) {
    	
    }

    @FXML
    void kundenNameAnlegen(ActionEvent event) {
    	
    }
    
    @FXML
    void kundenAnlegen(ActionEvent event) {
    	//TODO Bessere möglichkeit ?
    	try {
				DAOFactory.getKundeDAO().insertKunde(new Kunde(kundenNameAnlegen.getText(), kundenStrasseAnlegen.getText(), kundenOrtAnlegen.getText(), Integer.parseInt(kundenPlzAnlegen.getText())));
				kundenNameAnlegen.clear();
				kundenOrtAnlegen.clear();
				kundenStrasseAnlegen.clear();
				kundenPlzAnlegen.clear();
			} catch (NumberFormatException e) {
				System.out.println("geht nicht !");
		}
    }
    
    @FXML
    void neueBestellungMitKunde(MouseEvent event) {    	
    	int row = KundenTabelle.getSelectionModel().getSelectedCells().get(0).getRow();
    	initBestellung.setKunde(KundenTabelle.getItems().get(row));
    	
    	tabPane.getSelectionModel().select(bestellen);
    }   

    public void belagHinzufügen(int belagId) {	
    	Preisberechnung.preisBerechnung(initBestellung.getPizzen().get(initBestellung.getPizzen().size()-1));
    	Preisberechnung.preisBerechnung(initBestellung);
    	initBestellung.getPizzen().get(initBestellung.getPizzen().size()-1).addBelag(KonstantInstanceSaver.getBelag(belagId));
    	aktualisierungBestellView();
    }
    
    public void getraenkHinzufügen(int getraenkId) {
    	initBestellung.getGetraenke().add(KonstantInstanceSaver.getGetraenk(getraenkId));
    	Preisberechnung.preisBerechnung(initBestellung);
    	aktualisierungBestellView();
    }
    
    public void pizzaAuswahl(String pizzaGr) {
    	initBestellung.getPizzen().add(new Pizza(KonstantInstanceSaver.getPizzaGroesse(pizzaGr)));
    	Preisberechnung.preisBerechnung(initBestellung.getPizzen().get(initBestellung.getPizzen().size()-1));
    	Preisberechnung.preisBerechnung(initBestellung);
    	aktualisierungBestellView();

    }
    
    public void aktualisierungBestellView(){
    	TreeItem<String> root = new TreeItem<>("Bestellung");
//    	bestellung.setCellValueFactory(TreeTableColumn.CellDataFeatures<Bestellung, String> param) ->
//    	new ReadOnlyStringWrapper(param.getValue().getValue().toString()));
    	
    	bestellung.setCellValueFactory((CellDataFeatures<Bestellung, String> param) -> 
        new ReadOnlyStringWrapper(param.getValue().getValue().toString())); 
    	
		ArrayList<Pizza> pizzen = initBestellung.getPizzen();
    	for(int i = 0; i < pizzen.size() ; i++){
    		ArrayList<Belag> belag = pizzen.get(i).getBelag();
    		System.out.println(pizzen.get(i).getBelag());
    		TreeItem<String> pizza = new TreeItem<>(pizzen.get(i).getPizzaGroesse().getGroesse());
    		for(int y = 0; y < belag.size(); y++) {
    			pizza.getChildren().add(new TreeItem<>(belag.get(y).getName()));
    			System.out.println(belag.get(y).getName());
    		}
    		root.getChildren().add(pizza);
    	}
    	
    	for(int i = 0; i <initBestellung.getGetraenke().size(); i++){
    		
    	}
//    	bestellungsAnzeige.setRoot(root);
    } 
}

//TreeItem<Bestellung> root = new TreeItem<>(new Bestellung("Bestellung", 0.00));
//root.setExpanded(true);
//bestellungsAnzeige.setRoot(root);
//bestellungsAnzeige.setShowRoot(false);

package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;
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
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class KundenController implements Initializable  {
	private Main mainApp;
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
    private Tab kunde;
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
    @FXML
    private TreeTableView<TreeTableItem> bestellungsAnzeige;
    @FXML
    private TreeTableColumn<TreeTableItem, String> bestellung;
    @FXML
    private TreeTableColumn<TreeTableItem, Double> Preis;
    @FXML
    private Label labelPizzaAuswählen;
    @FXML
    private Button neuePizza;
    @FXML
    private Button bestellungAnlegenButton;
    @FXML
    private Label kundenNameAnzeige;
    @FXML
    private Label labelGesamtAnzeige;
    @FXML
    private Label gesamtAnzeige;
    @FXML
    private GridPane neueBestellungen;
    @FXML
    private Tab kasse;
    @FXML
    private GridPane kundeAnzeigeKasse;
    @FXML
    private Pane belegKunde;
    @FXML
    private Pane belegFirma;
    @FXML
    private Button print;
    
    private static final String titleTxt = "Status Update";
    
    TreeItem<TreeTableItem> root = new TreeItem<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		root.setExpanded(true);
		bestellungsAnzeige.setRoot(root);
		bestellungsAnzeige.setShowRoot(false);
		
		labelGesamtAnzeige.setVisible(false);
		gesamtAnzeige.setVisible(false);
		kundenNameAnzeige.setVisible(false);
		bestellungAnlegenButton.setVisible(false);
		bestellungsAnzeige.setVisible(false);
		pizzaGroesse.setVisible(false);
		labelPizzaAuswählen.setVisible(false);	
		bestellButtonsEinfach.setVisible(false);
    	bestellButtonsSpeziel.setVisible(false);
    	bestellGetraenke.setVisible(false);
    	neuePizza.setVisible(false);
		KundenId.setCellValueFactory(new PropertyValueFactory<Kunde, Integer>("kundenNummer"));
		KundenName.setCellValueFactory(new PropertyValueFactory<Kunde, String>("name"));
		KundenStrasse.setCellValueFactory(new PropertyValueFactory<Kunde, String>("strasse"));
		KundenOrt.setCellValueFactory(new PropertyValueFactory<Kunde, String>("ort"));
		KundenPlz.setCellValueFactory(new PropertyValueFactory<Kunde, Integer>("plz"));
		kueche();
		kasse();
		
		ObservableList<String> pizzaGr = FXCollections.observableArrayList(KonstantInstanceSaver.getPizzaGroessen().keySet());
		Collections.sort(pizzaGr);
		Collections.reverse(pizzaGr);
		pizzaGroesse.setItems(pizzaGr);

		pizzaGroesse.valueProperty().addListener(new ChangeListener<String>() {
                    @Override public void changed(ObservableValue ov, String t, String pizzaGr) {
                    	if (pizzaGr != null){
                    		pizzaAuswahl(pizzaGr);
                        	bestellButtonsEinfach.setVisible(true);
                        	bestellButtonsSpeziel.setVisible(true);
                        	bestellGetraenke.setVisible(true);
                        	pizzaGroesse.setVisible(false);
                        	labelPizzaAuswählen.setVisible(false);
                        	neuePizza.setVisible(true);
                    	}
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
    	try {
				DAOFactory.getKundeDAO().insertKunde(new Kunde(kundenNameAnlegen.getText(), kundenStrasseAnlegen.getText(), kundenOrtAnlegen.getText(), Integer.parseInt(kundenPlzAnlegen.getText())));
				kundenNameAnlegen.clear();
				kundenOrtAnlegen.clear();
				kundenStrasseAnlegen.clear();
				kundenPlzAnlegen.clear();
			} catch (NumberFormatException e) {
		}
    }
    
    @FXML
    void neueBestellungMitKunde(MouseEvent event) {    	
    	int row = KundenTabelle.getSelectionModel().getSelectedCells().get(0).getRow();
    	initBestellung.setKunde(KundenTabelle.getItems().get(row));
    	
    	tabPane.getSelectionModel().select(bestellen);
		pizzaGroesse.setVisible(true);
		labelPizzaAuswählen.setVisible(true);	
		bestellungAnlegenButton.setVisible(true);
		bestellungsAnzeige.setVisible(true);
		kundenNameAnzeige.setVisible(true);
		labelGesamtAnzeige.setVisible(true);
		gesamtAnzeige.setVisible(true);
		
		kundenNameAnzeige.setText(initBestellung.getKunde().getName());
		kundenNameAnzeige.setStyle("-fx-font: 30 arial;");
    }   

    public void belagHinzufügen(int belagId) {	
    	initBestellung.getPizzen().get(initBestellung.getPizzen().size()-1).addBelag(KonstantInstanceSaver.getBelag(belagId));
    	Preisberechnung.preisBerechnung(initBestellung.getPizzen().get(initBestellung.getPizzen().size()-1));
    	Preisberechnung.preisBerechnung(initBestellung);
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
        TreeItem<TreeTableItem> root = new TreeItem<>();
    	root.setExpanded(true);
		bestellungsAnzeige.setRoot(root);
		bestellungsAnzeige.setShowRoot(false);

    	bestellung.setCellValueFactory((CellDataFeatures<TreeTableItem, String> param) -> 
        new ReadOnlyStringWrapper(param.getValue().getValue().getName()));
    	
    	Preis.setCellValueFactory(new Callback<CellDataFeatures<TreeTableItem, Double>, ObservableValue<Double>>(){
    		@Override
    		public ObservableValue<Double> call(CellDataFeatures<TreeTableItem,Double> param2){
    			return param2.getValue().getValue().getPreisProperty().asObject();
    		}
    	});
    	
		ArrayList<Pizza> pizzen = initBestellung.getPizzen();
    	for(int i = 0; i < pizzen.size() ; i++){
    		ArrayList<Belag> belag = pizzen.get(i).getBelag();
    		TreeItem<TreeTableItem> pizza = new TreeItem<>(new TreeTableItem("Pizza "+pizzen.get(i).getPizzaGroesse().getGroesse(),pizzen.get(i).getPreis()));
    		for(int y = 0; y < belag.size(); y++) {
    			pizza.getChildren().add(new TreeItem<>(new TreeTableItem(belag.get(y).getName(),belag.get(y).getPreis(pizzen.get(i).getPizzaGroesse().getGroesse()))));
    		}
    		pizza.setExpanded(true);
    		root.getChildren().add(pizza);
    	}
    	
    	for(int i = 0; i <initBestellung.getGetraenke().size(); i++){
    		TreeItem<TreeTableItem> getraenk = new TreeItem<>(new TreeTableItem("Getränk "+ initBestellung.getGetraenke().get(i).getName(),initBestellung.getGetraenke().get(i).getPreis()));
    		root.getChildren().add(getraenk);
    	}
    	
    	gesamtAnzeige.setText(Double.toString(initBestellung.getPreis()));
    	gesamtAnzeige.setStyle("-fx-font-weight: bold;");
    } 
    
    @FXML
    void neuePizza(MouseEvent event) {
    	
    	pizzaGroesse.setVisible(true);
    	pizzaGroesse.getSelectionModel().clearSelection();

		bestellButtonsEinfach.setVisible(false);
    	bestellButtonsSpeziel.setVisible(false);
    	bestellGetraenke.setVisible(false);
    	neuePizza.setVisible(false);
    }

    @FXML
    void bestellungAnlegen(MouseEvent event) {
    	
    	ArrayList<Pizza> pizzen = initBestellung.getPizzen();
    	
    	
    	DAOFactory.getBestellungkDAO().insertBestellung(initBestellung);
    	
    	for(int i = 0; i < pizzen.size(); i++){
    		DAOFactory.getPizzaDAO().insertPizza(pizzen.get(i), initBestellung.getId());
    	}
    	
    	ArrayList<Getraenk> getraenke = initBestellung.getGetraenke();
    	
    	for(int i = 0; i < getraenke.size(); i++){
    		DAOFactory.getGetraenkDAO().insertGetraenkBestellt(getraenke.get(i), initBestellung.getId());
    	}
    	
    	kueche();
    	initBestellung = new Bestellung();
    	bestellungsAnzeige.setRoot(null);
    	pizzaGroesse.getSelectionModel().clearSelection();
    	labelGesamtAnzeige.setVisible(false);
		gesamtAnzeige.setVisible(false);
		kundenNameAnzeige.setVisible(false);
		bestellungAnlegenButton.setVisible(false);
		bestellungsAnzeige.setVisible(false);
		pizzaGroesse.setVisible(false);
		labelPizzaAuswählen.setVisible(false);	
		bestellButtonsEinfach.setVisible(false);
    	bestellButtonsSpeziel.setVisible(false);
    	bestellGetraenke.setVisible(false);
    	neuePizza.setVisible(false);
    	tabPane.getSelectionModel().select(kunde);

    }
    
    public void kueche(){
    	
		ArrayList<Bestellung> bestellungen = new ArrayList<Bestellung>();
		bestellungen.addAll(DAOFactory.getBestellungkDAO().neueBestellungen());

		int z = 0;
		for(int spalte = 0; spalte < neueBestellungen.getColumnConstraints().size() ; spalte++) {
			for(int zeile = 0; zeile < neueBestellungen.getRowConstraints().size() ; zeile++) {
				try {
					final int bestellId = bestellungen.get(z).getId();
					
					ArrayList<Pizza> pizzen = new ArrayList<Pizza>();
					pizzen.addAll(DAOFactory.getPizzaDAO().findPizza(bestellId));
					
					ScrollPane scrollPane;
					scrollPane = new ScrollPane();
					scrollPane.setMaxWidth(600);
					scrollPane.setMaxHeight(200);
					
					String t = bestellungen.get(z).getKunde().getName() + "\n";

					for(int x = 0 ; x < pizzen.size() ; x++){
						t += "Pizza " + pizzen.get(x).getPizzaGroesse().getGroesse();
						if(pizzen.get(x).getBelag() != null){
							t += ": ";
							for(int y = 0 ; y < pizzen.get(x).getBelag().size() ; y++){
								t += pizzen.get(x).getBelag().get(y).getName() + ", ";
							}
							t = t.substring(0, t.length()-2);
							t += "\n";
						}
					} 
						
					Text text = new Text(t);
					text.setStyle("-fx-line-spacing: 0.3em;");
					text.setFont(new Font(20));
					scrollPane.setContent(text);
					scrollPane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					      @Override
					      public void handle(MouseEvent e) {
					    	  Alert alert = new Alert(AlertType.CONFIRMATION);
					    	  alert.setTitle(titleTxt);
					    	  String s = "Alles Fertig ?";
					    	  alert.setContentText(s);
					    	  
					    	  Optional<ButtonType> result = alert.showAndWait();
					    	  
					    	  if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
					    		  DAOFactory.getBestellungkDAO().updateBestellung(bestellId);
					    		  neueBestellungen.getChildren().clear();
					    		  kueche();
					    	  }
					      }
					    });
					neueBestellungen.add(scrollPane,spalte,zeile);
					z++;
				} catch (Exception e) {

				}
			}
		}
    }
    
    public void kasse(){
    	
    	ArrayList<Bestellung> bestellungen = new ArrayList<Bestellung>();
		bestellungen.addAll(DAOFactory.getBestellungkDAO().fertigBestellungen());

		for(int z = 0 ; z < kundeAnzeigeKasse.getColumnConstraints().size(); z++){
			for(int i = 0 ; i < bestellungen.size() ; i++){
				System.out.println(i);
				
				int bestellId = bestellungen.get(i).getId();
				
				Button button = new Button(bestellungen.get(i).getKunde().getName());
	
				button.setMaxWidth(bestellungen.get(i).getKunde().getName().length()*10);
				button.setMaxHeight(50);
				button.setAlignment(Pos.CENTER);
				button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			          @Override
			          public void handle(MouseEvent e) {
			        	  belegKunde(bestellId);
			          }
			        });
				kundeAnzeigeKasse.add(button, z, 0);
			}
		}
		
		
		
		
		for(int i = 0 ; i < bestellungen.size() ; i++){
			int bestellId = bestellungen.get(i).getId();
			
			ArrayList<Pizza> pizzen = new ArrayList<Pizza>();
			pizzen.addAll(DAOFactory.getPizzaDAO().findPizza(bestellId));

		}
    }
    
    public void belegKunde(int bestellId){
    	System.out.println(bestellId);
    }
}

package ru.sapteh.controller;

import com.sun.jdi.event.ClassUnloadEvent;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.models.Client;
import ru.sapteh.models.Gender;
import ru.sapteh.models.Manufacturer;
import javafx.collections.transformation.FilteredList;
import ru.sapteh.service.ClientImpl;
import ru.sapteh.service.GenderImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class MainWindowController {
    private SessionFactory factory;
    private DAO<Client, Integer> dao;
    private ObservableList<Client> observableList;
    Client client = new Client();
    ObservableList<Character> genders = FXCollections.observableArrayList('м','ж');
    private DAO<Gender, Integer> genderIntegerDAO;


    @FXML
    private Button buttonCreate;

    @FXML
    private Button createEdit;

    @FXML
    private TableView<Client> tableView;

    @FXML
    private TableColumn<Client, Integer> idColumn;

    @FXML
    private TableColumn<Client, String> firstnameColumn;

    @FXML
    private TableColumn<Client, String> lastnameColumn;

    @FXML
    private TableColumn<Client, String> patronymicColumn;

    @FXML
    private TableColumn<Client, LocalDate> birthdayColumn;

    @FXML
    private TableColumn<Client, LocalDate> registrationColumn;

    @FXML
    private TableColumn<Client, String> emailColumn;

    @FXML
    private TableColumn<Client, String> phoneColumn;

    @FXML
    private TableColumn<Client, Character> genderColumn;

    @FXML
    private TableColumn<Client, String> imageColumn;

    @FXML
    private ComboBox<Character> genderBox;

    @FXML
    private TextField searchTxt;

    @FXML
    private ComboBox<String> comboBoxGender;


    @FXML
    void initialize(){
        initData();
        tableView.setItems(observableList);
        ObservableList<String> observableLiss = FXCollections.observableArrayList("Фамилия", "Имя", "Отчество");
        comboBoxGender.setItems(observableLiss);

        idColumn.setCellValueFactory(clientIntegerCellDataFeatures -> new SimpleObjectProperty<>(clientIntegerCellDataFeatures.getValue().getId()));
        firstnameColumn.setCellValueFactory(clientStringCellDataFeatures -> new SimpleObjectProperty<>(clientStringCellDataFeatures.getValue().getFirstName()));
        lastnameColumn.setCellValueFactory(clientStringCellDataFeatures -> new SimpleObjectProperty<>(clientStringCellDataFeatures.getValue().getLastName()));
        patronymicColumn.setCellValueFactory(clientStringCellDataFeatures -> new SimpleObjectProperty<>(clientStringCellDataFeatures.getValue().getPatronymic()));
        birthdayColumn.setCellValueFactory(clientDateCellDataFeatures -> new SimpleObjectProperty<>(clientDateCellDataFeatures.getValue().getBirthday()));
        registrationColumn.setCellValueFactory(clientDateCellDataFeatures -> new SimpleObjectProperty<>(clientDateCellDataFeatures.getValue().getRegistrationDate()));
        emailColumn.setCellValueFactory(clientStringCellDataFeatures -> new SimpleObjectProperty<>(clientStringCellDataFeatures.getValue().getEmail()));
        phoneColumn.setCellValueFactory(clientStringCellDataFeatures -> new SimpleObjectProperty<>(clientStringCellDataFeatures.getValue().getPhone()));
        genderColumn.setCellValueFactory(clientCharacterCellDataFeatures -> new SimpleObjectProperty<>(clientCharacterCellDataFeatures.getValue().getGender().getCode()));
        imageColumn.setCellValueFactory(clientStringCellDataFeatures -> new SimpleObjectProperty<>(clientStringCellDataFeatures.getValue().getPhotoPath()));
        tableView.getSelectionModel().selectedItemProperty().addListener((obj, oldValue, newValue) ->{
            client = newValue;
        });
        search();
        genderBox.valueProperty().addListener((obj, oldValue, newValue) ->{
            FilteredList<Client> filteredList = new FilteredList<>(observableList, p ->
                    newValue.equals(p.getGender().getCode())
            );
            tableView.setItems(filteredList);
        });
    }

    @FXML
    void actionCreate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/create.fxml"));
        AnchorPane pane = loader.load();
        Stage stage = new Stage();
        stage.setTitle("CREATE");
        stage.setScene(new Scene(pane));
        CreateController createController = loader.getController();
        createController.setData(observableList);
        stage.show();
    }

    @FXML
    void actionDelete(ActionEvent event) {
        DAO<Client, Integer> dao1 = new ClientImpl(factory);
        dao1.delete(client);
    }

    @FXML
    void actionEdit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/edit.fxml"));
        AnchorPane pane = loader.load();
        Stage stage = new Stage();
        stage.setTitle("EDIT");
        stage.setScene(new Scene(pane));
        EditController editController = loader.getController();
        editController.setData(client, observableList);
        stage.show();
    }

    public void initData(){
        factory = new Configuration().configure().buildSessionFactory();
        dao = new ClientImpl(factory);
        observableList = FXCollections.observableArrayList();
        observableList.addAll(dao.findByAll());

        genderIntegerDAO = new GenderImpl(factory);
        genderBox.setItems(genders);
    }

   public void search() {
        searchTxt.textProperty().addListener((obj, oldValue, newValue) -> {
            FilteredList<Client> filteredList = new FilteredList<>(observableList, p -> {
                if (newValue == null) {
                    return true;
                }
                if (p.getFirstName().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                }
                if (p.getLastName().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                }
                if (p.getPatronymic().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                }
                    return false;
            });
           tableView.setItems(filteredList);
        });
   }

}

package ru.sapteh.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.models.Client;
import ru.sapteh.models.Gender;
import ru.sapteh.service.ClientImpl;
import ru.sapteh.service.GenderImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateController {
    private SessionFactory factory;
    private DAO<Client, Integer> clientIntegerDAO;
    private DAO<Gender, Integer> genderIntegerDAO;
    private ObservableList<Client> observableList;
    private ObservableList<Gender> genders;

    @FXML
    private TextField firstNameTxt;

    @FXML
    private TextField lastNameTxt;

    @FXML
    private TextField patronymicTxt;

    @FXML
    private DatePicker birthdayPicker;

    @FXML
    private DatePicker regDatePicker;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField phoneTxt;

    @FXML
    private TextField photoPathTxt;

    @FXML
    private ComboBox<Gender> genderBox;

    @FXML
    private Button buttonOK;

    @FXML
    void initialize(){
        initData();
        genderBox.setItems(genders);
    }

    @FXML
    void actionOK(ActionEvent event) throws ParseException {
        Client client = new Client();
        client.setFirstName(firstNameTxt.getText());
        client.setLastName(lastNameTxt.getText());
        client.setPatronymic(patronymicTxt.getText());
        client.setBirthday(birthdayPicker.getValue());
        client.setRegistrationDate(birthdayPicker.getValue());
        client.setEmail(emailTxt.getText());
        client.setPhone(phoneTxt.getText());
        client.setPhotoPath(photoPathTxt.getText());
        client.setGender(genderBox.getValue());
        clientIntegerDAO.create(client);
        observableList.addAll(client);
    }

    public void initData(){
        factory = new Configuration().configure().buildSessionFactory();
        clientIntegerDAO = new ClientImpl(factory);
        genderIntegerDAO = new GenderImpl(factory);
        observableList = FXCollections.observableArrayList();
        genders = FXCollections.observableArrayList();
        observableList.addAll(clientIntegerDAO.findByAll());
        genders.addAll(genderIntegerDAO.findByAll());
    }

    public void setData (ObservableList observableList){
        this.observableList = observableList;

    }

}

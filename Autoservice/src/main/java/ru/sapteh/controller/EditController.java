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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class EditController {
    private SessionFactory factory;
    private DAO<Client, Integer> clientIntegerDAO;
    private ObservableList<Client> clients;
    private DAO<Gender, Integer> genderIntegerDAO;
    private ObservableList<Gender> genders;
    private Client client;

    @FXML
    private TextField firstNameTxt;

    @FXML
    private TextField lastNameTxt;

    @FXML
    private TextField patronymicTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField phoneTxt;

    @FXML
    private TextField photoPathTxt;

    @FXML
    private ComboBox<Gender> genderBox;

    @FXML
    private DatePicker birthdayPicker;

    @FXML
    private DatePicker regDatePicker;

    @FXML
    private Button buttonOK;

    @FXML
    void initialize() throws ParseException {
        initData();
        genderBox.setItems(genders);
    }

    @FXML
    void actionOK(ActionEvent event) {
        client.setFirstName(firstNameTxt.getText());
        client.setLastName(lastNameTxt.getText());
        client.setPatronymic(patronymicTxt.getText());
        client.setBirthday(birthdayPicker.getValue());
        client.setRegistrationDate(regDatePicker.getValue());
        client.setEmail(emailTxt.getText());
        client.setPhone(phoneTxt.getText());
        client.setPhotoPath(photoPathTxt.getText());
        client.setGender(genderBox.getValue());
        clientIntegerDAO.update(client);
        clients.clear();
        clients.addAll(clientIntegerDAO.findByAll());
        buttonOK.getScene().getWindow().hide();
    }

    public void initData() throws ParseException {
        factory = new Configuration().configure().buildSessionFactory();
        clientIntegerDAO = new ClientImpl(factory);
        genderIntegerDAO = new GenderImpl(factory);
        clients = FXCollections.observableArrayList();
        genders = FXCollections.observableArrayList();
        clients.addAll(clientIntegerDAO.findByAll());
        genders.addAll(genderIntegerDAO.findByAll());

    }
    public void setData (Client client, ObservableList observableList)  {
        this.clients = observableList;
        this.client = client;
        firstNameTxt.setText(client.getFirstName());
        lastNameTxt.setText(client.getLastName());
        patronymicTxt.setText(client.getPatronymic());
        emailTxt.setText(client.getEmail());
        phoneTxt.setText(client.getPhone());
        photoPathTxt.setText(client.getPhotoPath());
        genderBox.setValue(client.getGender());
        birthdayPicker.setValue(client.getBirthday());
        regDatePicker.setValue(client.getRegistrationDate());
    }

}

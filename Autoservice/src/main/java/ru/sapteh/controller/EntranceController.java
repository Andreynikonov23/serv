package ru.sapteh.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.models.User;
import ru.sapteh.service.UserServ;

import java.awt.*;
import java.io.IOException;

public class EntranceController {
    private SessionFactory factory;
    private DAO<User, Integer> dao;
    private ObservableList <User> observableList;
    User user = new User();

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label defaultLbl;
    @FXML
    private Button buttonOk;

    @FXML
    void actionOk(ActionEvent event) throws IOException {
        String login = loginField.getText();
        String password = passwordField.getText();
        for (User user : observableList) {
            if (login.equalsIgnoreCase(user.getLogin()) && password.equals(user.getPassword())) {
                Parent root = FXMLLoader.load(getClass().getResource("/view/mainWindow.fxml"));
                Stage stage = new Stage();
                stage.setTitle("ВХОД");
                Image image = new Image(String.valueOf(getClass().getResource("/image/service_logo.png")));
                stage.setScene(new Scene(root));
                stage.show();
                buttonOk.getScene().getWindow().hide();
            } else
                defaultLbl.setText("Хуй");
        }
    }


    @FXML
    void initialize(){
        initData();

    }
    public void initData(){
        factory = new Configuration().configure().buildSessionFactory();
        dao = new UserServ(factory);
        observableList = FXCollections.observableArrayList();
        observableList.addAll(dao.findByAll());
    }

}

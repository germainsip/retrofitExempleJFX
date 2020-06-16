package org.afpa.GUI;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import org.afpa.DAL.ApiService;
import org.afpa.DAL.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RetroexempleController implements Initializable {
    public TableView<Post> receptionTab;
    public TableColumn idCol;
    public TableColumn userIdCol;
    public TableColumn titreCol;
    public TableColumn texteCol;
    public Button rech;
    ObservableList<Post> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        chargeTableau();

    }

    private void chargeTableau() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Post>> call = apiService.getPosts();
        boolean flag;
        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                observableList = FXCollections.observableArrayList(response.body());
                idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
                titreCol.setCellValueFactory(new PropertyValueFactory<>("title"));
                texteCol.setCellValueFactory(new PropertyValueFactory<>("text"));
                System.out.println(response.code());
                receptionTab.setItems(observableList);

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setContentText("Erreur de connection!!!");
                    alert.showAndWait();
                });


            }
        });

    }

    public void recharger(ActionEvent actionEvent) {
        chargeTableau();
    }
}

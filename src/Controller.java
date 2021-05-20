import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.DevKG;
import view.JobViewCell;
import model.Vacancy;

import java.util.function.Predicate;


public class Controller {

    @FXML
    private RadioButton javaRadio;

    @FXML
    private ToggleGroup T1;

    @FXML
    private RadioButton phpRadio;

    @FXML
    private RadioButton cRadio;

    @FXML
    private RadioButton reactRadio;

    @FXML
    private RadioButton nodeRadio;

    @FXML
    private RadioButton JSRadio;

    @FXML
    private RadioButton iOSRadio;

    @FXML
    private RadioButton androidRadio;

    @FXML
    private Label headLabel;

    @FXML
    private Label avgLabel;


    @FXML
    private Button clearButton;

    @FXML
    private ListView<Vacancy> listView;

    private final ObservableList<Vacancy> dataList = FXCollections.observableArrayList();


    public void initialize() {
        DevKG.fillSampleData(dataList);
        listView.setCellFactory(jobListView -> new JobViewCell());
        listView.setItems(dataList);

        T1.selectedToggleProperty().addListener((ob, o, n) -> {
            RadioButton rb = (RadioButton)T1.getSelectedToggle();

            if (rb != null) {
                headLabel.setText("Search results for " + rb.getText()+ " developers");
            }

            if (javaRadio.isSelected()) {
                FilteredList<Vacancy> javaItems = new FilteredList<>(dataList);
                listView.setItems(javaItems);
                Predicate<Vacancy> containsJava = i -> i.getTitle().contains("Java");
                javaItems.setPredicate(containsJava);
            } else if (phpRadio.isSelected()) {
                FilteredList<Vacancy> phpItems = new FilteredList<>(dataList);
                listView.setItems(phpItems);
                Predicate<Vacancy> filter = i -> i.getTitle().contains("PHP");
                phpItems.setPredicate(filter);
            } else if (cRadio.isSelected()) {
                FilteredList<Vacancy> cItems = new FilteredList<>(dataList);
                listView.setItems(cItems);
                Predicate<Vacancy> containsC = i -> i.getTitle().contains("C#");
                cItems.setPredicate(containsC);
            } else if (reactRadio.isSelected()) {
                FilteredList<Vacancy> reactItems = new FilteredList<>(dataList);
                listView.setItems(reactItems);
                Predicate<Vacancy> containsReact = i -> i.getTitle().contains("React");
                reactItems.setPredicate(containsReact);
            } else if (nodeRadio.isSelected()) {
                FilteredList<Vacancy> nodeItems = new FilteredList<>(dataList);
                listView.setItems(nodeItems);
                Predicate<Vacancy> containsNode = i -> i.getTitle().contains("Node");
                nodeItems.setPredicate(containsNode);
            } else if (JSRadio.isSelected()) {
                FilteredList<Vacancy> JSItems = new FilteredList<>(dataList);
                listView.setItems(JSItems);
                Predicate<Vacancy> containsJS = i -> i.getTitle().contains("Javascript");
                JSItems.setPredicate(containsJS);
            } else if (iOSRadio.isSelected()) {
                FilteredList<Vacancy> iOSItems = new FilteredList<>(dataList);
                listView.setItems(iOSItems);
                Predicate<Vacancy> containsiOS = i -> i.getTitle().contains("iOS");
                iOSItems.setPredicate(containsiOS);
            } else if (androidRadio.isSelected()) {
                FilteredList<Vacancy> androidItems = new FilteredList<>(dataList);
                listView.setItems(androidItems);
                Predicate<Vacancy> containsAndroid = i -> i.getTitle().contains("Android");
                androidItems.setPredicate(containsAndroid);
            }
        });

    }

    @FXML
    void onClickClear(ActionEvent event) {
        System.out.println("c");
        FilteredList<Vacancy> clearFilter = new FilteredList<>(dataList);
        listView.setItems(clearFilter);
        clearFilter.setPredicate(null);
    }

    @FXML
    void onClickAbout(ActionEvent event) {
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION,"AlyNa Rahim");
        informationAlert.setTitle("About the App");
        informationAlert.setHeaderText("About the app - Copyrights");
        informationAlert.setContentText("Created on: 20-May-2021\n" +
                "By: AlyNa Rahim\n" +
                "Contact: +92 3352992280\n" +
                "Email: alyna.rahim_2021@ucentralasia.org");
        informationAlert.showAndWait();
    }

    @FXML
    void onClickExit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
}



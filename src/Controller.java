import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.DevKG;
import view.JobViewCell;
import model.Vacancy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;


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
    private RadioButton netRadio;

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

        T1.selectedToggleProperty().addListener(this::changed);

    }

    @FXML
    void onClickClear(ActionEvent event) {
        T1.selectToggle(null);
        FilteredList<Vacancy> clearFilter = new FilteredList<>(dataList);
        listView.setItems(clearFilter);
        clearFilter.setPredicate(null);
        headLabel.setText("Search results for developer jobs");
        avgLabel.setText("Median Salary");
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


    private void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {
        RadioButton rb = (RadioButton) T1.getSelectedToggle();

        if (rb != null) {
            headLabel.setText("Search results for " + rb.getText() + " developers");
        }

        if (javaRadio.isSelected()) {
            averageSalary("Java");
        } else if (phpRadio.isSelected()) {
            averageSalary("PHP");
        } else if (cRadio.isSelected()) {
            averageSalary("C#");
        } else if (netRadio.isSelected()) {
            averageSalary(".NET");
        } else if (nodeRadio.isSelected()) {
            averageSalary("Node");
        } else if (JSRadio.isSelected()) {
            averageSalary("JavaScript");
        } else if (iOSRadio.isSelected()) {
            averageSalary("iOS");
        } else if (androidRadio.isSelected()) {
            averageSalary("Android");
        }
    }

    public FilteredList<Vacancy> filterList (String text) {
        FilteredList<Vacancy> items = new FilteredList<>(dataList);
        listView.setItems(items);
        Predicate<Vacancy> containsText = i -> i.getTitle().contains(text);
        items.setPredicate(containsText);
        return items;
    }

    public void averageSalary (String text){
        String usdString = "";
        String kgsString = "";
        double usdMedian = 0;
        double kgsMedian = 0;

        FilteredList<Vacancy> items = filterList(text);

        for (int value = 0; value < items.size(); value++) {
            if (items.get(value).getSalary().contains("USD")) {
                usdString += (items.get(value).getSalary().strip().replace(" USD в месяц", "")
                        .replace("От ", "").replace(" USD за проект", "")) + " - ";

            } else if (items.get(value).getSalary().contains("KGS")) {
                kgsString += (items.get(value).getSalary().strip().replace(" KGS в месяц", "")
                        .replace("От ", "")) + " - ";
            }
        }

        // String list for usd and kgs
        if (!usdString.isEmpty()) {
            List<String> usdList = Arrays.asList(usdString.split(" - "));
            List<Integer> usdIntList = usdList.stream()
                    .map(u -> Integer.parseInt(u))
                    .collect(Collectors.toList());
            Collections.sort(usdIntList);
            usdMedian = usdIntList.get(usdIntList.size() / 2);
        }

        if (!kgsString.isEmpty()){
            List<String> kgsList = Arrays.asList(kgsString.split(" - "));
                List<Integer> kgsIntList = kgsList.stream()
                        .map(k -> Integer.parseInt(k))
                        .collect(Collectors.toList());
                Collections.sort(kgsIntList);
                kgsMedian = kgsIntList.get(kgsIntList.size() / 2);
            }

        avgLabel.setText("Median Salary: " + usdMedian + " USD and " + kgsMedian + " KGS");
    }

}



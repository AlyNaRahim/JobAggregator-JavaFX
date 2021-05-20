package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Vacancy;

import java.io.IOException;

public class JobViewCell extends ListCell<Vacancy> {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label titleLabel;

    @FXML
    private Label companyLabel;

    @FXML
    private Label salaryLabel;

    @FXML
    private Label countryLabel;

    @FXML
    private ImageView img;

    @FXML
    private Button applyButton;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Vacancy vacancy, boolean empty) {
        super.updateItem(vacancy, empty);

        if (empty || vacancy == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/view/customJobViewCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            titleLabel.setText(String.valueOf(vacancy.getTitle()));
            companyLabel.setText(String.valueOf(vacancy.getCompanyName()));
            salaryLabel.setText(String.valueOf(vacancy.getSalary()));
            countryLabel.setText(String.valueOf(vacancy.getCountry()));

            setText(null);
            setGraphic(anchorPane);
        }
    }
//    public void onClickApply{
//        application.getHostServices().showDocument("https://devkg.com/ru/jobs");
//    }
}
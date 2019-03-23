package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;

public class Controller implements Initializable {
    @FXML
    private TextField studentCode;

    @Override
    public void initialize(URL location, ResourceBundle resource) {

    }

    public void noteTimeOfCheckout(ActionEvent actionEvent) throws IOException {
        String filePath = "checkout.csv";
        String csvSplitBy = ",";
        String code = studentCode.getText();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        char[] time = timeStamp.toCharArray();
        String line = code + csvSplitBy + time[4] + time[5] + csvSplitBy + time[6] + time[7] + csvSplitBy + time[9] + time[10] + csvSplitBy + time[11] + time[12];
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
            bw.append(line + System.getProperty("line.separator"));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void noteTimeOfCheckIn(ActionEvent actionEvent) throws IOException {
        String filePath = "checkin.csv";
        String csvSplitBy = ",";
        String code = studentCode.getText();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        char[] time = timeStamp.toCharArray();
        String line = code + csvSplitBy + time[4] + time[5] + csvSplitBy + time[6] + time[7] + csvSplitBy + time[9] + time[10] + csvSplitBy + time[11] + time[12];
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
            bw.append(line + System.getProperty("line.separator"));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

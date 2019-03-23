package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField codeInput;

    @FXML
    private Label studentName;

    @FXML
    private Label studentCode;

    @FXML
    private Label studentAttendence;

    @FXML
    private Label message;

    @Override
    public void initialize(URL location, ResourceBundle resource) {

    }

    public void checkCode(javafx.event.ActionEvent actionEvent) {
        if (!checkForWrongInput()) {
            String filePath = "students.csv";
            File myFile = new File(filePath);
            if (myFile.exists()) {
                String line = "";
                String csvSplitBy = ",";
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(filePath));
                    while ((line = br.readLine()) != null) {
                        String[] student = line.split(csvSplitBy);
                        if (Integer.parseInt(student[0]) == Integer.parseInt(codeInput.getText())) {
                            studentName.setText(student[1] + " " + student[2]);
                            studentCode.setText(student[0]);
                            studentAttendence.setText(student[3]);
                            break;
                        }
                    }
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            message.setText("Please enter a valid code");
        }
    }

    private boolean checkForWrongInput() {
        //Check if the fields are empty
        if(this.codeInput.getText().isEmpty())
            return true;
        //Check for length
        if(this.codeInput.getText().length() != 3)
            return true;
        //Check for invalid characters
        for(char c: this.codeInput.getText().toCharArray())
            if(!Character.isDigit(c))
                return true;

        return false;
    }
}
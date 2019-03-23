package sample;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    private TextField lastName;

    @FXML
    private TextField firstName;

    @FXML
    private TextField groupe;

    @FXML
    private Label code;


    @Override
    public void initialize(URL location, ResourceBundle resource) {

    }

    public void addStudent(javafx.event.ActionEvent actionEvent) throws IOException {
        String filePath = "newpeople.csv";
        String csvSplitBy = ",";
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        try {
            String line;
            String newLine = "";
            int studentCode = 0;
            while ((line = br.readLine()) != null) {
                String[] student = line.split(csvSplitBy);
                studentCode = Integer.parseInt(student[0]) + 1;
                newLine = studentCode + csvSplitBy + lastName.getText() + csvSplitBy + firstName.getText() + csvSplitBy + groupe.getText() + System.getProperty("line.separator");
            }
            br.close();
            code.setText(Integer.toString(studentCode));
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
            bw.append(newLine);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

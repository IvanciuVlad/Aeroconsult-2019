package sample;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public void addAttendence(javafx.event.ActionEvent actionEvent) throws IOException {
        if (!checkForWrongInput()) {
            noteTimeOfCheckIn();
            String line = "";
            String csvSplitBy = ",";
            String filePath = "students.csv";
            String tempPath = "temp.csv";
            // creates a temporary file in which most the old cvs file is copied
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(tempPath));
                while ((line = br.readLine()) != null) {
                    String currentLine = "";
                    String[] student = line.split(csvSplitBy);
                    //except when it finds the desired student where it replaces attendence
                    if (Integer.parseInt(student[0]) == Integer.parseInt(codeInput.getText())) {
                        student[3] = Integer.toString(Integer.parseInt(student[3]) + 1);
                        currentLine = student[0] + csvSplitBy + student[1] + csvSplitBy + student[2] + csvSplitBy + student[3];

                        studentName.setText(student[1] + " " + student[2]);
                        studentCode.setText(student[0]);
                        studentAttendence.setText(student[3]);
                    } else {
                        currentLine = line;
                    }
                    bw.write(currentLine + System.getProperty("line.separator"));
                }
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // we delete the old file and replace with the temp file
            br.close();
            File myFile = new File(filePath);
            File tempFile = new File(tempPath);
            boolean delSuccess = myFile.delete();
            boolean renameSuccess = tempFile.renameTo(myFile);
            System.out.println("File deleted? "+delSuccess);
            System.out.println("File renamed? "+renameSuccess);
            delSuccess = tempFile.delete();
            System.out.println("File deleted? "+delSuccess);
        } else {
            message.setText("Please enter a valid input");
        }
    }

    public void deleteAttendence(javafx.event.ActionEvent actionEvent) throws IOException{
        if (!checkForWrongInput()) {
            String line = "";
            String csvSplitBy = ",";
            String filePath = "students.csv";
            String tempPath = "temp.csv";

            BufferedReader br = new BufferedReader(new FileReader(filePath));
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(tempPath));
                while ((line = br.readLine()) != null) {
                    String currentLine = "";
                    String[] student = line.split(csvSplitBy);
                    if (Integer.parseInt(student[0]) == Integer.parseInt(codeInput.getText())) {
                        student[3] = Integer.toString(Integer.parseInt(student[3]) -1);
                        currentLine = student[0] + csvSplitBy + student[1] + csvSplitBy + student[2] + csvSplitBy + student[3];

                        studentName.setText(student[1] + " " + student[2]);
                        studentCode.setText(student[0]);
                        studentAttendence.setText(student[3]);
                    } else {
                        currentLine = line;
                    }
                    bw.write(currentLine + System.getProperty("line.separator"));
                }
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            br.close();
            File myFile = new File(filePath);
            File tempFile = new File(tempPath);
            boolean delSuccess = myFile.delete();
            boolean renameSuccess = tempFile.renameTo(myFile);
            System.out.println("File deleted? "+delSuccess);
            System.out.println("File renamed? "+renameSuccess);

        } else {
            message.setText("Please enter a valid input");
        }
    }

    private void noteTimeOfCheckIn() throws IOException{
        String filePath = "checkin.csv";
        String csvSplitBy = ",";
        String code = codeInput.getText();
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

    public void noteTimeOfCheckOut(javafx.event.ActionEvent actionEvent) throws IOException {
        String filePath = "checkout.csv";
        String csvSplitBy = ",";
        String code = codeInput.getText();
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
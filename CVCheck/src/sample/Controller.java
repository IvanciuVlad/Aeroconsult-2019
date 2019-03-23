package sample;
import java.awt.*;
import java.io.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;



public class Controller implements Initializable {
    @FXML
    private TextField studentCode;

    @FXML
    private Label message;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
    }

    public void openCV(ActionEvent actionEvent) {
        if(!checkForWrongInput()) {
            message.setText(""); //resets label
            File student = findStudent();

            if (Desktop.isDesktopSupported()) {
                try {
                    if(student.exists()) //checks if pdf exists otherwise program throws error
                        Desktop.getDesktop().open(student);//will open pdf in default windows application
                    else
                        message.setText("The file does not exists");
                } catch (IOException ex) {
                    // no application registered for PDFs
                }
            }
        } else {
            message.setText("Enter a valid code"); //warning for user if wrong input
        }
    }

    public void saveCV(ActionEvent actionEvent) {
        if(!checkForWrongInput()) {
            message.setText("");
            File student = findStudent();
            String dest = System.getProperty("user.home") + "/Desktop/CVuri/" + student.getName() + ".pdf";
            File to = new File(dest);//creates destination file
            try {
                copyFile(student, to);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            message.setText("Enter a valid code"); //warning for user if wrong input
        }
    }
    private static void copyFile( File from, File to ) throws IOException {
        Files.copy( from.toPath(), to.toPath() );//copies file from one folder to another
    }

    private File findStudent() {
        File folder = new File("studenti/");//pulls a folder
        File[] listOfFiles = folder.listFiles();//creates an array made of the folder's files
        //Checks each file for the 3 digit substring and then returns it
        int i;
        for (i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains(studentCode.getText())) {
                System.out.println("File " + listOfFiles[i].getName());
                break;
            }
        }
        message.setText("The file does not exists");
        return listOfFiles[i];
    }

    private boolean checkForWrongInput(){
        //Check if the fields are empty
        if(this.studentCode.getText().isEmpty())
            return true;
        //Check for length
        if(this.studentCode.getText().length() != 3)
            return true;
        //Check for invalid characters
        for(char c: this.studentCode.getText().toCharArray())
            if(!Character.isDigit(c))
                return true;

        return false;
    }
}

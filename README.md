# Aeroconsult-2019
4 different aplications made for Euroavia Bucharest's job fair


It consists of 4 JavaFX applications. All of them use a 3 digit id code which was sent to the students before the event.

CVCheck:
  - it is used for companies to be able to visualize the CVs of students who signed up and take them on an USB stick if they wanted to
  - it consists of a text field and 2 buttons;
  - the 1st button will open the CV in the the default pdf application and the second will copy-paste it to a folder on the desktop
  - the CVs are saved in a folder named 'studenti/' and named under the format 'code_LastName_FirstName.pdf'
  
  
Attendence:
  - logs the time of entering and exiting for any students;
  - it has a text field and 2 buttons for enterign and exiting;
  - when presed they will log into a file called 'checkin.csv' or 'checkout.csv' the student code, the day, the hour and the minute;
  
Attendece&Registration:
  - name is not representative for what it does it simply checks the student code with the name, so the team at check in is sure they give the badge to the right person;
  - it takes the student's code and shows the name which is read from a csv file
  
CheckIn:
  - for students who didn't sign up 
  - it logs their name and university groupe into a csv file and gives them a 3 digit code to use for attendence

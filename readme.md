## Effort Logger

## Documentation
[Phase 6 Submission Template V1.pdf](https://github.com/BitwiseBenjamin/Project-Managment-GUI/files/11730531/Phase.6.Submission.Template.V1.pdf)

## Project Description

- Login: This page takes a username and password from the user, authenticates it with an SQL database and authorizes the user with the correct role and user session.
<img width="521" alt="Screenshot 2023-06-13 at 11 14 23 AM" src="https://github.com/BitwiseBenjamin/Project-Managment-GUI/assets/114360780/d5bbf1a7-edf6-45bb-be9a-6424531644c6">

- Project Overview: The project overview page gives the user a overview of all the projects they are apart of part of.
<img width="792" alt="Screenshot 2023-06-13 at 11 15 46 AM" src="https://github.com/BitwiseBenjamin/Project-Managment-GUI/assets/114360780/8ede5569-628b-4eef-94ab-ab4cb5989857">

- Data Analysis: This page takes the data stored in SQL for each project and displays in a graph or list to allow managers to keep track of the projects progress.
<img width="917" alt="Screenshot 2023-06-13 at 11 16 49 AM" src="https://github.com/BitwiseBenjamin/Project-Managment-GUI/assets/114360780/df1b4062-c678-4975-8c1f-36361950bb5e">

- Effort and Defect Logging:
- Efeort and Defect Updating:


-- RUN INSTRUCTIONS --
1. Open project in Eclipse
2. Right click on project within Eclipse -> Build path -> Configure Build Path
3. Navigate to the Libraries tab
4. Remove all JavaFX JARs (these will be added from your local computer). ENSURE THAT THE sqlite-jdbc-3.41.0.1.jar IS NOT REMOVED!
5. Click Add External JARs
6. Navigate to your install folder of JavaFX SDK
7. Navigate to the /lib folder
8. Add all the JARs in that folder
9. Hit Ok -> Apply and Close
10. Right click on the project within Eclipse -> Run as -> Run configurations
11. Ensure that the Main class: field has UI.LoginPage as the main class. If not, hit Search... and select it under src/UI/LoginPage.java
12. Navigate to the Arguments tab
13. Change the VM arguments: field to the following:
 --module-path "your-path-to\javafx-sdk-19.0.2.1\lib" --add-modules javafx.controls,javafx.fxml
 The path should be to the same lib folder that you got the JavaFX JARs from in step 8
14. Ensure that "Use the -XX:+ShowCodeDetailsInExceptionMessages argument when launching" is checked
15. Ensure that "Use the -XstartOnFirstThread argument when launching with SWT" is NOT checked.
16. Click apply and run!

-- IMPORTANT LOGIN INFORMATION --
There are two users to login with:
Username: AdrianName
Password: password
and
Username: Alphamort
Password: Password2

These users can view different projects, so please keep that in mind!

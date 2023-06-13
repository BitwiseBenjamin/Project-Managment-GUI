## Effort Logger

## Documentation
[Phase 6 Submission Template V1.pdf](https://github.com/BitwiseBenjamin/Project-Managment-GUI/files/11730531/Phase.6.Submission.Template.V1.pdf)

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

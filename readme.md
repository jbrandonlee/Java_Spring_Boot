# Instructions

1. Open 'Spring Tool Suit 4'

2. Ensure that JDK-17 is correctly configured

3. Files > Import > Existing Maven Project, Select Folder.
- It should automatically detect a POM.xml file
- After importing, the project should appear in the package explorer.

4. Ensure MySQL service is running on your PC

5. Edit 'applications.properties' file with your username and password for your SQL server. For example:
- spring.datasource.username=root
- spring.datasource.username=password

6. Create Database called 'lms' using your Database Explorer of course (e.g. HeidiSQL), and ensure that it is running on Port 3306.

7. If you are running this AFTER 10 Jan 2024:
- You will have to comment out or change the dates of Leaves BEFORE your current date under CommandLineRunner.
- As we ensure that all leaves can only be created in the 'future', the validation will fail for those Leaves and the database cannot be initialized with mock data.

8. Right-Click the Project > 'Run As' > 'Spring Boot App'.

9. Using your browser, go to http://localhost:8080/ for the app's login page. 
- Login with username 'boss' for an account with Admin, Manager, and Staff privileges.
- Login with username 'manager1' for an account with Manager and Staff privileges.
- Login with username 'staff1' for an account with only Staff privileges.
- Password is 'password1' for all accounts.
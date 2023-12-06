# Beauty Salon App 💅
![Project Status](https://img.shields.io/badge/status-complete-brightgreen)
![License](https://img.shields.io/badge/license-SETU-blue.svg)

## 	💠[Description](##Description)
This project is a simple management app created using Gradle Build Software and the Kotlin programming language.
> "Kotlin is designed to be expressive, concise and powerful. iIt's like a breath of fresh air for developers" - Andrey Breslav, Kotlin Lead Language Developer.

## 	💠[Table of Contents](##TableofContents)
1. 🔹Introduction
2. 🔹Features
3. 🔹Accessibility
4. 🔹Usage
5. 🔹Support
6. 🔹Contribution
7. 🔹Guidelines
8. 🔹Acknowledgements


## 	💠[Introduction](##Introduction)
This application uses a simple menu system paired with a utility called 'ScannerInput' to give users the choice between:

- Adding clients
- Listing clients
- Updating clients
- Deleting clients
- Clearing all clients
- Checking if there are clients
- Searching for clients by their first name, id, last name, street, county, email, phone or allergy
- Adding appointments
- Listing confirmed appointments
- Updating appointments
- Deleting appointments
- Searching appointments by their Id, time, date, treatment, cost, rating
- Exiting the app

The 'ScannerInput' class avails of the Java.Util.Scanner class and ensures that in the event the user enters the wrong datatype, they are prompted to enter the correct datatype and thus the program doesn't crash and their error is handled delicately. The utility also allows the program to bypass a Java Scanner class bug where the class doesn't flush its buffers correctly after reading an Int or Double. I created this project because:

- I wanted to become more proficient in operating the Gradle Build System
- I wanted to become even more familiar with using IntelliJ (the IDE that the program was built in). I built the project in IntelliJ because IntelliJ was actually created in 2011 by Jetbrains, the creators of Kotlin.
- I wanted to fine-tune my Kotlin programming skills because:
    - I have built projects in the Java programming language, another object-oriented language. Kotlin is interoperable with Java, runs on the Java Virtual Machine          and even relies on some Java Class Libraries.
    - kotlin is an evolution of Java but is more concise with a cleaner syntax.
    - kotlin offers null safety.
    - kotlin is the 2nd most popular JVM language.
    - kotlin is often used in android development, server-side development and mobile multiplatform development.
    - kotlin is used by many large companies including Netflix, Trello, Uber and Pinterest
 
   ## 💠[Features](##Features)
This project adheres to the Single Responsibility Principle by ensuring each set of Kotlin classes/files handle only one unique responsibility:
- The 'Main' file handles the code which relates to user input.
- The 'Client' class handles the code which relates to Client objects.
- The 'Appointment' class handles the code which relates to Appointment objects.
- The 'ClientApi' class handles the code which relates to an ArrayList which holds a number of Client objects.
- The 'ClientApiTest' class handles the code which relates to tests carried out on the functions in the 'ClientApi' class.
- The 'TreatmentUtilityTest' class handles the code which relates to tests carried out on the functions in the 'TreatmentUtility' object.
- The 'DateUtilityTest' class handles the code which relates to tests carried out on the functions in the 'DateUtility' object.
- The 'EmailUtilityTest' class handles the code which relates to tests carried out on the functions in the 'EmailUtility' object.
- The 'UtilitiesTest' class handles the code which relates to tests carried out on the functions in the 'Utilities' object.
- The 'Serializer' interface class, 'XML Serializer' file and'JSON Serializer' file handle code which relates to saving notes to and loading notes from a persistent storage file.
- The 'TreatmentUtility' object handles the code which relates to validation on user input of the noteCategory field.
- The 'DateUtility' object handles the code which relates to validation on user input of the date fields.
- The 'EmailUtility' object handles the code which relates to validation on user input of the noteStatus field.
- The 'ValidateInputClass' object handles the code which relates to ensuring prompts entered to users return valid responses.
- The 'Utilities' object handles the code which relates to ensuring indexes entered by users are within specific ranges.
  
The project is based on the Model View Presenter (MVP) architecture.
- The Models are the 'Client' and 'Appointment' classes as they store the application's data.
- The View is the 'Main' class as it
  - acts as a user interface
  - displays the Model
  - routes user commands to the Presenter to act upon the Model
- The Presenter is the 'ClientApi' class as it fetches the data from the Model and formats it for display in the View.

This application consists of:
- Functions in the 'Main.kt' file
  - mainMenu() to display a menu for user input
  - runMenu() to loop the mainMenu() to process the user's input
  - main() to run the program and call the runMenu() function
  - addClient() to allow users to add notes to the system
  - listClients() to allow users to avail of the options between listing all clients, all paid client and all unpaid clients
  - updateClient() to allow users to update clients that exist in the system
  - deleteClient() to allow users to delete clients from the system
  - clearAllClients() to allow users to delete all clients from the system
  - checkIfThereAreClients to allow users to check if there are clients in the system
  - functions to allow users to search for clients by different attributes they have
  - exitApp() to allow users to exit the application
  - load() to load data
  - save() to save data
    
- A data class called 'Client' in the 'Client.kt' class to represent single client data classes in the application. The data class consists of the following fields:
  - firstName(of type String)
  - lastName(of type String)
  - phone(of type Int)
  - hasPaid(of type Boolean)
  - street(of type String)
  - county(of type String)
  - allergy(of type String)
  - a mutable set of appointments
- A data class called 'Appointment' in the 'Appointment.kt' class to represent single appointment data classes in the application. The data class consists of the following fields:
  - time(of type String)
  - date(of type String)
  - rating(of type Int)
  - isConfirmed (of type Boolean)
  - treatment(of type String)
  - cost(of type Int)
      
- An arrayList called 'clients' in the 'ClientApi.kt' class to hold a number of Client data classes. The class consists of functions to carry out the following actions:
    - Adding clients
    - Listing clients
    - Updating clients
    - Deleting clients
    - Clearing all clients
    - Checking if there are clients
    - Searching for clients by their first name, id, last name, street, county, email, phone or allergy
 - A mutable set called 'appointments' in the 'Client.kt' class to hold a number of Appointment data classes. The set consists of functions to carry out the following actions:
    - Adding appointments
    - Listing confirmed appointments
    - Updating appointments
    - Deleting appointments
    - Searching appointments by their Id, time, date, treatment, cost, rating
    - get an appointment's id
- A series of tests in the 'NoteApiTest.kt' class to verify the functions in the 'notes' arrayList work using the JUnit5 testing framework.
- JSON and XML persistence files and methods to load clients to and save clients in a persistent storage file.
- Utilities are used to include user validation on email, treatment, date, phone, rating, time


## 💠[Accessibility](##Accessibility)
 Accessibility is implemented in the app in the following ways:
- all notes are written in simple language 
- emojis created using emojipedia and unicode escape sequence variables are placed in front of and after the instructions in the main menu in order to make them easier to comprehend
- colours created using ANSI escape sequences are used to differentiate between different actions, events and sentences in the application

## 💠[Usage](##Usage)
As the app was written in the Kotlin programming language, users can avail of the following software to run the application:
- IntelliJ Idea
- Android Studio
- Visual Studio Code (with Kotlin extensions)
- Netbeans (with Kotlin plugin)
- Eclipse (with Kotlin plugin)


## 💠[Support](##Support)
To get assistance with the app, make enquiries relating to the app or to provide feedback about the app, users can either:
- message the 'SylviaMartin1' Github account
- email the '20102981@mail.wit.ie' email address


## 💠[Contribution](##Contribution)
- Create your own copy of the repository using the 'Fork' button on the top right corner
- Create a new branch for your changes and give it a descriptive name that outlines the bug or feature you wish to change. ➡️command: *git checkout -b*
- Create your changes
- Commit your changes ➡️command: *git commit -m*
- Push your changes ➡️command: *git push*
- Submit a pull request from your branch to the main repository and describe the changes you made and explain why they are useful 
- Once your changes have been reviewed and approved, they will be merged into the main repository and you will be credited in the readMe file


## 💠[Guidelines](##Guidelines)
Contributions to the project are welcomed and encouraged. However, to contribute you must agree to the following:
> [!NOTE]
> Proper attribution to the project and its original authors is required.

> [!IMPORTANT]
> Contributions should be made openly and transparently. Secretive or proprietary development is not permitted.

> [!WARNING]
> Contributions do not grant ownership of the project or the ability to claim it as one's own.

📍**Thank you for contributing to my open-source project!** :smile: :thumbsup: 

## 💠[Acknowledgements](##Acknowledgements)
📍I'd like to acknowledge and thank my mentor 'sdrohan' on Github who provided guidance and expertise throughout the development of this project.



   


📌 eBank – JavaFX MVC Banking Simulator
-My second Java project, focused on architecture, clean structure, and scalable design

🔹Overview

eBank represents my second major Java project, developed immediately after completing my Swing Calculator.
While the first project focused on building a functional GUI and handling complex event logic, the main idea behind eBank was:

-Improving project structure

-Adopting real architectural patterns

-Learning modern Java development tools

The key goal was to rebuild everything I learned previously, but this time using cleaner, more maintainable code, organized according to a three-layer MVC architecture.
This project also marks the moment when I switched from Eclipse to IntelliJ IDEA, and learned to work with:
JavaFX, Scene Builder and FXML component-based UI development.
Unlike the calculator, where the focus was on UI behavior, the focus here was on architecture, data flow, and proper layering.

🔹Architecture

-The project follows a three-layer structure, each with its own responsibility:

1. GUI Layer (JavaFX FXML + Controllers)
Each screen has its own dedicated controller
Responsible only for UI behavior and user interaction

2. Application Layer (AppController)
Acts as a bridge between UI and data
Contains business logic
Coordinates communication between GUI and database layer

3. Data Layer (DataBaseController)
Reads and writes data from/to a JSON file
Simulates a lightweight database
Stores users, accounts, and transactions

This separation ensures the project is highly modular, easy to maintain, and logically structured — which was the primary learning goal.

🔹Features

-Login and user session handling
-Viewing, creating, and updating bank accounts
-Storing user and account data in JSON
-Reuzable JavaFX components (account cards, pages, modal windows)
-Navigation controller for managing window transitions
-Clean separation between UI, logic, and data
-Fully functional multi-screen application


🔹What This Project Represents


eBank demonstrates my progression as a Java developer:
What was missing in the Calculator → added here 🚀
-Full 3-layer MVC architecture
-JavaFX + Scene Builder
-Clear separation into controllers
-JSON-based data layer
-IntelliJ IDEA

-Early beginner code	Solidly structured application

🔹Technologies used

Java 25,
JavaFX (FXML + Scene Builder),
IntelliJ IDEA,
Gson (JSON handling),
Object-Oriented Design,
Custom Navigation System

⚠ Limitations 

This project is intentionally kept simple as part of my learning progression.
Current limitations include:
🔸 No real database
JSON was used as a “mini database” because I had not yet learned SQL or database systems.
This allowed me to focus on architecture instead of infrastructure.
🔸 Not a Maven project
Dependencies had to be added manually to the project structure.
A Maven rewrite would simplify configuration and dependency management.

These limitations do not affect functionality but represent areas for future improvement.

🚀 Running the Application

To run the project locally:
Clone the repository
Open the project in IntelliJ IDEA
Make sure JavaFX libraries are properly linked (if not using Maven)
Run the Main.java class from IntelliJ
No database setup is required — the app automatically creates and updates the JSON file that acts as the data store.

🔹Future Improvements

Potential enhancements for future versions:
Migration from JSON to a real SQL/NoSQL database,
converting project to Maven,
adding dependency injection,
more robust login/session logic,
transaction history and advanced banking features,
validation, error dialogs, and UI polish...

🙌 Acknowledgements

Certain improvements—such as structuring navigation, handling JSON more cleanly, and refining some logic—were done with assistance from ChatGPT, serving as a learning and debugging companion throughout development.

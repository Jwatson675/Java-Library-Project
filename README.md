# QUB Library Management System

This is a complete, console-based library management application written in Java. It uses a custom Java console library to create a multi-window, graphical-style interface directly in the terminal, complete with image support for title banners and book covers.

This project has been designed to follow object-oriented principles, with a clean separation of concerns between the main application logic, UI, and data models.

## Features

* **List All Books:** View the entire library catalogue.
* **List by Status:** Filter books by `AVAILABLE`, `ON_LOAN`, or `WITHDRAWN`.
* **Add a Book:** A comprehensive, step-by-step form with input validation for:
    * Title & Author (length)
    * ISBN (10-digit, numbers only)
    * Book Type (FICTION, NON_FICTION, REFERENCE)
    * Edition & Price (positive numbers)
    * Summary (length)
    * Cover Image (file validation)
* **Borrow/Return Books:** A fully functional check-in and check-out system that updates book status.
* **Remove a Book:** Mark a book as `WITHDRAWN` (soft delete).
* **Display Ranked List:** Show the most popular books, sorted by their loan count.
* **Dynamic UI:** The console windows are dynamically sized based on the user's screen resolution.

## How to Run

This project depends on a custom JAR file, `CSC1029Console.jar`. This file **must** be added to the project's build path to run.

**Prerequisites:**
* Java Development Kit (JDK) 8 or newer
* A Java IDE (Eclipse recommended)

**Setup & Run Instructions:**

1.  Clone this repository to your local machine.
2.  Open the project in your Java IDE.
3.  Add the `CSC1029Console.jar` file (located in the `/lib` folder) to your project's build path.
    * **In Eclipse:**
        1.  Right-click the project folder in the Package Explorer.
        2.  Go to **Build Path** -> **Configure Build Path...**
        3.  Click the **Libraries** tab.
        4.  Click **"Add JARs..."** (or "Add External JARs...").
        5.  Navigate to the `lib` folder in this project and select `CSC1029Console.jar`.
        6.  Click "Apply and Close".
4.  Navigate to `src/queensLibrary/QUBLibrary.java` and run the `main` method.

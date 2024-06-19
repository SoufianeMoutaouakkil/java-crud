<!-- Project title -->

# To-Do List with JSP and Servlets

## Description

This project is a simple to-do list web application. It is built using Java, JSP. the purpose of this project is to learn how to use JSP and Servlets to build a web application.

## Concepts covered

    - JSP
    - Servlets
    - JDBC
    - MySQL
    - Apache Tomcat
    - Custom tags
    - Session management
    - Authentication
    - Authorization
    - MVC
    - CRUD

## Features

    - related to task:
        - Add a task.
        - Edit a task.
        - Delete a task.
        - Mark a task as completed.
        - Mark a task as not completed.
    - related to user:
        - Register a user.
        - Login a user.
        - Logout a user.
        - Change password.
        - Delete account.

## Technologies

    - Java
    - JSP
    - Servlets
    - MySQL
    - JDBC
    - Apache Tomcat
    - Bootstrap
    - Awesome Font
    - Maven
    - VS Code
    - Git

## Tools

    - MySQL
    - Apache Tomcat
    - VS Code
    - Git

## How to run in dev mode

    - Clone the repository.
    - run MySQL server.
    - update the database configuration in the `src/main/java/app/db/DB.java` file.
    - run db.bat file to create the database, table, and insert some data.
    - run bad.bat file to Build And Deploy the project. (tomcat server should be in 'C:\tomcat' else update deploy.bat file)

## pages
    =====================================================================================
    - related to authentication:
        - Login page (/login, unauthenticated)                              ======= DONE
        - Register page (/register, unauthenticated)                        ======= DONE
        - Logout page (/logout, authenticated)                              ======= DONE
    - related to user:
        - Change password page (/profile/change-password, authenticated)
        - Delete account page (/profile/delete-account, authenticated)
        - Edit profile page (/profile/edit, authenticated)
    - related to task:
        - Home page (/tasks, authenticated)
        - Add task page (/tasks/add, authenticated)
        - Edit task page (/tasks/edit/*, authenticated)
    - related to error:
        - Error page (/error, authentication not checked)
    =====================================================================================

## Database schema

    - users
        - id
        - name
        - email
        - password
    - tasks
        - id
        - user_id
        - name
        - description
        - status

## project structure
    - src: contains 3 main folders, java, resources, webapp.
        - java: contains all the java files depending on 2 main packages, app, and com.
            - app: contains all the java files such as db, models, entities, and utils.
            - com: contains all the java files related to http such as servlets, filters.
        - resources: contains the translation files.
        - webapp: contains all the jsp files.
    - set of files for sql, tomcat, deployment, and git.

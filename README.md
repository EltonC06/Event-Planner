# Event Planner

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/EltonC06/Event-Planner/blob/main/LICENSE)

## About the Project

Event Planner is a Java Spring Boot web back-end appliccation created for educational purposes.

The application is designed as an event planning API that allows front-end applications to manage events. It enables owners and admins to create and manage events, which are then accessible to all users.

## Technologies Used

### Back-End
- Java (Spring Boot)
- H2 Console

## How to Run the Project

### Prerequesites
- Java IDE (Spring Tool Suite recommended)
- Java 21

### Step-by-Step Guide

1. **Clone the Repository**
   ```bash
   git clone git@github.com:EltonC06/Event-Planner.git
   ```

2. **Open the Project**
   - Open the project in a Java IDE with Spring Boot support (Spring Tool Suite is recomemended).

3. **Set Up the Database**
   - H2 database configuration is included by default. No additional setup is required for local development.

4. **Run the Application**
   - In your IDE, click to run the application.

5. **Testing the Application**
   - Since the application does not have a front-end interface, use a HTTP client like Postman to interact with the API endpoints.

## API Endpoints

Here are the main endpoints available for interacting with the application:

### User

- **Create User**
  - **Method:** `POST`
  - **URL:** `localhost:8080/users`
  - **Description:** Create a new user.
  - **Body:** `{ "userName": "Guilherme", "password": "4321", "role": "OWNER" }`

- **Get user by id**
  - **Method:** `GET`
  - **URL:** `localhost:8080/users/{id}`
  - **Description:** Get an user by id.

- **Get Users**
  - **Method:** `GET`
  - **URL:** `localhost:8080/users`
  - **Description:** Get all users created.

- **Update User**
  - **Method:** `PUT`
  - **URL:** `localhost:8080/users/{id}`
  - **Description:** Update the information about the user selected.
  - **Body:** `{ "userName": "Guilherme", "password": "1234", "role": "USER" }`

- **Delete User**
  - **Method:** `DELETE`
  - **URL:** `localhost:8080/users/{id}`
  - **Description:** Delete an user.

### Event

- **Create Event**
  - **Method:** `POST`
  - **URL:** `localhost:8080/events`
  - **Description:** Create a new event.
  - **Body:** `{ "name": "Event name" "date": "2000-01-03", "local": "Local name", "description": "Event description", "userId": "1", "eventStatus": "PLANNED" }`

- **Get event by id**
  - **Method:** `GET`
  - **URL:** `localhost:8080/events/{id}`
  - **Description:** Get an event by id.

- **Get Events**
  - **Method:** `GET`
  - **URL:** `localhost:8080/events`
  - **Description:** Get all events created.

- **Update event**
  - **Method:** `PUT`
  - **URL:** `localhost:8080/events/{id}`
  - **Description:** Update information for a specific event.
  - **Body:** `{ "name": "Event name" "date": "2000-01-03", "local": "Local name", "description": "Event description", "userId": "1", "eventStatus": "PLANNED" }`

- **Auto Status Update**
  - **Method:** `PUT`
  - **URL:** `localhost:8080/events/autostatusupdate/{id}`
  - **Description:** Automatically update the event status based on its date.

- **Update event**
  - **Method:** `PUT`
  - **URL:** `localhost:8080/events/cancel/{id}`
  - **Description:** Cancel an event. 

- **Delete event**
  - **Method:** `DELETE`
  - **URL:** `localhost:8080/events/{id}`
  - **Description:** Delete an event.

## Enum Values

- UserRole: `USER`, `ADM` and `OWNER`

- EventStatus: `PLANNED`, `CANCELLED` and `COMPLETED`

## Observations
  
- The program was tested using only Spring Tool Suite.

## How You Can Contribute

- Login System implementation.
- Spring Security implementation for user passwords.
- Develop the web front-end project part.

## Author

Elton da Costa Oliveira

[LinkedIn](https://www.linkedin.com/in/elton-da-costa/)

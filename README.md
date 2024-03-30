# Demo for Employee Profile Management Web Application

This repository contains a small but complete web application developed using the Java Spring Boot framework. The application allows users to manage employee profiles, including adding, editing, and deleting employee entries. Additionally, it provides a report screen to display gender-wise employee counts and consumes a third-party REST API to display cryptocurrency prices.



### Basic Information
URL: `http://localhost:8080`  
Admin: username: admin, password: 123456, role: ADMIN  
User: username: user, password: 123456, role: USER  


### Requirements
To run this application locally, ensure you have the following installed:
- Java JDK (version 17 or higher)
- Maven

### Installation
Clone this repository to your local machine:  
`git clone <repository_url>`

Build the project using Maven:  
`mvn clean install`

Run the application:  
`mvn spring-boot:run`
  


### Usage

#### Homepage
- The homepage contains a login.
- Upon successful login, users are redirected to dashboard.

#### Dashboard
- The dashboard displays a list of employees.
- Users can search by name and date of birth.
- Only Admin can add new employees.
  - Proper error handling for invalid data. 
- Both Admin and User can edit and delete existing employee entries.

#### Report Screen
- The report screen shows gender-wise employee counts.
  - This page is accessible publicly.
- Consumed the CoinDesk API to display cryptocurrency prices.

#### REST API
- The application provides a REST API endpoint to retrieve the employee list.
  - Endpoint: http://localhost:8080/api/v1/employee
  - Response: JSON format, paginated

#### Test Code 
- A Test code for calling the REST API (GET `/api/v1/employee`).
  - Can be found into `EmployeeApiTest` class


### Screenshots

Login page  
![](/assets/page_login.png "Login page")

Dashboard  
![](/assets/page_dashboard.png "Dashboard")

Add Employee  
![](/assets/page_add_employee.png "Add Employee")

Delete Employee  
![](/assets/page_delete_employee.png "Delete Employee")

Report with Third-party API consumed  
![](/assets/page_report.png "Report with Third-party API consumed")





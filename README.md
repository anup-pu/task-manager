# 🗂️ Task Manager

A simple and intuitive **Task Management & Productivity Tracker** built with **Spring Boot**, **MySQL**, **jQuery**, and **Bootstrap**.  
This project helps you manage your tasks with full CRUD operations and a clean UI.

---

## 🚀 Features

- ➕ Create new tasks with title, description, priority, and deadline
- 🔄 Update task status: `To Do → In Progress → Done`
- 🗑️ Delete completed or unwanted tasks
- 🔍 Filter tasks by deadline and priority
- 🧪 Includes backend unit testing with JUnit & Mockito

---

## 🧰 Tech Stack

- **Backend:** Spring Boot (Java), REST API, MySQL
- **Frontend:** HTML, CSS, Bootstrap 5, jQuery
- **Testing:** JUnit 5, Mockito

---

## 📁 Project Structure

src/
├── main/
│ ├── java/
│ │ └── com/taskmanager/...
│ └── resources/
│ ├── static/
│ │ ├── index.html
│ │ ├── styles.css
│ │ └── script.js
│ └── application.properties
└── test/
└── java/com/taskmanager/...

yaml
Copy
Edit

---

## ⚙️ How to Run

### Prerequisites
- Java 17 or 21
- Maven
- MySQL (create database `taskmanager_db`)

### Steps

```bash
git clone https://github.com/anup-pu/task-manager.git
cd task-manager
./mvnw spring-boot:run
Then open: http://localhost:8080/index.html

🧪 Run Tests
bash
Copy
Edit
./mvnw test
Unit tests are inside src/test/java/com/taskmanager/...

👨‍💻 Author
Anup Kumar
🔗 GitHub: https://github.com/anup-pu


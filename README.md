# Library Management System in Java

## Description
This project is a **library management system** developed in Java, which allows managing **books**, **partners**, and **loans**.  
The project follows a **layered architecture**, separating responsibilities:

- **Repository:** Handles database communication with CRUD operations (Create, Read, Update, Delete).  
- **Services:** Contains business logic and validations, such as checking if partners and books exist, stock availability, and loan return management.  
- **View:** Simple graphical interface using `JOptionPane`.  
- **Main:** Initializes repositories, services, and the view.

The system also includes **custom exceptions** for error handling: `DuplicateExceptionRecord`, `ErrorSystemException`, `NoExistentResourceException`.

---

## Technologies and Dependencies
- **Language:** Java 17  
- **Database:** MySQL (using JDBC)  
- **Connection:** MySQL Connector/J (`mysql-connector-java`)  
- **Interface:** `JOptionPane` for menus and forms  
- **Custom exceptions** for business and system error handling

---

<img width="567" height="575" alt="Captura desde 2025-10-14 22-06-29" src="https://github.com/user-attachments/assets/b50711b6-8978-41ad-9992-c9dc0f4857a5" />






---

## Features
### Books
- Add, update, delete, and list books.
- Stock validation when creating loans.
- Error handling if a book already exists (`DuplicateExceptionRecord`).

### Partners
- Add, update, delete, and list partners.
- Validate existence before creating a loan.
- Error handling if a partner does not exist (`NoExistentResourceException`).

### Loans
- Create loans only if **book and partner exist**.
- Automatically decrease book stock when loaned.
- Register loan returns and increase book stock.
- List all loans or search by ID.
- Error handling if stock is insufficient (`ErrorSystemException`).

---

## System Flow
1. **Start:** `Main.java` is executed, repositories and services are created, and the view is initialized.  
2. **Interface:** User interacts through menus in `JOptionPane`.  
3. **Actions:**
   - Register book or partner → Validate duplicates → Insert into repository.  
   - Create loan → Validate book and partner existence → Check stock → Insert loan → Update book stock.  
   - Return loan → Mark loan as returned → Increase book stock.  
   - List books, partners, or loans → Retrieve from repository and display in view.  
4. **Exceptions:** Business and system errors are caught and displayed to the user.

---

## Configuration and Execution
1. **Required dependencies:**
   - MySQL Connector/J (`mysql-connector-java`)
   - Java 17  

2. **Configure database connection in `Conexion.java`:**
3. **Compile and run the program:**
4. # Compile all Java files
javac -d bin src/com/mycompany/biblioteca/**/*.java

# Run the program
java -cp bin com.mycompany.biblioteca.Main


Author

Jose Gomez Guzman
Academic project: Library Management System in Java

<img width="964" height="644" alt="image" src="https://github.com/user-attachments/assets/961eeeea-20fe-4cd4-bd36-33b813e5ed7f" />


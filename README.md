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

## Architecture and Project Structure
com.mycompany.biblioteca
│
├─ db
│ └─ Conexion.java # JDBC connection management
├─ exeptions
│ ├─ DuplicateExceptionRecord.java
│ ├─ ErrorSystemException.java
│ └─ NoExistentResourceException.java
├─ model
│ ├─ Book.java # Book entity
│ ├─ Loan.java # Loan entity
│ └─ Partner.java # Partner entity
├─ repository
│ ├─ Repository.java # Generic CRUD interface
│ ├─ book/IBook.java
│ ├─ loan/ILoan.java
│ └─ partner/IPartner.java
├─ services
│ ├─ IServiceLoan.java
│ ├─ ImplementsLoan.java
│ ├─ IServiceBook.java
│ ├─ ImplementsBook.java
│ ├─ IServicePartner.java
│ └─ ImplementsPartner.java
├─ view
│ └─ BibliotecaView.java # User interface with menus and forms
└─ Main.java # System initialization and execution




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

# JAVA WEB MAVEN/NETBEANS - RENTING GAMES

## ⭐ Project Summary
This project contains two separate Java-based assignments (**Homework1** and **Homework2**) forming part of the *Sistemes Operatius Basats en Fitxers / Serveis Web* course.  
The project demonstrates skills in **Java web development**, **Maven project structure**, **NetBeans project layout**, and deployment of server‑side components.

- **Homework1** is a **Java Web Application** (NetBeans/Ant) with JSP files, static resources, and `WEB-INF` configuration.
- **Homework2** is a **Maven-based Java project** following a standard `pom.xml` structure with sources and tests.

Both assignments demonstrate understanding of Java server technologies, project structuring, compilation, packaging and execution.

---

## 🧩 Technologies & Skills Demonstrated
### **Java Web Development**
- JSP pages  
- `WEB-INF` configuration  
- Static resources (CSS, JS, images)  
- Web application packaging layout  

### **Build Tools**
- **Apache Ant** (Homework1 — NetBeans project)  
- **Maven** (Homework2 — `pom.xml`, standard lifecycle)  

### **General Software Engineering**
- Project structure management  
- Compilation and distribution folders (`build/`, `dist/`, `target/`)  
- ZIP packaging conventions  
- Folder separation between `src/`, `test/`, `web/`, and configuration directories  

---

## 📁 Project Structure

```
SOB-Java-Web-RentingGames/
└── Projecte/
    ├── Homework1/                        → Java Web Application (NetBeans project)
    │   ├── build.xml                     → Ant build script
    │   ├── src/                          → Java source code
    │   ├── web/                          → Web content (JSP, HTML, CSS, JS)
    │   ├── WEB-INF/                      → Deployment descriptors
    │   ├── build/                        → Build output
    │   └── dist/                         → Packaged WAR/JAR output
    │
    └── Homework2/                        → Maven Project
        ├── pom.xml                       → Maven configuration
        ├── src/                          → Main + test Java sources
        ├── nbproject/                    → NetBeans metadata
        └── target/                       → Maven build output
```

### Design Philosophy
- **Homework1** focuses on Java EE/Web basics with JSP and static resources.
- **Homework2** transitions to a **Maven-based workflow** demonstrating modern Java project organization.
- Clean separation of build files, sources, web content, and output directories.

---

## 🔍 Project Details

### **Homework1 (NetBeans — Ant Web App)**
Features:
- `index.html` entry point  
- Supporting JS, CSS, images  
- JSP pages located in `web/`  
- Preconfigured `WEB-INF` deployment structure  
- Build via `build.xml` with NetBeans support  

Typical for early‑semester Java web development.

---

### **Homework2 (Maven Project)**
Features:
- Standard Maven directory layout  
- `pom.xml` for dependencies and build lifecycle  
- Source code under `src/main/java`  
- Unit tests under `src/test/java`  
- Maven‑generated artifacts placed in `target/`  

Represents more modern Java project practices.

---

## ▶️ How to Build & Run

### **Homework1 — Ant / NetBeans Web App**
1. Open project in **NetBeans**  
2. Clean & build:
```
ant clean build
```
(or use NetBeans GUI)
3. Deploy via:
```
ant deploy
```
or run from NetBeans using the configured application server (GlassFish/Tomcat).

---

### **Homework2 — Maven Project**
Inside `Homework2/`:
```
mvn clean install
```

To run tests:
```
mvn test
```

To run the project:
```
mvn exec:java
```
(if configured in `pom.xml`)

---

## ✔ Summary
This repository contains two separate Java assignments demonstrating:
- Java Web Applications (JSP, WEB-INF layout)
- NetBeans/Ant project management
- Maven project structure and compilation
- Good software engineering project organisation

A solid demonstration of foundational back‑end and build‑tool skills.

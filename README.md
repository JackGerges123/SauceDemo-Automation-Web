# SauceDemo Automation Testing Project  

## 📌 Project Overview  
This project automates the testing of [SauceDemo](https://www.saucedemo.com/), a sample e-commerce web application provided by Swag Labs.  
It demonstrates end-to-end UI test automation using **Java, Selenium, TestNG, Page Object Model (POM)**, and reporting tools.
This project was built as part of the graduation project at ITI.  

---

## 📂 Project Structure  

```
saucedemo-automation/
│── src
│ ├── main
│ │ └── java
│ │ ├── automation/pageObjects/         # Page Object classes
│ │ ├── automation/drivers/             # WebDriver setup
│ │ └── automation/utils/               # Utilities (logs, json, screenshots)
│ │
│ └── test
│ └── java
│ └── automation/tests/                 # Test classes (grouped by feature)
│
│── resources/                          # Test data & suites
│── pom.xml                             # Maven dependencies
│── README.md 
│── .gitignore

---

## ✅ Covered Test Scenarios  

- **Login Tests**  
  - Valid user login  
  - Invalid credentials  
  - Empty Fields

- **Products Page**  
  - Add/remove items from cart  
  - Sort products (low→high, high→low)  

- **Cart & Checkout**  
  - Add multiple products  
  - Continue shopping after adding items  
  - Checkout with empty cart
  - Checkout with valid/invalid information  
  - End-to-end purchase flow  

- **UI/Validation**  
  - Error messages display correctly  
  - Logo & page elements visibility  

---

## ▶️ How to Run Automation Tests  

1. Clone the repository  
   ```bash
   git clone https://github.com/<JackGerges123>/saucedemo-automation.git
   cd saucedemo-automation
   ```

2. Install dependencies (Maven required)  
   ```bash
   mvn clean install
   ```

3. Run all tests  
   ```bash
   mvn test
   ```

4. Run using TestNG suite  
   ```bash
   mvn clean test -DsuiteXmlFile=EndToEndSuite.xml
   ```

5. Generate Allure Report (if configured)  
   ```bash
   allure serve test-logs/allure-results
   ```

---

## 🛠 Tools & Technologies Used  

- **Java** (programming language)  
- **Selenium WebDriver** (UI automation)  
- **TestNG** (test runner & reporting)  
- **Maven** (build & dependency management)  
- **Allure Reports / Extent Reports** (reporting)  
- **Page Object Model (POM)** (test design pattern)  
- **GitHub Actions/Jenkins** (optional CI/CD integration)  

---

## 🙌 Credits  

- [Sauce Labs](https://saucelabs.com/) for providing the SauceDemo application  
- ItI supporting team

# SauceDemo Automation Testing Project  

## 📌 Project Overview  
This project automates the testing of [SauceDemo](https://www.saucedemo.com/), a sample e-commerce web application provided by Sauce Labs.  
It demonstrates end-to-end UI test automation using **Java, Selenium, TestNG, Page Object Model (POM)**, and reporting tools.  

---

## 📂 Project Structure  

```
saucedemo-automation/
│── src
│   ├── main
│   │   └── java
│   │       ├── pages/         # Page Object Model classes
│   │       ├── utils/         # Driver factory, config reader
│   │
│   └── test
│       └── java
│           ├── tests/         # Test classes
│           └── base/          # Base test setup/teardown
│
│── pom.xml                    # Maven dependencies
│── testng.xml                 # TestNG suite configuration
│── README.md                  # Project documentation
│── .gitignore
```

---

## ✅ Covered Test Scenarios  

- **Login Tests**  
  - Valid user login  
  - Invalid credentials  
  - Locked-out user  

- **Products Page**  
  - Add/remove items from cart  
  - Sort products (low→high, high→low)  

- **Cart & Checkout**  
  - Add multiple products  
  - Continue shopping after adding items  
  - Checkout with valid/invalid information  
  - End-to-end purchase flow  

- **UI/Validation**  
  - Error messages display correctly  
  - Logo & page elements visibility  

---

## ▶️ How to Run Automation Tests  

1. Clone the repository  
   ```bash
   git clone https://github.com/<your-username>/saucedemo-automation.git
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
   mvn clean test -DsuiteXmlFile=testng.xml
   ```

5. Generate Allure Report (if configured)  
   ```bash
   allure serve target/allure-results
   ```

---

## ⚡ Automation Challenges  

- Synchronization issues with dynamic elements  
- Handling multiple users and different states  
- Test data management (valid vs invalid inputs)  
- Keeping Page Object Model clean and maintainable  

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
- Inspired by QA community best practices in automation  

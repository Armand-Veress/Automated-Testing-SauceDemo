# Automation Testing Learning Project   

### How to run
- Clone the repository
- Create a **/drivers** folder in the **root** directory of the project. Check your local browser versions and download the corresponding drivers into this folder:
	- **Chrome:** Download ChromeDriver (extract as *chromedriver.exe*)
	- **Firefox:** Download GeckoDriver (extract as *geckodriver.exe*)
  **Note:** The project is configured to run the tests on Chrome and Firefox, with a default fallback option (Chrome) if parameters are not specified.
- **Test Execution**: The framework uses TestNG XML suites to group and run tests. You can execute them using either Maven via Command Line or directly through your IDE.
	- Option A: **Via maven (command line)**
   ```bash
    # Run Functional Tests
	mvn clean test "-DsuiteXmlFile=src/test/resources/scripts/functional-tests.xml"

	# Run Authentication Tests
	mvn clean test "-DsuiteXmlFile=src/test/resources/scripts/auth-tests.xml"

	# Run Demo Tests
	mvn clean test "-DsuiteXmlFile=src/test/resources/scripts/demo-tests.xml"
   ```
	- Option B: **Via IDE (e.g. IntelliJ IDEA / VS Code)**
	Right-click on any of the .xml files to run the suite, or right-click on the Run button next to any **@Test** method to execute tests individually.

### Technical specifications:
- **Case Subject:** https://www.saucedemo.com
- **Project:** *Java 21+,  Maven*
- **Automation Framework:** *Selenium for Java*
```
<!-- Source: https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->  
<dependency>  
    <groupId>org.seleniumhq.selenium</groupId>  
    <artifactId>selenium-java</artifactId>  
    <version>4.46.0</version>  
    <scope>compile</scope>  
</dependency>	
```
- **Testing Framework:** *TestNG*
```
<!-- Source: https://mvnrepository.com/artifact/org.testng/testng -->  
<dependency>  
    <groupId>org.testng</groupId>  
    <artifactId>testng</artifactId>  
    <version>7.12.0</version>  
    <scope>test</scope>  
</dependency>	
```
- **Source of browser drivers used:**
	- *Chromium:* https://storage.googleapis.com/chrome-for-testing-public/151.0.7922.77/win64/chromedriver-win64.zip
	- *Geckodriver:* https://github.com/mozilla/geckodriver/releases/download/v0.37.1/geckodriver-v0.37.1-win64.zip

### Planning & Bug Tracking (Jira)

* **[Jira Tickets List (PDF)](./docs/Jira-ticket-list.pdf)** - *A structured PDF list with all tickets related to this project*
* **[Jira Tickets Details (PDF)](./docs/Jira-tickets.pdf)** - *A detailed PDF overview of all tickets related to this project*
* **[Jira Tickets Data (CSV)](./docs/Jira-tickets.csv)** - *Exported .csv file with all fields - interactive tabel*

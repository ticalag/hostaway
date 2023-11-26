
# Automation framework created for Hostaway Technical Task 

Requirements:
Please provide automation tests for https://kamil-demo.alpinizm.uz/ using Selenide (or Selenium), Allure, Java 11, JUnit or TestNG (without Cucumber) which will do the following:
1. Check Filters form: entry fields, checkboxes, minimum and maximum values, Amenities checkboxes, “Clear all” is functional, check buttons without clicking "Apply".
   To reach the Filters form on https://kamil-demo.alpinizm.uz/ press the Search button, press the Filter button.
2. Check that the 'All listings' page (https://kamil-demo.alpinizm.uz/all-listings) has the same amount of listings as the 'All' label.
   Please create only mentioned test cases and nothing more.
   In the result an informative Allure report should be formed. Please submit the result on GitHub.
   We expect to see the usage of AAA, POM, tests should be developed using method chaining (page object in fluent style).
   The mandatory requirement is that the project should successfully run with the help of the ‘mvn clean test’ command. Maven of not less than 3.8 version should be used. The project should have dependencies not older than one year from the current date or the latest accessible versions. In case of non-compliance with these requirements, the developed technical task will not be considered.
   Please don't hesitate to contact me if you have any questions or concerns.




#### Tech stack: java, selenium, testNG, Allure reporting


### To run the tests run: 

**mvn clean test**



### To generate allure report run:  

**allure serve target/allure-results**

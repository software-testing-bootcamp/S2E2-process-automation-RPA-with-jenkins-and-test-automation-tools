# Process automation (RPA) with Jenkins and Test Automation tools

![image](https://user-images.githubusercontent.com/89974862/156874989-80da2945-5a2d-4e55-81ce-b0223781f612.png)

In this video, we will do 2 process automation examples with Open Source technologies:

  - High-Code Process Automation with Java
  - Low-Code Process Automation with Jmeter (WDS)

 Before starting, You can check these 4 webinar docs on https://testingbootcamp.com
  - DevOps and Test Automation with Jenkins
  - Performance Testing with Jmeter
  - S2E1 — Selenium 4.0 with Java step by step
  - S2E2 — Mobile Test Automation with Appium 2.0 and Java

![image](https://user-images.githubusercontent.com/89974862/156874888-abafd451-e26f-4543-bc7e-28c77f4ce507.png)

We will get the cryptocurrency prices from markets and will write to MySQL DB until the whole operation has been failed. If the operation has been failed Java code or Jmeter will send Email and Discord notifications after that process will be stopped until we check it manually to be sure that's working again. The following picture describes the whole process automation

![image](https://user-images.githubusercontent.com/89974862/156874676-193ab354-22b3-42ea-a5da-8a4e9786fbb6.png)

Example Apps to collect the needed data are here:
  1.  Get data from Paribu Ticker API with Rest Assured -> https://www.paribu.com/ticker
  2.  Get Data from BTCTurk Ticker API with Rest Assured -> https://api.btcturk.com/api/v2/ticker
  3.  Get Data from Binance Ticker API with Rest Assured -> https://api.binance.com/api/v3/ticker/24hr
  4.  Get Data from CoinmarketCap Android App with Appium 2.0 -> https://apk.coinmarketcap.com/downloads/coinMarketCap-latest.apk
  5.  Get Data from CoinmarketCap Website with Selenium 4.0 -> https://coinmarketcap.com/currencies/bitcoin/

***

**Youtube Webinar Video**

https://www.youtube.com/watch?v=qfHaSGDO_ig

***

**MySQL Database Creation**

Before starting the project let's create a MySQL DB scheme with the needed initial Data with this file "coin_test_DB_Creation_Scripts.sql" on this repository.

![image](https://user-images.githubusercontent.com/89974862/156876279-ef5058f5-df24-4d4a-9c04-133b82a90cd8.png)

***

## 1. High-Code Process Automation with Java ##

Requirements:
  - IntelliJ IDEA CE Installation -> https://www.jetbrains.com/idea/download
  - Java JDK 11 -> https://www.oracle.com/tr/java/technologies/javase/jdk11-archive-downloads.html
  - Appium Server v2.0.0-beta.25 or later installation -> https://appium.io/
  - Appium Inspector v2022.1.2 or later installation -> https://github.com/appium/appium-inspector
  - Android Emulator (via Android Studio) -> https://developer.android.com/studio
  - MySQL Database with an admin user. ->  https://dev.mysql.com/downloads/mysql/
  - Jenkins 2.0+ Installation (Plugins: BlueOcean, Discord, Extended Email) -> https://www.jenkins.io/

**Getting Started with JAVA Project**
  1. Open Java Maven project with IntelliJ IDEA

  2. Set your DatabaseManager.java file with your MySQL Database connection credentials
  
  3. Download latest CoinMarketCap APK from its website and set "appium:app" capability's value on "PlatformManager.java"  file

  4. Start Appium server from your Terminal or Command Prompt "Appium &"

  5. To check the Automation on your computer, right click on the TestNG suite XML files and run or execute the following commands.

      cd ${JAVA_PROJECT_DIRECTORY}
      mvn clean test -Dsurefire.suiteXmlFiles=src/test/java/suites/APIGroup.xml
      mvn clean test -Dsurefire.suiteXmlFiles=src/test/java/suites/WebGroup.xml
      mvn clean test -Dsurefire.suiteXmlFiles=src/test/java/suites/MobileGroup.xml
  
  6. Manually trigger your Jenkins Pipeline Job to check the whole process automation. Sample Groovy script is the "jenkins-pipeline-script.groovy" file.

![image](https://user-images.githubusercontent.com/89974862/156876045-58e33fd4-0963-4f74-9a74-4ea861abc58e.png)


***

## 2. Low-Code Process Automation with Jmeter ##

Requirements:
  - Java JRE or JDK installation -> https://www.oracle.com/tr/java/technologies/javase/jdk11-archive-downloads.html
  - Apache Jmeter Binary -> https://jmeter.apache.org/download_jmeter.cgi
  - Jmeter Plugins Manager (JAR file) -> https://jmeter-plugins.org/wiki/PluginsManager/
  - MySQL Java Connector (JAR file) -> https://dev.mysql.com/downloads/connector/j/
  - ChromeDriver file -> https://chromedriver.chromium.org/
  - MySQL Community Server -> https://dev.mysql.com/downloads/mysql/
  - MySQL Workbench installation -> https://dev.mysql.com/downloads/workbench/

**Getting Started with JMETER Project**
  1. Download Jmeter Plugins Manager and MySQL Connector JAR files and then copy to the following locations.
    ${JMETER_INSTALLATION_PATH}/lib/ext/jmeter-plugins-manager-${VERSION}.jar
    ${JMETER_INSTALLATION_PATH}/lib/mysql-connector-java-${VERSION}.jar
    
  2. Start Jmeter and Click File->Open than select "jmeter_test-crypto.jmx" file 
     ${JMETER_INSTALLATION_PATH}/bin/Jmeter or Jmeter.bat(for Windows)
     
  3. SET your SMTP Server Settings or Discord API Hook URL. For Gmail SMTP Server usage, please allow less secure apps to use your Gmail address on this link https://myaccount.google.com/lesssecureapps

  4. SET your MySQL Server Connection Information on "JDBC.MySQL" section.
  
  5. SET your chromeDriver file location on "Chrome Driver Config" section.

![image](https://user-images.githubusercontent.com/89974862/156876224-a7cfaf99-b531-4ad9-93c4-649103d9d52e.png)

***

## 3. What is next? ##

Once you have collected enough data, you can start analyzing it and even create your own application. To be sure if you need AI support, you must first determine what you expect from AI and make sure it needs to be analyzed in a humanlike way.


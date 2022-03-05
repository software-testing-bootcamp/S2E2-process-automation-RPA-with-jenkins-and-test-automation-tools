pipeline {
    //agent any
    agent {
        node {
            label "master" // Manage Jenkins -> Manage Nodes -> Configure -> Set label as master
            customWorkspace "/Users/ozgur.kaya/Downloads/SoftwareTestingBootcamp/3/Bootcamp-Java-Automation"
        }
    }
    tools {
        // Manage Jenkins -> Global Tool Configuration -> Set MAVEN and JDK
        maven "Maven 3.8.2"
        jdk "JDK11"
    }
stages {
    stage('API Automation') { 
        steps {
                // Run Maven on a Unix/MacOS agent.
                sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/java/suites/APIGroup.xml"
            }
    }
    stage('Web Automation') { 
        steps {
                sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/java/suites/WebGroup.xml"
            }
    }
    stage('Mobile Automation') { 
        steps {
                sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/java/suites/MobileGroup.xml"
            }
    }

stage('Write Data') {
parallel {
    stage('Insert to DB') { 
        steps {
                echo 'RPA'
                //Fail this workflow
                //timeout(time: 3, unit: "SECONDS")
            }
    }
    stage('Insert to Excel') { 
        steps {
                echo 'RPA'
            }
    }
}
}
    stage('Post Steps') { 
        steps {
                echo 'IF Failed THEN send notification(s)'
                sleep(time: 5, unit: "SECONDS")
            }
    }
}
post{
    success{
        echo 'SUCCESS. Repeat Process Automation'
        build job: 'Cryptocurrency Price Tracking Process Automation (Java)', wait:false
    } 
    unsuccessful{
        echo 'FAILED. Send Notification and Stop.'
        emailext body: "Test Message. Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
            subject: 'Test Subject',
            from: 'yourmailaddress@gmail.com',
            to: 'marotok618@toudrum.com'
            
        discordSend description: "Jenkins Pipeline Build", 
            footer: "Error on Process Automation. Check your email for details", 
            link: env.BUILD_URL, 
            result: currentBuild.currentResult, 
            title: 'Error on Process Automation', 
            webhookURL: "https://discord.com/api/webhooks/9481151736/iOEziewRddddddduy3tNK_xR-0ibgQsDslfr4_1KlLfwOL"

        
        // send to Slack
        //slackSend (color: '#FFFF00', message: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")

    }
}


}

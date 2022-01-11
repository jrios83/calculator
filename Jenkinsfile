pipeline{
    agent any
    stages {
        stage("Checkout") {
            steps {
                git branch: 'main', url: 'https://github.com/jrios83/calculator.git'
            }
        }
        stage("Compile") {
            steps {
                sh "./gradlew compileJava"
            }
        }
        stage("Unit test") {
            steps {
                sh "./gradlew test"
            }
        }
        stage("Code coverage") {
            steps {
                sh "./gradlew jacocoTestReport"
                publishHTML (target: [reportDir:'build/reports/jacoco/test/html', reportFiles:'index.html', reportName: "JaCoCoReport"])
                sh "./gradlew jacocoTestCoverageVerification"
            }
        }
        stage("Static code analysis")
            steps {
                sh "./gradlew checkStypeMain"
            }
    }
}
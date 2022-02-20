pipeline{
    agent any
    environment {
        DOCKERHUB_CREDENTIALS = credentials('thinksec-dockerhub')
    }
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
        stage("Static code analysis") {
            steps {
                sh "./gradlew checkStyleMain"
                publishHTML (target: [reportDir: 'build/reports/checkstyle', reportFiles: 'main.html', reportName: 'Checkstyle Report'])
            }
        }
        stage("Package") {
            steps {
                sh "./gradlew build"
            }
        }
        stage("Docker build") {
            steps {
                sh "docker build -t thinksec/calculator ."
            }
        }
        stage("Docker login") {
            steps {
                sh "echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin"
            }
        }
        stage("Docker push") {
            steps {
                sh "docker push thinksec/calculator"
            }
        }
        stage("Deploy to staging") {
            steps {
                //sh "docker run -d --rm -p 8765:8080 --name calculator_test thinksec/calculator"
                sh "docker run --rm -p 8765:8080 -name calculator_test thinksec/calculator"
            }
        }
        stage("Acceptance test") {
            steps {
                sleep 60
                sh "chmod +x acceptance_test.sh && ./acceptance_test.sh"
            }
        }
    }
    post {
        always {
            sh "docker stop calculator_test"
            sh "docker logout"
        }
    }
}
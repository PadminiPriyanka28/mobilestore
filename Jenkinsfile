pipeline {
    agent any

    environment {
        // Define environment variables if needed
        JAVA_HOME = '/usr/lib/jvm/java-21-openjdk'
        MAVEN_HOME = '/usr/share/maven'
    }

    stages {
        // Stage 1: Checkout Code
        stage('Checkout') {
            steps {
                // Pull the latest code from your repository
                git 'https://github.com/PadminiPriyanka28/mobilestore.git'
            }
        }

        // Stage 2: Build
        stage('Build') {
            steps {
                // Run the build process (Maven)
                script {
                    // You can also use 'sh' or 'bat' for shell/command execution
                    sh 'mvn clean install'
                }
            }
        }

        // Stage 3: Run Tests
        stage('Test') {
            steps {
                // Run your unit tests with Maven
                script {
                    sh 'mvn test'
                }
            }
        }

        // Stage 4: Archive Test Results (Optional)
        stage('Archive Test Results') {
            steps {
                // Publish the JUnit test results so Jenkins can display them
                junit '**/target/test-*.xml'
            }
        }

        // Stage 5: Deploy (Optional)
        stage('Deploy') {
            steps {
                // Deploy your application (this could be to a remote server, Docker, etc.)
                script {
                    sh 'scp target/mobilestore-0.0.1-SNAPSHOT.jar ec2-user@3.86.243.213:/ec2-user/app'
                }
            }
        }
    }

    post {
        // Actions that happen after the pipeline (like notifications)
        always {
            // Always run these steps after the pipeline
            echo 'Cleaning up...'
        }
        success {
            // Actions for a successful build
            echo 'Build and tests passed!'
        }
        failure {
            // Actions for a failed build
            echo 'Build or tests failed.'
        }
    }
}

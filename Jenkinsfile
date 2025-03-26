pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                git 'https://github.com/VuDuyLe04/JENKINS-GITHUB.git'
            }
        }
        stage('Build & Test') {
            steps {
                script {
                    // Chạy Maven chỉ cho file test DoctorManagerTest
                    bat 'mvn clean test -Dtest=DoctorManagerTest'
                }
            }
        }
        stage('Publish Test Results') {
            steps {
                junit '**/target/test-*.xml'  // Tự động tìm kiếm file kết quả JUnit
            }
        }
    }
    post {
        always {
            mail bcc: '', body: 'Yêu tất cả mọi người', cc: '', from: '', replyTo: '', subject: 'Hello World', to: 'vuduyle004@gmail.com'
        }
    }
}

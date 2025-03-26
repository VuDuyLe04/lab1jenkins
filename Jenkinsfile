pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                git 'https://github.com/VuDuyLe04/lab1jenkins.git'  // Đảm bảo đường dẫn đúng
            }
        }
        stage('Build & Test') {
            steps {
                script {
                    // Chạy Maven chỉ cho file test DoctorManagerTest
                    dir('C:/ProgramData/Jenkins/.jenkins/workspace/Lab1-jenkins/test/lab1') {
                        bat 'mvn clean test -Dtest=DoctorManagerTest'
                    }
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

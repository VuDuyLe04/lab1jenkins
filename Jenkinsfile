pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                git 'https://github.com/VuDuyLe04/lab1jenkins.git'  // Đảm bảo đường dẫn đúng
            }
        }
        
        stage('Build') {
            steps {
                script {
                    // Thực hiện build (mvn clean install)
                    dir('C:/ProgramData/Jenkins/.jenkins/workspace/Lab1-jenkins') {
                        bat 'mvn clean install'  // Chạy build Maven
                    }
                }
            }
        }
        
        stage('Run') {
            steps {
                script {
                    // Chạy chương trình sau khi build thành công
                    dir('C:/ProgramData/Jenkins/.jenkins/workspace/Lab1-jenkins') {
                        bat 'mvn exec:java'  // Chạy chương trình Maven nếu có cấu hình main class trong pom.xml
                    }
                }
            }
        }
    }
    post {
        always {
            mail bcc: '', body: 'Yêu tất cả mọi người', cc: '', from: '', replyTo: '', subject: 'Hello World', to: 'vuduyle004@gmail.com'
        }
    }
}

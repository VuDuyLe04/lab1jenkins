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
        
    }
    post {
        always {
            mail bcc: '', body: 'SWT vu duy le', cc: '', from: '', replyTo: '', subject: 'Hello World', to: 'vuduyle004@gmail.com'
        }
    }
}

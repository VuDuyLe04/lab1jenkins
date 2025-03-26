pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                git 'https://github.com/VuDuyLe04/JENKINS-GITHUB.git'
            }
        }
        // stage('Run Test') {
        //     steps {
        //         script {
        //             // Chạy bài test với JUnit Console Launcher
        //             bat 'java -jar junit-platform-console-standalone-1.7.2.jar --class-path target/classes --scan-class-path'
        //         }
        //     }
        // }
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

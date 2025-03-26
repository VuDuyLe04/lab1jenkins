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

        // stage('Test') {
        //     steps {
        //         script {
        //             // Thực hiện test (mvn test)
        //             dir('C:/ProgramData/Jenkins/.jenkins/workspace/Lab1-jenkins/test/lab1') {
        //                 bat 'mvn clean test -Dtest=DoctorManagerTest'  // Chạy bài kiểm thử Maven
        //             }
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

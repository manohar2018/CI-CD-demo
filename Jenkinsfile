    pipeline {
        options {
            buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '30'))
            disableConcurrentBuilds()
        }
        agent any
        tools {
            maven 'mvn'
        }
        environment {
            APP_NAME = "demo-java"
            MAJOR_VERSION = "1"
            ART_VERSION = ""
        }
        parameters {
        
        string(name: 'IMAGE_TAG', defaultValue: 'latest', description: '')
        booleanParam(name: 'PUBLISH', defaultValue: false, description: 'Publish Docker Image?')

        }
        stages { 
            // stage('Code Checkout'){
            //     steps {
            //     git branch: '*/main', credentialsId: 'git', url: "https://github.com/manohar2018/CI-CD-demo.git"
            //     println "checkout done"
            //     }
            // }
            stage('Build Application') {
                steps {
                    sh '''
                    cd ${WORKSPACE}/demo
                    mvn clean package
                    '''
                }
            }

            stage('Run Sonar Analysis') {
                steps {

                    withSonarQubeEnv(credentialsId: 'svc-sonar', installationName: 'sonarqube-demo') { 
                    sh '''
                    cd ${WORKSPACE}/demo
                    export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-11.0.13.0.8-1.amzn2.0.3.x86_64
                    mvn clean verify sonar:sonar -Dsonar.projectKey=Demo-ci-cd
                    '''      
                sleep(10)   
                   }
                }
            }
            

            stage("Sonar Quality Gate") {
                // when {
                //     anyOf {
                //         branch 'main';
                //         changeRequest()
                //     }
                // }
                steps {
                    timeout(time: 1, unit: 'MINUTES') {
                        waitForQualityGate abortPipeline: true
                    }
                }
            }

            stage('UnitTest'){
                steps {
                    println "Inside UnitTest stage"
                    sh '''
                        cd ${WORKSPACE}/demo
                        mvn test -B
                        '''
                    step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
                }    
            }

            stage('Packaging and upload to Nexus'){
                steps {
                    println "[INFO]  Packaging the artifact and upload to nexus"
                    sh '''
                        file_path=$(find ${WORKSPACE}/demo/target/ -name *.jar)
                        file=$(basename ${file_path})
                        file_segments=($(echo ${file} | tr '-' ' '))
                        artifact=${file_segments[0]}
                        version=($(echo ${file_segments[1]} | tr '+' ' '))
                        public_version=${version[0]}
                        local_version=${version[1]}
                        curl -v \
                        --upload-file "${file_path}" \
                        -u admin:Manohar123$ \
                        'http://54.82.52.61:8081/repository/Demo-ci-cd/demo-grp/'"${artifact}/${public_version}/${artifact}-${public_version}"'.jar'
                        
                        '''
                }
            }

           stage('Build docker image'){
                steps {
                    sh "docker build -t public.ecr.aws/m4n3o5v2/demo:latest ."
                }
            }

            stage("Publish Docker Image") {
            // when {
            //     expression { return params.PUBLISH }
            // }
            steps {

                sh """
                 aws ecr-public get-login-password --region us-east-1 | docker login --username AWS --password-stdin public.ecr.aws/m4n3o5v2
                 docker push public.ecr.aws/m4n3o5v2/demo:latest
                 """
            }
        }
        stage('Deploy Non-Prod') {
            steps {
                sh"""
                    ansible-playbook demo_deploy.yml  -i inventory -u ec2-user --extra-vars "cur_env=dev"
                """
            }
        }
        stage('Test Non-Prod') {
            steps {
                sh"""
                    # Script to check the sanity/health endpoint & to invoke tsuit
                """
            }
        }
        stage('Approve Prod Deployment') {
            steps {
                script{
                    userInput = input(
                        id: 'Approve', message: 'Deploy to production?', parameters: [
                        [$class: 'BooleanParameterDefinition', defaultValue: true, description: '', name: 'Please confirm you agree with this']
                        ])
                }
                sh"""
                    # Script to check the sanity/health endpoint & to invoke tsuit
                """
            }
        }
        stage('Prod Deployment') {
            when {
                expression { userInput == true }
            }
            steps {
                sh"""
                    ansible-playbook demo_deploy.yml  -i inventory -u ec2-user --extra-vars "cur_env=prod"
                """
            }
        }
        stage('Test Prod') {
            steps {
                sh"""
                    # Script to check the sanity/health endpoint & to invoke tsuit
                """
            }
        }
      }
    }  
        


    
# CI-CD-demo
CI/CD automation for java servlet application.

#setup tools & infra on aws.
provisioned one ec2 and installed required softwares and tools and configured.
Jdk(8&11)
Apache-maven
Jenkins standalone
sonarqube
Nexus
Docker

#Automation of CI
Defined Jenkinsfile for maven build and integrated Uts and uploaded the result to sonarqube by using jacoco
artifacts uploaded to nexus
docker image created and pushed to ecr
#CD automation for lower environment
Dcoker swarm cluster created made one manager node and two worker nodes
Ansible used for configuration management 
Jenkins file 
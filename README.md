# CI-CD-demo

CI/CD automation for java application.

# setup tools & infra on aws.

provisioned one instance on aws and installed required softwares and configured.
Jdk
Apache-maven
Jenkins
sonarqube
Nexus
Docker

# Automation of CI

GITHUB webhook configured to trigger jenkins job for each selected event. here inthis case for each push.

Defined Jenkinsfile for maven build and integrated Uts and uploaded the test results to sonarqube by using jacoco
artifacts uploaded to nexus

# CD automation

Docker image creted for container based deployments.
Dcoker swarm cluster created for non-prod and for prod.
one manager and two worker nodes setup for each environment.
Ansible used for configuration management
manual approval enabled for promoting deployments to higher environment.
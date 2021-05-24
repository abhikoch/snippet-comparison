#!groovy

node {
	def projectName = "snippet-comparison"
	def buildVersion = ""
	def branchName = ""
	//def jdkHome = installTool "jdk1.8.0_141"
	//def mvnHome = installTool "apache-maven-3.3.9"
	def jdkHome = "C:\Program Files\Java\jdk1.8.0_271"

	stage('Checkout Source Code') {
		println " ************** Stage: Running Checkout Source Code ************** "
		checkout scm
	}

	stage('Build') {
		println " ************** Stage: Build the Code ************** "
		withEnv(["JAVA_HOME=${jdkHome}") {
					bat "mvnw clean compile"
		}
	}

	stage('Unit Test') {
		println " ************** Stage: Build the Unit Test and SonarQube ************** "
		bat "mvnw clean test"
	}

	stage('Package') {
		println " ************** Stage: Package of the Code ************** "
		bat "mvnw package"
	}

	stage('Comment On Pull Request') {
		println "★★★ Stage: Comment On Pull Request ★★★ "
	}

}

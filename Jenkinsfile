#!groovy

node {
	def projectName = "snippet-comparison"
	def buildVersion = ""
	def branchName = ""
	//def jdkHome = installTool "jdk1.8.0_141"
	//def mvnHome = installTool "apache-maven-3.3.9"

	stage('Checkout Source Code') {
		println " ************** Stage: Running Checkout Source Code ************** "
		checkout scm
	}

	stage('Build') {
		println " ************** Stage: Build the Code ************** "
		sh "mvnw clean compile"
	}

	stage('Unit Test') {
		println " ************** Stage: Build the Unit Test and SonarQube ************** "
		sh "mvnw clean test"
	}

	stage('Package') {
		println " ************** Stage: Package of the Code ************** "
		sh "mvnw package"
	}

	stage('Comment On Pull Request') {
		println "★★★ Stage: Comment On Pull Request ★★★ "
	}

}

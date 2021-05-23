#!/usr/bin/env groovy

node {
	def projectName = "snippet-comparison"
	def buildVersion = ""
	def branchName = ""
	//def jdkHome = installTool "jdk1.8.0_141"
	//def mvnHome = installTool "apache-maven-3.3.9"

	stage('Checkout Source Code') {
		println " ************** Stage: Running Checkout Source Code ************** "
		checkout scm
		notifyStash()
	}

	stage('Build') {
		println " ************** Stage: Build the Code ************** "

	}

	stage('Unit Test') {
		println " ************** Stage: Build the Unit Test and SonarQube ************** "
	}

	stage('Package') {
		println " ************** Stage: Package of the Code ************** "
	}

	stage('Comment On Pull Request') {
		println "★★★ Stage: Comment On Pull Request ★★★ "
	}

}

#!/usr/bin/env groovy

node {
	def projectName = "snippet-comparison"
	def buildVersion = ""
	def branchName = ""

	stage('Checkout Source Code') {
		println " ************** Stage: Running Checkout Source Code ************** "
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

#!/usr/bin/env groovy

node {
	def projectName = "snippet-comparison"
	def buildVersion = ""
	def branchName = ""
	try {
		def jdkHome = installTool "jdk1.8.0_141"
		def mvnHome = installTool "apache-maven-3.3.9"
		def gitShortCommitID = null;

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
			commentOnPullRequest("Everything Is Ok")
		}

	}catch (Exception e) {
		println "★★★ In Exception ★★★ "
		currentBuild.result = 'FAILURE'
	} finally {
		onComplete {
			notifyStash()
		}
	}


}

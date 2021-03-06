#!/usr/bin/env sh

if [ "$TRAVIS_BRANCH" = "master" ] && [ "$TRAVIS_PULL_REQUEST" = "false" ];
then
	if [ "$MANUAL_RELEASE_TRIGGERED" = "true" ];
	then
		echo "Sign, Upload archives to local repo, Upload archives to Sonatype, Close and release repository."
		./gradlew assemble -x signArchives
		./gradlew publishToNexusAndClose
	fi
fi
#!/usr/bin/env sh

if [ "$TRAVIS_BRANCH" = "master" ] && [ "$TRAVIS_PULL_REQUEST" = "false" ];
then
    openssl aes-256-cbc -K ${encrypted_548f3093cad7_key} -iv ${encrypted_548f3093cad7_iv} -in ${ENCRYPTED_GPG_KEY_LOCATION} -out ${GPG_KEY_LOCATION} -d
fi
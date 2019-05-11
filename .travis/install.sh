#!/usr/bin/env sh

if [ "$TRAVIS_BRANCH" = "master" ] && [ "$TRAVIS_PULL_REQUEST" = "false" ];
then
    echo "-K $encrypted_e4e4268fce40_key"
    echo "-iv $encrypted_e4e4268fce40_iv"
    echo "-in ${ENCRYPTED_GPG_KEY_LOCATION}"
    echo "-out ${GPG_KEY_LOCATION}"
    openssl aes-256-cbc -K $encrypted_e4e4268fce40_key -iv $encrypted_e4e4268fce40_iv -in ${ENCRYPTED_GPG_KEY_LOCATION} -out ${GPG_KEY_LOCATION} -d
fi
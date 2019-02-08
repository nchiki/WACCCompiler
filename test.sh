#!/bin/bash

VALID_TESTS="tests/valid"
INVALID_SEMANTIC_TESTS="tests/invalid/semanticErr"
INVALID_SYNTAX_TESTS="tests/invalid/syntaxErr"

DIRECTORY="tests/valid"
EXIT_CODE=0

if [[ $1 == "valid" ]]
then
    DIRECTORY=$VALID_TESTS
    EXIT_CODE=0
elif [[ $2 == "syntax_err" ]]
then
    DIRECTORY=$INVALID_SYNTAX_TESTS
    EXIT_CODE=100
elif [[ $3 == "semantic_err" ]]
then
    DIRECTORY=$INVALID_SEMANTIC_TESTS
    EXIT_CODE=200
else
    exit 127
fi

find $DIRECTORY -name "*.wacc" | while read fname; do
  sh compile $fname
  if [[ !($? -eq $EXIT_CODE) ]]
  then
        exit 1
  fi
done

exit 0
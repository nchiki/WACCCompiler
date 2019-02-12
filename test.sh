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
elif [[ $1 == "syntax_err" ]]
then
    DIRECTORY=$INVALID_SYNTAX_TESTS
    EXIT_CODE=100
elif [[ $1 == "semantic_err" ]]
then
    DIRECTORY=$INVALID_SEMANTIC_TESTS
    EXIT_CODE=200
else
    exit 127
fi

TESTS=$(find $DIRECTORY -name "*.wacc" | wc -l)

find $DIRECTORY -name "*.wacc" | (
    FAILED=0
    while read fname; do
        sh compile $fname > /dev/null 2>&1
        RETURNED=$?
        if [[ !($RETURNED -eq $EXIT_CODE) ]]
        then
            echo "$fname test failed, expected $EXIT_CODE but returned $RETURNED"
            FAILED=$(($FAILED + 1))
        fi
    done

    exit $FAILED
)

FAILED=$?
if [[ $FAILED -gt 0 ]]
then
    echo "$1 failed $FAILED out of $TESTS tests"
fi
exit $FAILED
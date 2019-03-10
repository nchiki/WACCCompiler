#!/usr/bin/env bash

BASIC="tests/valid/basic"
SEQUENCE="tests/valid/sequence"
PRINT="tests/valid/IO/print"
VARIABLE="tests/valid/variables"
EXPRESSION="tests/valid/expressions"
ARRAY="tests/valid/array"
CONDITIONAL="tests/valid/if"
LOOP="tests/valid/while"
SCOPE="tests/valid/scope"
SIMPLE_FUNCTIONS="tests/valid/function/simple_functions"
NESTED_FUNCTIONS="tests/valid/function/nested_functions"
RUNTIME_ERROR="tests/valid/runtimeErr"
PAIR="tests/valid/pairs"
ADVANCED="tests/valid/advanced"

DIRECTORY="tests/"

if [[ $1 == "basic" ]]
then
    DIRECTORY=$BASIC
elif [[ $1 == "sequence" ]]
then
    DIRECTORY=$SEQUENCE
elif [[ $1 == "print" ]]
then
    DIRECTORY=$PRINT
elif [[ $1 == "variable" ]]
then
    DIRECTORY=$VARIABLE
elif [[ $1 == "expression" ]]
then
    DIRECTORY=$EXPRESSION
elif [[ $1 == "array" ]]
then
    DIRECTORY=$ARRAY
elif [[ $1 == "conditional" ]]
then
    DIRECTORY=$CONDITIONAL
elif [[ $1 == "loop" ]]
then
    DIRECTORY=$LOOP
elif [[ $1 == "scope" ]]
then
    DIRECTORY=$SCOPE
elif [[ $1 == "simple-function" ]]
then
    DIRECTORY=$SIMPLE_FUNCTIONS
elif [[ $1 == "nested-function" ]]
then
    DIRECTORY=$NESTED_FUNCTIONS
elif [[ $1 == "runtime-error" ]]
then
    DIRECTORY=$RUNTIME_ERROR
elif [[ $1 == "pair" ]]
then
    DIRECTORY=$PAIR
elif [[ $1 == "advanced" ]]
then
    DIRECTORY=$ADVANCED
else
    echo Please enter valid argument
    exit 127
fi

TESTS=$(find $DIRECTORY -name "*.wacc" | wc -l)
echo ""
echo "-----------------------------------------------------"
find $DIRECTORY -name "*.wacc" | (
    FAILED=0
    while read fname; do
        sh compile $fname
        SHORTENED=$(basename ${fname::$((${#fname} - 5))})
	eval $(arm-linux-gnueabi-gcc -o $SHORTENED -mcpu=arm1176jzf-s -mtune=arm1176jzf-s "$SHORTENED.s")
	ACTUAL="$(eval qemu-arm -L /usr/arm-linux-gnueabi/ $SHORTENED)"
	ACTUAL_NO_ADDRESSES=$ACTUAL | sed -e 's/0x[0-9]*//g'
	EXPECTED="$(eval ruby refCompile $fname -x)"
	EXPECTED_NO_ADDRESSES=$EXPECTED | sed -e 's/0x[0-9]*//g'
	if [[ !("$ACTUAL_NO_ADDRESSES" = "$EXPECTED_NO_ADDRESSES") ]]
        then
            printf "$fname test failed\n\n"
            echo Expected output:
            echo $EXPECTED
            echo ""
            echo Actual output:
            echo $ACTUAL
            FAILED=$(($FAILED + 1))
        else
            printf "$fname test succeeded\n"
        fi
        eval rm $SHORTENED
        eval rm $SHORTENED.s
        echo "-----------------------------------------------------"
    done

    exit $FAILED
)

FAILED=$?
if [[ $FAILED -gt 0 ]]
then
    printf "\nFailed $FAILED out of $TESTS $1 tests\n\n"
else
    printf "\nAll $1 tests succeeded!\n\n"
fi
exit $FAILED

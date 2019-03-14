#!/usr/bin/env bash

USE_REF_COMPILER="NO"

ALL="tests/valid"
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
    USE_REF_COMPILER="YES"
elif [[ $1 == "scope" ]]
then
    DIRECTORY=$SCOPE
elif [[ $1 == "simple-function" ]]
then
    DIRECTORY=$SIMPLE_FUNCTIONS
elif [[ $1 == "nested-function" ]]
then
    DIRECTORY=$NESTED_FUNCTIONS
    USE_REF_COMPILER="YES"
elif [[ $1 == "runtime-error" ]]
then
    DIRECTORY=$RUNTIME_ERROR
    USE_REF_COMPILER="YES"
elif [[ $1 == "pair" ]]
then
    DIRECTORY=$PAIR
    USE_REF_COMPILER="YES"
elif [[ $1 == "advanced" ]]
then
    DIRECTORY=$ADVANCED
elif [[ $1 == "all" ]]
then
    DIRECTORY=$ADVANCED
else
    echo Please enter valid argument
    exit 127
fi

get_exit() {
    EXIT_REGEX='#[[:space:]]Exit:'$'\n''#[[:space:]]([0-9]*)'
    if [[ "$1" =~ $EXIT_REGEX ]]; then
        echo ${BASH_REMATCH[1]}
    else
        echo EXIT CANNOT BE FOUND
    fi;
}

get_output() {
    emptyRegex='#[[:space:]]Output:'$'\n''#[[:space:]]#empty#'
    if [[ "$1" =~ $emptyRegex ]]; then
        echo ""
    else
        regex='#[[:space:]]Output:'$'\n''(.*)'$'\n'''$'\n''# Exit'
        if [[ "$(get_exit "$1")" == "EXIT CANNOT BE FOUND" ]]; then
            regex='#[[:space:]]Output:'$'\n''(.*)'$'\n'''$'\n''# Program'
        fi
        OUTPUT_ARRAY=()
        if [[ "$1" =~ $regex ]]; then
            FULL_OUTPUT=${BASH_REMATCH[1]}
            regex='# (.*)'
            SAVEIFS=$IFS   # Save current IFS
            IFS=$'\n'      # Change IFS to new line
            for aString in ${FULL_OUTPUT[@]}; do
                if [[ ${aString} =~ $regex ]]; then
                    OUTPUT_ARRAY+=(${BASH_REMATCH[1]})
                fi
            done
            IFS=$SAVEIFS   # Restore IFS
        fi;
        OUTPUT=$( IFS=$'\n'; echo "${OUTPUT_ARRAY[*]}" )
        IFS=$SAVEIFS   # Restore IFS
        echo "$OUTPUT"
    fi;
}

TESTS=$(find $DIRECTORY -name "*.wacc" | wc -l)
echo ""
echo "-----------------------------------------------------"
find $DIRECTORY -name "*.wacc" | (
    FAILED=0
    while read fname; do
        sh compile $fname
        SHORTENED=$(basename ${fname::$((${#fname} - 5))})
        # Extract expected output from file
        if [ "$USE_REF_COMPILER" = "YES" ]; then
            EXPECTED="$(eval ruby refCompile $fname -x)"
	        EXPECTED="$(echo "$EXPECTED" | sed -r 's/0x[0-9a-z]+/#addrs#/g')"
	        EXPECTED="$(echo -e "${EXPECTED}" | sed -e 's/[[:space:]]*$//')"
        else
            FILE=`cat $fname`
            EXPECTED=$(get_output "$FILE")
            EXPECTED=$(echo "$EXPECTED" | sed -r 's/#input#//g')
            EXPECTED=$(echo "$EXPECTED" | sed -r 's/#output#/0/g')
        fi;

        # Get actual output
        eval $(arm-linux-gnueabi-gcc -o $SHORTENED -mcpu=arm1176jzf-s -mtune=arm1176jzf-s "$SHORTENED.s")
        ACTUAL="$(eval qemu-arm -L /usr/arm-linux-gnueabi/ $SHORTENED)"
        ACTUAL="$(echo "$ACTUAL" | sed -r 's/0x[0-9a-z]+/#addrs#/g')"
        ACTUAL="$(echo -e "${ACTUAL}" | sed -e 's/[[:space:]]*$//')"

        if [[ !("$ACTUAL" = "$EXPECTED") ]]
        then
            printf "$fname test failed\n\n"
            echo Expected output:
            echo "$EXPECTED"
            echo ""
            echo Actual output:
            echo "$ACTUAL"
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

#!/usr/bin/env bash

EXIT="tests/valid/basic/exit"
PRINT="tests/valid/IO/print"

DIRECTORY="tests/"

if [[ $1 == "exit" ]]
then
    DIRECTORY=$EXIT
elif [[ $1 == "print" ]]
then
    DIRECTORY=$PRINT
fi

TESTS=$(find $DIRECTORY -name "*.wacc" | wc -l)

find $DIRECTORY -name "*.wacc" | (
    FAILED=0
    while read fname; do
        sh compile $fname
        SHORTENED=${fname::$((${#fname} - 5))}
        SHORTENED=${SHORTENED##*/}
        echo $SHORTENED
	      eval $(arm-linux-gnueabi-gcc -o $SHORTENED -mcpu=arm1176jzf-s -mtune=arm1176jzf-s "$SHORTENED.s")
        echo end
        if [[ !($(sh qemu-arm -L /usr/arm-linux-gnueabi/ $SHORTENED) -eq $(sh ruby refCompile fname -x)) ]]
        then
            echo "$fname test failed, expected  but returned "
            FAILED=$(($FAILED + 1))
        fi
    done
)

FAILED=$?
if [[ $FAILED -gt 0 ]]
then
    echo "$1 failed $FAILED out of $TESTS tests"
fi
exit $FAILED

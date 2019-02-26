#!/usr/bin/env bash

EXIT="tests/valid/basic/exit"

DIRECTORY="tests/"

if [[ $1 == "exit" ]]
then
    DIRECTORY=$EXIT
fi

TESTS=$(find $DIRECTORY -name "*.wacc" | wc -l)

find $DIRECTORY -name "*.wacc" | (
    FAILED=0
    while read fname; do
        sh compile $fname > /dev/null 2>&1
        SHORTENED=${fname::-6}
        sh arm-linux-gnueabi-gcc -o $SHORTENED -mcpu=arm1176jzf-s -mtune=arm1176jzf-s "$SHORTENED.s"
        sh qemu-arm -L /usr/arm-linux-gnueabi/ $SHORTENED
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
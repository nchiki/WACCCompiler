#!/usr/bin/env bash

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

myString='# recursive calculation of the first 20 fibonacci numbers

# Output:
# The first 20 fibonacci numbers are:
# 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181...

# Program:

begin
  int fibonacci(int n, bool toPrint) is
    if n <= 1
    then
      return n
    else
      skip
    fi ;
    int f1 = call fibonacci(n - 1, toPrint) ;
    if toPrint
    then
      print f1 ;
      print ", "
    else
      skip
    fi ;
    int f2 = call fibonacci(n - 2, false) ;
    return f1 + f2
  end

  println "The first 20 fibonacci numbers are:" ;
  print "0, " ;
  int result = call fibonacci(19, true) ;
  print result ;
  println "..."
end

'

EXPECTED_EXIT=$(get_exit "$myString")
OUTPUT=$(get_output "$myString")

echo EXPECTED EXIT: $EXPECTED_EXIT
echo EXPECTED OUTPUT:
echo "$OUTPUT"
#!/usr/bin/env bash

output="# EXIT 5"
regex='^#[[:space:]]EXIT[[:space:]]([0-9])'
if [[ "$output" =~ $regex ]]; then
  echo "${BASH_REMATCH[1]}"
fi;

test="#EXIT 5
# OUTPUT
# test1
# test2

blah blah"

newRegex='^#[[:space:]]EXIT[[:space:]]([0-9])$
^#[[:space:]]OUT(.*?)'
if [[ "$test" =~ $newRegex ]]; then
  echo "${BASH_REMATCH[1]}"
  echo "${BASH_REMATCH[2]}"
fi;

#regex=""
#if [[ "$TEST" =~ ^EXI([a-z])$ ]]
#then
#    name="${BASH_REMATCH[1]}"
#    echo "${name}.jpg"    # concatenate strings
#    name="${name}.jpg"    # same thing stored in a variable
#else
#    echo "$TEST doesn't match" >&2 # this could get noisy if there are a lot of non-matching files
#fi
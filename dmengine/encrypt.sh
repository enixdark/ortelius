#!/bin/sh
# add -p to see key, iv and salt
#echo -n "postgres"
#echo -n "harvest" | openssl des3 -e -a -pass "pass:dm15k1ng"
echo -n "dm postgres postgres" | openssl des3 -e -a -pass "pass:dm15k1ng"

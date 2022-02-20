#!/bin/sh
test $(curl 192.168.101.7:8765/sum?a=1\&b=2) -eq 3
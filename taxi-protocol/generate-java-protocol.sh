#!/usr/bin/env bash

/home/petronic/jdk1.8.0_65/bin/java -jar wire-compiler-2.0.1-jar-with-dependencies.jar \
    --proto_path=src/main/proto \
    --java_out=src/main/java
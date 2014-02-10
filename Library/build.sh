#!/bin/sh

mvn clean package && mvn install:install-file -Dfile=target/WrapHeightGridView-0.0.2.apklib -DpomFile=custom_pom.xml 


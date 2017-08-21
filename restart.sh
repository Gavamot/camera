#!/bin/sh
sleep 5
sudo java -Dlog4j.configurationFile=log4j2.xml -Djava.library.path=/home/user/opencv-3.1.0/build/lib -jar ComPort.jar

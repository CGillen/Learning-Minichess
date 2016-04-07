#!/usr/bin/env bash
cd /vagrant/client-java/
make
java -jar -ea client.jar &

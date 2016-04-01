#!/usr/bin/env bash
cd /vagrant/client-java/
make
java -ea -jar client.jar &


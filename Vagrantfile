# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  # Every Vagrant development environment requires a box. You can search for
  # boxes at https://atlas.hashicorp.com/search.
  config.vm.box = "ubuntu/trusty64"

  # Forward port for framework
  config.vm.network "forwarded_port", guest: 8080, host: 8080

  config.vm.provision "shell", path: "bootstrap.sh"
#  config.vm.provision "shell", path: "framework.sh", run: "always"
#  config.vm.provision "shell", path: "client.sh", run: "always"
end

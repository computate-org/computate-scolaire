#!/bin/bash

export langueNom="frFR"
export appliNom="${appliNom:-computate.org}"
export appliChemin="${appliChemin:-$(readlink -f $( dirname $( dirname $( dirname ${BASH_SOURCE[0]} ) ) ) )}"
export appliComputateChemin="${appliComputateChemin:-/usr/local/src/computate}"

source "$appliComputateChemin/bin/frFR/00-functions.sh"

#################
# computate.org #
#################

computate "cd $appliChemin && mvn install"

computate "echo '
[Unit]
Description=Regarder l'"'"'"'"'"'"'appli computate et générer decode Java lorsque les fichiers source Java sont modifiés. 
After=network.target

[Service]
Type=simple
User=$USER
Group=$USER
WorkingDirectory=$appliChemin
Environment=appliNom=$appliNom
Environment=appliChemin=$appliChemin
Environment=appliComputateChemin=$appliComputateChemin
ExecStart=$appliComputateChemin/bin/$langueNom/regarder.sh

[Install]
WantedBy=multi-user.target
' | sudo tee /usr/lib/systemd/system/regarder-$appliNom.service"
computate "sudo systemctl daemon-reload"
computate "sudo systemctl restart regarder-$appliNom.service"
computate "systemctl status regarder-$appliNom.service --no-pager -l"
computate "sudo systemctl enable regarder-$appliNom.service"

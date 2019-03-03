#!/bin/bash

export langueNom="frFR"
export appliNom="${appliNom:-computate-scolaire}"
export appliChemin="${appliChemin:-$(readlink -f $( dirname $( dirname $( dirname ${BASH_SOURCE[0]} ) ) ) )}"
export appliComputateChemin="${appliComputateChemin:-/usr/local/src/computate}"

source "$appliComputateChemin/bin/frFR/00-functions.sh"

computate $appliComputateChemin/bin/$langueNom/installer.sh
computate $appliChemin/bin/$langueNom/installer.sh

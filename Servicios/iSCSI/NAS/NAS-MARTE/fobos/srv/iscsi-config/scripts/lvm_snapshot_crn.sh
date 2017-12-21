#!/bin/bash

SCRIPTS="/srv/iscsi-config/scripts"
cd $SCRIPTS

while read  linea
do
    li=$(echo $linea | cut -c1)
    if [ "$li" != "#" ]; then
        if [ "$li" != "\ " ]; then
            echo Ejecutando $linea
            lvmname=$(echo $linea | cut -d "," -f 1)
            retencion=$(echo $linea | cut -d "," -f 2)
#            echo $lvmname
#            echo $retencion
#            echo $lvmname"-20"`date +"%y%m%d"`
            ### lvremove -f /dev/vg0drbd/lun2back2
            echo "Creando el Snapshot del Volumen "$lvmname
            /sbin/lvcreate -s -l 100%ORIGIN -n $lvmname"-20"`date +"%y%m%d"` $lvmname
        else
            echo ignorando blancos
        fi
    else
        echo "ignorado #"
    fi
done < politica_snapshot.conf

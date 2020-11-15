#!/bin/bash

#
#	Este es un programa que genera los números de cuenta de los alumnos aleatoriamente.
#

for (( i=1; i<151; i++ ))
do
	echo $((($RANDOM%10000*100)+($RANDOM%100))) >> numerosdecuenta.txt
done


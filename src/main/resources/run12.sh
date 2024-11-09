#!/bin/sh
cd /usr/local/mysoftware/GSRfinder
python pre12.py --input $1 --gsr $2 --organism $3 --outdir $4

exit
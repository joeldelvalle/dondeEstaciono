#!flask/bin/python

import os

# Poner la ruta al script
pybabel = 'C:\\Python27\\Scripts\pybabel.exe'

os.system(pybabel + ' extract -F ' + os.getcwd().replace('install', 'babel.cfg') + ' -o messages.pot ' + os.getcwd().replace('install', ''))
os.system(pybabel + ' update -i messages.pot -d ' + os.getcwd().replace('install', 'application\\translations') + ' -l es')
os.system(pybabel + ' update -i messages.pot -d ' + os.getcwd().replace('install', 'application\\translations') + ' -l en')
__author__ = 'gromero'

import re

def getSeed():
    seedText = "tiriTiriTiriGadgetGadget"

    seed = 0
    for letra in seedText:
        seed = seed + ord(letra)

    return seed

def encrypted(mensaje):
    mensajeChar = ""
    for letra in mensaje:
        mensajeChar = mensajeChar + str(ord(letra) + getSeed())

    return mensajeChar.encode('base64')

def dencrypted(mensaje):
    decodeMessage = mensaje.decode('base64')

    dencrypt = ''
    for num in re.findall('....', decodeMessage):
        charDecoded = int(num) - getSeed()
        dencrypt = dencrypt + chr(charDecoded)

    return dencrypt
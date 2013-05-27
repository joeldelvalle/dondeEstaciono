__author__ = 'gromero'

import json
import logging

keys = [2, 7, 3, 5, 13, 21, 47, 31, 17, 23]

def convertStringToHex(str):
    messageChar = ''
    for letter in str:
        messageChar = messageChar + letter.encode("hex")

    mac = messageChar.upper()

    return mac

def getCalculateMac(hexa):
    mac = hexa
    while len(mac) > 10:
        sum = 0
        for letra in hexa:
            sum = sum + int(letra, 16)

        mac = convertStringToHex(str(sum))

    return mac

def sign(value):
    sign = ''
    for i in range(0, len(value), 1):
        sign = sign + str(int(value[i], 16) * keys[i])

    return sign

def buildMac(jsonStr):
    jsonHex = convertStringToHex(jsonStr)

    macFirstPart = getCalculateMac(jsonHex)

    macSecondPart = sign(macFirstPart)

    return macFirstPart.upper() + macSecondPart.upper()
'''
Created on 17/07/2013

@author: jdelvalle
'''

from application.communication.request import  Payload, sendRequest, Request
from application.security.mac import buildMac
from application.commons.convert import object2json, json2object
from application.security import encrypt
import json
import logging
from easydict import EasyDict as edict

# metodo que arma el request para solicitar informacion de applicacion al servidor
def createRequestToApplicationRest():
    payload = Payload('')
    request = Request(payload, buildMac(object2json('')), 'HASH-PUBLIC-WEB')
    return encrypt.encrypted(object2json(request))

# devuelve todos los timeType del server
def getAllTimeType():    
    response = sendRequest('/rest/application/list/timeType/all', createRequestToApplicationRest())
    logging.info(response)
    data = json.loads(response)
    return json2object(edict(data))

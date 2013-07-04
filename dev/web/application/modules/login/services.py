__author__ = 'gromero'

import json, urllib2, logging
from application.security import encrypt
from application.communication.request import Payload, sendRequest
from requests import LoginRequest
from application.security.mac import buildMac
from application import p
from application.commons.convert import object2json, json2object
from application.communication.request import Request
from easydict import EasyDict as edict
from application.commons.exceptions import LoginException

URL = "/rest/login/authentication"

def getRequest(user, password):
    loginRequest = LoginRequest(user, password)
    payload = Payload(loginRequest)
    request = Request(payload, buildMac(object2json(loginRequest)), 'HASH-PUBLIC-WEB')
    return request

def login(user, password):
    request = getRequest(user, password)
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    try:
        return json2object(edict(data))
    except Exception as exc:
        raise LoginException(exc.message)

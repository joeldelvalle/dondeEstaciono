__author__ = 'gromero'

from application.commons.convert import object2json, json2object
from application.commons.exceptions import LoginException
from application.communication.request import Payload, sendRequest, Request
from application.modules.configuration.requests import VehicleTypeRequest
from application.security import encrypt
from application.security.mac import buildMac
from easydict import EasyDict as edict
import json
import logging

URL = "/rest/find/byParking/vehicletyperequest"
URL_SAVE = "/rest/save/vehicletyperequest"
URL_REMOVE = "/rest/delete/vehicletyperequest"

def getAllVehicleTypeRequest(parkingIdentificationCode):
    vehiclerequest = VehicleTypeRequest(parkingIdentificationCode)
    payload = Payload(vehiclerequest)
    request = Request(payload, buildMac(object2json(vehiclerequest)), 'HASH-PUBLIC-WEB')
    return request

def getSaveVehicleTypeRequest(parkingIdentificationCode, description):
    vehiclerequest = VehicleTypeRequest(parkingIdentificationCode, description)
    payload = Payload(vehiclerequest)
    request = Request(payload, buildMac(object2json(vehiclerequest)), 'HASH-PUBLIC-WEB')
    return request

def getRemoveVehicleTypeRequest(parkingIdentificationCode, id):
    vehiclerequest = VehicleTypeRequest(parkingIdentificationCode=parkingIdentificationCode, id=id)
    payload = Payload(vehiclerequest)
    request = Request(payload, buildMac(object2json(vehiclerequest)), 'HASH-PUBLIC-WEB')
    return request

def getAllVehicleType(parkingIdentificationCode):
    request = getAllVehicleTypeRequest(parkingIdentificationCode)
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    return json2object(edict(data))

def saveVehicleType(parkingIdentificationCode, description):
    request = getSaveVehicleTypeRequest(parkingIdentificationCode, description)
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL_SAVE, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    obj = edict(data)
    if (obj.status == "success"):
        return obj.status
    else:
        return json2object(obj)

def removeVehicleType(parkingIdentificationCode, id):
    request = getRemoveVehicleTypeRequest(parkingIdentificationCode, id)
    logging.info(object2json(request))
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL_REMOVE, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    obj = edict(data)
    if (obj.status == "success"):
        return obj.status
    else:
        return json2object(obj)
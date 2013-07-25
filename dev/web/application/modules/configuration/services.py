__author__ = 'gromero'

from application.commons.convert import object2json, json2object
from application.communication.request import Payload, sendRequest, Request
from application.modules.configuration.requests import VehicleTypeRequest, FrequencyTypeRequest
from application.security import encrypt
from application.security.mac import buildMac
from easydict import EasyDict as edict
import json
import logging

URL_FIND = "/rest/find/byParking/"
URL_FIND_BY_ID = "/rest/find/byParking/byId/"
URL_SAVE = "/rest/save/"
URL_UPDATE = "/rest/update/"
URL_REMOVE = "/rest/delete/"

VEHICLE_TYPE_REQUEST = "vehicletyperequest"
FREQUENCY_TYPE_REQUEST = "frequencytyperequest"

'''
    VEHICLE_TYPE   METHODS   -   START
'''

# crea el request para la solicitud de todos los vehicleType
def getAllVehicleTypeRequest(parkingIdentificationCode):
    vehiclerequest = VehicleTypeRequest(parkingIdentificationCode)
    payload = Payload(vehiclerequest)
    request = Request(payload, buildMac(object2json(vehiclerequest)), 'HASH-PUBLIC-WEB')
    return request

# crea el request para grabar un vehicleType
def getSaveVehicleTypeRequest(parkingIdentificationCode, description):
    vehiclerequest = VehicleTypeRequest(parkingIdentificationCode, description)
    payload = Payload(vehiclerequest)
    request = Request(payload, buildMac(object2json(vehiclerequest)), 'HASH-PUBLIC-WEB')
    return request

# crea el request para actualizar un vehicleType
def getUpdateVehicleTypeRequest(parkingIdentificationCode, id, description):
    vehiclerequest = VehicleTypeRequest(parkingIdentificationCode, description, id)
    payload = Payload(vehiclerequest)
    request = Request(payload, buildMac(object2json(vehiclerequest)), 'HASH-PUBLIC-WEB')
    return request

# crea el request para borrar un vehicleType
def getRemoveVehicleTypeRequest(parkingIdentificationCode, id):
    vehiclerequest = VehicleTypeRequest(parkingIdentificationCode=parkingIdentificationCode, id=id)
    payload = Payload(vehiclerequest)
    request = Request(payload, buildMac(object2json(vehiclerequest)), 'HASH-PUBLIC-WEB')
    return request


# solicitud para obtener todos los vehicleType
def getAllVehicleType(parkingIdentificationCode):
    request = getAllVehicleTypeRequest(parkingIdentificationCode)
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL_FIND + VEHICLE_TYPE_REQUEST, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    return json2object(edict(data))

# solicitud para grabar un vehicleType
def saveVehicleType(parkingIdentificationCode, description):
    r = None
    
    request = getSaveVehicleTypeRequest(parkingIdentificationCode, description)
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL_SAVE + VEHICLE_TYPE_REQUEST, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    obj = edict(data)
    
    if (obj.status == "success"):
        r = obj.status
    else:
        r = json2object(obj)

    return r

# solicitud para actualizar un vehicleType
def updateVehicleType(parkingIdentificationCode, id, description):
    r = None
    
    request = getUpdateVehicleTypeRequest(parkingIdentificationCode, id, description)
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL_UPDATE + VEHICLE_TYPE_REQUEST, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    obj = edict(data)
    
    if (obj.status == "success"):
        r = obj.status
    else:
        r = json2object(obj)

    return r

# solicitud para borrar un vehicleType    
def removeVehicleType(parkingIdentificationCode, id):
    r = None
    
    request = getRemoveVehicleTypeRequest(parkingIdentificationCode, id)
    logging.info(object2json(request))
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL_REMOVE + VEHICLE_TYPE_REQUEST, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    obj = edict(data)
    
    if (obj.status == "success"):
        r = obj.status
    else:
        r = json2object(obj)

    return r


'''
    VEHICLE_TYPE   METHODS   -   END
'''






'''
    FREQUENCY_TYPE   METHODS   -   START
'''

# devuelve el tipo de frecuencia por ID
def getFrequencyTypeValueDescription(idFrequencyTypeValue):
    from application.commons.databaseCache import getFrequencyTypeValue
    return getFrequencyTypeValue(idFrequencyTypeValue)
    


# crea el request para la solicitud de todos los frequencyType
def getAllFrequencyTypeRequest(parkingIdentificationCode):
    frequencyrequest = FrequencyTypeRequest(parkingIdentificationCode)
    payload = Payload(frequencyrequest)
    request = Request(payload, buildMac(object2json(frequencyrequest)), 'HASH-PUBLIC-WEB')
    return request

# solicitud para obtener todos los frequencyType
def getAllFrequencyType(parkingIdentificationCode):
    request = getAllFrequencyTypeRequest(parkingIdentificationCode)
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL_FIND + FREQUENCY_TYPE_REQUEST, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    return json2object(edict(data))



# crea el request para grabar un frequencyType
def getSaveFrequencyTypeRequest(parkingIdentificationCode, description, type, time, timeType, priority, combinablePreviousFrequency):
    frequencyRequest = FrequencyTypeRequest(parkingIdentificationCode, None, description, type, time, timeType, priority, combinablePreviousFrequency)
    payload = Payload(frequencyRequest)
    request = Request(payload, buildMac(object2json(frequencyRequest)), 'HASH-PUBLIC-WEB')
    return request

# solicitud para grabar un frequencyType
def saveFrequencyType(parkingIdentificationCode, description, type, time, timeType, priority, combinablePreviousFrequency):
    result = None
    
    request = getSaveFrequencyTypeRequest(parkingIdentificationCode, description, type, time, timeType, priority, combinablePreviousFrequency)
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL_SAVE + FREQUENCY_TYPE_REQUEST, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    obj = edict(data)
    
    if (obj.status == "success"):
        result = obj.status
    else:
        result = json2object(obj)

    return result



# crea el request para borrar un frequencyType
def getRemoveFrequencyTypeRequest(parkingIdentificationCode, id):
    frequencyRequest = FrequencyTypeRequest(parkingIdentificationCode=parkingIdentificationCode, id=id)
    payload = Payload(frequencyRequest)
    request = Request(payload, buildMac(object2json(frequencyRequest)), 'HASH-PUBLIC-WEB')
    return request


# solicitud para borrar un frequencyType    
def removeFrequencyType(parkingIdentificationCode, id):
    r = None
    
    request = getRemoveFrequencyTypeRequest(parkingIdentificationCode, id)
    logging.info(object2json(request))
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL_REMOVE + FREQUENCY_TYPE_REQUEST, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    obj = edict(data)
    
    if (obj.status == "success"):
        r = obj.status
    else:
        r = json2object(obj)

    return r




# crea el request para buscar por id un frequencyType
def getFindFrequencyTypeByIdRequest(parkingIdentificationCode, id):
    frequencyRequest = FrequencyTypeRequest(parkingIdentificationCode=parkingIdentificationCode, id=id)
    payload = Payload(frequencyRequest)
    request = Request(payload, buildMac(object2json(frequencyRequest)), 'HASH-PUBLIC-WEB')
    return request



# solicitud para buscar un frequencyType por id    
def findFrequencyTypeById(parkingIdentificationCode, id):
    r = None
    
    request = getFindFrequencyTypeByIdRequest(parkingIdentificationCode, id)
    logging.info(object2json(request))
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL_FIND_BY_ID + FREQUENCY_TYPE_REQUEST, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    obj = edict(data)
    
    if (obj.status == "success"):
        r = json2object(obj)
    else:
        r = json2object(obj)

    return r





# crea el request para actualizar un frequencyType
def getUpdateFrequencyTypeRequest(parkingIdentificationCode, id, description, timeType, time, type, priority, combinablePreviousFrequency):
    frequencyRequest = FrequencyTypeRequest(parkingIdentificationCode, id, description, timeType, time, type, priority, combinablePreviousFrequency)
    payload = Payload(frequencyRequest)
    request = Request(payload, buildMac(object2json(frequencyRequest)), 'HASH-PUBLIC-WEB')
    return request


# solicitud para actualizar un frequencyType
def updateFrequencyType(parkingIdentificationCode, id, description, timeType, time, type, priority, combinablePreviousFrequency):
    r = None
    
    request = getUpdateFrequencyTypeRequest(parkingIdentificationCode, id, description, timeType, time, type, priority, combinablePreviousFrequency)
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL_UPDATE + FREQUENCY_TYPE_REQUEST, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    obj = edict(data)
    
    if (obj.status == "success"):
        r = obj.status
    else:
        r = json2object(obj)

    return r


'''
    FREQUENCY_TYPE   METHODS   -   END
'''


__author__ = 'gromero'

from application.commons.convert import object2json, json2object
from application.communication.request import Payload, sendRequest, Request
from application.modules.configuration.requests import VehicleTypeRequest, FrequencyTypeRequest, ParkingRatesRequest
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
PARKING_RATES_REQUEST = "parkingratesrequest"

'''
    VEHICLE_TYPE   METHODS   -   START
'''

# crea el request para la solicitud de todos los vehicleType
def __getAllVehicleTypeRequest(parkingIdentificationCode):
    vehiclerequest = VehicleTypeRequest(parkingIdentificationCode)
    payload = Payload(vehiclerequest)
    request = Request(payload, buildMac(object2json(vehiclerequest)), 'HASH-PUBLIC-WEB')
    return request

# crea el request para grabar un vehicleType
def __getSaveVehicleTypeRequest(parkingIdentificationCode, description):
    vehiclerequest = VehicleTypeRequest(parkingIdentificationCode, description)
    payload = Payload(vehiclerequest)
    request = Request(payload, buildMac(object2json(vehiclerequest)), 'HASH-PUBLIC-WEB')
    return request

# crea el request para actualizar un vehicleType
def __getUpdateVehicleTypeRequest(parkingIdentificationCode, id, description):
    vehiclerequest = VehicleTypeRequest(parkingIdentificationCode, description, id)
    payload = Payload(vehiclerequest)
    request = Request(payload, buildMac(object2json(vehiclerequest)), 'HASH-PUBLIC-WEB')
    return request

# crea el request para borrar un vehicleType
def __getRemoveVehicleTypeRequest(parkingIdentificationCode, id):
    vehiclerequest = VehicleTypeRequest(parkingIdentificationCode=parkingIdentificationCode, idFrequencyType=id)
    payload = Payload(vehiclerequest)
    request = Request(payload, buildMac(object2json(vehiclerequest)), 'HASH-PUBLIC-WEB')
    return request


# solicitud para obtener todos los vehicleType
def getAllVehicleType(parkingIdentificationCode):
    request = __getAllVehicleTypeRequest(parkingIdentificationCode)
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL_FIND + VEHICLE_TYPE_REQUEST, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    return json2object(edict(data))

# solicitud para grabar un vehicleType
def saveVehicleType(parkingIdentificationCode, description):
    r = None
    
    request = __getSaveVehicleTypeRequest(parkingIdentificationCode, description)
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
    
    request = __getUpdateVehicleTypeRequest(parkingIdentificationCode, id, description)
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
    
    request = __getRemoveVehicleTypeRequest(parkingIdentificationCode, id)
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




# crea el request para buscar por id un vehicleType
def __getFindVehicleTypeByIdRequest(parkingIdentificationCode, idFrequencyType):
    vehicleRequest = VehicleTypeRequest(parkingIdentificationCode=parkingIdentificationCode, idFrequencyType=idFrequencyType)
    payload = Payload(vehicleRequest)
    request = Request(payload, buildMac(object2json(vehicleRequest)), 'HASH-PUBLIC-WEB')
    return request



# solicitud para buscar un vehicleType por idFrequencyType    
def findVehicleTypeById(parkingIdentificationCode, id):
    r = None
    
    request = __getFindVehicleTypeByIdRequest(parkingIdentificationCode, id)
    logging.info(object2json(request))
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL_FIND_BY_ID + VEHICLE_TYPE_REQUEST, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    obj = edict(data)
    return json2object(obj)


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
def __getAllFrequencyTypeRequest(parkingIdentificationCode):
    frequencyrequest = FrequencyTypeRequest(parkingIdentificationCode)
    payload = Payload(frequencyrequest)
    request = Request(payload, buildMac(object2json(frequencyrequest)), 'HASH-PUBLIC-WEB')
    return request

# solicitud para obtener todos los frequencyType
def getAllFrequencyType(parkingIdentificationCode):
    request = __getAllFrequencyTypeRequest(parkingIdentificationCode)
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL_FIND + FREQUENCY_TYPE_REQUEST, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    return json2object(edict(data))



# crea el request para grabar un frequencyType
def __getSaveFrequencyTypeRequest(parkingIdentificationCode, description, type, time, timeType, priority, combinablePreviousFrequency):
    frequencyRequest = FrequencyTypeRequest(parkingIdentificationCode, None, description, type, time, timeType, priority, combinablePreviousFrequency)
    payload = Payload(frequencyRequest)
    request = Request(payload, buildMac(object2json(frequencyRequest)), 'HASH-PUBLIC-WEB')
    return request

# solicitud para grabar un frequencyType
def saveFrequencyType(parkingIdentificationCode, description, type, time, timeType, priority, combinablePreviousFrequency):
    result = None
    
    request = __getSaveFrequencyTypeRequest(parkingIdentificationCode, description, type, time, timeType, priority, combinablePreviousFrequency)
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
def __getRemoveFrequencyTypeRequest(parkingIdentificationCode, id):
    frequencyRequest = FrequencyTypeRequest(parkingIdentificationCode=parkingIdentificationCode, idFrequencyType=id)
    payload = Payload(frequencyRequest)
    request = Request(payload, buildMac(object2json(frequencyRequest)), 'HASH-PUBLIC-WEB')
    return request


# solicitud para borrar un frequencyType    
def removeFrequencyType(parkingIdentificationCode, id):
    r = None
    
    request = __getRemoveFrequencyTypeRequest(parkingIdentificationCode, id)
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
def __getFindFrequencyTypeByIdRequest(parkingIdentificationCode, idFrequencyType):
    frequencyRequest = FrequencyTypeRequest(parkingIdentificationCode=parkingIdentificationCode, idFrequencyType=idFrequencyType)
    payload = Payload(frequencyRequest)
    request = Request(payload, buildMac(object2json(frequencyRequest)), 'HASH-PUBLIC-WEB')
    return request



# solicitud para buscar un frequencyType por idFrequencyType    
def findFrequencyTypeById(parkingIdentificationCode, id):
    r = None
    
    request = __getFindFrequencyTypeByIdRequest(parkingIdentificationCode, id)
    logging.info(object2json(request))
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL_FIND_BY_ID + FREQUENCY_TYPE_REQUEST, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    obj = edict(data)
    return json2object(obj)





# crea el request para actualizar un frequencyType
def __getUpdateFrequencyTypeRequest(parkingIdentificationCode, id, description, timeType, time, type, priority, combinablePreviousFrequency):
    frequencyRequest = FrequencyTypeRequest(parkingIdentificationCode, id, description, timeType, time, type, priority, combinablePreviousFrequency)
    payload = Payload(frequencyRequest)
    request = Request(payload, buildMac(object2json(frequencyRequest)), 'HASH-PUBLIC-WEB')
    return request


# solicitud para actualizar un frequencyType
def updateFrequencyType(parkingIdentificationCode, id, description, timeType, time, type, priority, combinablePreviousFrequency):
    r = None
    
    request = __getUpdateFrequencyTypeRequest(parkingIdentificationCode, id, description, timeType, time, type, priority, combinablePreviousFrequency)
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








'''
    PARKING_RATES   METHODS   -   START
'''


# crea el request para la solicitud de todos los parkingRates
def __getAllParkingRatesRequest(parkingIdentificationCode):
    parkingRatesRequest = ParkingRatesRequest(parkingIdentificationCode)
    payload = Payload(parkingRatesRequest)
    request = Request(payload, buildMac(object2json(parkingRatesRequest)), 'HASH-PUBLIC-WEB')
    return request

# solicitud para obtener todos los parkingRates
def getAllParkingRates(parkingIdentificationCode):
    request = __getAllParkingRatesRequest(parkingIdentificationCode)
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL_FIND + PARKING_RATES_REQUEST, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    return json2object(edict(data))





# crea el request para grabar un parkingRates
def __getSaveParkingRateRequest(parkingIdentificationCode, idVehicleSelected, idFrequencySelected, amount):
    parkingRateRequest = ParkingRatesRequest(parkingIdentificationCode, None, idVehicleSelected, idFrequencySelected, amount)
    payload = Payload(parkingRateRequest)
    request = Request(payload, buildMac(object2json(parkingRateRequest)), 'HASH-PUBLIC-WEB')
    return request

# solicitud para grabar un parkingRates
def saveParkingRate(parkingIdentificationCode, idVehicleSelected, idFrequencySelected, amount):
    result = None
    
    request = __getSaveParkingRateRequest(parkingIdentificationCode, idVehicleSelected, idFrequencySelected, amount)
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL_SAVE + PARKING_RATES_REQUEST, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    obj = edict(data)
    
    if (obj.status == "success"):
        result = obj.status
    else:
        result = json2object(obj)

    return result




# crea el request para borrar un parkingRates
def __getRemoveParkingRateRequest(parkingIdentificationCode, id):
    parkingRatesRequest = ParkingRatesRequest(parkingIdentificationCode=parkingIdentificationCode, idParkingRate=id)
    payload = Payload(parkingRatesRequest)
    request = Request(payload, buildMac(object2json(parkingRatesRequest)), 'HASH-PUBLIC-WEB')
    return request


# solicitud para borrar un parkingRates    
def removeParkingRate(parkingIdentificationCode, id):
    r = None
    
    request = __getRemoveParkingRateRequest(parkingIdentificationCode, id)
    logging.info(object2json(request))
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL_REMOVE + PARKING_RATES_REQUEST, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    obj = edict(data)
    
    if (obj.status == "success"):
        r = obj.status
    else:
        r = json2object(obj)

    return r







# crea el request para buscar por id un parkingRates
def __getFindParkingRatesByIdRequest(parkingIdentificationCode, idParkingRate):
    parkingRatesRequest = ParkingRatesRequest(parkingIdentificationCode=parkingIdentificationCode, idParkingRate=idParkingRate)
    payload = Payload(parkingRatesRequest)
    request = Request(payload, buildMac(object2json(parkingRatesRequest)), 'HASH-PUBLIC-WEB')
    return request



# solicitud para buscar un frequencyType por idParkingRates    
def findParkingRatesById(parkingIdentificationCode, id):
    r = None
    
    request = __getFindParkingRatesByIdRequest(parkingIdentificationCode, id)
    logging.info(object2json(request))
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL_FIND_BY_ID + PARKING_RATES_REQUEST, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    obj = edict(data)
    return json2object(obj)






# crea el request para actualizar un parkingRates
def __getUpdateParkingRatesRequest(parkingIdentificationCode, id, vehicleTypeId, frequencyTypeId, amount):
    parkingRatesRequest = ParkingRatesRequest(parkingIdentificationCode, id, vehicleTypeId, frequencyTypeId, amount)
    payload = Payload(parkingRatesRequest)
    request = Request(payload, buildMac(object2json(parkingRatesRequest)), 'HASH-PUBLIC-WEB')
    return request


# solicitud para actualizar un parkingRates
def updateParkingRates(parkingIdentificationCode, id, vehicleTypeId, frequencyTypeId, amount):
    r = None
    
    request = __getUpdateParkingRatesRequest(parkingIdentificationCode, id, vehicleTypeId, frequencyTypeId, amount)
    dataEncrypted = encrypt.encrypted(object2json(request))
    response = sendRequest(URL_UPDATE + PARKING_RATES_REQUEST, dataEncrypted)
    logging.info(response)
    data = json.loads(response)
    obj = edict(data)
    
    if (obj.status == "success"):
        r = obj.status
    else:
        r = json2object(obj)

    return r




'''
    PARKING_RATES   METHODS   -   END
'''


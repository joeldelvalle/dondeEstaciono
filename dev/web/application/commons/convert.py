__author__ = 'gromero'

import json
from application.models.model import *
from easydict import EasyDict as edict
import jsonpickle, utils

KEY_ERROR = 'error'
KEY_USER = 'user'
KEY_VEHICLE_TYPE_LIST = 'vehicletypelist'
KEY_VEHICLE_TYPE = 'vehicletype'
KEY_TIME_TYPE = 'timeType'
KEY_TIME_TYPE_LIST = 'timetypelist'
KEY_FREQUENCY_TYPE_LIST = 'frequencytypelist'
KEY_FREQUENCY_TYPE = 'frequencytype'
KEY_PARKING_RATES_TYPE_LIST = 'parkingrateslist'
KEY_PARKING_RATES_TYPE = 'parkingrates'

keys = {
    KEY_USER: User,
    KEY_ERROR: Error,
    KEY_VEHICLE_TYPE_LIST: VehicleTypeList,
    KEY_VEHICLE_TYPE: VehicleType,
    KEY_TIME_TYPE: TimeType,
    KEY_TIME_TYPE_LIST: TimeTypeList,
    KEY_FREQUENCY_TYPE_LIST: FrequencyTypeList,
    KEY_FREQUENCY_TYPE: FrequencyType,
    KEY_PARKING_RATES_TYPE_LIST: ParkingRatesList,
    KEY_PARKING_RATES_TYPE: ParkingRates
}

# convierte un json a objeto
def json2object(d):
    obj = None
    for key in keys:
        if key in d.payload:
            r = d.payload[key]
            obj = keys[key](r)
            break
    return obj


# convierte un objeto a json
def object2json(obj):
    encode = jsonpickle.encode(obj, unpicklable=False)
    return utils.cleanWhiteSpaceFromJson(encode)
        

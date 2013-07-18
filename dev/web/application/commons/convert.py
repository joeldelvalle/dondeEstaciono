__author__ = 'gromero'

import json
from application.models.model import *
from easydict import EasyDict as edict
import jsonpickle, utils

KEY_ERROR = 'error'
KEY_USER = 'user'
KEY_VEHICLE_TYPE_LIST = 'vehicletypelist'
KEY_TIME_TYPE = 'timeType'
KEY_TIME_TYPE_LIST = 'timetypelist'
KEY_FREQUENCY_TYPE_LIST = 'frequencytypelist'

keys = {
    KEY_USER: User,
    KEY_ERROR: Error,
    KEY_VEHICLE_TYPE_LIST: VehicleTypeList,
    KEY_TIME_TYPE: TimeType,
    KEY_TIME_TYPE_LIST: TimeTypeList,
    KEY_FREQUENCY_TYPE_LIST: FrequencyTypeList
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
    return utils.strip(encode)
        

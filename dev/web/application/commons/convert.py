__author__ = 'gromero'

import json
from application.models.model import *
from easydict import EasyDict as edict
import jsonpickle, utils

KEY_ERROR = 'error'
KEY_USER = 'user'
KEY_VEHICLE_TYPE = 'vehicletypelist'

keys = {
    KEY_USER: User,
    KEY_ERROR: Error,
    KEY_VEHICLE_TYPE: VehicleTypeList
}

def json2object(d):
    obj = None
    for key in keys:
        if key in d.payload:
            r = d.payload[key]
            obj = keys[key](r)
            break
    return obj

def object2json(obj):
    encode = jsonpickle.encode(obj, unpicklable=False)
    return utils.strip(encode)
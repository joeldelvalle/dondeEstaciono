__author__ = 'gromero'

import json
from application.models.model import *
from easydict import EasyDict as edict
import jsonpickle, utils

KEY_ERROR = 'error'
KEY_USER = 'user'

keys = {
    KEY_USER: User,
    KEY_ERROR: Error
}

def json2object(d):
    obj = None
    for key in keys:
        if key in d.payload:
            r = d.payload[key]
            obj = keys[key](r)
            break
    if type(obj) is Error:
        raise Exception(obj.message)
    return obj

def object2json(obj):
    encode = jsonpickle.encode(obj, unpicklable=False)
    return utils.strip(encode)
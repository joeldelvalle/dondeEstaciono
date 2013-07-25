__author__ = 'gromero'

class Error(object):
    def __init__(self, data):
        self.code = data.code
        self.message = data.message


class User(object):
    def __init__(self, data):
        self.name = data.name
        self.email = data.email
        self.parking = Parking(data.parking)

    def is_authenticated(self):
        return True

    def is_active(self):
        return True

    def is_anonymous(self):
        return False

    def get_id(self):
        return 1


class Parking(object):
    def __init__(self, data):
        self.identificationCode = data.identificationCode


class Permission(object):
    pass


class VehicleType(object):
    def __init__(self, data):
        self.id = str(data.id).encode('base64')
        self.description = data.description

        
class VehicleTypeList(object):
    def __init__(self, data):
        self.vehicles = list()
        for d in data:
            v = VehicleType(d)
            self.vehicles.append(v)


class TimeType(object):
    def __init__(self, data):
        self.id = data.id
        self.description = data.description
        
        
class TimeTypeList(object):
    def __init__(self, data):
        self.timeTypeList = list()
        for d in data:
            v = TimeType(d)
            self.timeTypeList.append(v)


def createFrequencyTypeValue(idValue):
    from application.commons.databaseCache import getFrequencyTypeValue
    return getFrequencyTypeValue(idValue)

class FrequencyType(object):
    def __init__(self, data):
        self.id = str(data.id).encode('base64')
        self.description = data.description
        self.type = createFrequencyTypeValue(int(data.type))
        self.time = int(data.time)
        self.timeType = data.timeType
        self.priority=int(data.priority)
        self.combinablePreviousFrequency = bool(data.combinablePreviousFrequency)


class FrequencyTypeList(object):
    def __init__(self, data):
        self.frequencies = list()
        for d in data:
            v = FrequencyType(d)
            self.frequencies.append(v)
            

# los unicos valores posibles son frecuencia/fijo           
class FrequencyTypeValue(object):
    def __init__(self, id, description):
        self.id = id
        self.description = description                   
__author__ = 'gromero'

# objeto request para vehicleType
class VehicleTypeRequest(object):
    def __init__(self, parkingIdentificationCode, description=None, id=None):
        self.parkingIdentificationCode = parkingIdentificationCode
        if (description != None):
            self.description = description
        if (id != None):
            self.id = long(str(id).decode('base64'))
            
            
            
            
# objeto request para frequencyType            
class FrequencyTypeRequest(object):
    def __init__(self, parkingIdentificationCode, id=None, description=None, type=None, time=None, idTimeType=None, priority=None, combinablePreviousFrequency=None):
        
        self.parkingIdentificationCode = parkingIdentificationCode
       
        if (id != None):
            self.id = long(str(id).decode('base64'))
        
        if (description != None):
            self.description = description
        
        if (type != None):
            self.type = int(type)
       
        if (time != None):
            self.time = int(time)
            
            
        if (idTimeType != None):
            self.idTimeType = int(idTimeType)
       
        if (priority != None):
            self.priority=int(priority)
            
        if (combinablePreviousFrequency != None):
            self.combinablePreviousFrequency = bool(combinablePreviousFrequency)            
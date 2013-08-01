__author__ = 'gromero'

# objeto request para vehicleType
class VehicleTypeRequest(object):
    def __init__(self, parkingIdentificationCode, description=None, idFrequencyType=None):
        self.parkingIdentificationCode = parkingIdentificationCode
        if (description != None):
            self.description = description
        if (idFrequencyType != None):
            self.id = long(str(idFrequencyType).decode('base64'))
            
            
            
            
# objeto request para frequencyType            
class FrequencyTypeRequest(object):
    def __init__(self, parkingIdentificationCode, idFrequencyType=None, description=None, typeFrequency=None, time=None, idTimeType=None, priority=None, combinablePreviousFrequency=None):
        
        self.parkingIdentificationCode = parkingIdentificationCode
       
        if (idFrequencyType != None):
            self.id = long(str(idFrequencyType).decode('base64'))
        
        if (description != None):
            self.description = description
        
        if (typeFrequency != None):
            self.type = int(typeFrequency)
       
        if (time != None):
            self.time = int(time)
            
            
        if (idTimeType != None):
            self.idTimeType = int(idTimeType)
       
        if (priority != None):
            self.priority=int(priority)
            
        if (combinablePreviousFrequency != None):
            self.combinablePreviousFrequency = bool(combinablePreviousFrequency)
            
            

# objeto request para parkingRates
class ParkingRatesRequest(object):
    def __init__(self, parkingIdentificationCode, idParkingRate=None, vehicleType=None, frequencyType=None, amount=None):
        
        self.parkingIdentificationCode = parkingIdentificationCode
        
        if (idParkingRate != None):
            self.id = long(str(idParkingRate).decode('base64'))                        

        if (vehicleType != None):
            self.vehicleType = vehicleType
            
        if (frequencyType != None):
            self.frequencyType = frequencyType    
        
        if (amount != None):
            self.amount = float(amount.real)
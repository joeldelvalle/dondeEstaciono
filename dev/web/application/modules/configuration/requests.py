__author__ = 'gromero'

class VehicleTypeRequest(object):
    def __init__(self, parkingIdentificationCode, description=None, id=None):
        self.parkingIdentificationCode = parkingIdentificationCode
        if (description != None):
            self.description = description
        if (id != None):
            self.id = long(id)
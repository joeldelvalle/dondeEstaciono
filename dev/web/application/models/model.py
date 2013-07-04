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
        self.id = data.id
        self.description = data.description
        
class VehicleTypeList(object):
    def __init__(self, data):
        self.vehicles = list()
        for d in data:
            v = VehicleType(d)
            self.vehicles.append(v)
        
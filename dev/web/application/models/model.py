__author__ = 'gromero'

class Error(object):
    def __init__(self, data):
        self.code = data.code
        self.message = data.message

class User(object):
    def __init__(self, data):
        self.name = data.name
        self.identificationCode = data.identificationCode
        self.email = data.email
        self.state = data.state

    def is_authenticated(self):
        return True

    def is_active(self):
        return True

    def is_anonymous(self):
        return False

    def get_id(self):
        return 1

class Parking(object):
    pass

class Permission(object):
    pass
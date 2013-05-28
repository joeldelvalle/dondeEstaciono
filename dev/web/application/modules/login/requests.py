__author__ = 'gromero'

class LoginRequest(object):
    def __init__(self, user, password):
        self.user = user
        self.password = password
__author__ = 'gromero'

class Request(object):
    def __init__(self, payload, mac, userHash):
        self.payload = payload
        self.mac = mac
        self.userHash = userHash

class Payload(object):
    def __init__(self, obj):
        setattr(self, obj.__class__.__name__.lower(), obj)
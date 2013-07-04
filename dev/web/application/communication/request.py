__author__ = 'gromero'

from application import p
import urllib2
from application.security import encrypt

class Request(object):
    def __init__(self, payload, mac, userHash):
        self.payload = payload
        self.mac = mac
        self.userHash = userHash

class Payload(object):
    def __init__(self, obj):
        setattr(self, obj.__class__.__name__.lower(), obj)
        
def sendRequest(url, request):
    req = urllib2.Request(p['server'] + p['applicationName'] + url, request, headers={"Content-Type": "application/json"})
    resp = urllib2.urlopen(req).read()
    return unicode(encrypt.dencrypted(resp), errors="ignore")
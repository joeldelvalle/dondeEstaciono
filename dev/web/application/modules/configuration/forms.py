from application import db
from application.models.model import *
from flask.ext import wtf

# clase que representa el formulario en el abm de vehicleType
class VehicleTypeForm(wtf.Form):
    description = wtf.TextField(validators=[wtf.required()])

    def __init__(self, *args, **kwargs):
        kwargs['csrf_enabled'] = False
        super(VehicleTypeForm, self).__init__(*args, **kwargs)
        
        

# clase que representa al formulario en el abm de frequencyType
class FrequencyTypeForm(wtf.Form):
    
    description = wtf.TextField(validators=[wtf.required()])

    def __init__(self, *args, **kwargs):
        kwargs['csrf_enabled'] = False
        super(FrequencyTypeForm, self).__init__(*args, **kwargs)        
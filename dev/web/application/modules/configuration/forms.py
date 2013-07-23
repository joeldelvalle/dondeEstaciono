from application import db
from flask.ext import wtf
from application.commons import databaseCache
import logging
#from flask.ext.wtf import Form, TextField, BooleanField, FileField, file_required,         RadioField
#from flask.ext.wtf import Required

# clase que representa el formulario en el abm de vehicleType
class VehicleTypeForm(wtf.Form):
    description = wtf.TextField(validators=[wtf.required()])

    def __init__(self, *args, **kwargs):
        kwargs['csrf_enabled'] = False
        super(VehicleTypeForm, self).__init__(*args, **kwargs)
        
        

# clase que representa al formulario en el abm de frequencyType
class FrequencyTypeForm(wtf.Form):
    
    description = wtf.TextField(validators=[wtf.required()])
    
    timeType = wtf.SelectField(u'TimeType', default=999 )

    time = wtf.IntegerField(validators=[wtf.required()])

    type = wtf.HiddenField(wtf.required())
        
    priority = wtf.IntegerField(validators=[wtf.required()])
       
    combinablePreviousFrequency = wtf.HiddenField(wtf.required())
    

    def loadTimeTypeValues(self):
        self.timeType.choices = [(999, "Seleccione")]

        for timeTypeValue in databaseCache.getTimeTypeList():
            self.timeType.choices.append((timeTypeValue.id, timeTypeValue.description))
        
        
    def __init__(self, *args, **kwargs):
        kwargs['csrf_enabled'] = False
        super(FrequencyTypeForm, self).__init__(*args, **kwargs)
        self.loadTimeTypeValues()

    
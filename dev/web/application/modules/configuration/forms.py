from flask.ext import wtf
from application.commons import databaseCache
from flask_login import current_user
from application.modules.configuration import services


# clase que representa el formulario en el abm de vehicleType
class VehicleTypeForm(wtf.Form):
    description = wtf.TextField(validators=[wtf.required()])

    def __init__(self, *args, **kwargs):
        kwargs['csrf_enabled'] = False
        super(VehicleTypeForm, self).__init__(*args, **kwargs)
        
        

# clase que representa al formulario en el abm de frequencyType
class FrequencyTypeForm(wtf.Form):
    
    description = wtf.TextField(validators=[wtf.required()])
    
    timeType = wtf.SelectField(validators=[wtf.NoneOf(['999'], message=u'Seleccione un Tipo de Frecuencia', values_formatter=None) ], default='999' )

    time = wtf.IntegerField(validators=[wtf.required()])

    type = wtf.HiddenField(validators=[wtf.required()])
        
    priority = wtf.IntegerField(validators=[wtf.required()])
       
    combinablePreviousFrequency = wtf.HiddenField(validators=[wtf.required()])
    

    def loadTimeTypeValues(self):
        self.timeType.choices = [("999", "Seleccione")]

        for timeTypeValue in databaseCache.getTimeTypeList():
            self.timeType.choices.append(( str(timeTypeValue.id), timeTypeValue.description))
        
        
    def __init__(self, *args, **kwargs):
        kwargs['csrf_enabled'] = False
        super(FrequencyTypeForm, self).__init__(*args, **kwargs)
        self.loadTimeTypeValues()





class ParkingRatesForm(wtf.Form):
    
    vehicle = wtf.SelectField(validators=[wtf.required(), wtf.NoneOf(['999'], message=u'Seleccione un Vehiculo', values_formatter=None)], default='999')
    
    frequency = wtf.SelectField(validators=[wtf.required(), wtf.NoneOf(['999'], message=u'Seleccione un Vehiculo', values_formatter=None)], default='999')
    
    amount = wtf.DecimalField(validators=[wtf.required()])
    
    
    def loadVehicleTypeValues(self):
        self.vehicle.choices = [("999", "Seleccione")]

        for vehicleTypeValue in services.getAllVehicleType(current_user.parking.identificationCode).vehicles:
            self.vehicle.choices.append(( str(vehicleTypeValue.id).decode('base64'), vehicleTypeValue.description))
            
    
    def loadFrequencyTypeValues(self):
        self.frequency.choices = [("999", "Seleccione")]

        for frequencyTypeValue in services.getAllFrequencyType(current_user.parking.identificationCode).frequencies:
            self.frequency.choices.append(( str(frequencyTypeValue.id).decode('base64'), frequencyTypeValue.description))        
            
    
    def __init__(self, *args, **kwargs):
        kwargs['csrf_enabled'] = False
        super(ParkingRatesForm, self).__init__(*args, **kwargs)
        self.loadVehicleTypeValues()
        self.loadFrequencyTypeValues()
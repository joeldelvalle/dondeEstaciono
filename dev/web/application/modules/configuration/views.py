__author__ = 'gromero'

from application.commons.utils import cleanFields
from flask_login import current_user, login_required
from application.modules.configuration.forms import VehicleTypeForm, FrequencyTypeForm
from flask import url_for, redirect, request
from flask.templating import render_template
from application.modules.configuration import configuration_blueprint, services
from application.commons import databaseCache

action = None
response = None

@configuration_blueprint.route('/app/conf/vehicle', methods=['POST', 'GET'])
@login_required
def getAllVehicle():
    global action
    global response
    
    form = VehicleTypeForm(request.form)
    if form.validate_on_submit():
        action = 'save'
        response = services.saveVehicleType(current_user.parking.identificationCode, form.description.data)
        cleanFields(response, form)
    vehicleTypeList = services.getAllVehicleType(current_user.parking.identificationCode)
    rt = render_template('abm-vehicle.html', form=form, vehicleTypeList=vehicleTypeList, action=action, response=response)
    
    action = None
    response = None
    
    return rt

@configuration_blueprint.route('/app/conf/vehicle/update/<id>/<description>', methods=['POST', 'GET'])
@login_required
def updateVehicleType(id, description):
    global action
    global response
    
    form = VehicleTypeForm(request.form, description=description)
    if form.validate_on_submit():
        action = 'update'
        response = services.updateVehicleType(current_user.parking.identificationCode, id, form.description.data)
        return redirect(url_for('.getAllVehicle'))
    
    vehicleTypeList = services.getAllVehicleType(current_user.parking.identificationCode)
    rt = render_template('abm-vehicle.html', form=form, vehicleTypeList=vehicleTypeList, action=action, response=response)
    
    return rt

@configuration_blueprint.route('/app/conf/vehicle/remove/<id>', methods=['POST', 'GET'])
@login_required
def removeVehicleType(id):
    global action 
    global response
    
    action = 'remove'
    response = services.removeVehicleType(current_user.parking.identificationCode, id)
    
    return redirect(url_for('.getAllVehicle'))




'''
    FREQUENCY TYPE    METHODS  -  START
'''

# metodo que obtiene todas las frecuancias cargadas por un estacionamiento
@configuration_blueprint.route('/app/conf/frequency', methods=['POST', 'GET'])
@login_required
def getAllFrequency():
    global action
    global response
    
    form = FrequencyTypeForm(request.form)
    if form.validate_on_submit():
        action = 'save'
        response = services.saveFrequencyType(current_user.parking.identificationCode, form.description.data, form.type.data, form.time.data, int(form.timeType.data), form.priority.data, form.combinablePreviousFrequency.data)
        cleanFields(response, form)

    frequencyTypeList = services.getAllFrequencyType(current_user.parking.identificationCode)
    
    rt = render_template('abm-frequency.html', form=form, frequencyTypeList=frequencyTypeList, action=action, response=response)
    
    action = None
    response = None
    
    return rt    


# metodo para elimiar un frequencyType
@configuration_blueprint.route('/app/conf/frequency/remove/<id>', methods=['POST', 'GET'])
@login_required
def removeFrequencyType(id):
    global action 
    global response
    
    action = 'remove'
    response = services.removeFrequencyType(current_user.parking.identificationCode, id)
    
    return redirect(url_for('.getAllFrequency'))



# metodo para actualizar un frequencyType
@configuration_blueprint.route('/app/conf/frequency/update/<id>', methods=['POST', 'GET'])
@login_required
def updateFrequencyType(id):
    global action
    global response

    frequencyTypeSelected = services.findFrequencyTypeById(current_user.parking.identificationCode, id)

    form = FrequencyTypeForm(request.form, description=frequencyTypeSelected.description, timeType=frequencyTypeSelected.timeType.id, time=frequencyTypeSelected.time, type=frequencyTypeSelected.type.id, priority=frequencyTypeSelected.priority, combinablePreviousFrequency=frequencyTypeSelected.combinablePreviousFrequency)
    if form.validate_on_submit():
        action = 'update'
        response = services.updateFrequencyType(current_user.parking.identificationCode, id, form.description.data, form.timeType.data, form.time.data, form.type.data, form.priority.data, form.combinablePreviousFrequency.data)
        return redirect(url_for('.getAllFrequency'))

    frequencyTypeList = services.getAllFrequencyType(current_user.parking.identificationCode)
    
    rt = render_template('abm-frequency.html', form=form, frequencyTypeList=frequencyTypeList, action=action, response=response)
    
    action = None
    response = None
    
    return rt


'''
    FREQUENCY TYPE    METHODS  -  END
'''    

from application.models.model import Error, VehicleType
from application.commons.utils import cleanFields
__author__ = 'gromero'

from application.modules.configuration.forms import VehicleTypeForm
from flask import url_for, redirect, render_template, request
from flask.templating import render_template

from application.modules.configuration import configuration_blueprint, services

import logging

action = None
response = None

@configuration_blueprint.route('/app/conf/vehicle', methods=['POST', 'GET'])
def getAllVehicle():
    global action
    global response
    
    
    form = VehicleTypeForm(request.form)
    if form.validate_on_submit():
        action = 'save'
        response = services.saveVehicleType("OTT", form.description.data)
        cleanFields(response, form)
    vehicleTypeList = services.getAllVehicleType('OTT')
    rt = render_template('abm-vehicle.html', form=form, vehicleTypeList=vehicleTypeList, action=action, response=response)
    
    action = None
    response = None
    
    return rt

@configuration_blueprint.route('/app/conf/vehicle/update/<id>/<description>', methods=['POST', 'GET'])
def updateVehicleType(id, description):
    global action
    global response
    
    form = VehicleTypeForm(request.form, description=description)
    if form.validate_on_submit():
        action = 'update'
        response = services.updateVehicleType("OTT", id, form.description.data)
        return redirect(url_for('.getAllVehicle'))
    
    vehicleTypeList = services.getAllVehicleType('OTT')
    rt = render_template('abm-vehicle.html', form=form, vehicleTypeList=vehicleTypeList, action=action, response=response)
    
    return rt
    
@configuration_blueprint.route('/app/conf/vehicle/remove/<id>', methods=['POST', 'GET'])
def removeVehicleType(id):
    global action 
    global response
    
    action = 'remove'
    response = services.removeVehicleType("OTT", id)
    
    return redirect(url_for('.getAllVehicle'))
    
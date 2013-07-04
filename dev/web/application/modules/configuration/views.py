from application.models.model import Error
__author__ = 'gromero'

from application.modules.configuration.forms import VehicleTypeForm
from flask import url_for, redirect, render_template, request
from flask.templating import render_template

from application.modules.configuration import configuration_blueprint, services

import logging

action = None
response = None

@configuration_blueprint.route('/app/conf/vehicle', methods=['POST', 'GET'])
def abm_vehicle():
    global action
    global response
    
    form = VehicleTypeForm(request.form)
    if form.validate_on_submit():
        action = 'save'
        response = services.saveVehicleType("OTT", form.description.data)
    vehicleTypeList = services.getAllVehicleType('OTT')
    rt = render_template('abm-vehicle.html', form=form, vehicleTypeList=vehicleTypeList, action=action, response=response, Error=Error)
    
    action = None
    response = None
    
    return rt
    
@configuration_blueprint.route('/app/conf/vehicle/remove/<id>', methods=['POST', 'GET'])
def removeVehicleType(id):
    global action 
    global response
    
    action = 'remove'
    response = services.removeVehicleType("OTT", id)
    
    return redirect(url_for('.abm_vehicle'))
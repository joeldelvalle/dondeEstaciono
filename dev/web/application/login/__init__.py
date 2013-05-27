from application import app
from flask import Blueprint
from flask.ext.login import LoginManager

login_blueprint = Blueprint("login_blueprint", __name__, template_folder="templates")

login_manager = LoginManager()
login_manager.setup_app(app)

import models
import forms
import views


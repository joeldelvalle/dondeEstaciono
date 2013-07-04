from application import app
from flask import Blueprint

configuration_blueprint = Blueprint("configuration_blueprint", __name__, template_folder="templates")

import views
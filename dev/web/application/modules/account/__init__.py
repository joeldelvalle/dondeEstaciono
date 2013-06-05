from application import app
from flask import Blueprint

account_blueprint = Blueprint("account_blueprint", __name__, template_folder="templates")

import views
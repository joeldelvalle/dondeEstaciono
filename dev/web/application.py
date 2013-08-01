__author__ = 'gromero'

from application.modules.login import login_blueprint
from application.modules.account import account_blueprint
from application.modules.configuration import configuration_blueprint

from application import app, db, babel, p
from flask import url_for, redirect, render_template, request
from flask.ext.login import login_required
from flaskext.babel import refresh
import logging, urllib

app.register_blueprint(login_blueprint)
app.register_blueprint(account_blueprint)
app.register_blueprint(configuration_blueprint)

@app.route("/")
def index():
    return render_template('index.html')

@app.route("/app/<languaje>", endpoint='languaje_app')
@app.route("/app")
@login_required
def indexapp(languaje=None):
    if languaje:
        set_languaje(languaje)
    if request.args.get('next'):
        return redirect(urllib.unquote(request.args.get('next')))
    return render_template('index-app.html')

def set_languaje(languaje):
    app.config['BABEL_DEFAULT_LOCALE'] = languaje
    refresh()

if __name__ == '__main__':        
    db.create_all()
    app.run('127.0.0.1', 8000)
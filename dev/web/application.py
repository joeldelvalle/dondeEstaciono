__author__ = 'gromero'

from application.modules.login import login_blueprint
from application.modules.account import account_blueprint

from application import app, db
from flask import url_for, redirect, render_template, request
from flask.ext.login import login_required

app.register_blueprint(login_blueprint)
app.register_blueprint(account_blueprint)

@app.route("/")
def index():
    return render_template('index.html')

@app.route("/app")
@login_required
def indexapp():
    return render_template('index-app.html')
	
if __name__ == '__main__':        
    db.create_all()
    app.debug=True
    app.run('127.0.0.1', 8000)
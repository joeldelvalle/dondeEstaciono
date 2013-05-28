from application import app, db
from flask import url_for, redirect, render_template, request
from application.modules.login import login_blueprint

app.register_blueprint(login_blueprint)

@app.route("/")
def index():
    return render_template('index.html')
	
if __name__ == '__main__':
    db.create_all()
    app.debug=True
    app.run('127.0.0.1', 8000)
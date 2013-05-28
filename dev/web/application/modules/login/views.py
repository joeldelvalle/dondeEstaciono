from application import app, db
from forms import LoginForm
from flask import url_for, redirect, render_template, request
from application.modules.login import login_blueprint, login_manager
from flask.ext.login import login_user, logout_user, current_user
from application.models.model import User
import services
from flask.ext import wtf
from application.commons.exceptions import LoginException
from urllib2 import URLError

login_manager.login_view = "login_blueprint.login"

user = None

@login_manager.user_loader
def load_user(id):
    return user

@login_blueprint.route('/login', methods=['POST', 'GET'])
def login():
    form = LoginForm(request.form)
    if form.validate_on_submit():
        global user
        try:
            user = services.login(form.user.data, form.password.data)
        except LoginException as exc:
            return render_template('login.html', form=form, exception=exc)
        except URLError as error:
            return render_template('login.html', form=form, exception=Exception(error.reason.strerror))
        login_user(user)
        return redirect(url_for('index'))
    return render_template('login.html', form=form)


@login_blueprint.route('/logout/')
def logout():
    logout_user()
    return redirect(url_for('index'))
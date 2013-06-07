__author__ = 'gromero'

from flask.templating import render_template

from application.modules.account import account_blueprint

@account_blueprint.route('/app/account')
def account():
    return render_template('account.html')
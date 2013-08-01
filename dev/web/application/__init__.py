from flask import Flask
from flask.ext.sqlalchemy import SQLAlchemy
import logging
from logging.handlers import TimedRotatingFileHandler
from pyjavaproperties import Properties
from flaskext.babel import Babel

app = Flask(__name__)

db = SQLAlchemy(app)

app.config.update(
	SECRET_KEY = 'A0Zr98j/3yX R~XHH!jmN]LWX/,?RT',
	SQLALCHEMY_DATABASE_URI = 'sqlite:///db/app.sqlite',
	SQLALCHEMY_ECHO = True
)

babel = Babel(app)

#Open properties file
p = Properties()
p.load(open('properties/application.properties'))

#Logging
logging.basicConfig(level=logging.DEBUG)

#File
file = TimedRotatingFileHandler("log/dondeEstaciono.log", when="midnight", backupCount=10)
file.setLevel(logging.DEBUG)

formatter = logging.Formatter('%(asctime)s,%(msecs)d %(name)s %(levelname)s %(message)s')

file.setFormatter(formatter)

logging.getLogger('').addHandler(file)

#Console
console = logging.StreamHandler()
console.setLevel(logging.DEBUG)

console.setFormatter(formatter)

logging.getLogger('').addHandler(console)

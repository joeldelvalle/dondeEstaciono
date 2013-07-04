__author__ = 'gromero'

def strip(value):
    return value.replace(" ", "")

def cleanFields(response, form):
    if response == 'success':
        for field in form:
            field.data = None
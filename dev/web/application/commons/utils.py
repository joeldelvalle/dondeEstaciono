__author__ = 'gromero'

def strip(value):
    return value.replace(" ", "")

def cleanFields(response, form):
    if response == 'success':
        for field in form:
            field.data = None


# borra los espacios en blanco de un objeto json
def cleanWhiteSpaceFromJson(json):
    newJson = json.replace('": ', '":')
    newJson = newJson.replace(', "', ',"')
    return newJson
'''
Created on 17/07/2013

@author: joel.delvalle
'''

from application.modules.app.service import getAllTimeType
from application.models.model import FrequencyTypeValue

# map cache de timeType
timeTypeMapCache = {}
# list cache de timeType
timeTypeListCache = list()

# map tipos de frecuencia list - frecuencia/fijo
frequencyTypeValuesMapCache = {}
# list tipos de frecuencia list - frecuencia/fijo
frequencyTypeValuesListCache = list()



# carga los TimeType
def loadTimeTypeInformation():
    listTimeType = getAllTimeType()

    for timeType in listTimeType.timeTypeList:
        timeTypeMapCache[timeType.id] = timeType
        timeTypeListCache.append(timeType)


# carga los FrequencyTypeValues
def loadtFrequencyTypeValuesInformation():    
    value1 = FrequencyTypeValue(1, 'Frecuencia')
    value2 = FrequencyTypeValue(2, 'Fijo')
    frequencyTypeValuesListCache.append(value1)
    frequencyTypeValuesListCache.append(value2)
    
    frequencyTypeValuesMapCache[value1.id] = value1
    frequencyTypeValuesMapCache[value2.id] = value2



def getTimeTypeList():    
    if (timeTypeListCache.__len__() == 0):
        loadTimeTypeInformation()
    
    return timeTypeListCache



def getTimeType(idTimeType):
    if (timeTypeMapCache.__len__() == 0):
        loadTimeTypeInformation()
    
    return timeTypeMapCache[idTimeType]



def getFrequencyTypeValuesList():
    if (frequencyTypeValuesListCache.__len__() == 0):
        loadtFrequencyTypeValuesInformation()
    
    return frequencyTypeValuesListCache



def getFrequencyTypeValue(idFrequencyType):
    if (frequencyTypeValuesMapCache.__len__() == 0):
        loadtFrequencyTypeValuesInformation()
    
    return frequencyTypeValuesMapCache[idFrequencyType]


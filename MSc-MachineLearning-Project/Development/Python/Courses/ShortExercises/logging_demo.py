import logging
from _stat import filemode

logging.basicConfig(filename='mylogfile.log', filemode='w')

logger = logging.getLogger()
logger.setLevel(logging.ERROR)

logger.info('Info level log message')
logger.debug('Debug level message')
logging.error('Error level log message')
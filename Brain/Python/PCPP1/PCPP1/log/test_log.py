import logging
import os

# Ensure the file is created in the current folder
os.chdir(os.path.dirname(__file__))

# default format is '%(levelname)s:%(name)s:%(message)s'
FORMAT = '%(user)s %(asctime)s %(module)s %(name)s %(levelname)s %(message)s'

# default filemode is 'a' (append) and default level is WARNING
logging.basicConfig(filename='pcpp1.log', filemode='w', level=logging.DEBUG, format=FORMAT)
# logging.basicConfig()
logger = logging.getLogger('my_logger')

logger.critical('This is a critical message', extra={'user': 'admin'})
logger.error('This is an error message', extra={'user': 'thaidh'})
logging.log(logging.WARNING, "This is a warning message", extra={'user': 'anhmth'})
logger.warning('This is a warning message', extra={'user': 'anhmth'})
logger.info('This is an info message', extra={'user': 'admin'})
logger.debug('This is a debug message', extra={'user': 'admin'})


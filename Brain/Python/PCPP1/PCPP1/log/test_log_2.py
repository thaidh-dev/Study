import logging
import os

# Ensure the file is created in the current folder
os.chdir(os.path.dirname(__file__))

# default format is '%(message)s'
FORMAT = '%(asctime)s %(module)s %(name)s %(levelname)s %(message)s'

# default filemode is 'a' (append) and default level is WARNING
handler = logging.FileHandler('pcpp1.log', mode='w', encoding='utf-8')
# handler.setLevel(logging.DEBUG)
handler.setFormatter(logging.Formatter(FORMAT))
# handler.addFilter(logging.Filter('other_logger'))
handler.addFilter(logging.Filter('my_logger'))

logger = logging.getLogger('my_logger')
logger.addHandler(handler)
logger.critical('This is a critical message')
logger.error('This is an error message')
logger.warning('This is a warning message')
logger.info('This is an info message')
logger.debug('This is a debug message')

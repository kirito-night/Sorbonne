import inspect
import logging
import os

ENABLE = False

class TestDebug:
    logging.basicConfig(format = '%(levelname)s: %(message)s', level = logging.DEBUG)
    logger = logging.getLogger('tests')
    previous = ""

    def log(self, *argv):
        if not ENABLE:
            return

        caller = inspect.stack()[1]
        caller = os.path.basename(caller.filename) + " / " + caller.function
        if self.previous != caller:
            self.logger.info(caller)
            self.previous = caller

        for i in argv:
            self.logger.debug(i)
    

'''
Created on 7 nov. 2013

@author: Alexandre Bonhomme
'''
from fr.redteam.zarascraper.core.downloader import Downloader
from fr.redteam.zarascraper.core.product import Product
from fr.redteam.zarascraper.scrapers.scraper import Scraper
from fr.redteam.zarascraper.scrapers.zara.zara_browser import ZaraBrowser
import errno
import logging as log
import os
import time

class ZaraScrape(Scraper):

    BRAND_NAME = 'Zara'
    PAGE_BASE = 'http://www.zara.com/fr/'

    def __init__(self, lang):
        self.lang = lang
        self.downloader = Downloader()

    def setConfig(self, section, subsection, productType, bodyPart):
        self.section = section
        self.subsection = subsection
        self.type = productType
        self.bodies = bodyPart

    '''
        Perfom the scraping on Zara website
    '''
    def run(self, usePlainImage = True, download = False):
        if download:
            self.dl_folder = self.DL_FOLDER_PATH_BASE + self.lang + '/' + self.section + '/' + self.subsection + '/'
            # Create folder if is not existing
            try:
                os.makedirs(self.dl_folder)
            except OSError as exception:
                if exception.errno != errno.EEXIST:
                    raise

        log.info('-- Starting scraping --')

        home = self.downloader.getFile(self.PAGE_BASE + self.lang + '/')
        browser = ZaraBrowser(home)

        # Goto first menu level
        url = browser.getMenuLinkFromName(self.section)
        try:
            browser.goTo(url, 5)
        except:
            log.warning("Unable to get the page '" + url + "'. Omitting.")
            return []

        # Goto second menu level
        url = browser.getMenuLinkFromName(self.subsection)
        try:
            browser.goTo(url, 5)
        except:
            log.warning("Unable to get the page '" + url + "'. Omitting.")
            return []

        # Start items parsing
        i = 0
        itemList = []
        for item in browser.getProductsList():
            log.debug('zzZZZZzzz')
            time.sleep(3) # let's do it cool

            # Goto the product page
            try:
                browser.goTo(item['url'])
            except:
                log.warning("FAIL : Unable to download '" + item['name'] + "'. Omitting.")
                continue

            imgUrl = browser.getProductImageLink(usePlainImage)
            if imgUrl is None:
                log.info('FAIL : Unable to get product image for "' + item['name'] + '". Omitting.')
                continue

            color = browser.getProductColor()

            imgFilename = str(i) + '-' + item['name'].replace(' ', '_')
            imgPath = self.BRAND_NAME + '/' + \
                self.lang + '/' + \
                self.section + '/' + \
                self.subsection + '/' + \
                imgFilename + '.jpg'

            # build a product object
            product = Product(item['name'], self.BRAND_NAME, color, imgUrl, imgPath, self.type, self.bodies)
            itemList.append(product)

            log.info(product.toString())

            # Downloading file if flag is True
            if download:
                log.info('Downloading ' + imgFilename + '...')
                self.downloader.writeFile(imgUrl, self.dl_folder + imgFilename)

            # count the number of object
            i += 1

        log.info('-- Ending scraping --')
        log.info('-- ' + str(i) + ' images was scraped --')

        return itemList

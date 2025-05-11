import xml.etree.ElementTree as ET
import os

# Ensure the file is created in the current folder
os.chdir(os.path.dirname(__file__))

library = ET.Element('library')

book_1 = ET.SubElement(library, 'book', {'genre': 'fiction'})
ET.SubElement(book_1, 'title').text = 'The Great Gatsby'
ET.SubElement(book_1, 'author').text = 'F. Scott Fitzgerald'
book_2 = ET.SubElement(library, 'book', {'genre': 'dystopian', 'published': '1960'})
ET.SubElement(book_2, 'title').text = 'To Kill a Mockingbird'
ET.SubElement(book_2, 'author').text = 'Harper Lee'
book_3 = ET.SubElement(library, 'book', {'genre': 'dystopian'})
ET.SubElement(book_3, 'title').text = '1984'
ET.SubElement(book_3, 'author').text = 'George Orwell'

ET.ElementTree(library).write('library.xml', encoding='utf-8', xml_declaration=True)

tree = ET.parse('library.xml')
root = tree.getroot()

book_2_find = root.find('book')
print(book_2_find.get('genre'))
print(book_2_find[0].text)
# books_2_find = root.findall('book')
books_2_find = root.findall('bookkk')
print(books_2_find) # returns [] if no match is found

# print(root.tag)
# print(root.attrib)


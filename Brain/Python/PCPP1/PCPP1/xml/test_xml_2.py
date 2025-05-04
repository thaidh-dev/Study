import xml.etree.ElementTree as ET
import os

# Ensure the file is created in the current folder
os.chdir(os.path.dirname(__file__))

xml_data = '''
<library>
    <book genre="fiction">
        <title>The Great Gatsby</title>
        <author>F. Scott Fitzgerald</author>
    </book>
    <book genre="dystopian" published="1960">
        <title>To Kill a Mockingbird</title>
        <author>Harper Lee</author>
    </book>
    <book genre="dystopian">
        <title>1984</title>
        <author>George Orwell</author>
    </book>
</library>
'''
xml_data = '''<library><book genre="fiction"><title>The Great Gatsby</title><author>F. Scott Fitzgerald</author></book><book genre="dystopian" published="1960"><title>To Kill a Mockingbird</title><author>Harper Lee</author></book><book genre="dystopian"><title>1984</title><author>George Orwell</author></book></library>'''

# Parse the XML data
root = ET.fromstring(xml_data)

# Use the indent method to pretty-print the XML
ET.indent(root, space=" ", level=0)

# Write the formatted XML to a file
with open("formatted_library.xml", "w", encoding="utf-8") as file:
    tree = ET.ElementTree(root)
    # Use the indent method to pretty-print the XML
    ET.indent(tree.find('book'), space="       ", level=0)
    tree.write(file, encoding="unicode", xml_declaration=True)
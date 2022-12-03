import os
import zipfile
# assign directory
directory = 'helper'
 
# iterate over files in
# that directory
for filename in os.listdir(directory):
    f = os.path.join(directory, filename)
    # checking if it is a file
    if zipfile.is_zipfile(f):
        id = f.split(".")[0]
        print(id)
        with zipfile.ZipFile(f, 'r') as zip_ref:
            zip_ref.extractall("./")
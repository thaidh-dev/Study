python -m venv venv
venv\Scripts\activate
pip install -r requirements.txt

để debug thì cần search 'python' ở phần extension và cài cái Python ngay đầu tiên
chọn file muốn debug => ctrl + shift + p => python: select interpreter

spark-submit --driver-memory 12g --jars \path\to\postgresql-42.3.1.jar,path\to\mysql-connector-java-8.0.25.jar test.py

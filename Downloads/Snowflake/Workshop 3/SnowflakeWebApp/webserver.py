from flask import Flask, render_template, request
import snowflakeConnection as sfc
import pandas as pd

app = Flask("my website")
rowsJson = None

@app.route('/')
def homepage():
    # return 'Welcome to my website! My snowflake acct # is: ' + str(onerow)
    cur.execute("select top 7 * from LANGDESC order by factor_code desc")
    rows = pd.DataFrame(cur.fetchall(), columns=['ID', 'FACTOR_CODE', 'DESCRIPTION'])
    # html = rows.to_html()
    rows.to_csv('ThaiDH.csv', index=False)
    rows.to_json('ThaiDH.json', orient='records')
    global rowsJson
    rowsJson = rows.to_json(orient='records')
    html = rows.to_html(index=False)
    return render_template('index.html', html=html)


@app.route('/submit')
def submitPage():
    return render_template('submit.html')


@app.route('/chart')
def chart():
    return render_template('chart.html', rows=rowsJson)


@app.route('/thanks4submit', methods=["POST"])
def thanks4submit():
    fc = request.form.get("fc")
    des = request.form.get("des")
    sql = "insert into LANGDESC values ('" + fc + "', '" + des + "')"
    print(sql)
    cur.execute(sql)
    return render_template("thanks4submit.html", fc=fc, des=des)


cnx = sfc.sfconnect()
cur = cnx.cursor()
cur.execute("Select current_account()")
onerow = cur.fetchone()
print(onerow)

app.run()

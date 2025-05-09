import tkinter as tk

w = tk.Tk()
canvas = tk.Canvas(w, width=1000, height=1000)

canvas.create_line(10, 10, 200, 10, 10, 300, 200, 300, fill='blue', smooth=True, width=30, arrow=tk.BOTH)
canvas.pack()


canvas.create_arc(500, 50, 700, 250, start=0, fill='yellow', outline='black')
canvas.create_arc(500, 50, 700, 250, start=90, style=tk.CHORD, fill='yellow', outline='black')
canvas.create_arc(500, 50, 700, 250, start=180, style=tk.ARC, outline='black')
w.mainloop()
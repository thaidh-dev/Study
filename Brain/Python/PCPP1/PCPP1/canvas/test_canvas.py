import tkinter as tk

w = tk.Tk()
canvas = tk.Canvas(w, width=400, height=400)

canvas.create_line(10, 10, 200, 10, 10, 300, 200, 300, fill='blue', smooth=True, width=30, arrow=tk.BOTH)
canvas.pack()


w.mainloop()
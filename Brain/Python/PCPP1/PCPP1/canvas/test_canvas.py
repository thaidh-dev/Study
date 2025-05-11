import tkinter as tk
import os


# Ensure the file is created in the current folder
os.chdir(os.path.dirname(__file__))

w = tk.Tk()
canvas = tk.Canvas(w, width=1000, height=1000)

canvas.create_line(10, 10, 200, 10, 10, 300, 200, 300, fill='blue', smooth=True, width=30, arrow=tk.BOTH)

canvas.create_rectangle(10, 400, 70, 470, fill='red', outline='black', width=5)

canvas.create_arc(500, 50, 700, 250, start=0, fill='yellow', outline='black')
canvas.create_arc(500, 50, 700, 250, start=90, style=tk.CHORD, fill='yellow', outline='black')
canvas.create_arc(500, 50, 700, 250, start=180, style=tk.ARC, outline='black')

photo_image = tk.PhotoImage(file='image.png', width=500, height=500)
canvas.create_image(500, 600, image=photo_image)


canvas.pack()

w.mainloop()
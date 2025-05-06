import tkinter as tk

window = tk.Tk()
window.title("hello")

def button_click():
    print(entry.get())

def bind_button_click(event):
    print(entry.get())
    window.destroy()

text = tk.StringVar()
entry = tk.Entry(window, textvariable=text)
entry.pack()

# button = tk.Button(window, text="Hello", command=button_click)
button = tk.Button(window, text="Hello")
button.config(font=('Arial', '12', 'italic'))
button.bind("<Button-1>", bind_button_click)
button.pack()

window.mainloop()
print("waiting for window to close")
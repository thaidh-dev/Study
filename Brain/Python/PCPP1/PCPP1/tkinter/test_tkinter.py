import tkinter as tk
import time


window = tk.Tk()
window.title("hello")


def button_click():
    print(entry.get())


def bind_button_click(event):
    print(entry.get())
    print(event.num)
    print(event.type)


def on_idle():
    print(time.time())
    # button.after_idle(on_idle)  # Schedule it again


text = tk.StringVar()
entry = tk.Entry(window, textvariable=text)
entry.pack()

# button = tk.Button(window, text="Hello", command=button_click)
button = tk.Button(window, text="Hello", underline=1)
button.config(font=('Arial', '12', 'italic'))
button.bind("<Button-1>", bind_button_click)
button.pack()

label = tk.Message(window, text='This is a label. if the text is too long, u have to set wraplength to force word wrapping')
label.pack()

id_2_cancel = button.after(3000, on_idle)
# id_2_cancel = button.after_idle(on_idle)
# button.after_cancel(id_2_cancel)

window.mainloop()
print("waiting for window to close")

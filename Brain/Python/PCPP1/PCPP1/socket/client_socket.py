import socket

socket_client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
socket_client.connect(('localhost', 8080))

bytes_transmitted = socket_client.send(b'Hello, server!')
print(bytes_transmitted) # 14
reply_10_bytes = socket_client.recv(10)
reply_remaining_bytes = socket_client.recv(1024)
print("Received 10 bytes from server:", reply_10_bytes.decode()) # Hello, cli
print("Received all from server:", reply_remaining_bytes.decode()) # ent!


socket_client.shutdown(socket.SHUT_RDWR)
socket_client.close()
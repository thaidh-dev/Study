import socket

# Create a TCP/IP socket
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Bind the socket to the host and port
server_address = ('localhost', 8080)
server_socket.bind(server_address)

# Listen for incoming connections (the parameter indicates the backlog)
server_socket.listen(5)
print("Server is listening on {}:{}".format(*server_address))

# Wait for a connection (this call blocks until a client connects)
connection, client_address = server_socket.accept()
print("Connection established with:", client_address)

# Now you can use `connection` to send and receive data
try:
    """
        The recv() method will block until it receives at least one byte of data or until a timeout or error occurs. 
        It does not guarantee that it will receive all the data in one call.
        
        buffer_size specifies the maximum amount of data to be received at once (in bytes).
        If the connection is closed by the sender, recv() will return an empty byte string (b'').
    """
    data = connection.recv(1024)
    if data:
        print("Received:", data.decode()) # Hello, server!
        """
            send: send as much data as possible and returns the number of bytes that were actually transmitted
            sendall: all of the data has been sent or an error occurs. If everything is sent successfully, it returns None
        """
        connection.sendall(b"Hello, client!")
finally:
    connection.close()
    server_socket.close()

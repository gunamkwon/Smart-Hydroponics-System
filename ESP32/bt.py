from bluetooth import *
import time


def bt_setup():
    server_socket = BluetoothSocket(RFCOMM)
    port = 1


def connecting():
    server_socket.bind(("", port))
    server_socket.listen(1)
    client_socket, address = server_socket.accept()
    print("Accepted connection from ", address)


def read():
    data = client_socket.recv(1024)
    time.sleep(0.1)
    command = data.decode('utf-8')
    print("Received: %s" % command)
    return command


def send(str):
    client_socket.send(str.encode('utf-8'))
    time.sleep(0.1)


def close():
    client_socket.close()
    server_socket.close()

# 전역변수  client / server socket 써야함

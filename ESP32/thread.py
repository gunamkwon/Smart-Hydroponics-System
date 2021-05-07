import threading

from queue import Queue


def creator(data, q):
    print('Creating Data and putting it on the queue')
    print('\n')
    for item in data:
        evt = threading.Event()
        q.put((item, evt))
        print('Waiting for data to be doubled')
        evt.wait()


def consumer(q):
    while True:
        data, evt = q.get()
        print('Receive Original Data: {}'.format(data))
        processed = data * 5
        print('Receive Processed Data: {}'.format((processed)))
        print('\n')
        evt.set()
        q.task_done()

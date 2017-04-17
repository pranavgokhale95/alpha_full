import socket;
import time;
import mysha;

s=mysha.SHA();
msg = "Suraj"
dig=s.calSHA(msg);
print(dig);

sock = socket.socket(socket.AF_INET,socket.SOCK_STREAM);
addr = ("localhost",2122);

sock.connect(addr);

sock.send(msg);
time.sleep(1);
sock.send(dig);


sock.close();


import socket;
import mysha;

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM);

addr = ("localhost",2122);

sock.bind(addr);

sock.listen(1);

sock1,_ = sock.accept();

msg = sock1.recv(1024);
dgt = sock1.recv(1024);

print msg;
print dgt;

thisSHA = mysha.SHA().calSHA(msg);

if(thisSHA==dgt):
	print "Valid"
else:
	print "Invalid"

sock.close();

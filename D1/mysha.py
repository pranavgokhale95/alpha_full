class SHA:
	@staticmethod
	def chunks(n,s):
		l=[];
		i=0;
		while(i<len(n)):
			l.append(n[i:i+s]);
			i+=s;
		return l;

	@staticmethod
	def rol(n,b):
		return ( (n << b ) | ( n >> ( 32-b ) ) ) & 0xffffffff;

	def calSHA(self,data):
		bits = "";

		for i in range(len(data)):
			bits+='{0:08b}'.format(ord(data[i]));
		
		l1 = len(bits);

		bits+="1";
		
		while ( (len(bits)%512) != 448 ):
			bits+="0";

		bits+='{0:064b}'.format(l1);

		h0 = 0x67452301;
		h1 = 0xEFCDAB89;
		h2 = 0x98BADCFE;				
		h3 = 0X10325476;
		h4 = 0XC3D2E1F0;


		for c in SHA.chunks(bits,512):
			words = SHA.chunks(c,32);
			w = [0]*80;

			for i in range(16):
				w[i] = int(words[i],2);

			for i in range(16,80):
				w[i] = SHA.rol ( (w[i-3] ^ w[i-8] ^ w[i-14] ^ w[i-16]  ), 1 );

			
			a=h0;
			b=h1;
			c=h2;
			d=h3;
			e=h4;

			allf = 0xffffffff;

			for i in range(80):
				if (i>=0 and i<=19):
					f = ( b & c) | ( (~b) & d );
					k = 0X5A827999;
				elif ( i>=20 and i<=39 ):
					f = ( b ^ c ^ d ); 
					k = 0X6ED9EBA1;
				elif ( i>=40 and i<=59 ):
					f = ( b & c ) | ( b & d ) | ( c & d);
					k = 0X8F1BBCDC;
				else:
					f = ( b ^ c ^ d );
					k = 0XCA62C1D6;
			
				
				temp = SHA.rol(a,5) + e + f + k + w[i] & allf
				
				e = d;
				d = c;
				c = SHA.rol(b,30);
				b = a;
				a = temp;


			h0 = h0 + a & allf;
			h1 = h1 + b & allf;
			h2 = h2 + c & allf;
			h3 = h3 + d & allf;
			h4 = h4 + e & allf;
		return '%08x%08x%08x%08x%08x' % (h0,h1,h2,h3,h4);
print (SHA().calSHA("Pranav"))

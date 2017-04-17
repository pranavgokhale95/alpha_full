<%@ page import = "java.sql.*" %>
<%@ page import = "javax.sql.*" %>
<%@ page import = "java.io.*" %>
<%@ page import = "java.net.*" %>
<%@ page import = "java.io.*" %>
<%@ page import = "java.util.*" %>

<html>
	<a href='b2.jsp'>Back</a>
<br>

<BODY>

<h1>Plagarism</h1>

<%! 
	HashSet<String> originalHashSet = new HashSet<>();

	public void loadFile (String fileName) {
		try{
			BufferedReader br = new BufferedReader(new FileReader( new File (fileName ) ) );
		
			String s;

			String res="";

			while( (s=br.readLine()) != null ){
				res+=s;
				res+="\n";
			}

			res=res.replaceAll("\n"," ");

			String[] words = res.split(" ");

			for(String word:words){
				originalHashSet.add(word);
			}
		}catch(Exception ex){
		
		}

	}
%>

<%

	try {
		String text = request.getParameter("text");
		
		loadFile(application.getRealPath("/") + "file0.txt");
		loadFile(application.getRealPath("/") + "file1.txt");
		loadFile(application.getRealPath("/") + "file2.txt");
		loadFile(application.getRealPath("/") + "file3.txt");
		loadFile(application.getRealPath("/") + "file4.txt");

		HashSet<String> currentHashSet = new HashSet<>();
		
		
		String[] inputWords = text.split(" ");

		for(String word:inputWords){
			currentHashSet.add(word);
		}


		int count=0;

		for(String word:currentHashSet){
			if(originalHashSet.contains(word)){
				count++;
			}

		}

		double percentage = ((double)count/originalHashSet.size())*100;

		out.println("<p>" + percentage+ + "</p>");

	}catch ( Exception ex ){
		ex.printStackTrace();
	}

%>

</BODY>
</html>

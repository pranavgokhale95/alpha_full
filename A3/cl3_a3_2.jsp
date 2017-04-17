<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@ page import ="java.io.*" %>
<%@page import="java.net.*" %>
<%@page import="java.util.*" %>

<html>
<a href='cl3_a3.jsp'>Back</a>
<br>
<BODY>
<h1> Multiplication </h1>
<%
BufferedReader br = null;

                try {
                         Socket socketClient= new Socket("localhost",5567);
                           
                            out.println("Client: "+"Connection Established");
         
                            BufferedReader reader = 
                                        new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
         
                            BufferedWriter writer= 
                                        new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
                            String serverMsg;
                            String sCurrentLine;
                
                	String file = application.getRealPath("/") + "a.txt";
                        br = new BufferedReader(new FileReader(file));
                        out.println("\nReading File...");
                        String num2=br.readLine();
                        
                        String num1=br.readLine();
                        
                        int n1=Integer.parseInt(num1);
                        int n2=Integer.parseInt(num2);
                        
                        writer.write(""+n1+"\r\n");
                        writer.write(""+n2+"\r\n");
                        
                        writer.flush();
                        while((serverMsg = reader.readLine()) != null){
                                out.print("Client: " + serverMsg);
                        }
                        

                } catch (IOException e) {
                        e.printStackTrace();
                } 
%>
</BODY>
</html>


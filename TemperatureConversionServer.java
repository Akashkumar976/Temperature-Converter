// package com.socket.server;
import java.io.*;
import java.net.*;
import java.util.HashMap;
public class TemperatureConversionServer
 {
    public TemperatureConversionServer()
    {

    }
    public void startServer(int port)throws IOException
    {
        ServerSocket temperatureServerSocket=null;
        boolean listening=true;
        try{
            temperatureServerSocket=new ServerSocket(port);
            System.out.println("Temperature Conversion Server listening on port "+port+"....");
        }
        catch(IOException e)
        {
System.err.println("Could not listen on the port: "+port);
System.exit(-1);
        }
        Socket clientConnection=null;
        while(listening)
        {
            System.out.println("Waiting for a client connection---");
            clientConnection=temperatureServerSocket.accept();
            PrintWriter out=null;
            BufferedReader in=null;
            try{
                out=new PrintWriter(clientConnection.getOutputStream(),true);
                in=new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
                String inputLine,outputLine;
                outputLine="Enter the temperature in the Centigrade: ";
                out.println(outputLine);
                while((inputLine=in.readLine())!=null)
                {
                    if(inputLine.equalsIgnoreCase("Bye"))
                     break;
                     outputLine=convertToFarenheit(inputLine);
                     out.println(outputLine);
                }
                out.close();
                in.close();
                clientConnection.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    private String convertToFarenheit(String data)
    {
        double ctemp=Double.valueOf(data).intValue();
        double ftemp=(ctemp*9/5)+32;
        return String.valueOf(ftemp);
    }
    public static void main(String args[])throws IOException
    {
        TemperatureConversionServer server=new TemperatureConversionServer();
        server.startServer(6666);
    }
}

// package com.socket.client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TemperatureConversionClient
{
    String ipAddress="localhost";
    int port=6666;
    public TemperatureConversionClient()
    {

    }
    public void startClient()
    {
        Socket client=null;
        PrintWriter out=null;
        BufferedReader in=null;
        try{
            client=new Socket(ipAddress,port);
            out=new PrintWriter(client.getOutputStream(),true);
            in=new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedReader stdIn=new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;
            while((fromServer=in.readLine())!=null)
            {
                System.out.println("Server: "+fromServer);
                if(fromServer.indexOf("Bye")>0)
                break;
                fromUser=stdIn.readLine();
                if(fromUser!=null)
                {
                    System.out.println("Client: "+fromUser);
                    out.println(fromUser);
                }
            }
            out.close();
            in.close();
            stdIn.close();
            client.close();
        }
        catch(UnknownHostException e)
        {
            System.err.println("Don't know about the host: "+ipAddress);
            System.exit(1);
        }
        catch(IOException e)
        {
            System.err.println("Coulldn't get I/O for the connection to: "+ipAddress);
        }
        System.exit(0);
    }
    public static void main(String args[])
    {
        TemperatureConversionClient client=new TemperatureConversionClient();
        client.startClient();
    }
}
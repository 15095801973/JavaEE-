package servlet;

import core.HttpThread;
import core.Request;

import java.io.*;

public class MyServlet {
    public void doGet(Request request) throws IOException {
        String name=request.getHeader(0).split(" ")[1];

        String content=loadHtml(name);
        PrintWriter writer=new PrintWriter(new OutputStreamWriter(request.getOutputStream()));
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html;charset=UTF-8");
        writer.println("Content-Length:"+content.getBytes("UTF-8").length);
        writer.println();
        writer.print(content);
        writer.flush();
        writer.close();

    }
    public void doPost(Request request)
    {

    }
    private  String loadHtml(String name) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(HttpThread.class.getResourceAsStream(name)));
        StringBuffer buffer=new StringBuffer();
        String line=reader.readLine();
        while(line!=null)
        {
            buffer.append(line);
            buffer.append("\n");
            line=reader.readLine();
        }
        return buffer.toString();

    }
}

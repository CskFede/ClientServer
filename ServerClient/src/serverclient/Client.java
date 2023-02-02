/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package serverclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
  public static void main(String[] args) throws Exception {
    // Connette al server sulla porta 4444
    Socket socket = new Socket("localhost", 4444);
    try {
      // Invia il nome dell'host al server
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      out.println(InetAddress.getLocalHost().getHostName());

      // Riceve il nome dell'host del server
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String response = in.readLine();
      System.out.println("Il server si chiama: " + response);

      // Controlla la correttezza delle informazioni scambiate
      // utilizzando un comando di scripting shell
      Process p = Runtime.getRuntime().exec("hostname");
      BufferedReader in2 = new BufferedReader(new InputStreamReader(p.getInputStream()));
      String localHostname = in2.readLine();
      if (response.equals(localHostname)) {
        System.out.println("Le informazioni sono corrette");
      } else {
        System.out.println("Le informazioni NON sono corrette");
      }
    } finally {
      socket.close();
    }
  }
}
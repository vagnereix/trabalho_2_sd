package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerMain {
  public static void main(String[] args) {
    try {
      // Cria o registro RMI na porta 1099 (ou outra se desejar)
      LocateRegistry.createRegistry(1099);
      RemoteInvocationImpl remoteObj = new RemoteInvocationImpl();
      // Registra o objeto remoto com um nome (a URL inclui IP, porta e nome)
      Naming.rebind("rmi://localhost:1099/RemoteInvocationService", remoteObj);
      System.out.println("RemoteInvocationService registrado e aguardando requisições...");
    } catch (Exception e) {
      System.err.println("Exceção no servidor: " + e.toString());
      e.printStackTrace();
    }
  }
}

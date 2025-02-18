package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInvocation extends Remote {
  byte[] doOperation(String objectReference, String methodId, byte[] arguments) throws RemoteException;
}


package sample.serverGrafieken;

import sample.serverGrafieken.interfaces.GraphManagerServerInterface;

import java.io.IOException;
import java.sql.SQLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    // Set port number
    private static int portNumber = 1088;
    private static String ip = "127.0.0.1";
    // Set binding name for student administration
    private String bindingName = "GraphManager";

    // References to registry and student administration
    private Registry registry = null;
    private GraphManagerServerInterface serverManager = null;

    // Constructor
    public Server() throws SQLException, IOException, ClassNotFoundException {
        //get settings
        System.setProperty("java.rmi.server.hostname", ip);
        // Print port number for registry
        System.out.println("ip : " + ip);
        System.out.println("Server: Port number " + portNumber);

        // Create student administration
        serverManager = new GraphManagerServer();


        // Create registry at port number
        try {
            registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Server: Registry created on port number " + portNumber);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            registry = null;
        }

        // Bind student administration using registry
        try {
            registry.rebind(bindingName, serverManager);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot bind student administration");
            System.out.println("Server: RemoteException: " + ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("SERVER USING REGISTRY");
        if (args.length != 0) {
            if (!args[0].isEmpty()) {
                ip = args[0];
            }
            if (!args[1].isEmpty()) {
                portNumber = Integer.parseInt(args[1]);
            }
        }

        new Server();
    }
}

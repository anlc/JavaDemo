package other;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.CountDownLatch;

public class RmiTest {
    public interface RemoteHello extends Remote {
        String sayHello(String name) throws RemoteException;
    }

    public static class RemoteHelloImpl implements RemoteHello {
        public String sayHello(String name) throws RemoteException {
            return String.format("Hello, %s!", name);
        }
    }

    public static class RegistryServer {
        public static void main(String[] args) throws InterruptedException {
            try {
                LocateRegistry.createRegistry(8000); //Registry使用8000端口
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            CountDownLatch latch = new CountDownLatch(1);
            latch.await();  //挂起主线程，否则应用会退出
        }
    }

    public static class RMIServer {
        public static void main(String[] args) {
            RemoteHello remoteHello = new RemoteHelloImpl();
            try {
                RemoteHello stub = (RemoteHello) UnicastRemoteObject.exportObject(remoteHello, 4000); //导出服务，使用4000端口
                Registry registry = LocateRegistry.getRegistry("127.0.0.1", 8000); //获取Registry
                registry.bind("hello", stub); //使用名字hello，将服务注册到Registry
            } catch (AlreadyBoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class RMIClient {
        public static void main(String[] args) {
            try {
                Registry registry = LocateRegistry.getRegistry("127.0.0.1", 8000);  //获取注册中心引用
                RemoteHello remoteHello = (RemoteHello) registry.lookup("hello"); //获取RemoteHello服务
                System.out.println(remoteHello.sayHello("World"));  //调用远程方法
            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }
        }
    }

}

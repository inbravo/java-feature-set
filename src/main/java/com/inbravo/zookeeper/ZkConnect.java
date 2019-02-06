package com.inbravo.zookeeper;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * A sample test application to learn Zookeeper. First create the
 * <zookeeper-installation-directory>/conf/zoo.cfg with following content "tickTime=2000 *
 * dataDir=/data clientPort=2181" Start the Zookeeper server using: zkServer.cmd/zkServer.sh
 * executable
 * 
 * @author amit.dixit
 *
 */
public class ZkConnect {

  private ZooKeeper zk;

  private final CountDownLatch connSignal = new CountDownLatch(0);

  /* Host should be 127.0.0.1:3000,127.0.0.1:3001,127.0.0.1:3002 */
  public final ZooKeeper connect(final String host) throws Exception {

    zk = new ZooKeeper(host, 3000, new Watcher() {

      public void process(final WatchedEvent event) {

        if (event.getState() == KeeperState.SyncConnected) {
          connSignal.countDown();
        }
      }
    });

    connSignal.await();
    return zk;
  }

  public final void close() throws InterruptedException {
    zk.close();
  }

  public final void createNode(final String path, final byte[] data) throws Exception {
    zk.create(path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
  }

  public final void updateNode(final String path, final byte[] data) throws Exception {
    zk.setData(path, data, zk.exists(path, true).getVersion());
  }

  public final void deleteNode(final String path) throws Exception {
    zk.delete(path, zk.exists(path, true).getVersion());
  }

  public static void main(final String... args) throws Exception {

    final ZkConnect connector = new ZkConnect();

    /* Where connect where zookeeper instance is running */
    final ZooKeeper zk = connector.connect("127.0.0.1:2181");

    /* New node name */
    final String newNode = "/inbravoDate" + new Date();

    /* Create a new node */
    connector.createNode(newNode, new Date().toString().getBytes());

    /* Get all zookeeper nodes at root */
    final List<String> zNodes = zk.getChildren("/", true);

    /* Print name of all zookeeper nodes */
    for (final String zNode : zNodes) {

      System.out.println("ChildrenNode: " + zNode);
    }

    /* Get data from newly created node */
    byte[] data = zk.getData(newNode, true, zk.exists(newNode, true));

    System.out.println("GetData before setting");

    for (final byte dataPoint : data) {

      System.out.print((char) dataPoint);
    }

    System.out.println("GetData after setting");

    connector.updateNode(newNode, "Modified data".getBytes());

    data = zk.getData(newNode, true, zk.exists(newNode, true));

    for (byte dataPoint : data) {
      System.out.print((char) dataPoint);
    }

    /* Finally delete the node */
    connector.deleteNode(newNode);
  }
}

package com.waqasabbasi.configuration.zookeeper;

import com.waqasabbasi.types.ZookeeperException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ZKNode {


    /**
     * @param zooKeeper Zookeeper Session
     * @param path Path to Node
     * @param data Data to create the Node with
     * Method used to create Persistent Nodes in Zookeeper Ensemble
     */
    public static void create(ZooKeeper zooKeeper, String path, String data) throws KeeperException, InterruptedException, IOException, ZookeeperException {
        zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    //int[0] -> startRange, int[1] --> endRange
    public static int[] getIdRange(ZooKeeper zooKeeper) throws IOException, InterruptedException, KeeperException, ZookeeperException {

        boolean committed = false;
        String path = "/UniqueIDNode";
        int[] result = new int[2];
        int increment = 100000;

        while (!committed) {
            //Get data from Node
            byte[] b = zooKeeper.getData(path, null, null);
            String data = new String(b, StandardCharsets.UTF_8);

            /*
            1 --> 100,000
            101,000 -> 200,000

            if data == 0
            result[0] = 1
            result[1] = 100,000

            if data == 100,000
            result[0] = 101,000
            result[1] = 200,000
             */
            result[0] = Integer.parseInt(data) + 1;
            result[1] = result[0] + increment - 1;

            //Keep trying to the set id until it is succesful
            try {
                //set new data to node
                byte[] req = (result[1] + "").getBytes();
                zooKeeper.setData(path, req, zooKeeper.exists(path, false).getVersion());
                committed = true;
            } catch (KeeperException.BadVersionException | InterruptedException e) {
                committed = false;
            }
        }

        return result;
    }
}

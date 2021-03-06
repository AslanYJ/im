package com.yjlan.im.client.scan;

import com.yjlan.im.client.client.ImNettyClient;
import com.yjlan.im.common.utils.SpringBeanFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * @author yjlan
 * @version V1.0
 * @Description 窗口输入扫描
 * @date 2022.01.20 15:36
 *
 * */
public class ScanWorker extends Thread{

    private static final Logger LOGGER = LoggerFactory.getLogger(ScanWorker.class);

    private ImNettyClient imNettyClient;

    public ScanWorker() {
        this.imNettyClient = SpringBeanFactory.getBean(ImNettyClient.class);
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String msg = sc.nextLine();
            String[] strings = msg.split("-");
            String commandType = strings[0];
            if (commandType.equals("auth")) {
                imNettyClient.authenticate(strings[1]);
            } else if (commandType.equals("send")){
                // send-1-message 第二个参数是发送到谁那
                Long receiverId = Long.valueOf(strings[1]);
                String message = strings[2];
                imNettyClient.sendMessagePeer2Peer(receiverId,message);
            }  else if(commandType.equals("group")) {
                // group-1-message; 第二个参数是发送到那个群
                Long groupId = Long.valueOf(strings[1]);
                String message = strings[2];
                imNettyClient.sendGroupMessage(groupId,message);
            }
          
            LOGGER.info("msg :{}",msg);
        }
    }
}

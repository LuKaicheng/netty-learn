package io.github.lucifer.redisclient;

import java.io.Console;

import io.github.lucifer.redisclient.connector.RedisConnector;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;

public class RedisConsole {

    public static void main(String[] args) {
        Console cs = System.console();
        if (cs == null) {
            throw new IllegalStateException("不能使用控制台");
        }
        String line = null;
        RedisConnector connector = new RedisConnector("172.23.25.143", 6382);
        Channel channel = connector.execute();
        while (true) {
            line = cs.readLine();
            if ("exit".equals(line)) {
                break;
            }
            ByteBuf buf = Unpooled.copiedBuffer(encode(line), CharsetUtil.UTF_8);
            channel.writeAndFlush(buf);
        }
        System.exit(0);
    }

    private static String encode(String line) {
        String[] array = line.split(" ");
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("*").append(array.length).append("\r\n");
        for (String str : array) {
            strBuilder.append("$").append(str.length()).append("\r\n").append(str).append("\r\n");
        }
        return strBuilder.toString();
    }

}

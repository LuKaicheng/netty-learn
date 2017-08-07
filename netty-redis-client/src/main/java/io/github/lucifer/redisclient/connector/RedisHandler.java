package io.github.lucifer.redisclient.connector;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class RedisHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("收到响应:" + msg.toString(CharsetUtil.UTF_8));
    }
    
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.print("请输入 >>");
    }



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.print("连接已建立，请输入 >>");
    }

}

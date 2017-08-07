package io.github.lucifer.redisclient.connector;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class RedisConnector {

	private final String host;
	private final int port;

	public RedisConnector(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public Channel execute() {
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(new NioEventLoopGroup()).channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new RedisHandler());
					}
				});
		ChannelFuture future = bootstrap.connect(host, port);
		return future.syncUninterruptibly().channel();
	}
}

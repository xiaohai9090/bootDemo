package com.springboot.bootdemo.netty;

import com.springboot.bootdemo.handler.NetMsgBaseHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.log4j.Logger;

/**
 * @author xiaohai
 * @date 2023/3/6 19:50
 */
public class NettyServer {

    private static final Logger LOG = Logger.getLogger(NettyServer.class);


    public static void initNettyServer() {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {

            // 1.启动器负责组装netty组件，启动服务器
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 2.                  boss负责连接，accept时间         work（child）负责读写，处理器
            serverBootstrap.group(boss, worker);
            // 3.选择NioServerSocketChannel实现
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                    // 12.连接建立后，调用初始化方法
                    // 负责添加handler
                    LengthFieldBasedFrameDecoder fieldBasedFrameDecoder = new LengthFieldBasedFrameDecoder(512, 16, 4, 0, 0);
                    ChannelPipeline pipeline = nioSocketChannel.pipeline();
                    pipeline.addLast(fieldBasedFrameDecoder);
                    pipeline.addLast(new LoggingHandler(LogLevel.DEBUG));
                    pipeline.addLast(new MsgCoder());  // 17.将bytebuf 自定义解码
                    pipeline.addLast(new NetMsgBaseHandler());

//                    pipeline.addLast(new ClubChannelHandler());
//                    pipeline.addLast(new MemberChannelHandler());

                }
            });

            Channel channel = serverBootstrap.bind(8080).sync().channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            LOG.error("", e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }




}
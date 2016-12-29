package com.pci.data.could.card.system.handler.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.pci.data.could.card.system.tools.LogUtils;

/**
 * 监听服务器
 * 
 * @since 1.0.0
 * @author dzy
 */
public class SystemServiceServer {

	private static LogUtils logger = new LogUtils(SystemServiceServer.class);
	
	private int port;
	private int workerCorePoolSize = 16;
	private ChannelPipelineFactory channelPipelineFactory;
	private ExecutorService bossExecutor;
	private ExecutorService workerExecutor;
	private ServerBootstrap bootstrap;

	/**
	 * 构造方法
	 * 
	 * @param workerCorePoolSize
	 * @param channelPipelineFactory
	 * 
	 * @since 0.0.1
	 */
	public SystemServiceServer(int workerCorePoolSize,
			ChannelPipelineFactory channelPipelineFactory) {
		this.workerCorePoolSize = workerCorePoolSize;
		this.channelPipelineFactory = channelPipelineFactory;
	}

	/**
	 * 构造方法
	 * 
	 * @param port
	 * @param workerCorePoolSize
	 * @param channelPipelineFactory
	 * @param bossExecutor
	 * @param workerExecutor
	 * 
	 * @since 0.0.1
	 */
	public SystemServiceServer(int port, int workerCorePoolSize,
			ChannelPipelineFactory channelPipelineFactory, 
			ExecutorService bossExecutor, ExecutorService workerExecutor) {
		this.port = port;
		this.workerCorePoolSize = workerCorePoolSize;
		this.channelPipelineFactory = channelPipelineFactory;
		this.bossExecutor = bossExecutor;
		this.workerExecutor = workerExecutor;
	}

	/**
	 * 启动服务器
	 * 
	 * @throws Exception
	 * @since 0.0.1
	 */
	public void start() throws Exception {
		logger.info("开始启动云卡演示系统服务端...");
		checkAndInitializeExecutors();
		try {
			ChannelFactory factory = new NioServerSocketChannelFactory(
					bossExecutor, workerExecutor, workerCorePoolSize);
			bootstrap = new ServerBootstrap(factory);
			bootstrap.setPipelineFactory(channelPipelineFactory);
			bootstrap.bind(new InetSocketAddress(port));
			logger.info("云卡演示系统服务端启动成功，服务端口：" + port);
		} catch (Exception e) {
			logger.error("云卡演示系统服务端启动失败!", e);
			throw e;
		}
	}

	/**
	 * 停止服务器
	 * 
	 * @throws Exception
	 * @since 0.0.1
	 */
	public void stop() throws Exception {
		logger.info("开始停止云卡演示系统服务端...");
		if (bossExecutor != null) {
			bossExecutor.shutdown();
		}
		if (workerExecutor != null) {
			workerExecutor.shutdown();
		}
		if (bootstrap != null) {
			bootstrap.shutdown();
		}
		logger.info("云卡演示系统服务端停止成功");
	}

	/**
	 * 检查并初始化线程池
	 * 
	 * @since 0.0.1
	 */
	private void checkAndInitializeExecutors() {
		if (bossExecutor == null) {
			bossExecutor = Executors.newCachedThreadPool();
		}
		if (workerExecutor == null) {
			workerExecutor = Executors.newFixedThreadPool(workerCorePoolSize);
		}
	}

}

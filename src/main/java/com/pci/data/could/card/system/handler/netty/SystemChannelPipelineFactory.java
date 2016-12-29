package com.pci.data.could.card.system.handler.netty;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.jboss.netty.handler.execution.ExecutionHandler;

/**
 * PGWChannelPipelineFactory
 * 
 * @since 0.0.1
 * @author dzy
 */
public class SystemChannelPipelineFactory implements ChannelPipelineFactory {

	private FrameDecoder decoder;
	private ExecutionHandler executor;
	private SimpleChannelUpstreamHandler handler;

	public SystemChannelPipelineFactory(FrameDecoder decoder,
			ExecutionHandler executor, SimpleChannelUpstreamHandler handler) {
		this.decoder = decoder;
		this.executor = executor;
		this.handler = handler;
	}

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();
		pipeline.addLast("decoder", decoder);
		pipeline.addLast("executor", executor);
		pipeline.addLast("handler", handler);
		return pipeline;
	}

}

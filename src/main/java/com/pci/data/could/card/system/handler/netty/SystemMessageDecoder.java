package com.pci.data.could.card.system.handler.netty;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

import com.pci.data.could.card.system.tools.CustomConstants;

/**
 * 消息解码器
 * 
 * @since 0.0.1
 * @author dzy
 */
public class SystemMessageDecoder extends FrameDecoder {

	private Charset charset;

	public SystemMessageDecoder() {
		// 默认的字符编码为GBK
		this.charset = Charset.forName(CustomConstants.CHARSET_GBK);
	}

	public SystemMessageDecoder(String charsetName) {
		this.charset = Charset.forName(charsetName);
	}

	public void setCharset(String charsetName) {
		this.charset = Charset.forName(charsetName);
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) throws Exception {
		int readerIndex = buffer.readerIndex();
		
		if (buffer.readableBytes() < 8) {
			return null;
		}
		
		String length = buffer.toString(0, 8, charset);
		int actualFrameLength = Integer.valueOf(length);
		if (buffer.readableBytes() < actualFrameLength) {
			return null;
		}
		
		ChannelBuffer frame = extractFrame(buffer, readerIndex, actualFrameLength);
		buffer.readerIndex(actualFrameLength);
		return frame;
	}

}

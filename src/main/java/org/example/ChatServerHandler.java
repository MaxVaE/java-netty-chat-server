package org.example;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class ChatServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) {
        String uri = request.uri();

        if (uri.equals("/chat/messages")) {
            String response = MessageHandler.getAllMessage();

            sendHttpResponse(ctx, HttpResponseStatus.OK, response);

            return;
        }

        if (uri.equals("/chat/send")) {
            String stringContent = request.content().toString(CharsetUtil.UTF_8);
            String response = MessageHandler.sendMessage(stringContent);

            sendHttpResponse(ctx, HttpResponseStatus.OK, response);

            return;
        }

        sendHttpResponse(ctx, HttpResponseStatus.NOT_FOUND, "Endpoint not found");
    }

    private void sendHttpResponse(ChannelHandlerContext ctx, HttpResponseStatus status, String content) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status,
                Unpooled.copiedBuffer(content, CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        response.headers().set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:3000");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());

        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
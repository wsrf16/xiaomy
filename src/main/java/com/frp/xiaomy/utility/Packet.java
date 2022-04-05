package com.frp.xiaomy.utility;

import com.aio.portable.swiss.suite.bean.serializer.json.JacksonSugar;
import com.aio.portable.swiss.suite.log.facade.LogHub;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;


public class Packet {
    LogHub log = AppLogHubFactory.staticBuild1();

    private byte[] headBytes;
    private byte[] bodyBytes;
    private HashMap head;
    private String body;

    public Packet(HashMap head, String body) {
        this.head = head;
        this.body = body;

        this.headBytes = toByte(head);
        this.bodyBytes = toByte(this.body);
    }

    private static byte[] toByte(HashMap head) {
        return JacksonSugar.obj2Json(head).getBytes();
    }

    private static byte[] toByte(String body) {
        return body.getBytes();
    }

    private final static <T> T toHead(byte[] bytes) {
        return JacksonSugar.json2T(new String(bytes));
    }

    private final static String toBody(byte[] bytes) {
        return new String(bytes);
    }

    public Packet(InputStream inputStream) throws Exception {
        byte[] bytesReadHeadLength = new byte[4];
        byte[] bytesReadBodyLength = new byte[4];

        int hl = 0, hlSize = 4;
        while (hl < hlSize) {
            int aa = inputStream.read(bytesReadHeadLength, hl, hlSize - hl);

            if (aa == -1) {
                break;
            }

            if (aa == 0) {
                Thread.sleep(10L);
            }

            hl += aa;
        }


        int bl = 0, blSize = 4;
        while (bl < blSize) {
            int aa = inputStream.read(bytesReadBodyLength, bl, blSize - bl);
            if (aa == -1) {
                break;
            }
            if (aa == 0) {
                Thread.sleep(10L);
            }
            bl += aa;
        }


        this.headBytes = new byte[BaseUtility.byteToInt2(bytesReadHeadLength)];
        this.bodyBytes = new byte[BaseUtility.byteToInt2(bytesReadBodyLength)];


        try {
            int h = 0, hSize = BaseUtility.byteToInt2(bytesReadHeadLength);
            while (h < hSize) {
                int aa = inputStream.read(this.headBytes, h, hSize - h);
                if (aa == -1) {
                    break;
                }
                if (aa == 0) {
                    Thread.sleep(10L);
                }
                h += aa;
            }
            this.head = toHead(this.headBytes);

            int b = 0, bSize = BaseUtility.byteToInt2(bytesReadBodyLength);
            while (b < bSize) {
                int aa = inputStream.read(this.bodyBytes, b, bSize - b);
                if (aa == -1) {
                    break;
                }
                if (aa == 0) {
                    Thread.sleep(10L);
                }
                b += aa;
            }
//            this.body = toType(this.bodyBytes);
        } catch (Exception e) {
            log.e("发送错误", e);
            System.out.println("发送错误");
            throw new RuntimeException(e);
        }
    }

    public boolean Send(Socket socket) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(BaseUtility.intToBytes2(this.headBytes.length));
            outputStream.write(BaseUtility.intToBytes2(this.bodyBytes.length));
            outputStream.write(this.headBytes);
            outputStream.write(this.bodyBytes);
            outputStream.flush();
            return true;
        } catch (Exception e) {
            log.e("发送错误", e);
            System.out.println("发送错误");
            return false;
        }
    }

    public HashMap getHead() {
        return this.head;
    }

}

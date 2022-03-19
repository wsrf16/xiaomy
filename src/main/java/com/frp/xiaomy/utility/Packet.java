package com.frp.xiaomy.utility;

import com.aio.portable.swiss.suite.bean.serializer.json.JacksonSugar;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;


public class Packet
{
    private int Type;
    private int HeadLength;
    private int BodyLength;
    private byte[] Head;
    private byte[] Body;
    private HashMap HeadObject;

    public Packet(HashMap headObject, byte[] Body) {
        Body = (Body == null) ? "Null Body".getBytes() : Body;

        this.HeadObject = headObject;
        this.Head = headObject.toString().getBytes();
        this.HeadLength = (JacksonSugar.obj2Json(headObject).getBytes()).length;

        this.Body = Body;
        this.BodyLength = Body.length;
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


        this.Head = new byte[BaseUtility.byteToInt2(bytesReadHeadLength)];
        this.Body = new byte[BaseUtility.byteToInt2(bytesReadBodyLength)];



        try {
            int h = 0, hSize = BaseUtility.byteToInt2(bytesReadHeadLength);
            while (h < hSize) {
                int aa = inputStream.read(this.Head, h, hSize - h);

                if (aa == -1) {
                    break;
                }
                if (aa == 0) {
                    Thread.sleep(10L);
                }
                h += aa;
            }

            int b = 0, bSize = BaseUtility.byteToInt2(bytesReadBodyLength);

            while (b < bSize) {
                int aa = inputStream.read(this.Body, b, bSize - b);

                if (aa == -1) {
                    break;
                }
                if (aa == 0) {
                    Thread.sleep(10L);
                }
                b += aa;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            this.HeadObject = JacksonSugar.json2T(new String(this.Head));
        }
        catch (Exception e) {
            System.out.println("(错误)");
            throw e;
        }
    }





    public boolean Send(Socket socket) {
        try {
            OutputStream outputStream = socket.getOutputStream();


            outputStream.write(BaseUtility.intToBytes2(this.HeadLength));

            outputStream.write(BaseUtility.intToBytes2(this.BodyLength));

            outputStream.write(this.Head);

            outputStream.write(this.Body);

            outputStream.flush();


            return true;
        } catch (Exception e) {
            return false;
        }
    }





    public int getType() { return this.Type; }



    public void setType(int type) { this.Type = type; }



    public int getHeadLength() { return this.HeadLength; }



    public void setHeadLength(int headLength) { this.HeadLength = headLength; }



    public int getBodyLength() { return this.BodyLength; }



    public void setBodyLength(int bodyLength) { this.BodyLength = bodyLength; }



    public byte[] getHead() { return this.Head; }



    public void setHead(byte[] head) { this.Head = head; }



    public byte[] getBody() { return this.Body; }



    public void setBody(byte[] body) { this.Body = body; }



    public HashMap getHeadObject() { return this.HeadObject; }



    public void setHeadObject(HashMap headObject) { this.HeadObject = headObject; }
}

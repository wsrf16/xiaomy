package com.frp.xiaomy.utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ReadWriteRunnable implements Runnable {
    private Socket readSocket;
    private Socket writeSocket;
    private Boolean flag = Boolean.valueOf(true);


    public ReadWriteRunnable(Socket readSocket, Socket writeSocket) {
        this.readSocket = readSocket;
        this.writeSocket = writeSocket;
    }

    public void run() {
        byte[] b = new byte[2048];
        InputStream is = null;
        OutputStream os = null;
        try {
            is = this.readSocket.getInputStream();
            os = this.writeSocket.getOutputStream();
            while (!this.readSocket.isClosed() && !this.writeSocket.isClosed() && this.flag.booleanValue()) {
                int size = is.read(b);
                if (size > 0) {
                    os.write(b, 0, size);
                    continue;
                }
                if (size == 0) {
                    Thread.sleep(10L);
                    continue;
                }
                if (size == -1) {
                    this.flag = Boolean.valueOf(false);

                    break;
                }
            }
        } catch (Exception e) {

            this.flag = Boolean.valueOf(false);
        } finally {

            this.flag = Boolean.valueOf(false);

            try {
                if (is != null) {
                    is.close();
                }
                if (null != os) {
                    os.flush();
                    os.close();
                }
            } catch (IOException iOException) {
            }
        }
    }
}
package plain.spring.commons.elk;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;

import java.io.IOException;
import java.io.InputStream;

public class CachedBodyServletInputStream extends ServletInputStream {

    private final InputStream cachedBodyInputStream;

    public CachedBodyServletInputStream(InputStream cachedBodyInputStream) {
        this.cachedBodyInputStream = cachedBodyInputStream;
    }

    @Override
    public int read() throws IOException {
        return cachedBodyInputStream.read();
    }

    @Override
    public boolean isFinished() {
        try {
            return cachedBodyInputStream.available() == 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isReady() {
        return true; // Always return true for simplicity
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        // Implementation depends on your requirement
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
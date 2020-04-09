package com.kstarrain.framework.htmltopdf;

/**
 * @author: DongYu
 * @create: 2020-04-08 16:36
 * @description:
 */
public class HtmlToPdfException extends RuntimeException {
    public HtmlToPdfException() {
    }

    public HtmlToPdfException(String message) {
        super(message);
    }

    public HtmlToPdfException(String message, Throwable cause) {
        super(message, cause);
    }

    public HtmlToPdfException(Throwable cause) {
        super(cause);
    }

    public HtmlToPdfException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

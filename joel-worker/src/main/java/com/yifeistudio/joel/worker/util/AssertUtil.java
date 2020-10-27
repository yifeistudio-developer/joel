package com.yifeistudio.joel.worker.util;

import com.yifeistudio.joel.worker.config.AssertException;
import com.yifeistudio.joel.worker.config.BadParameterException;

import java.util.Collection;
import java.util.Map;

/**
 * @author yi
 * @since 2020/10/26-6:59 下午
 */
public final class AssertUtil {

    private static final int BAD_PARAMETER = 400;

    private AssertUtil() { }

    public static void isTrue(boolean express, String msg) {
        isTrue(express, BAD_PARAMETER, msg);
    }

    public static void isTrue(boolean express, int code, String msg) {
        if (!express) {
            if (code == BAD_PARAMETER) {
                throw new BadParameterException(BAD_PARAMETER, msg);
            }
            throw new AssertException(code, msg);
        }
    }

    public static void isTrue(boolean condition, boolean express, String msg) {
        if (condition) {
            isTrue(express, msg);
        }
    }

    public static void isTrue(boolean condition, boolean express, int code, String msg) {
        if (condition) {
            isTrue(express, code, msg);
        }
    }


    public static void notNull(Object obj, String msg) {
        notNull(obj, BAD_PARAMETER, msg);
    }

    public static void notNull(Object obj, int code, String msg) {
        isTrue(obj != null, code, msg);
        if (obj instanceof String) {
            boolean isNotBlank = ((String) obj).trim().length() > 0;
            isTrue(isNotBlank, code, msg);
            return;
        }
        if (obj instanceof Collection) {
            boolean isNotEmpty = !((Collection<?>) obj).isEmpty();
            isTrue(isNotEmpty, code, msg);
            return;
        }
        if (obj instanceof Map) {
            boolean isNotEmpty = !((Map<?, ?>) obj).isEmpty();
            isTrue(isNotEmpty, code, msg);
        }
    }

    public static void notNull(boolean condition, Object obj, String msg) {
        if (condition) {
            notNull(obj, msg);
        }
    }

    public static void notNull(boolean condition, Object obj, int code, String msg) {
        if (condition) {
            notNull(obj, code, msg);
        }
    }

}

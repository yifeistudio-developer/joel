package com.yifeistudio.joel.worker;

import com.yifeistudio.joel.worker.config.AssertException;
import com.yifeistudio.joel.worker.config.BadParameterException;
import com.yifeistudio.joel.worker.util.AssertUtil;
import org.junit.Test;

import java.util.Collections;

/**
 * @author yi
 * @since 2020/10/26-8:00 下午
 */
public class AssertUtilTests {

    private static final String ASSERT_EXCEPTION = "assert exception";

    private static final String BAD_PARAMETER_EXCEPTION = "bad parameter exception";

    private static final String NO_EXCEPTION = "no exception";

    @Test
    public void assertIsTrueTest() {
        System.out.println("starting tests");
        // 1.
        AssertUtil.isTrue(true, NO_EXCEPTION);
        System.out.println("passed : " + 1);

        // 2.
        try {
            AssertUtil.isTrue(false, BAD_PARAMETER_EXCEPTION);
        } catch (Exception e) {
            assert e.getMessage().equals(BAD_PARAMETER_EXCEPTION);
            assert e instanceof BadParameterException;
            System.out.println("passed : " + 2);
        }

        // 3.
        try {
            AssertUtil.isTrue(false, 500, ASSERT_EXCEPTION);
        } catch (Exception e) {
            assert e.getMessage().equals(ASSERT_EXCEPTION);
            assert e instanceof AssertException;
            System.out.println("passed : " + 3);
        }

        // 4.
        AssertUtil.isTrue(true, true, NO_EXCEPTION);
        System.out.println("passed : " + 4);

        // 5.
        try {
            AssertUtil.isTrue(true, false, BAD_PARAMETER_EXCEPTION);
        } catch (Exception e) {
            assert e.getMessage().equals(BAD_PARAMETER_EXCEPTION);
            assert e instanceof BadParameterException;
            System.out.println("passed : " + 5);
        }

        // 6.
        try {
            AssertUtil.isTrue(true, false, 500, ASSERT_EXCEPTION);
        } catch (Exception e) {
            assert e.getMessage().equals(ASSERT_EXCEPTION);
            assert e instanceof AssertException;
            System.out.println("passed : " + 6);
        }

        // 7.
        AssertUtil.isTrue(false, true, BAD_PARAMETER_EXCEPTION);
        System.out.println("passed : " + 7);

        // 8.
        AssertUtil.isTrue(false, false, BAD_PARAMETER_EXCEPTION);
        System.out.println("passed : " + 8);

        // 9.
        AssertUtil.isTrue(false, false, 500, ASSERT_EXCEPTION);
        System.out.println("passed : " + 9);

        // 10
        AssertUtil.isTrue(false, false, 500, ASSERT_EXCEPTION);
        System.out.println("passed : " + 10);

        System.out.println("testing ended.");
    }

    @Test
    public void notNullTest() {
        System.out.println("starting tests");

        // 1.
        AssertUtil.notNull("abc", BAD_PARAMETER_EXCEPTION);

        System.out.println("passed : " + 1);
        // 2.
        try {
            AssertUtil.notNull(null, BAD_PARAMETER_EXCEPTION);
        } catch (Exception e) {
            assert e.getMessage().equals(BAD_PARAMETER_EXCEPTION);
            assert e instanceof BadParameterException;
            System.out.println("passed : " + 2);
        }
        // 3.
        AssertUtil.notNull("abc", 500, ASSERT_EXCEPTION);
        System.out.println("passed : " + 3);

        // 4.
        try {
            AssertUtil.notNull(null, 500, ASSERT_EXCEPTION);
        } catch (Exception e) {
            assert e.getMessage().equals(ASSERT_EXCEPTION);
            assert e instanceof AssertException;
            System.out.println("passed : " + 4);
        }

        // 5.
        AssertUtil.notNull(true, "abc", NO_EXCEPTION);
        System.out.println("passed : " + 5);

        // 6.
        try {
            AssertUtil.notNull(true, null, BAD_PARAMETER_EXCEPTION);
        } catch (Exception e) {
            assert e.getMessage().equals(BAD_PARAMETER_EXCEPTION);
            assert e instanceof BadParameterException;
            System.out.println("passed : " + 6);
        }

        // 7.
        AssertUtil.notNull(false, null, NO_EXCEPTION);
        System.out.println("passed : " + 7);

        // 8.
        AssertUtil.notNull(false, "abc", NO_EXCEPTION);
        System.out.println("passed : " + 8);

        // 9.
        try {
            AssertUtil.notNull("", BAD_PARAMETER_EXCEPTION);
        } catch (Exception e) {
            assert e.getMessage().equals(BAD_PARAMETER_EXCEPTION);
            assert e instanceof BadParameterException;
            System.out.println("passed : " + 9);
        }

        // 10.
        try {
            AssertUtil.notNull(Collections.emptyList(), 500, ASSERT_EXCEPTION);
        } catch (Exception e) {
            assert e.getMessage().equals(ASSERT_EXCEPTION);
            assert e instanceof AssertException;
            System.out.println("passed : " + 10);
        }

        // 11.
        try {
            AssertUtil.notNull(Collections.emptyMap(), 500, ASSERT_EXCEPTION);
        } catch (Exception e) {
            assert e.getMessage().equals(ASSERT_EXCEPTION);
            assert e instanceof AssertException;
            System.out.println("passed : " + 11);
        }

        System.out.println("testing ended.");
    }

}

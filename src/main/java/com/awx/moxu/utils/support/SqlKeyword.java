package com.awx.moxu.utils.support;

import com.awx.moxu.utils.Func;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.Map;
public class SqlKeyword {
    private static final String SQL_REGEX = "'|%|--|insert|delete|select|count|group|union|drop|truncate|alter|grant|execute|exec|xp_cmdshell|call|declare|sql";
    private static final String EQUAL = "_equal";
    private static final String NOT_EQUAL = "_notequal";
    private static final String LIKE = "_like";
    private static final String NOT_LIKE = "_notlike";
    private static final String GT = "_gt";
    private static final String LT = "_lt";
    private static final String DATE_GT = "_dategt";
    private static final String DATE_EQUAL = "_dateequal";
    private static final String DATE_LT = "_datelt";
    private static final String IS_NULL = "_null";
    private static final String NOT_NULL = "_notnull";
    private static final String IGNORE = "_ignore";

    public SqlKeyword() {
    }

    public static void buildCondition(Map<String, Object> query, QueryWrapper<?> qw) {
        if (!Func.isEmpty(query)) {
            query.forEach((k, v) -> {
                if (!Func.hasEmpty(new Object[]{k, v}) && !k.endsWith("_ignore")) {
                    if (k.endsWith("_equal")) {
                        qw.eq(getColumn(k, "_equal"), v);
                    } else if (k.endsWith("_notequal")) {
                        qw.ne(getColumn(k, "_notequal"), v);
                    } else if (k.endsWith("_notlike")) {
                        qw.notLike(getColumn(k, "_notlike"), v);
                    } else if (k.endsWith("_gt")) {
                        qw.gt(getColumn(k, "_gt"), v);
                    } else if (k.endsWith("_lt")) {
                        qw.lt(getColumn(k, "_lt"), v);
                    } else if (k.endsWith("_dategt")) {
                        qw.gt(getColumn(k, "_dategt"), v);
                    } else if (k.endsWith("_dateequal")) {
                        qw.eq(getColumn(k, "_dateequal"), v);
                    } else if (k.endsWith("_datelt")) {
                        qw.lt(getColumn(k, "_datelt"), v);
                    } else if (k.endsWith("_null")) {
                        qw.isNull(getColumn(k, "_null"));
                    } else if (k.endsWith("_notnull")) {
                        qw.isNotNull(getColumn(k, "_notnull"));
                    } else {
                        qw.like(getColumn(k, "_like"), v);
                    }

                }
            });
        }
    }

    private static String getColumn(String column, String keyword) {
        return humpToUnderline(removeSuffix(column, keyword));
    }
    public static String humpToUnderline(String para) {
        para = lowerFirst(para);
        StringBuilder sb = new StringBuilder(para);
        int temp = 0;

        for(int i = 0; i < para.length(); ++i) {
            if (Character.isUpperCase(para.charAt(i))) {
                sb.insert(i + temp, "_");
                ++temp;
            }
        }

        return sb.toString().toLowerCase();
    }
    public static String lowerFirst(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] = (char)(arr[0] + 32);
            return new String(arr);
        } else {
            return str;
        }
    }
    public static String removeSuffix(CharSequence str, CharSequence suffix) {
        if (!Func.isEmpty(str) && !Func.isEmpty(suffix)) {
            String str2 = str.toString();
            return str2.endsWith(suffix.toString()) ? subPre(str2, str2.length() - suffix.length()) : str2;
        } else {
            return "";
        }
    }
    public static String subPre(CharSequence string, int toIndex) {
        return sub(string, 0, toIndex);
    }
    public static String sub(CharSequence str, int fromIndex, int toIndex) {
        if (Func.isEmpty(str)) {
            return "";
        } else {
            int len = str.length();
            if (fromIndex < 0) {
                fromIndex += len;
                if (fromIndex < 0) {
                    fromIndex = 0;
                }
            } else if (fromIndex > len) {
                fromIndex = len;
            }

            if (toIndex < 0) {
                toIndex += len;
                if (toIndex < 0) {
                    toIndex = len;
                }
            } else if (toIndex > len) {
                toIndex = len;
            }

            if (toIndex < fromIndex) {
                int tmp = fromIndex;
                fromIndex = toIndex;
                toIndex = tmp;
            }

            return fromIndex == toIndex ? "" : str.toString().substring(fromIndex, toIndex);
        }
    }

    public static String filter(String param) {
        return param == null ? null : param.replaceAll("(?i)'|%|--|insert|delete|select|count|group|union|drop|truncate|alter|grant|execute|exec|xp_cmdshell|call|declare|sql", "");
    }
}

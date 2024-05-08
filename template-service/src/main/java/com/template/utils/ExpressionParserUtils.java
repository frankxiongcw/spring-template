package com.template.utils;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 表达式解析工具类
 *
 * @author xiong.canwei
 * @version v1.0
 * @date 2020/2/18 21:28
 */
public final class ExpressionParserUtils {

    /**
     * SPEL解析器
     */
    private static final ExpressionParser SPEL_PARSER = new SpelExpressionParser();

    /**
     * 根据SPEL表达式，获取方法上的参数
     *
     * @param expression SPEL表达式
     * @param map        {@code <参数名,参数>}
     * @param targetType 转换类型,null为原始类型返回
     * @return {@code T} 获取的值
     */
    public static <T> T parseSpelExpression(String expression, Map<String, Object> map, Class<T> targetType) {
        if (StringUtils.isEmpty(expression)) {
            return null;
        }
        // SPEL上下文
        EvaluationContext context = new StandardEvaluationContext();
        // 把方法参数放入SPEL上下文中
        map.forEach(context::setVariable);
        return SPEL_PARSER.parseExpression(expression, new TemplateParserContext()).getValue(context, targetType);
    }

    /**
     * 获取方法参数名称
     *
     * @param method 方法对象
     * @return {@code String[]} 参数名称列表
     */
    public static String[] getMethodParameterNames(Method method) {
        // 获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        return u.getParameterNames(method);
    }
}

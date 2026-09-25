package com.example.gongdijigong.data;

/**
 * 精确金额计算工具。
 * 所有金额运算均用 [BigDecimal]（而非 float/double），并在运算后统一保留 2 位小数，
 * 从根本上避免“0.1+0.2=0.30000000000000004”这类浮点误差，保证工钱结算不出错。
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004J\u0016\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004J\u0010\u0010\u000b\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\bJ&\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004\u00a8\u0006\u0012"}, d2 = {"Lcom/example/gongdijigong/data/MoneyCalc;", "", "()V", "add", "Ljava/math/BigDecimal;", "a", "b", "fmt", "", "bd", "mul", "parse", "s", "recordAmount", "hours", "unitPrice", "overtimeHours", "overtimePrice", "app_debug"})
public final class MoneyCalc {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.gongdijigong.data.MoneyCalc INSTANCE = null;
    
    private MoneyCalc() {
        super();
    }
    
    /**
     * 乘法，保留 2 位小数，四舍五入
     */
    @org.jetbrains.annotations.NotNull()
    public final java.math.BigDecimal mul(@org.jetbrains.annotations.NotNull()
    java.math.BigDecimal a, @org.jetbrains.annotations.NotNull()
    java.math.BigDecimal b) {
        return null;
    }
    
    /**
     * 加法，保留 2 位小数
     */
    @org.jetbrains.annotations.NotNull()
    public final java.math.BigDecimal add(@org.jetbrains.annotations.NotNull()
    java.math.BigDecimal a, @org.jetbrains.annotations.NotNull()
    java.math.BigDecimal b) {
        return null;
    }
    
    /**
     * 记工金额 = 工时×工价 + 加班工时×加班工价
     */
    @org.jetbrains.annotations.NotNull()
    public final java.math.BigDecimal recordAmount(@org.jetbrains.annotations.NotNull()
    java.math.BigDecimal hours, @org.jetbrains.annotations.NotNull()
    java.math.BigDecimal unitPrice, @org.jetbrains.annotations.NotNull()
    java.math.BigDecimal overtimeHours, @org.jetbrains.annotations.NotNull()
    java.math.BigDecimal overtimePrice) {
        return null;
    }
    
    /**
     * 去掉多余的尾零，以普通字符串展示
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fmt(@org.jetbrains.annotations.NotNull()
    java.math.BigDecimal bd) {
        return null;
    }
    
    /**
     * 从字符串安全解析，失败返回 0
     */
    @org.jetbrains.annotations.NotNull()
    public final java.math.BigDecimal parse(@org.jetbrains.annotations.Nullable()
    java.lang.String s) {
        return null;
    }
}
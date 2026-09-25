package com.example.gongdijigong.data;

/**
 * Room 类型转换器。
 * 金额一律用 BigDecimal（字符串存储）并在计算时采用精确运算，避免 float/double 浮点误差 —— 这是“不能算错”的关键。
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\u0019\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\tH\u0007\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\rH\u0007\u00a2\u0006\u0002\u0010\u000eJ\u0019\u0010\u000f\u001a\u0004\u0018\u00010\r2\b\u0010\u0005\u001a\u0004\u0018\u00010\fH\u0007\u00a2\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007J\u0019\u0010\u0012\u001a\u0004\u0018\u00010\t2\b\u0010\u0005\u001a\u0004\u0018\u00010\bH\u0007\u00a2\u0006\u0002\u0010\u0013\u00a8\u0006\u0014"}, d2 = {"Lcom/example/gongdijigong/data/Converters;", "", "()V", "bigDecimalToString", "", "value", "Ljava/math/BigDecimal;", "intToWorkType", "Lcom/example/gongdijigong/data/WorkType;", "", "(Ljava/lang/Integer;)Lcom/example/gongdijigong/data/WorkType;", "localDateToLong", "", "Ljava/time/LocalDate;", "(Ljava/time/LocalDate;)Ljava/lang/Long;", "longToLocalDate", "(Ljava/lang/Long;)Ljava/time/LocalDate;", "stringToBigDecimal", "workTypeToInt", "(Lcom/example/gongdijigong/data/WorkType;)Ljava/lang/Integer;", "app_debug"})
public final class Converters {
    
    public Converters() {
        super();
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String bigDecimalToString(@org.jetbrains.annotations.Nullable()
    java.math.BigDecimal value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.math.BigDecimal stringToBigDecimal(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer workTypeToInt(@org.jetbrains.annotations.Nullable()
    com.example.gongdijigong.data.WorkType value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final com.example.gongdijigong.data.WorkType intToWorkType(@org.jetbrains.annotations.Nullable()
    java.lang.Integer value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long localDateToLong(@org.jetbrains.annotations.Nullable()
    java.time.LocalDate value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.time.LocalDate longToLocalDate(@org.jetbrains.annotations.Nullable()
    java.lang.Long value) {
        return null;
    }
}
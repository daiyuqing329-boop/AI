package com.example.gongdijigong.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u0017"}, d2 = {"Lcom/example/gongdijigong/data/WorkRecordDao;", "", "delete", "", "record", "Lcom/example/gongdijigong/data/WorkRecord;", "(Lcom/example/gongdijigong/data/WorkRecord;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllByProject", "projectId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "setSettled", "id", "settled", "", "(JZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sumAll", "Ljava/math/BigDecimal;", "sumUnsettled", "app_debug"})
@androidx.room.Dao()
public abstract interface WorkRecordDao {
    
    @androidx.room.Query(value = "SELECT * FROM work_record WHERE project_id = :projectId ORDER BY date DESC, id DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllByProject(long projectId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.gongdijigong.data.WorkRecord>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM work_record ORDER BY date DESC, id DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.gongdijigong.data.WorkRecord>> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.example.gongdijigong.data.WorkRecord record, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.example.gongdijigong.data.WorkRecord record, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE work_record SET settled = :settled WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setSettled(long id, boolean settled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * 某项目未结算的记工总额
     */
    @androidx.room.Query(value = "SELECT COALESCE(SUM(amount), 0) FROM work_record WHERE project_id = :projectId AND settled = 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sumUnsettled(long projectId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.math.BigDecimal> $completion);
    
    /**
     * 某项目记工总额
     */
    @androidx.room.Query(value = "SELECT COALESCE(SUM(amount), 0) FROM work_record WHERE project_id = :projectId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sumAll(long projectId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.math.BigDecimal> $completion);
}
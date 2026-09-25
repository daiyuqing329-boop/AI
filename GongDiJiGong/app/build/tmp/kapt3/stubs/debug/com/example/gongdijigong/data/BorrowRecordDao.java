package com.example.gongdijigong.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u0012"}, d2 = {"Lcom/example/gongdijigong/data/BorrowRecordDao;", "", "delete", "", "record", "Lcom/example/gongdijigong/data/BorrowRecord;", "(Lcom/example/gongdijigong/data/BorrowRecord;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllByProject", "projectId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "sumBorrow", "Ljava/math/BigDecimal;", "sumSettled", "app_debug"})
@androidx.room.Dao()
public abstract interface BorrowRecordDao {
    
    @androidx.room.Query(value = "SELECT * FROM borrow_record WHERE project_id = :projectId ORDER BY date DESC, id DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllByProject(long projectId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.gongdijigong.data.BorrowRecord>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM borrow_record ORDER BY date DESC, id DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.gongdijigong.data.BorrowRecord>> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.example.gongdijigong.data.BorrowRecord record, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.example.gongdijigong.data.BorrowRecord record, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * 借支合计(借支为正)
     */
    @androidx.room.Query(value = "SELECT COALESCE(SUM(amount), 0) FROM borrow_record WHERE project_id = :projectId AND kind = \'\u501f\u652f\'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sumBorrow(long projectId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.math.BigDecimal> $completion);
    
    /**
     * 已结算合计(结算为负)
     */
    @androidx.room.Query(value = "SELECT COALESCE(SUM(amount), 0) FROM borrow_record WHERE project_id = :projectId AND kind = \'\u7ed3\u7b97\'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sumSettled(long projectId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.math.BigDecimal> $completion);
}
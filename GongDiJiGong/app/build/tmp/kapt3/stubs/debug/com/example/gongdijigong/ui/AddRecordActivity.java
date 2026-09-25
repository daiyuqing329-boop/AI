package com.example.gongdijigong.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000fH\u0002J\u0010\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\fH\u0002J\u0012\u0010\u0019\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0015H\u0002J\b\u0010\u001f\u001a\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/example/gongdijigong/ui/AddRecordActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/gongdijigong/databinding/ActivityAddRecordBinding;", "db", "Lcom/example/gongdijigong/data/AppDatabase;", "getDb", "()Lcom/example/gongdijigong/data/AppDatabase;", "db$delegate", "Lkotlin/Lazy;", "hourPerWork", "Ljava/math/BigDecimal;", "projects", "", "Lcom/example/gongdijigong/data/Project;", "selectedDate", "Ljava/time/LocalDate;", "selectedProjectId", "", "applyTemplate", "", "p", "hourlyRate", "price", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "prefs", "Landroid/content/SharedPreferences;", "save", "updatePreview", "app_debug"})
public final class AddRecordActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.gongdijigong.databinding.ActivityAddRecordBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy db$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.gongdijigong.data.Project> projects = null;
    private long selectedProjectId = 0L;
    @org.jetbrains.annotations.NotNull()
    private java.math.BigDecimal hourPerWork;
    @org.jetbrains.annotations.NotNull()
    private java.time.LocalDate selectedDate;
    
    public AddRecordActivity() {
        super();
    }
    
    private final com.example.gongdijigong.data.AppDatabase getDb() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final android.content.SharedPreferences prefs() {
        return null;
    }
    
    /**
     * 使用工地模板：每工小时数 + 一个工的价，自动带出。
     */
    private final void applyTemplate(com.example.gongdijigong.data.Project p) {
    }
    
    /**
     * 小时工价 = 一个工的价 ÷ 每工小时数。
     */
    private final java.math.BigDecimal hourlyRate(java.math.BigDecimal price) {
        return null;
    }
    
    /**
     * 金额 = (正常小时 + 加班小时) × 小时工价。
     * 小时工价 = 工价 ÷ 每工小时数，没有倍率。
     */
    private final void updatePreview() {
    }
    
    private final void save() {
    }
}
package com.example.gongdijigong.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\"B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u0012\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\u0010\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/example/gongdijigong/ui/CalendarActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/gongdijigong/databinding/ActivityCalendarBinding;", "cells", "", "Ljava/time/LocalDate;", "dayAmount", "", "", "Ljava/math/BigDecimal;", "dayHours", "dayInfo", "", "", "Lcom/example/gongdijigong/data/WorkRecord;", "db", "Lcom/example/gongdijigong/data/AppDatabase;", "getDb", "()Lcom/example/gongdijigong/data/AppDatabase;", "db$delegate", "Lkotlin/Lazy;", "month", "Ljava/time/YearMonth;", "recordsByDay", "today", "load", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "showDayDetail", "position", "GridAdapter", "app_debug"})
public final class CalendarActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.gongdijigong.databinding.ActivityCalendarBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy db$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private java.time.YearMonth month;
    @org.jetbrains.annotations.NotNull()
    private java.time.LocalDate today;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.time.LocalDate> cells = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.Long, java.math.BigDecimal> dayAmount = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.Long, java.math.BigDecimal> dayHours = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.Long, java.util.List<com.example.gongdijigong.data.WorkRecord>> recordsByDay = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.Integer, java.util.List<com.example.gongdijigong.data.WorkRecord>> dayInfo = null;
    
    public CalendarActivity() {
        super();
    }
    
    private final com.example.gongdijigong.data.AppDatabase getDb() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void load() {
    }
    
    private final void showDayDetail(int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u0004H\u0016J\"\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016\u00a8\u0006\u000f"}, d2 = {"Lcom/example/gongdijigong/ui/CalendarActivity$GridAdapter;", "Landroid/widget/BaseAdapter;", "(Lcom/example/gongdijigong/ui/CalendarActivity;)V", "getCount", "", "getItem", "Ljava/time/LocalDate;", "pos", "getItemId", "", "getView", "Landroid/view/View;", "convertView", "parent", "Landroid/view/ViewGroup;", "app_debug"})
    public final class GridAdapter extends android.widget.BaseAdapter {
        
        public GridAdapter() {
            super();
        }
        
        @java.lang.Override()
        public int getCount() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.Nullable()
        public java.time.LocalDate getItem(int pos) {
            return null;
        }
        
        @java.lang.Override()
        public long getItemId(int pos) {
            return 0L;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public android.view.View getView(int pos, @org.jetbrains.annotations.Nullable()
        android.view.View convertView, @org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent) {
            return null;
        }
    }
}
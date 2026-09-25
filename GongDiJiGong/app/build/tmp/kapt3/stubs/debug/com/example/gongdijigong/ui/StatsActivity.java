package com.example.gongdijigong.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u0019\u001aB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0012\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0016\u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0006H\u0002J\u0016\u0010\u0016\u001a\u00020\u000f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u001b"}, d2 = {"Lcom/example/gongdijigong/ui/StatsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/gongdijigong/databinding/ActivityStatsBinding;", "colors", "", "", "db", "Lcom/example/gongdijigong/data/AppDatabase;", "getDb", "()Lcom/example/gongdijigong/data/AppDatabase;", "db$delegate", "Lkotlin/Lazy;", "load", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupBar", "allRecords", "Lcom/example/gongdijigong/data/WorkRecord;", "setupPie", "entries", "Lcom/github/mikephil/charting/data/PieEntry;", "Adapter", "VH", "app_debug"})
public final class StatsActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.gongdijigong.databinding.ActivityStatsBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy db$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> colors = null;
    
    public StatsActivity() {
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
    
    private final void setupPie(java.util.List<? extends com.github.mikephil.charting.data.PieEntry> entries) {
    }
    
    private final void setupBar(java.util.List<com.example.gongdijigong.data.WorkRecord> allRecords) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\b\u0086\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016J\u001c\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\u0002R\u00020\u00032\u0006\u0010\r\u001a\u00020\tH\u0016J\u001c\u0010\u000e\u001a\u00060\u0002R\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\tH\u0016J\u0014\u0010\u0012\u001a\u00020\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/example/gongdijigong/ui/StatsActivity$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/gongdijigong/ui/StatsActivity$VH;", "Lcom/example/gongdijigong/ui/StatsActivity;", "(Lcom/example/gongdijigong/ui/StatsActivity;)V", "items", "", "Lcom/example/gongdijigong/ui/StatRow;", "getItemCount", "", "onBindViewHolder", "", "h", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "submit", "list", "", "app_debug"})
    public final class Adapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.gongdijigong.ui.StatsActivity.VH> {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.example.gongdijigong.ui.StatRow> items = null;
        
        public Adapter() {
            super();
        }
        
        public final void submit(@org.jetbrains.annotations.NotNull()
        java.util.List<com.example.gongdijigong.ui.StatRow> list) {
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public com.example.gongdijigong.ui.StatsActivity.VH onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.example.gongdijigong.ui.StatsActivity.VH h, int position) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/gongdijigong/ui/StatsActivity$VH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "b", "Lcom/example/gongdijigong/databinding/ItemStatBinding;", "(Lcom/example/gongdijigong/ui/StatsActivity;Lcom/example/gongdijigong/databinding/ItemStatBinding;)V", "getB", "()Lcom/example/gongdijigong/databinding/ItemStatBinding;", "app_debug"})
    public final class VH extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.example.gongdijigong.databinding.ItemStatBinding b = null;
        
        public VH(@org.jetbrains.annotations.NotNull()
        com.example.gongdijigong.databinding.ItemStatBinding b) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.gongdijigong.databinding.ItemStatBinding getB() {
            return null;
        }
    }
}
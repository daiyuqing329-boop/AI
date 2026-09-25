package com.example.gongdijigong.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0011\u0012B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\b\u0010\u0010\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u0013"}, d2 = {"Lcom/example/gongdijigong/ui/ProjectsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/gongdijigong/databinding/ActivityProjectsBinding;", "db", "Lcom/example/gongdijigong/data/AppDatabase;", "getDb", "()Lcom/example/gongdijigong/data/AppDatabase;", "db$delegate", "Lkotlin/Lazy;", "load", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "showAddDialog", "Adapter", "VH", "app_debug"})
public final class ProjectsActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.gongdijigong.databinding.ActivityProjectsBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy db$delegate = null;
    
    public ProjectsActivity() {
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
    
    private final void showAddDialog() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\b\u0086\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016J\u001c\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\u0002R\u00020\u00032\u0006\u0010\r\u001a\u00020\tH\u0016J\u001c\u0010\u000e\u001a\u00060\u0002R\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\tH\u0016J\u0014\u0010\u0012\u001a\u00020\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/example/gongdijigong/ui/ProjectsActivity$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/gongdijigong/ui/ProjectsActivity$VH;", "Lcom/example/gongdijigong/ui/ProjectsActivity;", "(Lcom/example/gongdijigong/ui/ProjectsActivity;)V", "items", "", "Lcom/example/gongdijigong/data/Project;", "getItemCount", "", "onBindViewHolder", "", "h", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "submit", "list", "", "app_debug"})
    public final class Adapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.gongdijigong.ui.ProjectsActivity.VH> {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.example.gongdijigong.data.Project> items = null;
        
        public Adapter() {
            super();
        }
        
        public final void submit(@org.jetbrains.annotations.NotNull()
        java.util.List<com.example.gongdijigong.data.Project> list) {
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public com.example.gongdijigong.ui.ProjectsActivity.VH onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.example.gongdijigong.ui.ProjectsActivity.VH h, int position) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/gongdijigong/ui/ProjectsActivity$VH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "b", "Lcom/example/gongdijigong/databinding/ItemProjectBinding;", "(Lcom/example/gongdijigong/ui/ProjectsActivity;Lcom/example/gongdijigong/databinding/ItemProjectBinding;)V", "getB", "()Lcom/example/gongdijigong/databinding/ItemProjectBinding;", "app_debug"})
    public final class VH extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.example.gongdijigong.databinding.ItemProjectBinding b = null;
        
        public VH(@org.jetbrains.annotations.NotNull()
        com.example.gongdijigong.databinding.ItemProjectBinding b) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.gongdijigong.databinding.ItemProjectBinding getB() {
            return null;
        }
    }
}
package com.example.gongdijigong.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile ProjectDao _projectDao;

  private volatile WorkRecordDao _workRecordDao;

  private volatile BorrowRecordDao _borrowRecordDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `project` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `boss` TEXT NOT NULL, `work_type` INTEGER NOT NULL, `unit_price` TEXT NOT NULL, `overtime_price` TEXT NOT NULL, `note` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `work_record` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `project_id` INTEGER NOT NULL, `date` INTEGER NOT NULL, `work_type` INTEGER NOT NULL, `hours` TEXT NOT NULL, `unit_price` TEXT NOT NULL, `amount` TEXT NOT NULL, `overtime_hours` TEXT NOT NULL, `overtime_price` TEXT NOT NULL, `settled` INTEGER NOT NULL, `note` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `borrow_record` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `project_id` INTEGER NOT NULL, `date` INTEGER NOT NULL, `amount` TEXT NOT NULL, `kind` TEXT NOT NULL, `note` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '3cfee2c66da3caea91c5b42e9ea1a563')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `project`");
        db.execSQL("DROP TABLE IF EXISTS `work_record`");
        db.execSQL("DROP TABLE IF EXISTS `borrow_record`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsProject = new HashMap<String, TableInfo.Column>(7);
        _columnsProject.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProject.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProject.put("boss", new TableInfo.Column("boss", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProject.put("work_type", new TableInfo.Column("work_type", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProject.put("unit_price", new TableInfo.Column("unit_price", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProject.put("overtime_price", new TableInfo.Column("overtime_price", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProject.put("note", new TableInfo.Column("note", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProject = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProject = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProject = new TableInfo("project", _columnsProject, _foreignKeysProject, _indicesProject);
        final TableInfo _existingProject = TableInfo.read(db, "project");
        if (!_infoProject.equals(_existingProject)) {
          return new RoomOpenHelper.ValidationResult(false, "project(com.example.gongdijigong.data.Project).\n"
                  + " Expected:\n" + _infoProject + "\n"
                  + " Found:\n" + _existingProject);
        }
        final HashMap<String, TableInfo.Column> _columnsWorkRecord = new HashMap<String, TableInfo.Column>(11);
        _columnsWorkRecord.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkRecord.put("project_id", new TableInfo.Column("project_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkRecord.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkRecord.put("work_type", new TableInfo.Column("work_type", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkRecord.put("hours", new TableInfo.Column("hours", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkRecord.put("unit_price", new TableInfo.Column("unit_price", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkRecord.put("amount", new TableInfo.Column("amount", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkRecord.put("overtime_hours", new TableInfo.Column("overtime_hours", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkRecord.put("overtime_price", new TableInfo.Column("overtime_price", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkRecord.put("settled", new TableInfo.Column("settled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkRecord.put("note", new TableInfo.Column("note", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWorkRecord = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWorkRecord = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWorkRecord = new TableInfo("work_record", _columnsWorkRecord, _foreignKeysWorkRecord, _indicesWorkRecord);
        final TableInfo _existingWorkRecord = TableInfo.read(db, "work_record");
        if (!_infoWorkRecord.equals(_existingWorkRecord)) {
          return new RoomOpenHelper.ValidationResult(false, "work_record(com.example.gongdijigong.data.WorkRecord).\n"
                  + " Expected:\n" + _infoWorkRecord + "\n"
                  + " Found:\n" + _existingWorkRecord);
        }
        final HashMap<String, TableInfo.Column> _columnsBorrowRecord = new HashMap<String, TableInfo.Column>(6);
        _columnsBorrowRecord.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBorrowRecord.put("project_id", new TableInfo.Column("project_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBorrowRecord.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBorrowRecord.put("amount", new TableInfo.Column("amount", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBorrowRecord.put("kind", new TableInfo.Column("kind", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBorrowRecord.put("note", new TableInfo.Column("note", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBorrowRecord = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBorrowRecord = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBorrowRecord = new TableInfo("borrow_record", _columnsBorrowRecord, _foreignKeysBorrowRecord, _indicesBorrowRecord);
        final TableInfo _existingBorrowRecord = TableInfo.read(db, "borrow_record");
        if (!_infoBorrowRecord.equals(_existingBorrowRecord)) {
          return new RoomOpenHelper.ValidationResult(false, "borrow_record(com.example.gongdijigong.data.BorrowRecord).\n"
                  + " Expected:\n" + _infoBorrowRecord + "\n"
                  + " Found:\n" + _existingBorrowRecord);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "3cfee2c66da3caea91c5b42e9ea1a563", "a7959b893b86a0a35bc11609f0caa601");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "project","work_record","borrow_record");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `project`");
      _db.execSQL("DELETE FROM `work_record`");
      _db.execSQL("DELETE FROM `borrow_record`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ProjectDao.class, ProjectDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(WorkRecordDao.class, WorkRecordDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(BorrowRecordDao.class, BorrowRecordDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public ProjectDao projectDao() {
    if (_projectDao != null) {
      return _projectDao;
    } else {
      synchronized(this) {
        if(_projectDao == null) {
          _projectDao = new ProjectDao_Impl(this);
        }
        return _projectDao;
      }
    }
  }

  @Override
  public WorkRecordDao workRecordDao() {
    if (_workRecordDao != null) {
      return _workRecordDao;
    } else {
      synchronized(this) {
        if(_workRecordDao == null) {
          _workRecordDao = new WorkRecordDao_Impl(this);
        }
        return _workRecordDao;
      }
    }
  }

  @Override
  public BorrowRecordDao borrowRecordDao() {
    if (_borrowRecordDao != null) {
      return _borrowRecordDao;
    } else {
      synchronized(this) {
        if(_borrowRecordDao == null) {
          _borrowRecordDao = new BorrowRecordDao_Impl(this);
        }
        return _borrowRecordDao;
      }
    }
  }
}

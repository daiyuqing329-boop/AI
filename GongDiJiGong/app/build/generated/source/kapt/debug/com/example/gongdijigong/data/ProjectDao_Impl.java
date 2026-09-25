package com.example.gongdijigong.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ProjectDao_Impl implements ProjectDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Project> __insertionAdapterOfProject;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<Project> __deletionAdapterOfProject;

  private final SharedSQLiteStatement __preparedStmtOfUpdate;

  public ProjectDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProject = new EntityInsertionAdapter<Project>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `project` (`id`,`name`,`boss`,`work_type`,`unit_price`,`overtime_price`,`hour_per_work`,`note`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Project entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getBoss() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getBoss());
        }
        final Integer _tmp = __converters.workTypeToInt(entity.getWorkType());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, _tmp);
        }
        final String _tmp_1 = __converters.bigDecimalToString(entity.getUnitPrice());
        if (_tmp_1 == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmp_1);
        }
        final String _tmp_2 = __converters.bigDecimalToString(entity.getOvertimePrice());
        if (_tmp_2 == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp_2);
        }
        final String _tmp_3 = __converters.bigDecimalToString(entity.getHourPerWork());
        if (_tmp_3 == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp_3);
        }
        if (entity.getNote() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getNote());
        }
      }
    };
    this.__deletionAdapterOfProject = new EntityDeletionOrUpdateAdapter<Project>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `project` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Project entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__preparedStmtOfUpdate = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE project SET name = ?, boss = ?, work_type = ?, unit_price = ?, overtime_price = ?, hour_per_work = ?, note = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final Project project, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfProject.insertAndReturnId(project);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final Project project, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfProject.handle(project);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final long id, final String name, final String boss, final WorkType workType,
      final BigDecimal unitPrice, final BigDecimal overtimePrice, final BigDecimal hourPerWork,
      final String note, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdate.acquire();
        int _argIndex = 1;
        if (name == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, name);
        }
        _argIndex = 2;
        if (boss == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, boss);
        }
        _argIndex = 3;
        final Integer _tmp = __converters.workTypeToInt(workType);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindLong(_argIndex, _tmp);
        }
        _argIndex = 4;
        final String _tmp_1 = __converters.bigDecimalToString(unitPrice);
        if (_tmp_1 == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, _tmp_1);
        }
        _argIndex = 5;
        final String _tmp_2 = __converters.bigDecimalToString(overtimePrice);
        if (_tmp_2 == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, _tmp_2);
        }
        _argIndex = 6;
        final String _tmp_3 = __converters.bigDecimalToString(hourPerWork);
        if (_tmp_3 == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, _tmp_3);
        }
        _argIndex = 7;
        if (note == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, note);
        }
        _argIndex = 8;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdate.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getAll(final Continuation<? super List<Project>> $completion) {
    final String _sql = "SELECT * FROM project ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Project>>() {
      @Override
      @NonNull
      public List<Project> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfBoss = CursorUtil.getColumnIndexOrThrow(_cursor, "boss");
          final int _cursorIndexOfWorkType = CursorUtil.getColumnIndexOrThrow(_cursor, "work_type");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unit_price");
          final int _cursorIndexOfOvertimePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "overtime_price");
          final int _cursorIndexOfHourPerWork = CursorUtil.getColumnIndexOrThrow(_cursor, "hour_per_work");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final List<Project> _result = new ArrayList<Project>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Project _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpBoss;
            if (_cursor.isNull(_cursorIndexOfBoss)) {
              _tmpBoss = null;
            } else {
              _tmpBoss = _cursor.getString(_cursorIndexOfBoss);
            }
            final WorkType _tmpWorkType;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfWorkType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfWorkType);
            }
            _tmpWorkType = __converters.intToWorkType(_tmp);
            final BigDecimal _tmpUnitPrice;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfUnitPrice)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfUnitPrice);
            }
            _tmpUnitPrice = __converters.stringToBigDecimal(_tmp_1);
            final BigDecimal _tmpOvertimePrice;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfOvertimePrice)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfOvertimePrice);
            }
            _tmpOvertimePrice = __converters.stringToBigDecimal(_tmp_2);
            final BigDecimal _tmpHourPerWork;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfHourPerWork)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfHourPerWork);
            }
            _tmpHourPerWork = __converters.stringToBigDecimal(_tmp_3);
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            _item = new Project(_tmpId,_tmpName,_tmpBoss,_tmpWorkType,_tmpUnitPrice,_tmpOvertimePrice,_tmpHourPerWork,_tmpNote);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getById(final long id, final Continuation<? super Project> $completion) {
    final String _sql = "SELECT * FROM project WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Project>() {
      @Override
      @Nullable
      public Project call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfBoss = CursorUtil.getColumnIndexOrThrow(_cursor, "boss");
          final int _cursorIndexOfWorkType = CursorUtil.getColumnIndexOrThrow(_cursor, "work_type");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unit_price");
          final int _cursorIndexOfOvertimePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "overtime_price");
          final int _cursorIndexOfHourPerWork = CursorUtil.getColumnIndexOrThrow(_cursor, "hour_per_work");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final Project _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpBoss;
            if (_cursor.isNull(_cursorIndexOfBoss)) {
              _tmpBoss = null;
            } else {
              _tmpBoss = _cursor.getString(_cursorIndexOfBoss);
            }
            final WorkType _tmpWorkType;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfWorkType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfWorkType);
            }
            _tmpWorkType = __converters.intToWorkType(_tmp);
            final BigDecimal _tmpUnitPrice;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfUnitPrice)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfUnitPrice);
            }
            _tmpUnitPrice = __converters.stringToBigDecimal(_tmp_1);
            final BigDecimal _tmpOvertimePrice;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfOvertimePrice)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfOvertimePrice);
            }
            _tmpOvertimePrice = __converters.stringToBigDecimal(_tmp_2);
            final BigDecimal _tmpHourPerWork;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfHourPerWork)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfHourPerWork);
            }
            _tmpHourPerWork = __converters.stringToBigDecimal(_tmp_3);
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            _result = new Project(_tmpId,_tmpName,_tmpBoss,_tmpWorkType,_tmpUnitPrice,_tmpOvertimePrice,_tmpHourPerWork,_tmpNote);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

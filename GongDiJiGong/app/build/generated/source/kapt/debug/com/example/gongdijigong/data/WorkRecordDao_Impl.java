package com.example.gongdijigong.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
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
public final class WorkRecordDao_Impl implements WorkRecordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WorkRecord> __insertionAdapterOfWorkRecord;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<WorkRecord> __deletionAdapterOfWorkRecord;

  private final SharedSQLiteStatement __preparedStmtOfSetSettled;

  public WorkRecordDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWorkRecord = new EntityInsertionAdapter<WorkRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `work_record` (`id`,`project_id`,`date`,`work_type`,`hours`,`unit_price`,`amount`,`overtime_hours`,`overtime_price`,`settled`,`note`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WorkRecord entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getProjectId());
        statement.bindLong(3, entity.getDate());
        final Integer _tmp = __converters.workTypeToInt(entity.getWorkType());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, _tmp);
        }
        final String _tmp_1 = __converters.bigDecimalToString(entity.getHours());
        if (_tmp_1 == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmp_1);
        }
        final String _tmp_2 = __converters.bigDecimalToString(entity.getUnitPrice());
        if (_tmp_2 == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp_2);
        }
        final String _tmp_3 = __converters.bigDecimalToString(entity.getAmount());
        if (_tmp_3 == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp_3);
        }
        final String _tmp_4 = __converters.bigDecimalToString(entity.getOvertimeHours());
        if (_tmp_4 == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, _tmp_4);
        }
        final String _tmp_5 = __converters.bigDecimalToString(entity.getOvertimePrice());
        if (_tmp_5 == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, _tmp_5);
        }
        final int _tmp_6 = entity.getSettled() ? 1 : 0;
        statement.bindLong(10, _tmp_6);
        if (entity.getNote() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getNote());
        }
      }
    };
    this.__deletionAdapterOfWorkRecord = new EntityDeletionOrUpdateAdapter<WorkRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `work_record` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WorkRecord entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__preparedStmtOfSetSettled = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE work_record SET settled = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final WorkRecord record, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfWorkRecord.insertAndReturnId(record);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final WorkRecord record, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfWorkRecord.handle(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object setSettled(final long id, final boolean settled,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfSetSettled.acquire();
        int _argIndex = 1;
        final int _tmp = settled ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
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
          __preparedStmtOfSetSettled.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllByProject(final long projectId,
      final Continuation<? super List<WorkRecord>> $completion) {
    final String _sql = "SELECT * FROM work_record WHERE project_id = ? ORDER BY date DESC, id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, projectId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<WorkRecord>>() {
      @Override
      @NonNull
      public List<WorkRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProjectId = CursorUtil.getColumnIndexOrThrow(_cursor, "project_id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfWorkType = CursorUtil.getColumnIndexOrThrow(_cursor, "work_type");
          final int _cursorIndexOfHours = CursorUtil.getColumnIndexOrThrow(_cursor, "hours");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unit_price");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfOvertimeHours = CursorUtil.getColumnIndexOrThrow(_cursor, "overtime_hours");
          final int _cursorIndexOfOvertimePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "overtime_price");
          final int _cursorIndexOfSettled = CursorUtil.getColumnIndexOrThrow(_cursor, "settled");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final List<WorkRecord> _result = new ArrayList<WorkRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WorkRecord _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpProjectId;
            _tmpProjectId = _cursor.getLong(_cursorIndexOfProjectId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final WorkType _tmpWorkType;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfWorkType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfWorkType);
            }
            _tmpWorkType = __converters.intToWorkType(_tmp);
            final BigDecimal _tmpHours;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfHours)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfHours);
            }
            _tmpHours = __converters.stringToBigDecimal(_tmp_1);
            final BigDecimal _tmpUnitPrice;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfUnitPrice)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfUnitPrice);
            }
            _tmpUnitPrice = __converters.stringToBigDecimal(_tmp_2);
            final BigDecimal _tmpAmount;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfAmount);
            }
            _tmpAmount = __converters.stringToBigDecimal(_tmp_3);
            final BigDecimal _tmpOvertimeHours;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfOvertimeHours)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfOvertimeHours);
            }
            _tmpOvertimeHours = __converters.stringToBigDecimal(_tmp_4);
            final BigDecimal _tmpOvertimePrice;
            final String _tmp_5;
            if (_cursor.isNull(_cursorIndexOfOvertimePrice)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getString(_cursorIndexOfOvertimePrice);
            }
            _tmpOvertimePrice = __converters.stringToBigDecimal(_tmp_5);
            final boolean _tmpSettled;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfSettled);
            _tmpSettled = _tmp_6 != 0;
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            _item = new WorkRecord(_tmpId,_tmpProjectId,_tmpDate,_tmpWorkType,_tmpHours,_tmpUnitPrice,_tmpAmount,_tmpOvertimeHours,_tmpOvertimePrice,_tmpSettled,_tmpNote);
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
  public Object getAll(final Continuation<? super List<WorkRecord>> $completion) {
    final String _sql = "SELECT * FROM work_record ORDER BY date DESC, id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<WorkRecord>>() {
      @Override
      @NonNull
      public List<WorkRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProjectId = CursorUtil.getColumnIndexOrThrow(_cursor, "project_id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfWorkType = CursorUtil.getColumnIndexOrThrow(_cursor, "work_type");
          final int _cursorIndexOfHours = CursorUtil.getColumnIndexOrThrow(_cursor, "hours");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unit_price");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfOvertimeHours = CursorUtil.getColumnIndexOrThrow(_cursor, "overtime_hours");
          final int _cursorIndexOfOvertimePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "overtime_price");
          final int _cursorIndexOfSettled = CursorUtil.getColumnIndexOrThrow(_cursor, "settled");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final List<WorkRecord> _result = new ArrayList<WorkRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WorkRecord _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpProjectId;
            _tmpProjectId = _cursor.getLong(_cursorIndexOfProjectId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final WorkType _tmpWorkType;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfWorkType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfWorkType);
            }
            _tmpWorkType = __converters.intToWorkType(_tmp);
            final BigDecimal _tmpHours;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfHours)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfHours);
            }
            _tmpHours = __converters.stringToBigDecimal(_tmp_1);
            final BigDecimal _tmpUnitPrice;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfUnitPrice)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfUnitPrice);
            }
            _tmpUnitPrice = __converters.stringToBigDecimal(_tmp_2);
            final BigDecimal _tmpAmount;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfAmount);
            }
            _tmpAmount = __converters.stringToBigDecimal(_tmp_3);
            final BigDecimal _tmpOvertimeHours;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfOvertimeHours)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfOvertimeHours);
            }
            _tmpOvertimeHours = __converters.stringToBigDecimal(_tmp_4);
            final BigDecimal _tmpOvertimePrice;
            final String _tmp_5;
            if (_cursor.isNull(_cursorIndexOfOvertimePrice)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getString(_cursorIndexOfOvertimePrice);
            }
            _tmpOvertimePrice = __converters.stringToBigDecimal(_tmp_5);
            final boolean _tmpSettled;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfSettled);
            _tmpSettled = _tmp_6 != 0;
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            _item = new WorkRecord(_tmpId,_tmpProjectId,_tmpDate,_tmpWorkType,_tmpHours,_tmpUnitPrice,_tmpAmount,_tmpOvertimeHours,_tmpOvertimePrice,_tmpSettled,_tmpNote);
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
  public Object sumUnsettled(final long projectId,
      final Continuation<? super BigDecimal> $completion) {
    final String _sql = "SELECT COALESCE(SUM(amount), 0) FROM work_record WHERE project_id = ? AND settled = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, projectId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<BigDecimal>() {
      @Override
      @NonNull
      public BigDecimal call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final BigDecimal _result;
          if (_cursor.moveToFirst()) {
            final String _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(0);
            }
            _result = __converters.stringToBigDecimal(_tmp);
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

  @Override
  public Object sumAll(final long projectId, final Continuation<? super BigDecimal> $completion) {
    final String _sql = "SELECT COALESCE(SUM(amount), 0) FROM work_record WHERE project_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, projectId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<BigDecimal>() {
      @Override
      @NonNull
      public BigDecimal call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final BigDecimal _result;
          if (_cursor.moveToFirst()) {
            final String _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(0);
            }
            _result = __converters.stringToBigDecimal(_tmp);
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

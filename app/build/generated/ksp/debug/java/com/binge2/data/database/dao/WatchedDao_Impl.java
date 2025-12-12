package com.binge2.data.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.binge2.data.database.entities.WatchedEntity;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class WatchedDao_Impl implements WatchedDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WatchedEntity> __insertionAdapterOfWatchedEntity;

  private final SharedSQLiteStatement __preparedStmtOfMarkAsUnwatched;

  private final SharedSQLiteStatement __preparedStmtOfMarkEpisodeAsUnwatched;

  public WatchedDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWatchedEntity = new EntityInsertionAdapter<WatchedEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `watched` (`id`,`userId`,`contentId`,`contentType`,`episodeId`,`seasonNumber`,`episodeNumber`,`watchedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WatchedEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getUserId());
        statement.bindLong(3, entity.getContentId());
        statement.bindString(4, entity.getContentType());
        if (entity.getEpisodeId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getEpisodeId());
        }
        if (entity.getSeasonNumber() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getSeasonNumber());
        }
        if (entity.getEpisodeNumber() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getEpisodeNumber());
        }
        statement.bindLong(8, entity.getWatchedAt());
      }
    };
    this.__preparedStmtOfMarkAsUnwatched = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM watched WHERE userId = ? AND contentId = ? AND contentType = ?";
        return _query;
      }
    };
    this.__preparedStmtOfMarkEpisodeAsUnwatched = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM watched WHERE userId = ? AND episodeId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object markAsWatched(final WatchedEntity item,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWatchedEntity.insert(item);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object markAsUnwatched(final long userId, final int contentId, final String contentType,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAsUnwatched.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, userId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, contentId);
        _argIndex = 3;
        _stmt.bindString(_argIndex, contentType);
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
          __preparedStmtOfMarkAsUnwatched.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object markEpisodeAsUnwatched(final long userId, final int episodeId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkEpisodeAsUnwatched.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, userId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, episodeId);
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
          __preparedStmtOfMarkEpisodeAsUnwatched.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<WatchedEntity>> getWatchedForUser(final long userId) {
    final String _sql = "SELECT * FROM watched WHERE userId = ? ORDER BY watchedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"watched"}, new Callable<List<WatchedEntity>>() {
      @Override
      @NonNull
      public List<WatchedEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfContentId = CursorUtil.getColumnIndexOrThrow(_cursor, "contentId");
          final int _cursorIndexOfContentType = CursorUtil.getColumnIndexOrThrow(_cursor, "contentType");
          final int _cursorIndexOfEpisodeId = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeId");
          final int _cursorIndexOfSeasonNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "seasonNumber");
          final int _cursorIndexOfEpisodeNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeNumber");
          final int _cursorIndexOfWatchedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "watchedAt");
          final List<WatchedEntity> _result = new ArrayList<WatchedEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WatchedEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpUserId;
            _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
            final int _tmpContentId;
            _tmpContentId = _cursor.getInt(_cursorIndexOfContentId);
            final String _tmpContentType;
            _tmpContentType = _cursor.getString(_cursorIndexOfContentType);
            final Integer _tmpEpisodeId;
            if (_cursor.isNull(_cursorIndexOfEpisodeId)) {
              _tmpEpisodeId = null;
            } else {
              _tmpEpisodeId = _cursor.getInt(_cursorIndexOfEpisodeId);
            }
            final Integer _tmpSeasonNumber;
            if (_cursor.isNull(_cursorIndexOfSeasonNumber)) {
              _tmpSeasonNumber = null;
            } else {
              _tmpSeasonNumber = _cursor.getInt(_cursorIndexOfSeasonNumber);
            }
            final Integer _tmpEpisodeNumber;
            if (_cursor.isNull(_cursorIndexOfEpisodeNumber)) {
              _tmpEpisodeNumber = null;
            } else {
              _tmpEpisodeNumber = _cursor.getInt(_cursorIndexOfEpisodeNumber);
            }
            final long _tmpWatchedAt;
            _tmpWatchedAt = _cursor.getLong(_cursorIndexOfWatchedAt);
            _item = new WatchedEntity(_tmpId,_tmpUserId,_tmpContentId,_tmpContentType,_tmpEpisodeId,_tmpSeasonNumber,_tmpEpisodeNumber,_tmpWatchedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object isMovieWatched(final long userId, final int contentId,
      final Continuation<? super Boolean> $completion) {
    final String _sql = "SELECT EXISTS(SELECT 1 FROM watched WHERE userId = ? AND contentId = ? AND contentType = 'movie')";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, contentId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Boolean>() {
      @Override
      @NonNull
      public Boolean call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Boolean _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp != 0;
          } else {
            _result = false;
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
  public Object isEpisodeWatched(final long userId, final int episodeId,
      final Continuation<? super Boolean> $completion) {
    final String _sql = "SELECT EXISTS(SELECT 1 FROM watched WHERE userId = ? AND episodeId = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, episodeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Boolean>() {
      @Override
      @NonNull
      public Boolean call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Boolean _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp != 0;
          } else {
            _result = false;
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

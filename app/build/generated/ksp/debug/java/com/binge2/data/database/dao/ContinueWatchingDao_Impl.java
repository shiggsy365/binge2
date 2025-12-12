package com.binge2.data.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.binge2.data.database.entities.ContinueWatchingEntity;
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
public final class ContinueWatchingDao_Impl implements ContinueWatchingDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ContinueWatchingEntity> __insertionAdapterOfContinueWatchingEntity;

  private final SharedSQLiteStatement __preparedStmtOfRemoveFromContinueWatching;

  private final SharedSQLiteStatement __preparedStmtOfCleanupFinishedItems;

  public ContinueWatchingDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfContinueWatchingEntity = new EntityInsertionAdapter<ContinueWatchingEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `continue_watching` (`id`,`userId`,`contentId`,`contentType`,`title`,`posterPath`,`backdropPath`,`episodeId`,`seasonNumber`,`episodeNumber`,`episodeTitle`,`position`,`duration`,`lastWatched`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ContinueWatchingEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getUserId());
        statement.bindLong(3, entity.getContentId());
        statement.bindString(4, entity.getContentType());
        statement.bindString(5, entity.getTitle());
        if (entity.getPosterPath() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getPosterPath());
        }
        if (entity.getBackdropPath() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getBackdropPath());
        }
        if (entity.getEpisodeId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getEpisodeId());
        }
        if (entity.getSeasonNumber() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getSeasonNumber());
        }
        if (entity.getEpisodeNumber() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getEpisodeNumber());
        }
        if (entity.getEpisodeTitle() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getEpisodeTitle());
        }
        statement.bindLong(12, entity.getPosition());
        statement.bindLong(13, entity.getDuration());
        statement.bindLong(14, entity.getLastWatched());
      }
    };
    this.__preparedStmtOfRemoveFromContinueWatching = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM continue_watching WHERE userId = ? AND contentId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfCleanupFinishedItems = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM continue_watching WHERE userId = ? AND (position * 100 / duration) >= 90";
        return _query;
      }
    };
  }

  @Override
  public Object updateProgress(final ContinueWatchingEntity item,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfContinueWatchingEntity.insert(item);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object removeFromContinueWatching(final long userId, final int contentId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveFromContinueWatching.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, userId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, contentId);
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
          __preparedStmtOfRemoveFromContinueWatching.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object cleanupFinishedItems(final long userId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfCleanupFinishedItems.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, userId);
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
          __preparedStmtOfCleanupFinishedItems.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ContinueWatchingEntity>> getContinueWatchingForUser(final long userId) {
    final String _sql = "SELECT * FROM continue_watching WHERE userId = ? ORDER BY lastWatched DESC LIMIT 20";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"continue_watching"}, new Callable<List<ContinueWatchingEntity>>() {
      @Override
      @NonNull
      public List<ContinueWatchingEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfContentId = CursorUtil.getColumnIndexOrThrow(_cursor, "contentId");
          final int _cursorIndexOfContentType = CursorUtil.getColumnIndexOrThrow(_cursor, "contentType");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "posterPath");
          final int _cursorIndexOfBackdropPath = CursorUtil.getColumnIndexOrThrow(_cursor, "backdropPath");
          final int _cursorIndexOfEpisodeId = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeId");
          final int _cursorIndexOfSeasonNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "seasonNumber");
          final int _cursorIndexOfEpisodeNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeNumber");
          final int _cursorIndexOfEpisodeTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeTitle");
          final int _cursorIndexOfPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "position");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfLastWatched = CursorUtil.getColumnIndexOrThrow(_cursor, "lastWatched");
          final List<ContinueWatchingEntity> _result = new ArrayList<ContinueWatchingEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ContinueWatchingEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpUserId;
            _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
            final int _tmpContentId;
            _tmpContentId = _cursor.getInt(_cursorIndexOfContentId);
            final String _tmpContentType;
            _tmpContentType = _cursor.getString(_cursorIndexOfContentType);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpPosterPath;
            if (_cursor.isNull(_cursorIndexOfPosterPath)) {
              _tmpPosterPath = null;
            } else {
              _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
            }
            final String _tmpBackdropPath;
            if (_cursor.isNull(_cursorIndexOfBackdropPath)) {
              _tmpBackdropPath = null;
            } else {
              _tmpBackdropPath = _cursor.getString(_cursorIndexOfBackdropPath);
            }
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
            final String _tmpEpisodeTitle;
            if (_cursor.isNull(_cursorIndexOfEpisodeTitle)) {
              _tmpEpisodeTitle = null;
            } else {
              _tmpEpisodeTitle = _cursor.getString(_cursorIndexOfEpisodeTitle);
            }
            final long _tmpPosition;
            _tmpPosition = _cursor.getLong(_cursorIndexOfPosition);
            final long _tmpDuration;
            _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
            final long _tmpLastWatched;
            _tmpLastWatched = _cursor.getLong(_cursorIndexOfLastWatched);
            _item = new ContinueWatchingEntity(_tmpId,_tmpUserId,_tmpContentId,_tmpContentType,_tmpTitle,_tmpPosterPath,_tmpBackdropPath,_tmpEpisodeId,_tmpSeasonNumber,_tmpEpisodeNumber,_tmpEpisodeTitle,_tmpPosition,_tmpDuration,_tmpLastWatched);
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
  public Object getContinueWatchingItem(final long userId, final int contentId,
      final String contentType, final Continuation<? super ContinueWatchingEntity> $completion) {
    final String _sql = "SELECT * FROM continue_watching WHERE userId = ? AND contentId = ? AND contentType = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, contentId);
    _argIndex = 3;
    _statement.bindString(_argIndex, contentType);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ContinueWatchingEntity>() {
      @Override
      @Nullable
      public ContinueWatchingEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfContentId = CursorUtil.getColumnIndexOrThrow(_cursor, "contentId");
          final int _cursorIndexOfContentType = CursorUtil.getColumnIndexOrThrow(_cursor, "contentType");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "posterPath");
          final int _cursorIndexOfBackdropPath = CursorUtil.getColumnIndexOrThrow(_cursor, "backdropPath");
          final int _cursorIndexOfEpisodeId = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeId");
          final int _cursorIndexOfSeasonNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "seasonNumber");
          final int _cursorIndexOfEpisodeNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeNumber");
          final int _cursorIndexOfEpisodeTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeTitle");
          final int _cursorIndexOfPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "position");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfLastWatched = CursorUtil.getColumnIndexOrThrow(_cursor, "lastWatched");
          final ContinueWatchingEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpUserId;
            _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
            final int _tmpContentId;
            _tmpContentId = _cursor.getInt(_cursorIndexOfContentId);
            final String _tmpContentType;
            _tmpContentType = _cursor.getString(_cursorIndexOfContentType);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpPosterPath;
            if (_cursor.isNull(_cursorIndexOfPosterPath)) {
              _tmpPosterPath = null;
            } else {
              _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
            }
            final String _tmpBackdropPath;
            if (_cursor.isNull(_cursorIndexOfBackdropPath)) {
              _tmpBackdropPath = null;
            } else {
              _tmpBackdropPath = _cursor.getString(_cursorIndexOfBackdropPath);
            }
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
            final String _tmpEpisodeTitle;
            if (_cursor.isNull(_cursorIndexOfEpisodeTitle)) {
              _tmpEpisodeTitle = null;
            } else {
              _tmpEpisodeTitle = _cursor.getString(_cursorIndexOfEpisodeTitle);
            }
            final long _tmpPosition;
            _tmpPosition = _cursor.getLong(_cursorIndexOfPosition);
            final long _tmpDuration;
            _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
            final long _tmpLastWatched;
            _tmpLastWatched = _cursor.getLong(_cursorIndexOfLastWatched);
            _result = new ContinueWatchingEntity(_tmpId,_tmpUserId,_tmpContentId,_tmpContentType,_tmpTitle,_tmpPosterPath,_tmpBackdropPath,_tmpEpisodeId,_tmpSeasonNumber,_tmpEpisodeNumber,_tmpEpisodeTitle,_tmpPosition,_tmpDuration,_tmpLastWatched);
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

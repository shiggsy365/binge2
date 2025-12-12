package com.binge2.data.database;

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
import com.binge2.data.database.dao.ContinueWatchingDao;
import com.binge2.data.database.dao.ContinueWatchingDao_Impl;
import com.binge2.data.database.dao.UserDao;
import com.binge2.data.database.dao.UserDao_Impl;
import com.binge2.data.database.dao.WatchedDao;
import com.binge2.data.database.dao.WatchedDao_Impl;
import com.binge2.data.database.dao.WatchlistDao;
import com.binge2.data.database.dao.WatchlistDao_Impl;
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
  private volatile UserDao _userDao;

  private volatile WatchlistDao _watchlistDao;

  private volatile WatchedDao _watchedDao;

  private volatile ContinueWatchingDao _continueWatchingDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `avatarPath` TEXT, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `watchlist` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER NOT NULL, `contentId` INTEGER NOT NULL, `contentType` TEXT NOT NULL, `title` TEXT NOT NULL, `posterPath` TEXT, `addedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `watched` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER NOT NULL, `contentId` INTEGER NOT NULL, `contentType` TEXT NOT NULL, `episodeId` INTEGER, `seasonNumber` INTEGER, `episodeNumber` INTEGER, `watchedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `continue_watching` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER NOT NULL, `contentId` INTEGER NOT NULL, `contentType` TEXT NOT NULL, `title` TEXT NOT NULL, `posterPath` TEXT, `backdropPath` TEXT, `episodeId` INTEGER, `seasonNumber` INTEGER, `episodeNumber` INTEGER, `episodeTitle` TEXT, `position` INTEGER NOT NULL, `duration` INTEGER NOT NULL, `lastWatched` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'e5efe41e1a1dfeb4453d2eebec782b1e')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `users`");
        db.execSQL("DROP TABLE IF EXISTS `watchlist`");
        db.execSQL("DROP TABLE IF EXISTS `watched`");
        db.execSQL("DROP TABLE IF EXISTS `continue_watching`");
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
        final HashMap<String, TableInfo.Column> _columnsUsers = new HashMap<String, TableInfo.Column>(4);
        _columnsUsers.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("avatarPath", new TableInfo.Column("avatarPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsers = new TableInfo("users", _columnsUsers, _foreignKeysUsers, _indicesUsers);
        final TableInfo _existingUsers = TableInfo.read(db, "users");
        if (!_infoUsers.equals(_existingUsers)) {
          return new RoomOpenHelper.ValidationResult(false, "users(com.binge2.data.database.entities.UserEntity).\n"
                  + " Expected:\n" + _infoUsers + "\n"
                  + " Found:\n" + _existingUsers);
        }
        final HashMap<String, TableInfo.Column> _columnsWatchlist = new HashMap<String, TableInfo.Column>(7);
        _columnsWatchlist.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWatchlist.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWatchlist.put("contentId", new TableInfo.Column("contentId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWatchlist.put("contentType", new TableInfo.Column("contentType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWatchlist.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWatchlist.put("posterPath", new TableInfo.Column("posterPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWatchlist.put("addedAt", new TableInfo.Column("addedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWatchlist = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWatchlist = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWatchlist = new TableInfo("watchlist", _columnsWatchlist, _foreignKeysWatchlist, _indicesWatchlist);
        final TableInfo _existingWatchlist = TableInfo.read(db, "watchlist");
        if (!_infoWatchlist.equals(_existingWatchlist)) {
          return new RoomOpenHelper.ValidationResult(false, "watchlist(com.binge2.data.database.entities.WatchlistEntity).\n"
                  + " Expected:\n" + _infoWatchlist + "\n"
                  + " Found:\n" + _existingWatchlist);
        }
        final HashMap<String, TableInfo.Column> _columnsWatched = new HashMap<String, TableInfo.Column>(8);
        _columnsWatched.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWatched.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWatched.put("contentId", new TableInfo.Column("contentId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWatched.put("contentType", new TableInfo.Column("contentType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWatched.put("episodeId", new TableInfo.Column("episodeId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWatched.put("seasonNumber", new TableInfo.Column("seasonNumber", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWatched.put("episodeNumber", new TableInfo.Column("episodeNumber", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWatched.put("watchedAt", new TableInfo.Column("watchedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWatched = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWatched = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWatched = new TableInfo("watched", _columnsWatched, _foreignKeysWatched, _indicesWatched);
        final TableInfo _existingWatched = TableInfo.read(db, "watched");
        if (!_infoWatched.equals(_existingWatched)) {
          return new RoomOpenHelper.ValidationResult(false, "watched(com.binge2.data.database.entities.WatchedEntity).\n"
                  + " Expected:\n" + _infoWatched + "\n"
                  + " Found:\n" + _existingWatched);
        }
        final HashMap<String, TableInfo.Column> _columnsContinueWatching = new HashMap<String, TableInfo.Column>(14);
        _columnsContinueWatching.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContinueWatching.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContinueWatching.put("contentId", new TableInfo.Column("contentId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContinueWatching.put("contentType", new TableInfo.Column("contentType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContinueWatching.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContinueWatching.put("posterPath", new TableInfo.Column("posterPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContinueWatching.put("backdropPath", new TableInfo.Column("backdropPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContinueWatching.put("episodeId", new TableInfo.Column("episodeId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContinueWatching.put("seasonNumber", new TableInfo.Column("seasonNumber", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContinueWatching.put("episodeNumber", new TableInfo.Column("episodeNumber", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContinueWatching.put("episodeTitle", new TableInfo.Column("episodeTitle", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContinueWatching.put("position", new TableInfo.Column("position", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContinueWatching.put("duration", new TableInfo.Column("duration", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContinueWatching.put("lastWatched", new TableInfo.Column("lastWatched", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysContinueWatching = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesContinueWatching = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoContinueWatching = new TableInfo("continue_watching", _columnsContinueWatching, _foreignKeysContinueWatching, _indicesContinueWatching);
        final TableInfo _existingContinueWatching = TableInfo.read(db, "continue_watching");
        if (!_infoContinueWatching.equals(_existingContinueWatching)) {
          return new RoomOpenHelper.ValidationResult(false, "continue_watching(com.binge2.data.database.entities.ContinueWatchingEntity).\n"
                  + " Expected:\n" + _infoContinueWatching + "\n"
                  + " Found:\n" + _existingContinueWatching);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "e5efe41e1a1dfeb4453d2eebec782b1e", "7b719520e409f4353a42c22637dd364d");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "users","watchlist","watched","continue_watching");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `users`");
      _db.execSQL("DELETE FROM `watchlist`");
      _db.execSQL("DELETE FROM `watched`");
      _db.execSQL("DELETE FROM `continue_watching`");
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
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(WatchlistDao.class, WatchlistDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(WatchedDao.class, WatchedDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ContinueWatchingDao.class, ContinueWatchingDao_Impl.getRequiredConverters());
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
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public WatchlistDao watchlistDao() {
    if (_watchlistDao != null) {
      return _watchlistDao;
    } else {
      synchronized(this) {
        if(_watchlistDao == null) {
          _watchlistDao = new WatchlistDao_Impl(this);
        }
        return _watchlistDao;
      }
    }
  }

  @Override
  public WatchedDao watchedDao() {
    if (_watchedDao != null) {
      return _watchedDao;
    } else {
      synchronized(this) {
        if(_watchedDao == null) {
          _watchedDao = new WatchedDao_Impl(this);
        }
        return _watchedDao;
      }
    }
  }

  @Override
  public ContinueWatchingDao continueWatchingDao() {
    if (_continueWatchingDao != null) {
      return _continueWatchingDao;
    } else {
      synchronized(this) {
        if(_continueWatchingDao == null) {
          _continueWatchingDao = new ContinueWatchingDao_Impl(this);
        }
        return _continueWatchingDao;
      }
    }
  }
}

package com.example.packyourbag.Dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.packyourbag.Models.Items;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ItemsDao_Impl implements ItemsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Items> __insertionAdapterOfItems;

  private final EntityDeletionOrUpdateAdapter<Items> __deletionAdapterOfItems;

  private final SharedSQLiteStatement __preparedStmtOfCheckuncheck;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllSystemItems;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllByCategory;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllCategoryAndAddedBy;

  public ItemsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfItems = new EntityInsertionAdapter<Items>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `items` (`ID`,`itemname`,`category`,`addedby`,`checked`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Items entity) {
        statement.bindLong(1, entity.getID());
        if (entity.getItemname() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getItemname());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getCategory());
        }
        if (entity.getAddedby() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getAddedby());
        }
        final Integer _tmp = entity.getChecked() == null ? null : (entity.getChecked() ? 1 : 0);
        if (_tmp == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, _tmp);
        }
      }
    };
    this.__deletionAdapterOfItems = new EntityDeletionOrUpdateAdapter<Items>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `items` WHERE `ID` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Items entity) {
        statement.bindLong(1, entity.getID());
      }
    };
    this.__preparedStmtOfCheckuncheck = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "update items set checked=? where ID=?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllSystemItems = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "delete from items where addedby=?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllByCategory = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "delete from items where category=?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllCategoryAndAddedBy = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "delete from items where category=? and addedby=?";
        return _query;
      }
    };
  }

  @Override
  public void saveItem(final Items items) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfItems.insert(items);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Items items) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfItems.handle(items);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void checkuncheck(final int id, final boolean checked) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfCheckuncheck.acquire();
    int _argIndex = 1;
    final int _tmp = checked ? 1 : 0;
    _stmt.bindLong(_argIndex, _tmp);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, id);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfCheckuncheck.release(_stmt);
    }
  }

  @Override
  public Integer deleteAllSystemItems(final String addedBy) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllSystemItems.acquire();
    int _argIndex = 1;
    if (addedBy == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, addedBy);
    }
    try {
      __db.beginTransaction();
      try {
        final Integer _result = _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteAllSystemItems.release(_stmt);
    }
  }

  @Override
  public Integer deleteAllByCategory(final String category) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllByCategory.acquire();
    int _argIndex = 1;
    if (category == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, category);
    }
    try {
      __db.beginTransaction();
      try {
        final Integer _result = _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteAllByCategory.release(_stmt);
    }
  }

  @Override
  public Integer deleteAllCategoryAndAddedBy(final String category, final String addedBy) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllCategoryAndAddedBy.acquire();
    int _argIndex = 1;
    if (category == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, category);
    }
    _argIndex = 2;
    if (addedBy == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, addedBy);
    }
    try {
      __db.beginTransaction();
      try {
        final Integer _result = _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteAllCategoryAndAddedBy.release(_stmt);
    }
  }

  @Override
  public List<Items> getAll(final String category) {
    final String _sql = "select * from items where category=? order by id asc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfID = CursorUtil.getColumnIndexOrThrow(_cursor, "ID");
      final int _cursorIndexOfItemname = CursorUtil.getColumnIndexOrThrow(_cursor, "itemname");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfAddedby = CursorUtil.getColumnIndexOrThrow(_cursor, "addedby");
      final int _cursorIndexOfChecked = CursorUtil.getColumnIndexOrThrow(_cursor, "checked");
      final List<Items> _result = new ArrayList<Items>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Items _item;
        _item = new Items();
        final int _tmpID;
        _tmpID = _cursor.getInt(_cursorIndexOfID);
        _item.setID(_tmpID);
        final String _tmpItemname;
        if (_cursor.isNull(_cursorIndexOfItemname)) {
          _tmpItemname = null;
        } else {
          _tmpItemname = _cursor.getString(_cursorIndexOfItemname);
        }
        _item.setItemname(_tmpItemname);
        final String _tmpCategory;
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _tmpCategory = null;
        } else {
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        }
        _item.setCategory(_tmpCategory);
        final String _tmpAddedby;
        if (_cursor.isNull(_cursorIndexOfAddedby)) {
          _tmpAddedby = null;
        } else {
          _tmpAddedby = _cursor.getString(_cursorIndexOfAddedby);
        }
        _item.setAddedby(_tmpAddedby);
        final Boolean _tmpChecked;
        final Integer _tmp;
        if (_cursor.isNull(_cursorIndexOfChecked)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(_cursorIndexOfChecked);
        }
        _tmpChecked = _tmp == null ? null : _tmp != 0;
        _item.setChecked(_tmpChecked);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Integer getItemsCount() {
    final String _sql = "select count(*) from items";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final Integer _result;
      if (_cursor.moveToFirst()) {
        final Integer _tmp;
        if (_cursor.isNull(0)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(0);
        }
        _result = _tmp;
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Items> getAllSelected(final Boolean checked) {
    final String _sql = "select * from items where checked=? order by id asc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final Integer _tmp = checked == null ? null : (checked ? 1 : 0);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfID = CursorUtil.getColumnIndexOrThrow(_cursor, "ID");
      final int _cursorIndexOfItemname = CursorUtil.getColumnIndexOrThrow(_cursor, "itemname");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfAddedby = CursorUtil.getColumnIndexOrThrow(_cursor, "addedby");
      final int _cursorIndexOfChecked = CursorUtil.getColumnIndexOrThrow(_cursor, "checked");
      final List<Items> _result = new ArrayList<Items>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Items _item;
        _item = new Items();
        final int _tmpID;
        _tmpID = _cursor.getInt(_cursorIndexOfID);
        _item.setID(_tmpID);
        final String _tmpItemname;
        if (_cursor.isNull(_cursorIndexOfItemname)) {
          _tmpItemname = null;
        } else {
          _tmpItemname = _cursor.getString(_cursorIndexOfItemname);
        }
        _item.setItemname(_tmpItemname);
        final String _tmpCategory;
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _tmpCategory = null;
        } else {
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        }
        _item.setCategory(_tmpCategory);
        final String _tmpAddedby;
        if (_cursor.isNull(_cursorIndexOfAddedby)) {
          _tmpAddedby = null;
        } else {
          _tmpAddedby = _cursor.getString(_cursorIndexOfAddedby);
        }
        _item.setAddedby(_tmpAddedby);
        final Boolean _tmpChecked;
        final Integer _tmp_1;
        if (_cursor.isNull(_cursorIndexOfChecked)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getInt(_cursorIndexOfChecked);
        }
        _tmpChecked = _tmp_1 == null ? null : _tmp_1 != 0;
        _item.setChecked(_tmpChecked);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

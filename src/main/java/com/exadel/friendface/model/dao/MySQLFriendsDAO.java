package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.entities.Friend;
import com.exadel.friendface.model.entities.User;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.DeleteQuery;
import com.healthmarketscience.sqlbuilder.InsertQuery;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbColumn;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSchema;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSpec;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;

import java.util.Collection;
import java.util.List;

/**
 * User: S. Fink
 * Date: 2/7/11
 * Time: 1:12 PM
 */

public class MySQLFriendsDAO extends AbstractDatabaseDAO implements FriendsDAO {
    private DbTable userTable;
    private DbColumn id;
    private DbColumn username;
    private DbColumn userSurname;

    private DbTable friendsTable;
    private DbColumn friendId;
    private DbColumn userId;
    private DbColumn isApproved;

    public MySQLFriendsDAO() {
        DbSpec spec = new DbSpec();
        DbSchema schema = spec.addDefaultSchema();
        userTable = schema.addTable("users");
        id = userTable.addColumn("id");
        username = userTable.addColumn("username");
        userSurname = userTable.addColumn("userSurname");

        friendsTable = schema.addTable("friends");
        friendId = friendsTable.addColumn("friendId");
        userId = friendsTable.addColumn("userId");
        isApproved = friendsTable.addColumn("isApproved");
    }

    public int addFriend(Friend friend) throws Exception {
        String insertStatement = new InsertQuery(friendsTable)
                .addColumn(userId, friend.getUserId())
                .addColumn(friendId, friend.getFriendId())
                .addColumn(isApproved, friend.getApproved())
                .toString();
        createPreparedStatement(insertStatement);
        return executeUpdate();
    }

    public int deleteFriend(Friend friend) throws Exception {
        String deleteStatement = new DeleteQuery(friendsTable)
                .addCondition(BinaryCondition.equalTo(friendId, friend.getFriendId()))
                .addCondition(BinaryCondition.equalTo(userId, friend.getUserId()))
                .toString();
        createPreparedStatement(deleteStatement);
        return executeUpdate();
    }

    public Collection getFriends(User user) throws Exception {
        String selectStatement = new SelectQuery()
                .addColumns(userId, username, userSurname, friendId, isApproved)
                .addCustomJoin(SelectQuery.JoinType.INNER, userTable, friendsTable, BinaryCondition.equalTo(friendId, id))
                .addCondition(BinaryCondition.equalTo(userId, user.getId()))
                .toString();
        createPreparedStatement(selectStatement);
        return executeMultipleSelect(Friend.class);
    }
}

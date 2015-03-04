package com.rudenkoInc.dao.transactionDao;

import com.rudenkoInc.dao.workToDoInterface.UnitOfWork;

public interface TransactionManager {

    public <T, E extends Exception> T doInTransaction(UnitOfWork<T, E> workToDo) throws E;
}

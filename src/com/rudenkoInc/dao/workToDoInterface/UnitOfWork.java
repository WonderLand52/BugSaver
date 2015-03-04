package com.rudenkoInc.dao.workToDoInterface;

public interface UnitOfWork<T, E extends Exception> {

    public T doInTx() throws E;
}

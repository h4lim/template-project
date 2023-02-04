package com.template.common.delivery;

import com.template.common.domain.contract.BaseContract;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDelivery<T, E> implements BaseContract.IBaseDelivery {

    private final BaseContract.IRestMessage<T, E> iRestMessage;

    @Autowired
    public BaseDelivery(BaseContract.IRestMessage<T, E> iRestMessage) {
        this.iRestMessage = iRestMessage;
    }
}

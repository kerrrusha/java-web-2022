package com.kerrrusha.lab234.service.block_client.result;

import com.kerrrusha.lab234.model.User;
import com.kerrrusha.lab234.service.AbstractResult;

public class BlockClientResult extends AbstractResult {

    private User client;

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }
}
